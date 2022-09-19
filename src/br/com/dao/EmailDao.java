
package br.com.dao;

import br.com.bean.Email;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class EmailDao {
    
    public static void inserir(Email em)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Email(email, senha, servidor, porta, seguranca, ativo) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, em.getEmail());
            stmt.setString(2, em.getSenha());
            stmt.setString(3, em.getSeguranca());
            stmt.setInt(4, em.getPorta());
            stmt.setString(5, em.getSeguranca());
            stmt.setBoolean(6, em.isAtivo());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Email Salvo Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(Email em)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_Email where ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, em.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Email Excluido Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void alterar(Email em)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Email SET email=?, senha=?, servidor=?, porta=?, seguranca=?, ativo=? WHERE ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, em.getEmail());
            stmt.setString(2, em.getSenha());
            stmt.setString(3, em.getServidor());
            stmt.setInt(4, em.getPorta());
            stmt.setString(5, em.getSeguranca());
            stmt.setBoolean(6, em.isAtivo());
            stmt.setInt(7, em.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Email Alterado Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Email> listar() throws SQLException {
        try {
            List<Email> listaEmail = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Email";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Email em = new Email();
                em.setId(rs.getInt("ID"));
                em.setEmail(rs.getString("email"));
                em.setSenha(rs.getString("senha"));
                em.setServidor(rs.getString("servidor"));
                em.setPorta(rs.getInt("porta"));
                em.setSeguranca(rs.getString("seguranca"));
                em.setAtivo(rs.getBoolean("ativo"));
                listaEmail.add(em);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaEmail;
        } catch (Exception ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
}
