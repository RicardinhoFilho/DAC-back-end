package com.bantads.conta.DTO;

import java.io.Serializable;
import java.sql.Date;

public class ContaDTO implements Serializable {
	private int id;
	private int idUsuario;
	private Date data;
	private boolean ativo;
	private double saldo;
	private int idGerente;
	private double salario;
	private String rejeitadoMotivo;
	private Date rejeitadoData;

	public ContaDTO() {
		super();
	}

	public ContaDTO(int id, int idUsuario, Date data, boolean ativo, double saldo, int idGerente, double salario,
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
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

	public int getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(int idGerente) {
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
