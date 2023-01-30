/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.controller;

import com.bantads.cliente.dto.ClienteDTO;
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
import java.util.Arrays;
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
    public ResponseEntity<List<ClienteDTO>> getCliente() {
        try {
            List<Cliente> clienteOp = clienteRepository.findAll();
            List<ClienteDTO> response = Arrays.asList(mapper.map(clienteOp, ClienteDTO[].class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
        try {
            Optional<Cliente> clienteOp = clienteRepository.findById(id);

            if (clienteOp.isPresent()) {
                ClienteDTO response = mapper.map(clienteOp.get(), ClienteDTO.class);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/por-cpf/{cpf}")
    public ResponseEntity<ClienteDTO> getClientePorCpf(@PathVariable String cpf) {
        try {
            Cliente cliente = clienteRepository.findByCpf(cpf);

            if (cliente != null) {
                ClienteDTO response = mapper.map(cliente, ClienteDTO.class);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/por-email/{email}")
    public ResponseEntity<ClienteDTO> getClientePorEmail(@PathVariable String email) {
        try {
            Cliente cliente = clienteRepository.findByEmail(email);

            if (cliente != null) {
                ClienteDTO response = mapper.map(cliente, ClienteDTO.class);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/cadastro")
    ResponseEntity<ClienteDTO> cadastro(@RequestBody ClienteDTO clienteDTO) {
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
                ClienteDTO response = mapper.map(cliente, ClienteDTO.class);
                return new ResponseEntity<ClienteDTO>(HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> putCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteUp) {
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
                ClienteDTO response = mapper.map(cliente, ClienteDTO.class);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}