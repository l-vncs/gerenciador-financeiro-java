package Service;

import Model.Despesa;
import Model.Receita;
import Model.Transacao;
import Util.ArquivoUtil;

import java.util.ArrayList;
import java.util.List;

public class FinanceiroService {
    //Criando um ArrayList para armazenar os valores referentes a Receita e despesa

    private List <Transacao> Transacoes = new ArrayList<>();

    //Aqui são onde os valores são acionados para sua armazenagem e cálculos

    public void adicionarReceita(Receita receita){
        Transacoes.add(receita);

    }

    public void adicionarDespesa(Despesa despesa){
        Transacoes.add(despesa);

    }

    //cálculo de saldo

    public double calcularSaldo(){
       double saldo = 0;

        //instanceof para polimorfismo, detectando os valores referentes a receita e despesa, adiciona e subtrai do total

        for (Transacao t : Transacoes){
            if (t instanceof Receita){
                saldo+= t.getValor();
            }
            else if (t instanceof Despesa) {
                saldo-= t.getValor();
            }
        }
        return saldo;
        }

    public void listarTudo(){
        System.out.println("================================================");
        System.out.println("TRANSAÇÕES:");
        for (Transacao t : Transacoes){
            //Utilizando instanceof novamente, dessa vez caso ele receba um valor referente a receita ele adiciona à lista

            if(t instanceof Receita){
                System.out.println("Receita: " + t.getDescricao() + "\nR$ " + t.getValor());
            }
            else{
                System.out.println("Despesa: " + t.getDescricao() + "\nR$ " + t.getValor());
            }
            System.out.println("================================================");
    }

    }

    public void salvarDados(){
        ArquivoUtil.salvar(Transacoes);
    }

    public void carregarDados(){
        ArquivoUtil.carregar();
    }



}