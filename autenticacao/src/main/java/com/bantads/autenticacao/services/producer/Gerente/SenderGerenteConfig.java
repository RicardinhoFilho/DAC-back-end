/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.services.producer.Gerente;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author leonardozanotti
 */
@Configuration
public class SenderGerenteConfig {
    @Value("autocadastro-gerente-rollback")
    private String queueRollbackAutocadastroGerente;

    @Bean
    public Queue queueRollbackAutocadastroGerente() {
        return new Queue(queueRollbackAutocadastroGerente);
    }
}
