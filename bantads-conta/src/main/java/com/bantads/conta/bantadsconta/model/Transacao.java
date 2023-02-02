package com.bantads.conta.bantadsconta.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transacao")
public class Transacao implements Serializable {
	private int id;
	private int idCliente;
	private int tipoTransacao;
	private double valorTransacao;
	private double saldo;
	private Date data;
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

        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transacao")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="id_cliente")
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Column(name="tipo_transacao")
	public int getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(int tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	@Column(name="valor_transacao")
	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	@Column(name="saldo")
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Column(name="data_transacao")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name="destinatario")
	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}
}
