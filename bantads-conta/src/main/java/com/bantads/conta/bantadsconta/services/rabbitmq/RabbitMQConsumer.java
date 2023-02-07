package com.bantads.conta.bantadsconta.services.rabbitmq;

import java.sql.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.conta.bantadsconta.DTO.ContaDTO;
import com.bantads.conta.bantadsconta.data.ContaRepository;
import com.bantads.conta.bantadsconta.data.CUD.ContaCUDRepository;
import com.bantads.conta.bantadsconta.model.Conta;
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
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContaCUDRepository contaRepository;

    @RabbitListener(queues = FILA_REGISTRO_CONTA_CLIENTE)
    public void registraNovoCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var conta = objectMapper.readValue(msg, ContaDTO.class);

      ContaCUD c = new ContaCUD(
                conta.getIdUsuario(), new Date(System.currentTimeMillis()), false, conta.getSaldo(), conta.getIdGerente(),
           conta.getSalario());

      var teste=  contaRepository.save(c);
        System.out.println("Salvo (" +msg + ") " + teste.getIdUsuario());
    }
}
