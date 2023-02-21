package com.bantads.conta.bantadsconta.model.CUD;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bantads.conta.bantadsconta.DTO.TransacaoDTO;

@Entity
@Table(name="transacao")
public class TransacaoCUD {
    @Id
	@Column(name="id_transacao")
	private Long id;
    @Column(name="id_cliente")
	private Long idCliente;
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
	
	public TransacaoCUD() {
		super();
	}
	
	public TransacaoCUD(Long id, Long idCliente, int tipoTransacao, double valorTransacao, double saldo,
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

	public TransacaoCUD(TransacaoDTO transacao) {
    	this.id = transacao.getId();
    	this.data = new java.sql.Date(transacao.getData().getTime());
    	this.idCliente = transacao.getIdCliente();
    	this.tipoTransacao = transacao.getTipoTransacao();
    	this.valorTransacao = transacao.getValorTransacao();
    	this.saldo = transacao.getSaldo();
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
	
	
	public int getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(int destinatario) {
		this.destinatario = destinatario;
	}
}
