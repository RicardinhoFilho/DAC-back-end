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
	private Long id;
	private Long idCliente;
	private int tipoTransacao;
	private double valorTransacao;
	private double saldo;
	private Date data;
	private Long destinatario;
	
	public TransacaoCUD() {
		super();
	}
	
	public TransacaoCUD(Long id, Long idCliente, int tipoTransacao, double valorTransacao, double saldo,
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

	public TransacaoCUD(TransacaoDTO transacao) {
    	this.id = transacao.getId();
    	this.data = new Date (transacao.getData().getTime());
    	this.idCliente = transacao.getIdCliente();
    	this.tipoTransacao = transacao.getTipoTransacao();
    	this.valorTransacao = transacao.getValorTransacao();
    	//this.saga = UUID.randomUUID();
    }
	
        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_transacao")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="id_cliente")
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
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
	public Long getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Long destinatario) {
		this.destinatario = destinatario;
	}
}
