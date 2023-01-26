package com.bantads.conta.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	
	public static List<TransacaoDTO> lista = new ArrayList<>();
	
	@GetMapping("/transacaos")
	public List<TransacaoDTO> obterTodasTransacaos() {
		return lista;
	}
	
	@GetMapping("/transacaos/{id}")
	public TransacaoDTO obterTodosUsuarios(@PathVariable("id") int id) {
		TransacaoDTO transacao = lista.stream().filter(item -> item.getId() == id).findAny().orElse(null);
		return transacao;
	}
	
	@PostMapping("/transacao")
	public TransacaoDTO inserirUsuario(@RequestBody TransacaoDTO transacao) {
		TransacaoDTO t = lista.stream().max(Comparator.comparing(TransacaoDTO::getId)).orElse(null);
		if(t == null)
			transacao.setId(1);
		else
			transacao.setId(t.getId() + 1);
	repositorio.save(mapper.map(transacao, Transacao.class));
	//lista.add(transacao);
	return transacao;
	}

	
	static {
		lista.add(new TransacaoDTO(1, 2, 1, 500, 5, 200, 1665958829));
		lista.add(new TransacaoDTO(2, 3, 1, 100, 5, 100, 1669660753));		
	}
}
