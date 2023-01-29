package com.bantads.conta.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.conta.DTO.TransacaoDTO;
import com.bantads.conta.model.Conta;
import com.bantads.conta.model.Transacao;
import com.bantads.conta.repository.ContaRepository;
import com.bantads.conta.repository.TransacaoRepository;

@CrossOrigin
@RestController
public class TransacaoController {
	@Autowired
	private TransacaoRepository repositorio;
	@Autowired
	private ContaRepository repositorioConta;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/transacaos")
	public ResponseEntity<List<TransacaoDTO>> obterTodasTransacaos() {
		try {
			List<Transacao> lista =repositorio.findAll();
			List<TransacaoDTO> response = lista.stream()
					.map(item -> mapper.map(item, 
							TransacaoDTO.class)).
					collect(Collectors.toList());
			ResponseEntity.ok().body(response);
			return ResponseEntity.ok().body(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
		
	}
	
	@PostMapping("/transacao")
	public ResponseEntity<TransacaoDTO> inserirUsuario(@RequestBody TransacaoDTO transacao) {
		
		Optional<Conta> contaOrigem = repositorioConta.findById((long) transacao.getIdCliente());
		Conta origem = contaOrigem.get();
		Optional<Conta> contaDestinatario = null;
		Conta destinatario = null;
		
		if(!contaOrigem.isPresent())
			return ResponseEntity.status(500).build();
		
		if(transacao.getDestinatario() != 0) {
			contaDestinatario = repositorioConta.findById((long) transacao.getDestinatario());
			destinatario  = contaDestinatario.get();
		}
		
		repositorio.save(mapper.map(transacao, Transacao.class));
		
		switch (transacao.getTipoTransacao()) {
		case 1: //deposito
			origem.setSaldo(transacao.getSaldo());
			repositorioConta.save(origem);
			break;
			
		case 2: //saque
			origem.setSaldo(transacao.getSaldo());
			repositorioConta.save(origem);
			break;
			
		case 3: //transferencia
			origem.setSaldo(transacao.getSaldo());
			repositorioConta.save(origem);
			destinatario.setSaldo(destinatario.getSaldo() + transacao.getValorTransacao());
			repositorioConta.save(destinatario);
			break;
			
		default:
			return ResponseEntity.status(500).build();
		}
		
	return ResponseEntity.ok().body(transacao);
	}
}
