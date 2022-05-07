/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dominio.Conta;
import java.sql.SQLException;
/**
 *
 * @author Juliana Santiago
 */
public class ContaDAO {
    private Conexao con;
    private final String BUSCAR = "select * from \"conta\" where \"num_conta\" = ?";
    private final String INCLUSAO = "insert into \"conta\" values (?,?,?)";
    private final String incluirNovoSaldo = "update \"conta\" set \"saldo\"=? where \"num_conta\" = ?";
    private final String EXCLUIR = "delete from \"conta\" where \"num_conta\"=?";
    private final String SAQUE = "update \"conta\" set \"saldo\"=? where \"num_conta\"=?";
    public ContaDAO(){
        con = new Conexao("jdbc:postgresql://localhost:5432/BDBanco","postgres","postgres");
    }
    
    public Conta buscar(int num_conta){
        Conta c = null;
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(BUSCAR);
            instrucao.setInt(1,num_conta);
            ResultSet rs = instrucao.executeQuery();
            if(rs.next()){
                c = new Conta(rs.getInt("num_conta"), rs.getString("senha"), rs.getDouble("saldo"));
            }
            con.desconectar();
        }catch(SQLException e){
            System.out.println("Erro na busca: "+e.getMessage());
        }
        return c;
    }
    
    public void incluir(Conta c){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(INCLUSAO);
            instrucao.setInt(1, c.getNum_conta());
            instrucao.setString(2, c.getSenha());
            instrucao.setDouble(3, c.getSaldo());
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão: "+e.getMessage());
        }
    }
    
    public void incluirNovoSaldo(double novoSaldo, Conta c, double saldoAntigo){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(incluirNovoSaldo);
            instrucao.setDouble(1, novoSaldo+saldoAntigo);
            instrucao.setInt(2,c.getNum_conta());
            instrucao.executeQuery();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão do novo saldo: "+e.getMessage());
        }
    }
    
    public void excluir(int num_conta){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(EXCLUIR);
            instrucao.setInt(1, num_conta);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na Exclusão: "+e.getMessage());
        }
    }

    public void saqueSaldo(double valorSaque, int num_conta){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(SAQUE);
            instrucao.setDouble(1,valorSaque);
            instrucao.setInt(2,num_conta);
            instrucao.executeQuery();
            con.desconectar();
        }catch(Exception e){
             System.out.println("Erro na busca: "+e.getMessage());
        }
    }
}
