package com.bantads.conta.bantadsconta.model;

public class Notificacao {
    private boolean status;
    private String message;
    private Long idUsuario;

    public Notificacao(boolean status, String message, Long idUsuario) {
        this.status = status;
        this.message = message;
        this.idUsuario = idUsuario;
    }

    public String getMessage() {
        return message;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}
