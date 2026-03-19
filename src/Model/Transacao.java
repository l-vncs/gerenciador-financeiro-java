package Model;

public class Transacao {
    protected String descricao;
    protected double valor;
    protected String data;
// Transacao serve como classe base para Receita e Despesa pois seus valores são repetidos
    public Transacao (String descricao, double valor,  String data) {
        this.descricao= descricao;
        this.valor = valor;
        this.data = data;

    }

    public String getDescricao(){
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getTipo(){
        return "Transacao";
    }

}
