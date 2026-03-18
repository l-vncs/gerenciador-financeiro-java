package Main;

import Model.Despesa;
import Model.Receita;
import Service.FinanceiroService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args){
        //criacao dos scanners:
        Scanner scanner = new Scanner(System.in);
        //A classe FinanceiroService recebe service como uma maneira de repassar a classe FinanceiroService
        FinanceiroService service= new FinanceiroService();
        service.carregarDados();
        int opcao;
       //Opcao do Switch Case

        System.out.println("BEM VINDO AO SISTEMA DE CONTROLE DE FINANÇAS!");
        do{
            System.out.println("================================================");
            System.out.println("SELECIONE UMA OPCAO ABAIXO");
            System.out.println("1 - CADASTRAR RECEITA");
            System.out.println("2 - CADASTRAR DESPESA");
            System.out.println("3 - VERIFICAR SALDO");
            System.out.println("4 - LISTAR TUDO");
            System.out.println("0 - SAIR");
            System.out.println("================================================");
            opcao = scanner.nextInt();
            scanner.nextLine();
            //scanner.nextLine para limpar o buffer

        switch (opcao){
            case 1:

                System.out.println("Digite a descricao: ");
                String descricao = scanner.nextLine();

                System.out.println("Digite o valor da receita: ");

                double valor = scanner.nextDouble();
                scanner.nextLine();
                //Double nao consome buffer, portanto é preciso limpar manualmente

                System.out.println("Digite a data: ");
                String data = scanner.nextLine();

                //Receita passa o valor para "r" que recebe os atributos da Receita (descricao, valor e data)
                Receita r = new Receita(descricao, valor, data);
                if (valor <= 0) {
                    System.out.println("Valor inválido!");
                    break;
                }
                service.adicionarReceita (r);
                service.salvarDados();
                // O service é puxado aqui para passar adicionar os valores de "r" para a classe FinanceiroService
                break;



            case 2:
                System.out.println("Digite a descricao: ");
                descricao = scanner.nextLine();

                System.out.println("Digite o valor da despesa: ");
                valor = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Digite a data: ");
                data = scanner.nextLine();

                //Mesma coisa aqui para a despesa
                Despesa d = new Despesa(descricao, valor, data);
                if (valor <= 0) {
                    System.out.println("Valor inválido!");
                    break;
                }
                service.adicionarDespesa(d);
                service.salvarDados();

                break;


            case 3:
                //Aqui puxamos o service novamente, dessa vez para retornar dois calculos (listar e calcular saldo)
                System.out.println("Verificando saldo...");
                System.out.printf("Seu Saldo é: R$ %.2f%n", service.calcularSaldo());
                break;

            case 4:
                System.out.println("Listando receitas, despesas e saldo: ");
                service.listarTudo();
                System.out.println("TOTAL: " + "R$ " + service.calcularSaldo());
                break;

            case 0:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opção Inválida");
        }


    }while (opcao!=0);
    }
}
