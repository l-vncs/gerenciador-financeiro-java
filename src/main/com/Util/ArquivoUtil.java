package com.Util;
import com.Model.Despesa;
import com.Model.Receita;
import com.Model.Transacao;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ArquivoUtil {

    /*Funcao de salvamento, por meio da lista Transacao, o filewriter pega as informações
    e transforma em um arquivo .txt*/

    //aqui é criado uma String para o caminho para facilitar o processo
    private static final String CAMINHO =
            System.getProperty("user.dir") + "/src/data/dados_financeiros.txt";

    public static void salvar (List <Transacao> Transacoes){
        try{
            File arquivo = new File(CAMINHO);

            if(arquivo.getParentFile()!=null){
                arquivo.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(arquivo);

            //cria o arquivo e verifica se existe a pasta, caso não exista ele cria a pasta necessária

            for (Transacao t: Transacoes ){
                //por meio deste loop o sistema pega o t como receita e else passa como despesa
                String tipo = (t instanceof Receita) ? "Receita" : "Despesa";
                writer.write(tipo + ";"+
                        t.getDescricao() + ";" +
                        t.getValor() + ";" +
                        t.getData() + "\n");
                // apos isso ele define tipo de transacao, descricao, valor e data, todos juntos separados por ";"

            }
            writer.close();
            System.out.println("SALVANDO DADOS EM: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Erro ao salvar!");
        }
        // a exception é necessaria para caso nao seja possivel salvar

    }
    public static List<Transacao> carregar(){
        List <Transacao> lista = new ArrayList<>();
        //criamos uma nova arraylist dentro de "Transacao" para carregar as informações listadas
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO))){
            String linha;
        //definimos as informacoes em linha e após isso o .split separa por ";"
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");

                if (partes.length < 4) continue; // proteção

                String tipo = partes[0];
                String descricao = partes[1];
                double valor = Double.parseDouble(partes[2]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(partes[3], formatter);

                if(tipo.equals("Receita")){
                    lista.add(new Receita(descricao, valor, data));
                } else {
                    lista.add(new Despesa(descricao, valor, data));
                }
            }

        }catch(IOException e){
            System.out.println("Erro ao carregar:");
            e.printStackTrace();
        }
        System.out.println("Usando arquivo em: " + CAMINHO);


    return lista;
    }
}