package com.bantads.gerente.bantadsgerente.services.rabbitmq;

import com.bantads.gerente.bantadsgerente.data.GerenteRepository;
import com.bantads.gerente.bantadsgerente.dto.GerenteDTO;
import com.bantads.gerente.bantadsgerente.model.Gerente;
import com.bantads.gerente.bantadsgerente.utils.Security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
@Component
public class RabbitMQConsumer {

    public static final String FILA_CREATE_GERENTE = "FILA_CREATE_GERENTE";
    public static final String FILA_ATRIBUI_CONTA_GERENTE = "FILA_ATRIBUI_CONTA_GERENTE";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = FILA_CREATE_GERENTE)
    public void registraNovoCliente(String msg) throws JsonMappingException, JsonProcessingException {
        var gerente = objectMapper.readValue(msg, GerenteDTO.class);
        try {
            if (gerenteRepository.existsByCpf(gerente.getCpf())) {
                System.out.println("Já existe um gerente com este cpf");
                return;
            }

            if(gerenteRepository.findByEmail(gerente.getEmail())!= null){
                System.out.println("Já existe um gerente com este email");
                return;
            }
            Gerente g = new Gerente(
                    gerente.getNome(),
                    gerente.getEmail(),
                    Security.hash(gerente.getSenha()),
                    gerente.getCpf(),
                    gerente.getTelefone(),
                    gerente.getCargo());
            Gerente novoGerente = gerenteRepository.save(g);
            System.out.println(objectMapper.writeValueAsString(novoGerente));
            var JSON = objectMapper.writeValueAsString(novoGerente.getId());
            rabbitTemplate.convertAndSend(FILA_ATRIBUI_CONTA_GERENTE, JSON);
        } catch (Exception e) {
            System.out.println(e);

        }

    }

}