package com.Model;

import java.time.LocalDate;

public class Receita extends Transacao {
    public Receita(String descricao, double valor, LocalDate data) {
        super(descricao, valor, data);
    }

    // Construtor COM ID (para buscar do banco)
    public Receita(int id, String descricao, double valor, LocalDate data) {
        super(id, descricao, valor, data);
    }

    @Override
    public String getTipo() {
        return "RECEITA";
    }

    @Override
    public String toString() {
        return String.format("[RECEITA] %s - R$ %.2f - %s", descricao, valor, data);
    }


}
