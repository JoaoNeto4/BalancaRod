
package br.com.dao;

import br.com.bean.Configuracoes;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class ConfiguracoesDao {
    
    public static void inserir(Configuracoes conf)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Configuracoes( envia_fotos, envia_email, pesagem_auto) VALUES(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, conf.isEnviaFotos());
            stmt.setBoolean(2, conf.isEnviaEmail());
            stmt.setBoolean(3, conf.isPesaAuto());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Configuração Salvo Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(Configuracoes conf)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_Configuracoes where ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, conf.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Configuração Excluida Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void alterar(Configuracoes conf)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Configuracoes SET envia_fotos=?, envia_email=?, pesagem_auto=? WHERE ID= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, conf.isEnviaFotos());
            stmt.setBoolean(2, conf.isEnviaEmail());
            stmt.setBoolean(3, conf.isPesaAuto());
            stmt.setInt(4, conf.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Configuração Alterada Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Configuracoes retornaInfoConfig(int ID) throws SQLException {
        try {
            
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Configuracoes where ID="+ID+"";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Configuracoes conf = new Configuracoes();
            if(rs.next()) {
                
                conf.setId(rs.getInt("ID"));
                conf.setEnviaFotos(rs.getBoolean("envia_fotos"));
                conf.setEnviaEmail(rs.getBoolean("envia_email"));
                conf.setPesaAuto(rs.getBoolean("pesagem_auto"));
                
                stmt.close();
                rs.close();
                con.close();
                return conf;
                
            }else{
                stmt.close();
                rs.close();
                con.close();
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
}
