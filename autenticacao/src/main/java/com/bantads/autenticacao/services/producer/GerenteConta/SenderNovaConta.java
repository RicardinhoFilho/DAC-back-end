/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.producer.GerenteConta;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author leonardozanotti
 */
@Component
public class SenderNovaConta {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueRollbackNovaConta;

    public void send(UUID saga) {
        this.template.convertAndSend(this.queueRollbackNovaConta.getName(), saga);
    }
}
