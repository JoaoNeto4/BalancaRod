
package br.com.dao;

import br.com.bean.Operador;
import br.com.bean.Permissao;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class OperadorDao {
    
    public static void inserir(Operador op)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Operador( ID_Permissao, nome, senha, funcao, observacoes) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, op.getPermissao().getId());
        stmt.setString(2, op.getNome());
        stmt.setString(3, op.getSenha());
        stmt.setString(4, op.getFuncao());
        stmt.setString(5, op.getObservacao());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Operador Salvo Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Operador op)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Operador where ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, op.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Operador Excluido Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Operador op)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Operador SET ID_Permissao=?, nome=?, senha=?, funcao=?, observacoes=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, op.getPermissao().getId());
        stmt.setString(2, op.getNome());
        stmt.setString(3, op.getSenha());
        stmt.setString(4, op.getFuncao());
        stmt.setString(5, op.getObservacao());
        stmt.setInt(6, op.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Operador Alterada Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Operador> listar() throws SQLException {
        List<Operador> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Operador order by nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Operador op = new Operador();
            op.setId(rs.getInt("ID"));
            stmt.setInt(1, op.getPermissao().getId());
            stmt.setString(2, op.getNome());
            stmt.setString(3, op.getSenha());
            stmt.setString(4, op.getFuncao());
            stmt.setString(5, op.getObservacao());
            listaControle.add(op);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
     
     public static List<Operador> pesquisar(Operador op) throws SQLException {
        List<Operador> listaOperador = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select P.ID, P.nivel, P.observacao, OP.ID, OP.id, P.ativo from TB_Permissao as P inner join TB_Operador as OP on P.id_controle=OP.id where P.nome like'"+op.getNome()+"%' order by P.nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
            Permissao p = new Permissao();
            p.setId(rs.getInt("P.ID"));
            p.setNivel(rs.getString("P.nivel"));
            p.setObservacao(rs.getString("P.observacao"));
            
            Operador operador = new Operador();
            operador.setId(rs.getInt("OP.ID"));
            operador.setNome(rs.getString("OP.nome"));
            operador.setSenha(rs.getString("OP.senha"));
            operador.setFuncao(rs.getString("OP.funcao"));
            operador.setObservacao(rs.getString("OP.observacoes"));
            operador.setPermissao(p);
            listaOperador.add(operador);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaOperador;
     }

}
