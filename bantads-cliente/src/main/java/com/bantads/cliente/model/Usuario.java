
package com.bantads.cliente.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String _id;
    private String email;
    private String senha;
    private String cargo;
    private boolean ativo;

    public String getCargo() {
        return cargo;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String get_id() {
        return _id;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public Usuario(String id, String email, String senha, String cargo, boolean ativo) {
        this._id = id;
        this.email = email;
        this.cargo = cargo;
        this.ativo = ativo;
        this.senha = senha;
    }
}
