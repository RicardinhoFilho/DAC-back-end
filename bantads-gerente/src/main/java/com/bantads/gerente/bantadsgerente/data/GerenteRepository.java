package com.bantads.gerente.bantadsgerente.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bantads.gerente.bantadsgerente.model.Gerente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    public Gerente findByCpf(String cpf);
    public boolean existsByCpf(String cpf);
    @Query("from Gerente where email = :email and senha = :senha")
    public Gerente login(@Param("email") String email,
            @Param("senha") String senha);
}
