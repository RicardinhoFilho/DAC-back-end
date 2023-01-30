package com.bantads.autenticacao.bantadsautenticacao;

import com.bantads.autenticacao.bantadsautenticacao.tools.Security;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRabbit
@SpringBootApplication
public class BantadsAutenticacaoApplication {

	public static void main(String[] args) {
            System.out.println(Security.hash("Admin2022."));
		SpringApplication.run(BantadsAutenticacaoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
