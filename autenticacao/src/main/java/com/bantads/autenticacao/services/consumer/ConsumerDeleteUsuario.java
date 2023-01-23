/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.consumer;

import com.bantads.autenticacao.model.Usuario;
import com.bantads.autenticacao.repository.UsuarioRepository;
import com.bantads.autenticacao.services.producer.Gerente.SenderRollbackDeleteGerente;
import java.util.Optional;
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
public class ConsumerDeleteUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenderRollbackDeleteGerente senderRollbackDeleteGerente;

    @RabbitListener(queues = "delete-usuario")
    public void receive(@Payload String json){
        try {
            UUID id = UUID.fromString(json);
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);
            Usuario usuario = usuarioOp.get();
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);
            UUID id = UUID.fromString(json);
            senderRollbackDeleteGerente.send(id);
        }
    }
}
