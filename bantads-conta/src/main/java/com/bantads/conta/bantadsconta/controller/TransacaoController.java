package com.bantads.conta.bantadsconta.controller;

import com.bantads.conta.bantadsconta.DTO.TransacaoContaDTO;
import com.bantads.conta.bantadsconta.DTO.TransacaoDTO;
import com.bantads.conta.bantadsconta.model.CUD.ContaCUD;
import com.bantads.conta.bantadsconta.model.CUD.TransacaoCUD;
import com.bantads.conta.bantadsconta.model.R.TransacaoR;
import com.bantads.conta.bantadsconta.services.EmissorTransacao;
import com.bantads.conta.bantadsconta.data.CUD.ContaCUDRepository;
import com.bantads.conta.bantadsconta.data.CUD.TransacaoCUDRepository;
import com.bantads.conta.bantadsconta.data.R.TransacaoRRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TransacaoController {
	@Autowired
	private EmissorTransacao emitirTransacao;
	
	@Autowired
	private TransacaoRRepository transacaoRRepository;
	
	@Autowired
	private TransacaoCUDRepository transacaoCUDRepository;
        
	@Autowired
	private ContaCUDRepository contaCUDRepository;
        
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/transacaos")
	public ResponseEntity<List<TransacaoDTO>> obterTodasTransacaos() {
		try {
			List<TransacaoR> lista = transacaoRRepository.findAll();
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
	
	@PostMapping("/transacaos")
	public ResponseEntity<TransacaoDTO> inserirUsuario(@RequestBody TransacaoDTO transacao) {
		
		try {
			TransacaoCUD transacaoCUD = new TransacaoCUD(transacao);
			
			Optional<ContaCUD> contaOrigem = contaCUDRepository.findById((long) transacao.getIdCliente());
			ContaCUD origem = contaOrigem.get();
			Optional<ContaCUD> contaDestinatario = null;
			ContaCUD destinatario = null;
			
			if(!contaOrigem.isPresent())
				return ResponseEntity.status(500).build();
			
			if(transacao.getDestinatario() != 0) {
				contaDestinatario = contaCUDRepository.findById((long) transacao.getDestinatario());
				destinatario  = contaDestinatario.get();
			}
			
			transacaoCUDRepository.save(transacaoCUD);
			
			switch (transacao.getTipoTransacao()) {
			case 1: //deposito
				origem.setSaldo(transacao.getSaldo());
				contaCUDRepository.save(origem);
				break;
				
			case 2: //saque
				origem.setSaldo(transacao.getSaldo());
				contaCUDRepository.save(origem);
				break;
				
			case 3: //transferencia
				origem.setSaldo(transacao.getSaldo());
				contaCUDRepository.save(origem);
				destinatario.setSaldo(destinatario.getSaldo() + transacao.getValorTransacao());
				contaCUDRepository.save(destinatario);
				break;
				
			default:
				return ResponseEntity.status(500).build();
			}
			
			// MANDA PRA FILA PARA ATUALIZAR O BANCO R
			TransacaoContaDTO transacaoConta = new TransacaoContaDTO(transacaoCUD, origem, destinatario);
			emitirTransacao.enviarInserirBancoLeitura(transacaoConta);
			
			return ResponseEntity.ok().body(transacao);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
		
	}
}
