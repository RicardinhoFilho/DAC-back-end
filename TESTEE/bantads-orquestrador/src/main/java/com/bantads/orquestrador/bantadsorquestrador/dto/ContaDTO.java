package com.bantads.orquestrador.bantadsorquestrador.dto;

import java.sql.Date;

public class ContaDTO {
    private Long id;
	private Long idUsuario;
	private Date data;
	private boolean ativo;
	private double saldo;
	private Long idGerente;
	private double salario;
	private String rejeitadoMotivo;
	private Date rejeitadoData;

	public ContaDTO() {
		super();
	}

	public ContaDTO(Long id, Long idUsuario, Date data, boolean ativo, double saldo, Long idGerente, double salario,
			String rejeitadoMotivo, Date rejeitadoData) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.data = data;
		this.ativo = ativo;
		this.saldo = saldo;
		this.idGerente = idGerente;
		this.salario = salario;
		this.rejeitadoMotivo = rejeitadoMotivo;
		this.rejeitadoData = rejeitadoData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getRejeitadoMotivo() {
		return rejeitadoMotivo;
	}

	public void setRejeitadoMotivo(String rejeitadoMotivo) {
		this.rejeitadoMotivo = rejeitadoMotivo;
	}

	public Date getRejeitadoData() {
		return rejeitadoData;
	}

	public void setRejeitadoData(Date rejeitadoData) {
		this.rejeitadoData = rejeitadoData;
	}
}
