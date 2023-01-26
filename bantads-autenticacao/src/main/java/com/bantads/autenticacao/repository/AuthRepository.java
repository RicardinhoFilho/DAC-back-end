/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.repository;

/**
 *
 * @author leonardozanotti
 */
import com.bantads.autenticacao.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AuthRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByEmail(String email);
    @Query("from Usuario where email = :email and senha = :senha and ativo = true")
    public Usuario login(@Param("email") String email,
            @Param("senha") String senha);
}
