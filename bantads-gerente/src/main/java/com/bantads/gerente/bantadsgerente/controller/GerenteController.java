package com.bantads.gerente.bantadsgerente.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.gerente.bantadsgerente.data.GerenteRepository;
import com.bantads.gerente.bantadsgerente.model.Gerente;
import com.bantads.gerente.bantadsgerente.utils.Security;

@CrossOrigin
@RestController
@RequestMapping("gerentes")
public class GerenteController {
    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> getGerente(@PathVariable Long id) {
        try {
            Optional<Gerente> gerenteOp = gerenteRepository.findById(id);

            if (gerenteOp.isPresent()) {
                Gerente gerente = gerenteOp.get();
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/por-cpf")
    public ResponseEntity<Gerente> getGerentePorCpf(@RequestBody Gerente gerenteBody) {
        try {
            Gerente gerente = gerenteRepository.findByCpf(gerenteBody.getCpf());

            if (gerente != null) {
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/novo")
    ResponseEntity<Gerente> cadastro(@RequestBody Gerente gerente) {
        try {
            Gerente g = new Gerente(
                    gerente.getNome(),
                    gerente.getEmail(),
                    Security.hash(gerente.getSenha()),
                    gerente.getCpf(),
                    gerente.getTelefone(),
                    gerente.getCargo());
            Gerente novoGerente = gerenteRepository.save(g);
            if (novoGerente != null) {
                Gerente response = mapper.map(novoGerente, Gerente.class);
                return new ResponseEntity<Gerente>(HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> putGerente(@PathVariable Long id, @RequestBody Gerente gerenteUp) {
        try {
            Optional<Gerente> gerenteOp = gerenteRepository.findById(id);
            if (gerenteOp.isPresent()) {
                if (gerenteRepository.existsByCpf(gerenteUp.getCpf()))
                    return ResponseEntity.status(409).build();
                Gerente gerente = gerenteOp.get();
                gerente.setEmail(gerenteUp.getEmail());
                gerente.setSenha(gerenteUp.getSenha());
                gerente.setNome(gerenteUp.getNome());
                gerente.setCpf(gerenteUp.getCpf());
                gerente.setTelefone(gerenteUp.getTelefone());
                gerente = gerenteRepository.save(gerente);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}