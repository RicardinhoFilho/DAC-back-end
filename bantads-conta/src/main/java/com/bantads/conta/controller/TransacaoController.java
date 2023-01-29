package com.bantads.conta.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.conta.DTO.TransacaoDTO;
import com.bantads.conta.model.Transacao;
import com.bantads.conta.repository.TransacaoRepository;

@CrossOrigin
@RestController
public class TransacaoController {
	@Autowired
	private TransacaoRepository repositorio;
	@Autowired
	private ModelMapper mapper;
	
	public static List<TransacaoDTO> listaFixa = new ArrayList<>();
	
	@GetMapping("/transacaos")
	public List<TransacaoDTO> obterTodasTransacaos() {
		List<Transacao> lista =repositorio.findAll();
		return lista.stream()
				.map(item -> mapper.map(item, 
						TransacaoDTO.class)).
				collect(Collectors.toList());
	}
	
	@GetMapping("/transacaos/{id}")
	public TransacaoDTO obterTodosUsuarios(@PathVariable("id") int id) {
		TransacaoDTO transacao = listaFixa.stream().filter(item -> item.getId() == id).findAny().orElse(null);
		return transacao;
	}
	
	@PostMapping("/transacao")
	public TransacaoDTO inserirUsuario(@RequestBody TransacaoDTO transacao) {
		//TODO, verificar se conta existe
		
		//TODO, atualizar conta cliente
		
		//TODO, atualizar transacao
		repositorio.save(mapper.map(transacao, Transacao.class));
	return transacao;
	}

	
	static {
		//listaFixa.add(new TransacaoDTO(1, 2, 1, 500, 200, ));
		//listaFixa.add(new TransacaoDTO(2, 3, 1, 100, 100, 1669660753));		
	}
}
