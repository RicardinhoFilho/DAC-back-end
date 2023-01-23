package com.bantads.autenticacao;

import com.bantads.autenticacao.services.email.EmailService;
import com.bantads.autenticacao.services.producer.Cliente.SenderAprovacao;
import com.bantads.autenticacao.services.producer.Cliente.SenderCliente;
import com.bantads.autenticacao.services.producer.Gerente.SenderGerente;
import com.bantads.autenticacao.services.producer.Gerente.SenderRollbackDeleteGerente;
import com.bantads.autenticacao.services.producer.GerenteConta.SenderGerenteConta;
import com.bantads.autenticacao.services.producer.GerenteConta.SenderNovaConta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bantads.autenticacao.utils.Security;

@SpringBootApplication
public class AutenticacaoApplication {

	public static void main(String[] args) {
            SpringApplication.run(AutenticacaoApplication.class, args);
	}
        
        @Bean
	public ModelMapper modelMapper() {
            return new ModelMapper();
	}

	
	@Bean
	public ObjectMapper objectMapper() {
            return new ObjectMapper();
	}

	@Bean
	public EmailService emailService() {
            return new EmailService();
	}
        
        @Bean
	public SenderCliente senderC() {
		return new SenderCliente();
	}

	@Bean
	public SenderGerente senderG() {
		return new SenderGerente();
	}

	@Bean
	public SenderGerenteConta senderGC() {
		return new SenderGerenteConta();
	}

	@Bean
	public SenderAprovacao senderAp(){
		return new SenderAprovacao();
	}

	@Bean
	public SenderNovaConta senderNc(){
		return new SenderNovaConta();
	}

	@Bean
	public SenderRollbackDeleteGerente senderRdg(){
		return new SenderRollbackDeleteGerente();
	}
}
