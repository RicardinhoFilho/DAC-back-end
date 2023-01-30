package com.bantads.autenticacao.bantadsautenticacao.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.dto.UsuarioDTO;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.tools.Security;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable String id) {
        try {
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

            if (usuarioOp.isPresent()) {
                UsuarioDTO gerente = mapper.map(usuarioOp.get(), UsuarioDTO.class);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            List<UsuarioDTO> response = Arrays.asList(mapper.map(usuarios, UsuarioDTO[].class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioRepository.login(usuarioDTO.getEmail(), Security.hash(usuarioDTO.getSenha()));
            if (usuario != null) {
                UsuarioDTO response = mapper.map(usuario, UsuarioDTO.class);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
