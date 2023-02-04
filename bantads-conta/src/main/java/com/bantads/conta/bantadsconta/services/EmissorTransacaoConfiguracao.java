package com.bantads.conta.bantadsconta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmissorTransacaoConfiguracao {
	@Value("transacao-inserir-banco")
	private String inserirTransacaoBancoLeitura;
	
	@Bean
	public Queue inserirTransacaoBancoLeitura() {
		return new Queue(inserirTransacaoBancoLeitura, true);
	}
}
