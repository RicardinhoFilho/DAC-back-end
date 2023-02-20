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
	
	@Bean
	public Queue inserirTransacaoBancoLeitura() {
		return new Queue(inserirTransacaoBancoLeitura, true);
		
	}
	@Bean
	public Queue inserirContaBancoLeitura() {
		return new Queue(inserirContaBancoLeitura, true);
		
	}

	
}
