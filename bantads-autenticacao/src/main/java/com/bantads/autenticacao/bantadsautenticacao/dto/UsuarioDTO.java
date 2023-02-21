package com.bantads.autenticacao.bantadsautenticacao.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UsuarioDTO implements Serializable {
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

    public UsuarioDTO(String id, String email, String senha, String cargo, boolean ativo) {
        this._id = id;
        this.email = email;
        this.cargo = cargo;
        this.ativo = ativo;
    }

    public UsuarioDTO() {

    }

}
