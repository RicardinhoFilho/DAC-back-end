package com.bantads.conta.bantadsconta.data;

import com.bantads.conta.bantadsconta.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    public Optional<Conta> findByIdUsuario(Long userId);

    public List<Conta> findByIdGerente(Long userId);

    @Query("from Conta where idGerente = :gerenteId and ativo = false")
    public List<Conta> findPendentesByIdGerente(@Param("gerenteId") Long gerenteId);
}
