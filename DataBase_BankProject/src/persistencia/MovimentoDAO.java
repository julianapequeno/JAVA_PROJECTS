/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import dominio.Conta;
import dominio.Movimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juliana Santiago
 */
public class MovimentoDAO {
    private final String INCLUIR = "insert into \"movimento\" (\"cod\", \"valor\",\"num_conta\") values (?,?,?)";
    private final String RELATORIO = "select * from \"movimento\" where \"num_conta\"=?";
    private final String EXCLUIR = "delete * from \"movimento\" where \"num_conta\" = ?";
    private Conexao con;
    
    public MovimentoDAO(){
        
    }
    
    public void incluir(Movimento m, int num_conta){
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(INCLUIR);
            instrucao.setDouble(1, m.getCod());
            instrucao.setDouble(2,m.getValor());
            instrucao.setInt(3, num_conta);
            instrucao.execute();
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro na inclusão: "+e.getMessage());
        }
    }
    
    public ArrayList<Movimento> relatorio(int num_conta){
        ArrayList<Movimento> listaDepositos = new ArrayList<>();
        Movimento m = null;
        try{
            con.conectar();
            PreparedStatement instrucao = con.getConexao().prepareStatement(RELATORIO);
            instrucao.setInt(1,num_conta);
            ResultSet rs = instrucao.executeQuery();
            while(rs.next()){
                m = new Movimento(rs.getInt("cod"), rs.getDouble("valor"), rs.getInt("num_conta"));
                listaDepositos.add(m);
            }
            con.desconectar();
        }catch(Exception e){
            System.out.println("Erro no relatório: "+e.getMessage());
        }
        return listaDepositos;
        
    }
    
    public void excluirMov(int num_conta){
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
    
    
}
