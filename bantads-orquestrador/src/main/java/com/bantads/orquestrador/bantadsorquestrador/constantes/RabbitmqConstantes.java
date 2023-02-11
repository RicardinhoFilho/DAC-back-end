package com.bantads.orquestrador.bantadsorquestrador.constantes;

public class RabbitmqConstantes {
    // CREATE_CLIENTE
    public static final String FILA_REGISTRO_CLIENTE = "FILA_REGISTRO_CLIENTE";
    public static final String FILA_AUTENTICACAO_CLIENTE = "FILA_AUTENTICACAO_CLIENTE";
    public static final String FILA_REGISTRO_CONTA_CLIENTE = "FILA_REGISTRO_CONTA_CLIENTE";

    // ROLLBACK_CREATE_CLIENTE
    public static final String FILA_ERRO_NOVO_CLIENTE = "FILA_ERRO_NOVO_CLIENTE";
    public static final String FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO = "FILA_ERRO_NOVO_CLIENTE_AUTENTICACAO";

    // UPDATE_CLIENTE
    public static final String FILA_UPDATE_CLIENTE = "FILA_UPDATE_CLIENTE";
    public static final String FILA_UPDATE_AUTENTICACAO_CLIENTE = "FILA_UPDATE_AUTENTICACAO_CLIENTE";

    // ROLLBACK_UPDATE_CLIENTE
    public static final String FILA_ERRO_UPDATE_CLIENTE = "FILA_ERRO_UPDATE_CLIENTE";
    public static final String FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE = "FILA_ERRO_UPDATE_AUTENTICACAO_CLIENTE";

    // UPDATE_CONTA
    public static final String FILA_UPDATE_CONTA = "FILA_UPDATE_CONTA";
    public static final String FILA_NOTIFICA_UPDATE_CONTA = "FILA_NOTIFICA_UPDATE_CONTA";


     // CREATE_GERENTE
     public static final String FILA_CREATE_GERENTE = "FILA_CREATE_GERENTE";
     public static final String FILA_ATRIBUI_CONTA_GERENTE = "FILA_ATRIBUI_CONTA_GERENTE";

    // DELETE_GERENTE
    public static final String FILA_DELETE_GERENTE = "FILA_DELETE_GERENTE";
    public static final String FILA_DISTRIBUI_CONTAS_GERENTE = "FILA_DISTRIBUI_CONTAS_GERENTE";
     

    public static final String FILA_REGISTRO_GERENTE_CLIENTE = "FILA_REGISTRO_GERENTE_CLIENTE";
}
