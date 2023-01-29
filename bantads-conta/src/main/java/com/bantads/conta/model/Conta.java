package com.bantads.conta.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta implements Serializable {
	@Id
	// @GeneratedValue
	@Column(name = "id_conta")
	private int id;
	@Column(name = "id_usuario")
	private int idUsuario;
	@Column(name = "data")
	private Date data;
	@Column(name = "ativo")
	private boolean ativo;
	@Column(name = "saldo")
	private double saldo;
	@Column(name = "id_gerente")
	private int idGerente;
	@Column(name = "salario")
	private double salario;
	@Column(name = "rejeitado_motivo")
	private String rejeitadoMotivo;
	@Column(name = "rejeitado_data")
	private Date rejeitadoData;

	public Conta() {
		super();
	}

	public Conta(int id, int idUsuario, Date data, boolean ativo, double saldo, int idGerente, double salario,
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
