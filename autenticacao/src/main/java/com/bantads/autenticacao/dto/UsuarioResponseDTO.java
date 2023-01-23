/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.dto;

import com.bantads.autenticacao.model.Cargo;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author leonardozanotti
 */
public class UsuarioResponseDTO implements Serializable {
    private UUID id;
    private String email;
    private Cargo cargo;

    public UsuarioResponseDTO() {
        super();
    }

    public UsuarioResponseDTO(UUID id, String email, Cargo cargo) {
        super();
        this.id = id;
        this.email = email;
        this.cargo = cargo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
