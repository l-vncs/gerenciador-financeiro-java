package com.Model;

import java.time.LocalDate;

public class Despesa extends Transacao {

    // Construtor SEM ID
    public Despesa(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    // Construtor COM ID (para buscar do banco)
    public Despesa(int id, String descricao, double valor, LocalDate data) {
        super(id, descricao, valor, data);
    }

    @Override
    public String getTipo() {
        return "DESPESA";
    }

    @Override
    public String toString() {
        return String.format("[DESPESA] %s - R$ %.2f - %s", descricao, valor, data);
    }
}