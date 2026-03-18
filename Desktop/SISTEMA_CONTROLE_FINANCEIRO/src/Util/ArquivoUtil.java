package Util;
import Model.Despesa;
import Model.Receita;
import Model.Transacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;


public class ArquivoUtil {
    /*Funcao de salvamento, por meio da lista Transacao, o filewriter pega as informações
    e transforma em um arquivo .txt*/
    public static void salvar (List <Transacao> Transacoes){
        try(FileWriter writer = new FileWriter("src/Main/ teste_dados.txt")){
            for (Transacao t: Transacoes ){
                //por meio deste loop o sistema pega o t como receita e else passa como despesa
                String tipo = (t instanceof Receita) ? "Receita" : "Despesa";
                writer.write(tipo + ";"+
                        t.getDescricao() + ";" +
                        t.getValor() + ";" +
                        t.getData() + "\n");
                // apos isso ele define tipo de transacao, descricao, valor e data, todos juntos separados por ";"

            }
            System.out.println("SALVANDO DADOS...");

        } catch (IOException e) {
            System.out.println("Erro ao salvar!");
        }
        // a exception é necessaria para caso nao seja possivel salvar

    }
    public static List<Transacao> carregar(){
        List <Transacao> lista = new ArrayList<>();
        //criamos uma nova arraylist dentro de "Transacao" para carregar as informações listadas
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Main/ teste_dados.txt"))){

            String linha;
        //definimos as informacoes em linha e após isso o .split separa por ";"
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(";");
                String tipo = partes [0];
                String descricao = partes [1];
                double valor = Double.parseDouble(partes[2]); //Double.parseDouble para transformar double em string e passar para o vetor
                String data = partes [3];
                if(tipo.equals("Receita")){
                    lista.add(new Receita(descricao, valor, data));
                }
                else{
                    lista.add(new Despesa(descricao, valor, data));
                }


            }

        }catch(IOException e){
            System.out.println("Erro ao carregar, arquivo não enccontrado ou não existente!");
        }


    return lista;
    }
}