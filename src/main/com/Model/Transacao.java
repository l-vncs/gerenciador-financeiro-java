package com.Model;

import java.time.LocalDate;

public abstract class Transacao {
    protected int id;
    protected String descricao;
    protected double valor;
    protected LocalDate data;

    // Construtor SEM ID (para criação pelo usuário)
    public Transacao(String descricao, double valor, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Construtor COM ID (para quando buscar do banco)
    public Transacao(int id, String descricao, double valor, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    // Metodo abstrato para retornar o tipo
    public abstract String getTipo();

    @Override
    public abstract String toString();
}