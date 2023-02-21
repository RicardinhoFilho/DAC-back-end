package com.bantads.autenticacao.bantadsautenticacao.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.tools.Security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class RabbitMQConsumer {

    private static final String FILA_AUTENTICACAO = "FILA_AUTENTICACAO";
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = FILA_AUTENTICACAO)
    public void create(String msg) throws JsonMappingException, JsonProcessingException {
        var usuario = objectMapper.readValue(msg, Usuario.class);
       usuario.setSenha(Security.hash(usuario.getSenha())); 
        try {
            
            System.out.println("AQUI: "+ msg);
            repository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);

        }

   }
}
