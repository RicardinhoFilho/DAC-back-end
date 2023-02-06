/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.services.rabbitmq;

import com.bantads.cliente.dto.ClienteDTO;
import com.bantads.cliente.model.Cliente;
import com.bantads.cliente.repository.ClienteRepository;
import com.bantads.cliente.utils.Security;
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
    public static final String FILA_REGISTRO_CLIENTE = "FILA_REGISTRO_CLIENTE";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @RabbitListener(queues = FILA_REGISTRO_CLIENTE)
    public void registraNovoCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var cliente = objectMapper.readValue(msg, ClienteDTO.class);
       
        Cliente u = new Cliente(
            cliente.getNome(),
            cliente.getEmail(),
                Security.generateStrongPassword(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEstado(),
                cliente.getCidade(),
                cliente.getCep(),
                cliente.getRua(),
                cliente.getNumero(),
                cliente.getComplemento(),
                cliente.getCargo(),
                cliente.isAtivo());

        clienteRepository.save(u);
        System.out.println("Salvo (" + u.getNome() + ") " + msg);
    }
}