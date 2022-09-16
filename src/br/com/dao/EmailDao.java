
package br.com.dao;

import br.com.bean.Email;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class EmailDao {
    
    public static void inserir(Email em)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Email(email, senha) VALUES(?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, em.getEmail());
        stmt.setString(2, em.getSenha());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Email Salvo Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Email em)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Email where ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, em.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Email Excluido Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Email em)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Email SET email=?, senha=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, em.getEmail());
        stmt.setString(2, em.getSenha());
        stmt.setInt(7, em.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Operador Alterada Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Email> listar() throws SQLException {
        List<Email> listaEmail = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Email order by ID";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Email em = new Email();
            em.setId(rs.getInt("ID"));
            stmt.setString(2, em.getEmail());
            stmt.setString(3, em.getSenha());
            listaEmail.add(em);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaEmail;
     }
}
