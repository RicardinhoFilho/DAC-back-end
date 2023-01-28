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
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/por-cpf/{cpf}")
    public ResponseEntity<Cliente> getClientePorCpf(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteRepository.findByCpf(cpf);

            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/por-email/{email}")
    public ResponseEntity<Cliente> getClientePorEmail(@PathVariable String email) {
        try {
            Cliente cliente = clienteRepository.findByEmail(email);

            if (cliente != null) {
                return ResponseEntity.ok(cliente);
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
            if (clienteRepository.existsByCpf(clienteDTO.getCpf()))
                return ResponseEntity.status(409).build();
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
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> putCliente(@PathVariable Long id, @RequestBody Cliente clienteUp) {
        try {
            Optional<Cliente> clienteOp = clienteRepository.findById(id);
            if (clienteOp.isPresent()) {
                Cliente cliente = clienteOp.get();
                cliente.setNome(clienteUp.getNome());
                cliente.setEmail(clienteUp.getEmail());
                cliente.setSenha(Security.hash(clienteUp.getSenha()));
                cliente.setCpf(clienteUp.getCpf());
                cliente.setTelefone(clienteUp.getTelefone());
                cliente.setEstado(clienteUp.getEstado());
                cliente.setCidade(clienteUp.getCidade());
                cliente.setCep(clienteUp.getCep());
                cliente.setRua(clienteUp.getRua());
                cliente.setNumero(clienteUp.getNumero());
                cliente.setComplemento(clienteUp.getComplemento());
                cliente.setCargo(clienteUp.getCargo());
                cliente.setAtivo(clienteUp.isAtivo());
                cliente = clienteRepository.save(cliente);
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}