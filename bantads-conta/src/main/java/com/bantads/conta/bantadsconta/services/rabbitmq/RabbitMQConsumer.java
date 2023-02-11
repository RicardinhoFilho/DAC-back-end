package com.bantads.conta.bantadsconta.services.rabbitmq;

import java.sql.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.conta.bantadsconta.DTO.ContaDTO;
import com.bantads.conta.bantadsconta.data.ContaRepository;
import com.bantads.conta.bantadsconta.data.CUD.ContaCUDRepository;
import com.bantads.conta.bantadsconta.model.Conta;
import com.bantads.conta.bantadsconta.model.Notificacao;
import com.bantads.conta.bantadsconta.model.CUD.ContaCUD;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ricardofilho
 */

@Component
public class RabbitMQConsumer {
    public static final String FILA_REGISTRO_CONTA_CLIENTE = "FILA_REGISTRO_CONTA_CLIENTE";
    public static final String FILA_ERRO_NOVO_CLIENTE = "FILA_ERRO_NOVO_CLIENTE";
    public static final String FILA_UPDATE_CONTA = "FILA_UPDATE_CONTA";
    public static final String FILA_NOTIFICA_UPDATE_CONTA = "FILA_NOTIFICA_UPDATE_CONTA";
    public static final String FILA_ATRIBUI_CONTA_GERENTE = "FILA_ATRIBUI_CONTA_GERENTE";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContaCUDRepository contaRepository;

    @RabbitListener(queues = FILA_REGISTRO_CONTA_CLIENTE)
    public void registraNovoCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var conta = objectMapper.readValue(msg, ContaDTO.class);
        var id_gerente_menos_clientes = contaRepository.idGerenteMenosClientes().get(0);
        ContaCUD c = new ContaCUD(
                conta.getIdUsuario(), new Date(System.currentTimeMillis()), false, conta.getSaldo(),
                id_gerente_menos_clientes,
                conta.getSalario());

        contaRepository.save(c);
        System.out.println("Salvo (" + msg + ") " + conta.getIdUsuario());
    }

    @RabbitListener(queues = FILA_ERRO_NOVO_CLIENTE)
    public void erroCriacaoClienteRollBack(String msg) throws JsonMappingException, JsonProcessingException {
        Long id_usuario = objectMapper.readValue(msg, Long.class);
        contaRepository.excluirPorCliente(id_usuario);

        System.out.println("Excluir (" + msg + ") ");
    }

    @RabbitListener(queues = FILA_UPDATE_CONTA)
    public void updateConta(String msg) throws JsonMappingException, JsonProcessingException {
        var conta = objectMapper.readValue(msg, ContaDTO.class);
        Long id = (conta.getId());
        var contaByDb = contaRepository.findById(id).get();

        contaByDb.setSalario(conta.getSalario());
        contaByDb.setAtivo(conta.isAtivo());
        contaByDb.setSaldo(conta.getSaldo());
        contaByDb.setSalario(conta.getSalario());
        contaByDb.setRejeitadoMotivo(conta.getRejeitadoMotivo());
        if (conta.isAtivo() == false)
            contaByDb.setRejeitadoData(new Date(System.currentTimeMillis()));

        contaRepository.save(contaByDb);
        Notificacao notificacao = new Notificacao(contaByDb.isAtivo(), contaByDb.getRejeitadoMotivo(),
                contaByDb.getIdUsuario());
        var NOTIFICACAO = objectMapper.writeValueAsString(notificacao);
        System.out.println("Atualização de conta salva(" + msg + ") ");
        rabbitTemplate.convertAndSend(FILA_NOTIFICA_UPDATE_CONTA, NOTIFICACAO);
        System.out.println("Envio de email inserido na lista");
    }

    @RabbitListener(queues = FILA_ATRIBUI_CONTA_GERENTE)
    public void atribuiContaGerente(String msg) throws JsonMappingException, JsonProcessingException {
        Long id_gerente = objectMapper.readValue(msg, Long.class);
        var id_gerente_mais_clientes = contaRepository.idGerenteMaisClientes().get(0);

       var conta =  contaRepository.findByIdGerente(id_gerente_mais_clientes).get(0);
       contaRepository.updateGerenteConta(id_gerente, conta.getId());
        System.out.println("Atualizada conta (" + conta.getId() + ") ");
    }

}
