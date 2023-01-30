/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.bantadsautenticacao.data;

import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author leonardozanotti
 */
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query("{ 'email': ?0, 'senha': ?1, 'ativo': true }")
    public Usuario login(@Param("email") String email,
            @Param("senha") String senha);
}
