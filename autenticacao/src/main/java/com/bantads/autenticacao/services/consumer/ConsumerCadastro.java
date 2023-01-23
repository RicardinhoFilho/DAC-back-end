/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.consumer;

import com.bantads.autenticacao.model.Cargo;
import com.bantads.autenticacao.model.Usuario;
import com.bantads.autenticacao.repository.UsuarioRepository;
import com.bantads.autenticacao.services.email.EmailService;
import com.bantads.autenticacao.services.producer.Cliente.SenderCliente;
import com.bantads.autenticacao.services.producer.Gerente.SenderGerente;
import com.bantads.autenticacao.services.producer.GerenteConta.SenderGerenteConta;
import com.bantads.autenticacao.utils.Security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author leonardozanotti
 */

@Component
public class ConsumerCadastro {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService mailSenderService;

    @Autowired
    private SenderCliente senderCliente;

    @Autowired
    private SenderGerente senderGerente;

    @Autowired
    private SenderGerenteConta senderGerenteConta;

    @RabbitListener(queues = "autocadastro-autenticacao")
    public void receive(@Payload String json) throws JsonProcessingException {
        try {
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            String password = createPassword();
            usuario.setAtivo(false);
            if (usuario.getCargo() == Cargo.Gerente) {
                usuario.setSenha(Security.hash(password));      
                usuario.setAtivo(true);          
                sendMail(usuario, password);
            }
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            if (usuario.getCargo() == Cargo.Gerente) {
                senderGerente.send(usuario.getSaga());
                senderGerenteConta.send(usuario.getSaga());
            }
            if (usuario.getCargo() == Cargo.Cliente) {
                senderCliente.send(usuario.getSaga());
            }
        }
    }

    private String createPassword() {
        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 26 + 97);
        }
        return password;
    }

    private void sendMail(Usuario usuario, String senha) {
        String to = usuario.getEmail();
        String subject = "Bantads " + usuario.getCargo() + " - Cadastro realizado com sucesso";
        String body = "Seu cadastro foi realizado com sucesso.\n" + "Sua senha é: " + senha;
        mailSenderService.sendMail(to, subject, body);
    }
}