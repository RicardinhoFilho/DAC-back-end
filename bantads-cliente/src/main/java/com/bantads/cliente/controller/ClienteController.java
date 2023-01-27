/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.controller;

import com.bantads.cliente.model.Cliente;
import com.bantads.cliente.repository.ClienteRepository;
import com.bantads.cliente.utils.Security;
import java.util.Optional;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
/**
 *
 * @author leonardozanotti
 */
@CrossOrigin
@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/list")
    public ResponseEntity<List<Cliente>> getUsuario() {
        try {
            List<Cliente> usuarioOp = clienteRepository.findAll();

            return ResponseEntity.ok(usuarioOp);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getUsuario(@PathVariable Long id) {
        try {
            Optional<Cliente> usuarioOp = clienteRepository.findById(id);

            if (usuarioOp.isPresent()) {
                Cliente gerente = mapper.map(usuarioOp.get(), Cliente.class);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<Cliente> login(@RequestBody Cliente usuarioDTO) {
        try {
            Cliente usuario = clienteRepository.login(usuarioDTO.getEmail(), Security.hash(usuarioDTO.getSenha()));
            if (usuario != null) {
                Cliente response = mapper.map(usuario, Cliente.class);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @PostMapping("/cadastro")
    ResponseEntity<Cliente> cadastro(@RequestBody Cliente usuarioDTO) {
        try {
            Cliente u = new Cliente(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                Security.hash(usuarioDTO.getSenha()),
                usuarioDTO.getCpf(),
                usuarioDTO.getTelefone(),
                usuarioDTO.getEstado(),
                usuarioDTO.getCidade(),
                usuarioDTO.getCep(),
                usuarioDTO.getRua(),
                usuarioDTO.getNumero(),
                usuarioDTO.getComplemento(),
                usuarioDTO.getCargo(),
                usuarioDTO.isAtivo()
            );
            Cliente usuario = clienteRepository.save(u);
            if (usuario != null) {
                Cliente response = mapper.map(usuario, Cliente.class);
                return new ResponseEntity<Cliente>(HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }
}