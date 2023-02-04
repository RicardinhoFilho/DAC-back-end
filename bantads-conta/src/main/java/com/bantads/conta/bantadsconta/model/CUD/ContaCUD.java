package com.bantads.conta.bantadsconta.model.CUD;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bantads.conta.bantadsconta.DTO.ContaDTO;

@Entity
@Table(name = "conta")
public class ContaCUD {
	private Long id;
	private Long idUsuario;
	private Date data;
	private boolean ativo;
	private double saldo;
	private Long idGerente;
	private double salario;
	private String rejeitadoMotivo;
	private Date rejeitadoData;

	public ContaCUD() {
		super();
	}
        
        public ContaCUD(Long idUsuario, Date data, boolean ativo, double saldo, Long idGerente, double salario,
			String rejeitadoMotivo, Date rejeitadoData) {
		super();
		this.idUsuario = idUsuario;
		this.data = data;
		this.ativo = ativo;
		this.saldo = saldo;
		this.idGerente = idGerente;
		this.salario = salario;
		this.rejeitadoMotivo = rejeitadoMotivo;
		this.rejeitadoData = rejeitadoData;
	}

	public ContaCUD(Long id, Long idUsuario, Date data, boolean ativo, double saldo, Long idGerente, double salario,
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

        public ContaCUD(Long idUsuario, Date data, boolean ativo, double saldo, Long idGerente, double salario) {
            super();
            this.idUsuario = idUsuario;
            this.data = data;
            this.ativo = ativo;
            this.saldo = saldo;
            this.idGerente = idGerente;
            this.salario = salario;
        }
        
        public ContaCUD(ContaDTO conta) {
        	super();
        	this.id = conta.getId();
        	this.idGerente = conta.getIdGerente();
        	this.idUsuario = conta.getIdUsuario();
        	this.data = conta.getData();
        	this.saldo = conta.getSaldo();
        	this.salario = conta.getSalario();
        	//this.saga = conta.getSaga();
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_conta", unique=true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

        @Column(name = "id_usuario")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

        @Column(name = "data")
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

        @Column(name = "ativo")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

        @Column(name = "saldo")
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Column(name = "id_gerente")
	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}

	@Column(name = "salario")
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Column(name = "rejeitado_motivo")
	public String getRejeitadoMotivo() {
		return rejeitadoMotivo;
	}

	public void setRejeitadoMotivo(String rejeitadoMotivo) {
		this.rejeitadoMotivo = rejeitadoMotivo;
	}

	@Column(name = "rejeitado_data")
	public Date getRejeitadoData() {
		return rejeitadoData;
	}

	public void setRejeitadoData(Date rejeitadoData) {
		this.rejeitadoData = rejeitadoData;
	}
}
