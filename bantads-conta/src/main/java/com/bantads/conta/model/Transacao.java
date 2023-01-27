package com.bantads.conta.model;

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transacao")
public class Transacao implements Serializable {
private static final Long serialVersionUID = 1L;
	
	@Id
	//@GeneratedValue
	@Column(name="id_transacao")
	private int id;
	@Column(name="id_cliente")
	private int idCliente;
	@Column(name="tipo_transacao")
	private int tipoTransacao;
	@Column(name="valor_transacao")
	private double valorTransacao;
	@Column(name="destinatario")
	private int destinatario;
	@Column(name="saldo")
	private double saldo;
	@Column(name="data_transacao")
	private double data;
	
	public Transacao() {
		super();
	}
	
	public Transacao(int id, int idCliente, int tipoTransacao, double valorTransacao, int destinatario, double saldo,
			double data) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.tipoTransacao = tipoTransacao;
		this.valorTransacao = valorTransacao;
		this.destinatario = destinatario;
		this.saldo = saldo;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
}
