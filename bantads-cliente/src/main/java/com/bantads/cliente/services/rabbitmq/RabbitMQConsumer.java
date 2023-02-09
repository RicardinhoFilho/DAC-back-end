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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leonardozanotti
 */
@Component
public class RabbitMQConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public static final String FILA_REGISTRO_CLIENTE = "FILA_REGISTRO_CLIENTE";
    public static final String FILA_ERRO_NOVO_CLIENTE = "FILA_ERRO_NOVO_CLIENTE";

    public static final String FILA_UPDATE_CLIENTE = "FILA_UPDATE_CLIENTE";
    public static final String FILA_ERRO_UPDATE_CLIENTE = "FILA_ERRO_UPDATE_CLIENTE";
    public static final String FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO = "FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @RabbitListener(queues = FILA_REGISTRO_CLIENTE)
    public void registraNovoCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var cliente = objectMapper.readValue(msg, ClienteDTO.class);
        try {
            Cliente u = new Cliente(
                    cliente.getId(),
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

        } catch (Exception e) {
            // ROLLBACK
            System.out.println("Erro ao salvar cliente (" + cliente.getNome() + ") " + msg);
            var id_erro = objectMapper.writeValueAsString(cliente.getId());

            rabbitTemplate.convertAndSend(FILA_ERRO_NOVO_CLIENTE, id_erro);
            rabbitTemplate.convertAndSend(FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO, id_erro);

        }

    }

    @RabbitListener(queues = FILA_UPDATE_CLIENTE)
    public void updateCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var cliente = objectMapper.readValue(msg, ClienteDTO.class);
        try {
            Long id = (cliente.getId());
            if (id != null) {
                var userFromDb = clienteRepository.findById(id);
                userFromDb.get().setNome(cliente.getNome());
                userFromDb.get().setSenha(cliente.getSenha());
                userFromDb.get().setTelefone(cliente.getTelefone());
                userFromDb.get().setEstado(cliente.getEstado());
                userFromDb.get().setCidade(cliente.getCidade());
                userFromDb.get().setCep(cliente.getCep());
                userFromDb.get().setRua(cliente.getRua());
                userFromDb.get().setNumero(cliente.getNumero());
                userFromDb.get().setComplemento(cliente.getComplemento());
                userFromDb.get().setAtivo(cliente.isAtivo());
                clienteRepository.save(userFromDb.get());

                // clienteRepository.;
                System.out.println("Salvo (" + cliente.getNome() + ") " + msg);
            }

        } catch (Exception e) {
            // ROLLBACK
            System.out.println("Erro ao salvar cliente (" + cliente.getNome() + ") " + msg);
            var id_erro = objectMapper.writeValueAsString(cliente.getId());

            rabbitTemplate.convertAndSend(FILA_ERRO_NOVO_CLIENTE, id_erro);
            rabbitTemplate.convertAndSend(FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO, id_erro);

        }

    }
}