package com.bantads.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.conta.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
