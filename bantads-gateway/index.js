const dotenv = require('dotenv');
const jwt = require('jsonwebtoken');
const http = require('http');
const express = require('express');
const httpProxy = require('express-http-proxy');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const logger = require('morgan');
const helmet = require('helmet');
const cors = require('cors');

// Configs
const app = express();
const PORT = 5000;
dotenv.config({ path: `${process.env.NODE_ENV !== undefined ? '.env.dev' : '.env'}` });
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(logger('dev'));
app.use(helmet());
app.use(cookieParser());
app.use(cors());

// Proxy
const loginServiceProxy = httpProxy(process.env.HOST_ORQUESTRADOR + process.env.PATH_ORQUESTRADOR + '/login', {
  proxyReqBodyDecorator: function (bodyContent, srcReq) {
    try {
      const { login, senha } = bodyContent;
      const retBody = {};
      retBody.email = login;
      retBody.senha = senha;
      bodyContent = retBody;
    } catch (e) {
      console.log('ERRO: ' + e);
    }
    return bodyContent;
  },
  proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
    proxyReqOpts.headers['Content-Type'] = 'application/json';
    proxyReqOpts.method = 'POST';
    return proxyReqOpts;
  },
  userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
    if (proxyRes.statusCode === 200) {
      const str = Buffer.from(proxyResData).toString('utf-8');
      const objBody = JSON.parse(str);
      const id = objBody.id;
      const token = jwt.sign({ id }, process.env.SECRET, { expiresIn: 300 });
      userRes.status(200);
      return { auth: true, token, data: objBody };
    } else {
      userRes.status(401);
      return { message: 'Login inválido!' };
    }
  },
});

// Json Web Token function
function verifyJWT(req, res, next) {
  const token = req.headers['x-access-token'];
  if (!token) return res.status(401).json({ auth: false, message: 'Token não fornecido.' });

  jwt.verify(token, process.env.SECRET, (err, decoded) => {
    if (err) return res.status(500).json({ auth: false, message: 'Falha ao autenticar o token.' });
    req.userId = decoded.id;
    next();
  });
}

// ORQUESTRADOR
app.post(process.env.PATH_ORQUESTRADOR + '/login', (req, res, next) => {
  loginServiceProxy(req, res, next);
});

app.post(process.env.PATH_ORQUESTRADOR + '/logout', verifyJWT, (req, res) => {
  res.json({ auth: false, token: null });
});

// CLIENTE
app.post(process.env.PATH_CLIENTE + '/cadastro', async (req, res, next) => {
  httpProxy(process.env.HOST_CLIENTE, {
    userResDecorator: function (proxyRes, _proxyResData, _userReq, userRes) {
      if (proxyRes.statusCode == 201) {
        userRes.status(201);
        return { message: 'Cadastro realizado com sucesso. Consulte seu e-mail para próximos passos.' };
      } else {
        userRes.status(proxyRes.statusCode);
        return { message: 'Um erro ocorreu em seu cadastro. Tente novamente.' };
      }
    },
  })(req, res, next);
});

app.get(`${process.env.PATH_CLIENTE}/:id`, verifyJWT, (req, res, next) => {
  httpProxy(process.env.HOST_CLIENTE + `/${req.query.id}`, {
    userResDecorator: function (proxyRes, proxyResData, _userReq, userRes) {
      if (proxyRes.statusCode == 200) {
        var str = Buffer.from(proxyResData).toString('utf-8');
        userRes.status(200);
        return str;
      } else {
        userRes.status(proxyRes.statusCode);
        return { message: 'Um erro ocorreu ao buscar o cliente.' };
      }
    },
  })(req, res, next);
});

app.get(process.env.PATH_CLIENTE + '/list', verifyJWT, async (_req, res) => {
  httpProxy(process.env.HOST_CLIENTE + '/list', {
    proxyReqBodyDecorator: function (bodyContent, srcReq) {
      return bodyContent;
    },
    proxyReqOptDecorator: function (proxyReqOpts, srcReq) {
      proxyReqOpts.headers['Content-Type'] = 'application/json';
      proxyReqOpts.method = 'POST';
      return proxyReqOpts;
    },
    userResDecorator: function (proxyRes, proxyResData, userReq, userRes) {
      if (proxyRes.statusCode === 200) {
        const str = Buffer.from(proxyResData).toString('utf-8');
        const objBody = JSON.parse(str);
        userRes.status(200);
        return { clientes: objBody };
      } else {
        userRes.status(401);
        return { message: 'Um erro ocorreu ao buscar clientes.' };
      }
    },
  })(req, res, next);
});

// GERENTE

// SERVER
const server = http.createServer(app);
server.listen(PORT, () => console.log(`Server running on port ${PORT}`));