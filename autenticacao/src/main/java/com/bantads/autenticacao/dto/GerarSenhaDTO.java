/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bantads.autenticacao.dto;

import java.util.UUID;

/**
 *
 * @author leonardozanotti
 */
public class GerarSenhaDTO {
    private UUID idExternoUsuario;
    private UUID saga;

    public GerarSenhaDTO() {
    }

    public GerarSenhaDTO(UUID idExternoUsuario, UUID saga) {
        this.idExternoUsuario = idExternoUsuario;
        this.saga = saga;
    }

    public UUID getIdExternoUsuario() {
        return idExternoUsuario;
    }

    public void setIdExternoUsuario(UUID idExternoUsuario) {
        this.idExternoUsuario = idExternoUsuario;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }
}
