package com.bantads.orquestrador.bantadsorquestrador.coneccao;

import com.bantads.orquestrador.bantadsorquestrador.constantes.RabbitmqConstantes;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;

public class RabbitMQConnection {

	private static final String NOME_EXCHANGE = "amq.direct";

	private AmqpAdmin amqpAdmin;

	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
		adiciona();

	}

	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}

	private DirectExchange trocaDireta() {
		return new DirectExchange(NOME_EXCHANGE);
	}

	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}

	// está função é executada assim que nossa classe é instanciada pelo Spring,
	// devido a anotação @Component
	@javax.annotation.PostConstruct
	private void adiciona() {
		Queue FILA_REGISTRO_CLIENTE = this.fila(RabbitmqConstantes.FILA_REGISTRO_CLIENTE);
		Queue FILA_AUTENTICACAO_CLIENTE = this.fila(RabbitmqConstantes.FILA_AUTENTICACAO_CLIENTE);
		Queue FILA_REGISTRO_CONTA_CLIENTE = this.fila(RabbitmqConstantes.FILA_REGISTRO_CONTA_CLIENTE);
		Queue FILA_REGISTRO_GERENTE_CLIENTE = this.fila(RabbitmqConstantes.FILA_REGISTRO_GERENTE_CLIENTE);
		Queue FILA_ERRO_NOVO_CLIENTE = this.fila(RabbitmqConstantes.FILA_ERRO_NOVO_CLIENTE);
		Queue FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO = this.fila(RabbitmqConstantes.FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO);
		Queue FILA_UPDATE_CLIENTE = this.fila(RabbitmqConstantes.FILA_UPDATE_CLIENTE);
		Queue FILA_UPDATE_AUTENTICACAO_CLIENTE = this.fila(RabbitmqConstantes.FILA_UPDATE_AUTENTICACAO_CLIENTE);
		Queue FILA_ERRO_UPDATE_CLIENTE = this.fila(RabbitmqConstantes.FILA_ERRO_UPDATE_CLIENTE);
		Queue FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE = this
				.fila(RabbitmqConstantes.FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE);
		Queue FILA_UPDATE_CONTA = this
				.fila(RabbitmqConstantes.FILA_UPDATE_CONTA);
		Queue FILA_NOTIFICA_UPDATE_CONTA = this
				.fila(RabbitmqConstantes.FILA_NOTIFICA_UPDATE_CONTA);
		Queue FILA_CREATE_GERENTE = this
				.fila(RabbitmqConstantes.FILA_CREATE_GERENTE);

		Queue FILA_ATRIBUI_CONTA_GERENTE = this
				.fila(RabbitmqConstantes.FILA_ATRIBUI_CONTA_GERENTE);

		DirectExchange troca = this.trocaDireta();

		Binding LIGACAO_FILA_REGISTRO_CLIENTE = this.relacionamento(FILA_REGISTRO_CLIENTE, troca);
		Binding LIGACAO_FILA_AUTENTICACAO_CLIENTE = this.relacionamento(FILA_AUTENTICACAO_CLIENTE, troca);
		Binding LIGACAO_FILA_REGISTRO_CONTA_CLIENTE = this.relacionamento(FILA_REGISTRO_CONTA_CLIENTE, troca);
		Binding LIGACAO_FILA_REGISTRO_GERENTE_CLIENTE = this.relacionamento(FILA_REGISTRO_GERENTE_CLIENTE, troca);
		Binding LIGACAO_FILA_ERRO_NOVO_CLIENTE = this.relacionamento(FILA_ERRO_NOVO_CLIENTE, troca);
		Binding LIGACAO_FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO = this.relacionamento(FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO,
				troca);
		Binding LIGACAO_FILA_UPDATE_CLIENTE = this.relacionamento(FILA_UPDATE_CLIENTE, troca);
		Binding LIGACAO_FILA_UPDATE_AUTENTICACAO_CLIENTE = this.relacionamento(FILA_UPDATE_AUTENTICACAO_CLIENTE, troca);
		Binding LIGACAO_FILA_ERRO_UPDATE_CLIENTE = this.relacionamento(FILA_ERRO_UPDATE_CLIENTE, troca);
		Binding LIGACAO_FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE = this
				.relacionamento(FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE, troca);

		Binding LIGACAO_FILA_UPDATE_CONTA = this
				.relacionamento(FILA_UPDATE_CONTA, troca);

		Binding LIGACAO_FILA_NOTIFICA_UPDATE_CONTA = this
				.relacionamento(FILA_NOTIFICA_UPDATE_CONTA, troca);
		Binding LIGACAO_FILA_CREATE_GERENTE = this
				.relacionamento(FILA_CREATE_GERENTE, troca);

		Binding LIGACAO_FILA_ATRIBUI_CONTA_GERENTE = this
				.relacionamento(FILA_ATRIBUI_CONTA_GERENTE, troca);

		// Criando as filas no RabbitMQ
		this.amqpAdmin.declareQueue(FILA_REGISTRO_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_REGISTRO_CONTA_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_REGISTRO_GERENTE_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_ERRO_NOVO_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO);
		this.amqpAdmin.declareQueue(FILA_UPDATE_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_UPDATE_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_ERRO_UPDATE_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareQueue(FILA_UPDATE_CONTA);
		this.amqpAdmin.declareQueue(FILA_NOTIFICA_UPDATE_CONTA);
		this.amqpAdmin.declareQueue(FILA_CREATE_GERENTE);
		this.amqpAdmin.declareQueue(FILA_ATRIBUI_CONTA_GERENTE);

		this.amqpAdmin.declareExchange(troca);

		this.amqpAdmin.declareBinding(LIGACAO_FILA_REGISTRO_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_REGISTRO_CONTA_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_REGISTRO_GERENTE_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_ERRO_NOVO_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_UPDATE_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_UPDATE_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_ERRO_UPDATE_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_UPDATE_CONTA);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_NOTIFICA_UPDATE_CONTA);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_CREATE_GERENTE);
		this.amqpAdmin.declareBinding(LIGACAO_FILA_ATRIBUI_CONTA_GERENTE);

	}
}
