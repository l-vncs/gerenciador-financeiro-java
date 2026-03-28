package com.Service;

import com.Model.Transacao;
import com.Model.Receita;
import com.Model.Despesa;
import com.dataConnection.TransacaoRepository;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroService {
    private List<Transacao> transacoes = new ArrayList<>();
    private TransacaoRepository repository = new TransacaoRepository();

    public void carregarDados() {
        System.out.println("Carregando dados do banco...");
    }

    // ===== ADICIONAR =====

    public void adicionarReceita(Receita receita) {
        transacoes.add(receita);
    }

    public void adicionarDespesa(Despesa despesa) {
        transacoes.add(despesa);
    }

    // ===== SALVAR =====

    public void salvarDados() {
        for (Transacao t : transacoes) {
            if (t instanceof Receita) {
                repository.salvarReceita((Receita) t);
            } else if (t instanceof Despesa) {
                repository.salvarDespesa((Despesa) t);
            }
        }
        transacoes.clear();
    }

    // ===== BUSCAR =====

    public Transacao buscarTransacao(int id) {
        return repository.buscarPorId(id);
    }

    // ===== ATUALIZAR =====

    public void atualizarTransacao(Transacao transacao) {
        repository.atualizar(transacao);
    }

    // ===== DELETAR =====

    public void deletarTransacao(int id) {
        repository.deletar(id);
    }

    public void deletarTodasAsTransacoes() {
        repository.deletarTodas();
    }

    // ===== LISTAR =====

    public void listarTudo() {
        List<String> transacoes = repository.listarTodasFormatadas();

        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.");
        } else {
            System.out.println("\n====== HISTÓRICO DE TRANSAÇÕES ======");
            for (String transacao : transacoes) {
                System.out.println(transacao);
            }
            System.out.println("=====================================\n");
        }
    }

    // ===== CÁLCULOS =====

    public double calcularSaldo() {
        return repository.calcularSaldo();
    }

    public double calcularTotalReceitas() {
        return repository.calcularTotalReceitas();
    }

    public double calcularTotalDespesas() {
        return repository.calcularTotalDespesas();
    }
}