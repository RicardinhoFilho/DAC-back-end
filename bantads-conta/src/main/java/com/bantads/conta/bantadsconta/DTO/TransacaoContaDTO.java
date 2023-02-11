package com.bantads.conta.bantadsconta.DTO;

import java.io.Serializable;

import com.bantads.conta.bantadsconta.model.CUD.ContaCUD;
import com.bantads.conta.bantadsconta.model.CUD.TransacaoCUD;

public class TransacaoContaDTO implements Serializable {
	
	private TransacaoCUD transacaoCUD;
	private ContaCUD origem;
	private ContaCUD destino;
	
	public TransacaoContaDTO() {}
	
	public TransacaoContaDTO(TransacaoCUD transacaoCUD, ContaCUD origem, ContaCUD destino) {
		super();
		this.transacaoCUD = transacaoCUD;
		this.origem = origem;
		this.destino = destino;
	}
	
	public TransacaoCUD getTransacaoCUD() {
		return transacaoCUD;
	}
	public void setTransacaoCUD(TransacaoCUD transacaoCUD) {
		this.transacaoCUD = transacaoCUD;
	}
	public ContaCUD getOrigem() {
		return origem;
	}
	public void setOrigem(ContaCUD origem) {
		this.origem = origem;
	}
	public ContaCUD getDestino() {
		return destino;
	}
	public void setDestino(ContaCUD destino) {
		this.destino = destino;
	}
	
	
}
