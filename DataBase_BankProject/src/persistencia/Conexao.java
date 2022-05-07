/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Juliana Santiago
 */
public class Conexao {
    private String caminho;
    private String usuario;
    private String senha;
    private Connection minhaCon;
    
    public Conexao(String c, String u, String s){
        caminho = c;
        usuario = u;
        senha = s;
    }
    
    public void conectar(){
        try{
            Class.forName("org.postgresql.Driver");
            minhaCon = DriverManager.getConnection(caminho, usuario, senha);
        }catch(Exception e){
            System.out.println("Erro na conexao: "+e.getMessage());
        }
    }
    public void desconectar(){
        try{
            minhaCon.close();
        }catch(Exception e){
            System.out.println("Erro na desconexao: "+e.getMessage());
        }
    }
    public Connection getConexao(){
        return minhaCon;
    }
}

