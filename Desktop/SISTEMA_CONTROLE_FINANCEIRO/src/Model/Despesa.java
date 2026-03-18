package Model;

import java.time.LocalDate;

public class Despesa extends Transacao {
    private String descricao;
    private double valor;
    private String data;

    public Despesa(String descricao, double valor, String data) {
        super(descricao, valor, data);
    }
    @Override
    public String getTipo(){
        return "Despesa";
    }
}
