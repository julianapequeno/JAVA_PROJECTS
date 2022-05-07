/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dominio.Conta;
import dominio.Movimento;
import java.util.ArrayList;
import java.util.Scanner;
import persistencia.ContaDAO;
import persistencia.MovimentoDAO;

/**
 *
 * @author Juliana Santiago
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op, op2;
        double valorSaque, novoSaldo;
        String senha;
        int numConta;
        Conta c = new Conta();
        Movimento m = new Movimento();
        ContaDAO cDAO = new ContaDAO();
        MovimentoDAO mDAO = new MovimentoDAO();
        Scanner ler = new Scanner(System.in);
        Double valorDepositado;
        ArrayList<Movimento> listaDepositos;
        int num;
        do{
            c = new Conta();
            cDAO = new ContaDAO();
            System.out.println("Bem Vindo ao Banco Virtual!");
            System.out.println("----------------------------");
            System.out.println("Entre com os seus dados:");
            System.out.println("Número da conta:");
            numConta = ler.nextInt();
            c.setNum_conta(numConta);
            ler.nextLine();
            System.out.println("Senha:");
            senha = ler.nextLine();
            c.setSenha(senha);
            c = cDAO.buscar(c.getNum_conta());
            if(c == null){
                System.out.println("Cadastrando novos dados...");
                c = new Conta();
                c.setNum_conta(numConta);
                c.setSenha(senha);
                c.setSaldo(0);
                cDAO.incluir(c);
                //Volta para a tela 01
                System.out.println("Cadastro realizado com sucesso!");
                System.out.println("");
            }else {
                System.out.println("Acesso Permitido!");
                do{
                    System.out.println("      Menu Principal");
                    System.out.println("----------------------------");
                    System.out.println("1- Verificar Saldo");
                    System.out.println("2- Realizar Saque");
                    System.out.println("3- Realizar Depósito");
                    System.out.println("4- Relátorio de Depósitos");
                    System.out.println("5- Cancelar Conta");
                    System.out.println("6- Sair do Sistema");
                    op2 = ler.nextInt();
                    switch(op2){
                            case 1: System.out.println("Seu saldo é: ");
                                    c = cDAO.buscar(c.getNum_conta());
                                    System.out.println(c.getSaldo());
                                break;
                            case 2: System.out.println("Realizando saque...");
                                    System.out.println("Qual será o valor do saque?");
                                    valorSaque = ler.nextDouble();
                                    if(valorSaque <= c.getSaldo()){
                                        novoSaldo = c.getSaldo() - valorSaque;
                                        cDAO.saqueSaldo(novoSaldo, c.getNum_conta());
                                        System.out.println("Saque efetuado com sucesso!");
                                    }else if(valorSaque > c.getSaldo()){
                                        System.out.println("Valor não disponível!");
                                        break;
                                    }
                                break;
                            case 3: System.out.println("Realizando depósito na conta");
                                    System.out.println("Qual o valor que deseja depositar?");
                                    valorDepositado = ler.nextDouble();
                                    cDAO.incluirNovoSaldo(valorDepositado, c, c.getSaldo());
                                    m.setValor(valorDepositado); 
                                    mDAO.incluir(m, c.getNum_conta());
                                    System.out.println("Depósito realizado com sucesso!"); 
                                break;
                            case 4: System.out.println("Relatório de depósitos...");
                                    mDAO = new MovimentoDAO();
                                    listaDepositos = new ArrayList<>();
                                    listaDepositos = mDAO.relatorio(c.getNum_conta());
                                    for(int i = 0; i < listaDepositos.size(); i++){
                                        System.out.println("------------------------------------");
                                        System.out.println("Código: "+listaDepositos.get(i).getCod());
                                        System.out.println("Valor:"+listaDepositos.get(i).getValor());
                                        System.out.println("Número da conta:"+listaDepositos.get(i).getNum_conta());
                                    }
                                break;
                            case 5: System.out.println("Cancelando conta...");
                            
                                    System.out.println("Nome: "+c.getNum_conta());
                                    System.out.println("Saldo: "+c.getSaldo());
                                    System.out.println("Confirma a exclusão (1-sim, 2-não)? ");
                                        if(ler.nextInt()==1){
                                            mDAO.excluirMov(c.getNum_conta());
                                            cDAO.excluir(c.getNum_conta());
                                            System.out.println("Cancelamento efetuado com sucesso!");
                                        }
                                    op2 = 6;
                                break;
                            case 6: System.out.println("Sistema finalizado");
                                break;
                            default: System.out.println("opção inválida!");
                    }
                }while(op2!=6);
            }
            System.out.println("Deseja abrir novamente o menu?");
            System.out.println("1- SIM // 2- NÃO");
            num = ler.nextInt();
            if(num == 1)continue;
        }while(num!=2);
    }
}
        

