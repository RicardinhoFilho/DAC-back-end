package com.bantads.conta.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.conta.DTO.ContaDTO;
import com.bantads.conta.model.Conta;
import com.bantads.conta.repository.ContaRepository;

@CrossOrigin
@RestController
public class ContaController {
	@Autowired
	private ContaRepository repositorio;
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/obter/{id}")
	public ResponseEntity<ContaDTO> obterPorId(@PathVariable("id") Long id) {
		try {
			Optional<Conta> contaOpt = repositorio.findById(id);

			if (!contaOpt.isPresent())
				return ResponseEntity.notFound().build();

			Conta conta = contaOpt.get();

			if (conta != null) {
				ContaDTO response = mapper.map(conta, ContaDTO.class);
				return ResponseEntity.ok().body(response);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

}
