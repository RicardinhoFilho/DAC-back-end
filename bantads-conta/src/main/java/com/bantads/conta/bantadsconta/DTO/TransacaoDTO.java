package com.bantads.conta.bantadsconta.DTO;

import java.io.Serializable;
import java.util.Date;

public class TransacaoDTO implements Serializable {
	private Long id;
	private Long idCliente;
	private int tipoTransacao;
	private double valorTransacao;
	private double saldo;
	private Date data;
	private Long destinatario;
	
	public TransacaoDTO() {
		super();
	}
	
	public TransacaoDTO(Long id, Long idCliente, int tipoTransacao, double valorTransacao, double saldo,
			Date data, Long destinatario) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.tipoTransacao = tipoTransacao;
		this.valorTransacao = valorTransacao;
		this.saldo = saldo;
		this.data = data;
		this.destinatario = destinatario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
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
	
	public Long getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Long destinatario) {
		this.destinatario = destinatario;
	}
}
