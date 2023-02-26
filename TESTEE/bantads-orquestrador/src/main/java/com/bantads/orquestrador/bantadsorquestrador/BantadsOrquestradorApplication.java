package com.bantads.orquestrador.bantadsorquestrador;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bantads.orquestrador.bantadsorquestrador.coneccao.RabbitMQConnection;

@EnableRabbit
@SpringBootApplication
public class BantadsOrquestradorApplication {

	public static void main(String[] args) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
		RabbitMQConnection conn = new RabbitMQConnection(new RabbitAdmin(connectionFactory));
		SpringApplication.run(BantadsOrquestradorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
