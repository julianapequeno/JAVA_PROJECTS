/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Juliana Santiago
 */
public class Movimento {
    private int cod;
    private double valor;
    private int num_conta;

    public Movimento(){
        
    }
    
    public Movimento(int cod,double valor, int numC){
        this.valor = valor;
        num_conta = numC;
        setCod(this.cod++);
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }
    
    
}
