/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.consumer;

import com.bantads.autenticacao.model.Usuario;
import com.bantads.autenticacao.repository.UsuarioRepository;
import com.bantads.autenticacao.services.email.EmailService;
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
public class ConsumerRollbackNovaSenha {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService mailSenderService;

    @RabbitListener(queues = "nova-senha-rollback")
    public void receive(@Payload String json) {
        try {
            UUID saga = UUID.fromString(json);
            Usuario usuario = usuarioRepository.findBySaga(saga);
            if (usuario != null){
                usuario.setSenha(null);
                usuario.setAtivo(false);
                usuarioRepository.save(usuario);
            }
            sendMail(usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendMail(Usuario usuario) {
        String to = usuario.getEmail();
        String subject = "Erro ao cadastrar nova conta";
        String body = "Ocorreu um erro ao cadastrar sua conta.\n" + "Em breve um gerente fará a análise da sua solicitação novamente.";
        mailSenderService.sendMail(to, subject, body);
    }
}
