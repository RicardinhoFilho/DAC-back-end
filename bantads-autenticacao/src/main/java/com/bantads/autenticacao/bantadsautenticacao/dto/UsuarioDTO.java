package com.bantads.autenticacao.bantadsautenticacao.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
    @Id private String _id;
    private String email;
    private String senha;
    private String cargo;
    private boolean ativo;
}
