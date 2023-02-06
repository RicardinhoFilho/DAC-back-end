package com.bantads.orquestrador.bantadsorquestrador.dto;

public class ValidaReponse {
    private String menssagem;
    private Boolean status;

    public ValidaReponse(String menssagem, Boolean status) {
        this.menssagem = menssagem;
        this.status = status;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
