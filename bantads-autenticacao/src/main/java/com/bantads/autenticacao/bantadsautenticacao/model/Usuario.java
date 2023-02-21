package com.bantads.autenticacao.bantadsautenticacao.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "usuario")
public class Usuario implements Serializable {
    @Id
    private String _id;
    private String email;
    private String senha;
    private String cargo;
    private boolean ativo;

    public String getEmail() {
        return email;
    }

    public String get_id() {
        return _id;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String id, String email, String senha, String cargo, boolean ativo) {
        this._id = id;
        this.email = email;
        this.cargo = cargo;
        this.ativo = ativo;
        this.senha = senha;
    }

    public Usuario() {

    }

}
