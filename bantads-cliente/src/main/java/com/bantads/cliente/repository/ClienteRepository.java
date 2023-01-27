/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.cliente.repository;

/**
 *
 * @author leonardozanotti
 */
import com.bantads.cliente.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByEmail(String email);
    @Query("from Cliente where email = :email and senha = :senha and ativo = true")
    public Cliente login(@Param("email") String email,
            @Param("senha") String senha);
}
