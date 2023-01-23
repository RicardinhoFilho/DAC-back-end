/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.consumer;

import com.bantads.autenticacao.repository.UsuarioRepository;
import java.util.UUID;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author leonardozanotti
 */
@Component
public class ConsumerRollback {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RabbitListener(queues = "autocadastro-autenticacao-rollback")
    public void receive(@Payload String json) {
        try {
            UUID saga = UUID.fromString(json);
            usuarioRepository.deleteBySaga(saga);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
