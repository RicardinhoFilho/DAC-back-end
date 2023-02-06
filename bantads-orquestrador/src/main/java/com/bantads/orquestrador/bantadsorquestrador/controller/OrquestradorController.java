/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.orquestrador.bantadsorquestrador.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.orquestrador.bantadsorquestrador.constantes.RabbitmqConstantes;
import com.bantads.orquestrador.bantadsorquestrador.dto.ClienteDTO;
import com.bantads.orquestrador.bantadsorquestrador.dto.ValidaReponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author leonardozanotti
 */
@CrossOrigin
@RestController
@RequestMapping("orquestrador")
public class OrquestradorController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;
   
    @PostMapping("/cliente")
    ResponseEntity<?> enfileirarCliente(@RequestBody ClienteDTO clienteDto) throws JsonProcessingException {
        ValidaReponse verificacao = clienteDto.ValidaCliente();
        if(verificacao.getStatus() == true){
            var json = objectMapper.writeValueAsString(clienteDto);
            rabbitTemplate.convertAndSend(RabbitmqConstantes.FILA_REGISTRO_CLIENTE, json);
            rabbitTemplate.convertAndSend(RabbitmqConstantes.FILA_AUTENTICACAO_CLIENTE, json);
            rabbitTemplate.convertAndSend(RabbitmqConstantes.FILA_REGISTRO_CONTA_CLIENTE, json);
            rabbitTemplate.convertAndSend(RabbitmqConstantes.FILA_REGISTRO_GERENTE_CLIENTE, json);
            return new ResponseEntity<>("Enfileirado: " + json, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(verificacao.getMenssagem(), HttpStatus.OK);
        }
        
    }

   
}
