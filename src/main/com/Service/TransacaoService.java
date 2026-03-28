package com.Service;
import com.Model.Transacao;
import com.dataConnection.TransacaoRepository;

import java.util.List;

public class TransacaoService {
    private TransacaoRepository repository = new TransacaoRepository();

    public void adicionarTransacao(Transacao transacao){
        try {
            repository.save(transacao);
        } catch (Exception e) {
            System.out.println("Erro ao salvar! " + e.getMessage());
        }
    }

    public void atualizarTransacao(Transacao transacao){
        try {
            repository.update(transacao);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar! " + e.getMessage());
        }
    }

    public void removerTransacao(int id){
        try {
            repository.delete(id);
        } catch (Exception e){
            System.out.println("Erro ao deletar! " + e.getMessage());
        }
    }

    public List<Transacao> listarTodasTransacoes(){
        try {
            return repository.findAll();
        } catch (Exception e){
            System.out.println("Erro ao listar! " + e.getMessage());
            return List.of();
        }
    }
}