package com.bantads.orquestrador.bantadsorquestrador.dto;

public class GerenteDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String cargo;

    public GerenteDTO() {
        super();
    }

    public GerenteDTO(String nome, String email, String senha, String cpf, String telefone, String cargo) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public GerenteDTO(Long id, String nome, String email, String senha, String cpf, String telefone, String cargo) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
