/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.services.rabbitmq;

import com.bantads.cliente.dto.ClienteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leonardozanotti
 */
@Component
public class RabbitMQConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    
    @RabbitListener(queues="ufpr.teste")
    public void receiveMessage(String msg) throws JsonMappingException, JsonProcessingException {
        var cliente = objectMapper.readValue(msg, ClienteDTO.class);
        System.out.println("RECEBIDA ("+ cliente.getNome() + ") "+ msg);
    }
}