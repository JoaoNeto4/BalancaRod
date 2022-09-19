
package br.com.dao;

import br.com.bean.Permissao;
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


public class PermissaoDao {
    
    public static void inserir(Permissao pm)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Permissao( nivel, observacao) VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pm.getNivel());
            stmt.setString(2, pm.getObservacao());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Permissão Salva Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PermissaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(Permissao pm)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_Permissao where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pm.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Permissão Excluida Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PermissaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void alterar(Permissao pm)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Permissao SET nivel=?, observaco=? WHERE id= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pm.getNivel());
            stmt.setString(2, pm.getObservacao());
            stmt.setInt(3, pm.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Permissão Alterada Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PermissaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Permissao> listar() throws SQLException {
        try {
            List<Permissao> listaPermissao = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Permissao order by ID";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permissao per = new Permissao();
                per.setId(rs.getInt("ID"));
                per.setNivel(rs.getString("nivel"));
                per.setObservacao(rs.getString("observacao"));
                
                listaPermissao.add(per);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaPermissao;
        } catch (Exception ex) {
            Logger.getLogger(PermissaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    public static void main(String[] args) throws SQLException {
        PermissaoDao.listar();
    }
     
     public static List<Permissao> pesquisar(Permissao permissao) throws SQLException {
        try {
            List<Permissao> listaControle = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Permissao where nivel like'"+permissao.getNivel()+"%' order by nivel";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permissao c = new Permissao();
                c.setId(rs.getInt("ID"));
                c.setNivel(rs.getString("nivel"));
                c.setObservacao(rs.getString("observacao"));
                listaControle.add(c);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaControle;
        } catch (Exception ex) {
            Logger.getLogger(PermissaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     
    
}
