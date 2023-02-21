package com.bantads.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RabbitMQConsumer {

    private static final String FILA_AUTENTICACAO = "FILA_AUTENTICACAO";
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = FILA_AUTENTICACAO)
    public void deleteCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var usuario = objectMapper.readValue(msg, Usuario.class);
     
        try {
            System.out.println(msg);
            repository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);

        }

   }
}
