/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.controller;

import com.bantads.autenticacao.model.Usuario;
import com.bantads.autenticacao.utils.Security;
import java.util.Optional;

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
import com.bantads.autenticacao.repository.AuthRepository;
/**
 *
 * @author leonardozanotti
 */
@CrossOrigin
@RestController
@RequestMapping("usuarios")
public class AuthController {
    @Autowired
    private AuthRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        try {
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

            if (usuarioOp.isPresent()) {
                Usuario gerente = mapper.map(usuarioOp.get(), Usuario.class);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<Usuario> login(@RequestBody Usuario usuarioDTO) {
        try {
            Usuario usuario = usuarioRepository.login(usuarioDTO.getEmail(), Security.hash(usuarioDTO.getSenha()));
            if (usuario != null) {
                Usuario response = mapper.map(usuario, Usuario.class);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}