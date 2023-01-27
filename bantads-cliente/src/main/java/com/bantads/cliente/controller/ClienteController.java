/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.cliente.model.Cliente;
import com.bantads.cliente.repository.ClienteRepository;
import com.bantads.cliente.utils.Security;

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
    public ResponseEntity<List<Cliente>> getCliente() {
        try {
            List<Cliente> clienteOp = clienteRepository.findAll();

            return ResponseEntity.ok(clienteOp);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        try {
            Optional<Cliente> clienteOp = clienteRepository.findById(id);

            if (clienteOp.isPresent()) {
                Cliente gerente = mapper.map(clienteOp.get(), Cliente.class);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<Cliente> login(@RequestBody Cliente clienteDTO) {
        try {
            Cliente cliente = clienteRepository.login(clienteDTO.getEmail(), Security.hash(clienteDTO.getSenha()));
            if (cliente != null) {
                Cliente response = mapper.map(cliente, Cliente.class);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/cadastro")
    ResponseEntity<Cliente> cadastro(@RequestBody Cliente clienteDTO) {
        try {
            Cliente u = new Cliente(
                    clienteDTO.getNome(),
                    clienteDTO.getEmail(),
                    Security.hash(clienteDTO.getSenha()),
                    clienteDTO.getCpf(),
                    clienteDTO.getTelefone(),
                    clienteDTO.getEstado(),
                    clienteDTO.getCidade(),
                    clienteDTO.getCep(),
                    clienteDTO.getRua(),
                    clienteDTO.getNumero(),
                    clienteDTO.getComplemento(),
                    clienteDTO.getCargo(),
                    clienteDTO.isAtivo());
            Cliente cliente = clienteRepository.save(u);
            if (cliente != null) {
                Cliente response = mapper.map(cliente, Cliente.class);
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