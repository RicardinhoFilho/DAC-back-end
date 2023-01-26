package com.bantads.gerente.bantadsgerente.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.gerente.bantadsgerente.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    public Gerente findByCpf(String cpf);

    public boolean existsByCpf(String cpf);
}
