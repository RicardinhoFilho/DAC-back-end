package com.bantads.conta.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transacao")
public class Transacao implements Serializable {
	
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
	@Column(name="saldo")
	private double saldo;
	@Column(name="data_transacao")
	private Date data;
	@Column(name="destinatario")
	private int destinatario;
	
	public Transacao() {
		super();
	}
	
	public Transacao(int id, int idCliente, int tipoTransacao, double valorTransacao, double saldo,
			Date data, int destinatario) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.tipoTransacao = tipoTransacao;
		this.valorTransacao = valorTransacao;
		this.saldo = saldo;
		this.data = data;
		this.destinatario = destinatario;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}
}
