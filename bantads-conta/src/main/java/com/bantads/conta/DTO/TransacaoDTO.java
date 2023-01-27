package com.bantads.conta.DTO;

import java.io.Serializable;
import java.math.BigInteger;

public class TransacaoDTO implements Serializable {
	private int id;
	private int idCliente;
	private int tipoTransacao;
	private double valorTransacao;
	private int destinatario;
	private double saldo;
	private double data;
	
	public TransacaoDTO() {
		super();
	}
	
	public TransacaoDTO(int id, int idCliente, int tipoTransacao, double valorTransacao, int destinatario, double saldo,
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
