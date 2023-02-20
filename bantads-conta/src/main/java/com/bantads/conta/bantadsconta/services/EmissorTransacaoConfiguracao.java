package com.bantads.conta.bantadsconta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray.ReadOnly;

@Configuration
public class EmissorTransacaoConfiguracao {
	@Value("FILA_TRANSACAO_INSERIR")
	private String inserirTransacaoBancoLeitura;
	
	 public  static final String inserirContaBancoLeitura = "FILA_CONTA_INSERIR";
	 public  static final String updateContaBancoLeitura = "FILA_CONTA_UPDATE";
	
	@Bean
	public Queue inserirTransacaoBancoLeitura() {
		return new Queue(inserirTransacaoBancoLeitura, true);
		
	}
	@Bean
	public Queue inserirContaBancoLeitura() {
		return new Queue(inserirContaBancoLeitura, true);
		
	}

	@Bean
	public Queue updateContaBancoLeitura() {
		return new Queue(updateContaBancoLeitura, true);
		
	}

	
}
