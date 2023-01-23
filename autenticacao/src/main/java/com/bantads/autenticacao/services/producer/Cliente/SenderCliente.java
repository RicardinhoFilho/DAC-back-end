/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.producer.Cliente;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author leonardozanotti
 */

@Component
public class SenderCliente {
    @Autowired
    public RabbitTemplate template;

    @Autowired
    private Queue queueRollbackAutocadastroAutenticacao;

    public void send(UUID saga) throws JsonProcessingException {
        this.template.convertAndSend(this.queueRollbackAutocadastroAutenticacao.getName(), saga);
    }
}
