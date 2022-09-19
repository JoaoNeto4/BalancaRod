
package br.com.dao;

import br.com.bean.ParceiroNegocio;
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

public class ParceiroNegocioDao {
    
    public static void inserir(ParceiroNegocio pn)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_ParceiroNegocio( fantasia, razaoSocial, cpf_cnpj, estado, cidade, bairro, rua, numero, telefone, celular, email, emailAlt, Observacoes) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pn.getFantasia());
            stmt.setString(2, pn.getRazaoSocial());
            stmt.setString(3, pn.getCpf_cnpj());
            stmt.setString(4, pn.getEstado());
            stmt.setString(5, pn.getCidade());
            stmt.setString(6, pn.getBairro());
            stmt.setString(7, pn.getRua());
            stmt.setInt(8, pn.getNumero());
            stmt.setString(9, pn.getTelefone());
            stmt.setString(10, pn.getCelular());
            stmt.setString(11, pn.getEmail());
            stmt.setString(12, pn.getEmailAlt());
            stmt.setString(13, pn.getEmailAlt());
            stmt.setString(14, pn.getObservacao());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Parceiro de Negócio Salvo Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ParceiroNegocioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(ParceiroNegocio pn)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_ParceiroNegocio where ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pn.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Parceiro de Negócio Excluído Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ParceiroNegocioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void alterar(ParceiroNegocio pn)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_ParceiroNegocio SET fantasia=?, razaoSocial=?, cpf_cnpj=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, telefone=?, celular=?, email=?, emailAlt=?, Observacoes=? WHERE ID= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pn.getFantasia());
            stmt.setString(2, pn.getRazaoSocial());
            stmt.setString(3, pn.getCpf_cnpj());
            stmt.setString(4, pn.getEstado());
            stmt.setString(5, pn.getCidade());
            stmt.setString(6, pn.getBairro());
            stmt.setString(7, pn.getRua());
            stmt.setInt(8, pn.getNumero());
            stmt.setString(9, pn.getTelefone());
            stmt.setString(10, pn.getCelular());
            stmt.setString(11, pn.getEmail());
            stmt.setString(12, pn.getEmailAlt());
            stmt.setString(13, pn.getEmailAlt());
            stmt.setString(14, pn.getObservacao());
            stmt.setInt(15, pn.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Parceiro de Negócio Alterado Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ParceiroNegocioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<ParceiroNegocio> listar() throws SQLException {
        try {
            List<ParceiroNegocio> listaControle = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_ParceiroNegocio order by fantasia";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ParceiroNegocio pn = new ParceiroNegocio();
                pn.setId(rs.getInt("ID"));
                stmt.setInt(1, pn.getId());
                stmt.setString(1, pn.getFantasia());
                stmt.setString(2, pn.getRazaoSocial());
                stmt.setString(3, pn.getCpf_cnpj());
                stmt.setString(4, pn.getEstado());
                stmt.setString(5, pn.getCidade());
                stmt.setString(6, pn.getBairro());
                stmt.setString(7, pn.getRua());
                stmt.setInt(8, pn.getNumero());
                stmt.setString(9, pn.getTelefone());
                stmt.setString(10,pn.getCelular());
                stmt.setString(11, pn.getEmail());
                stmt.setString(12, pn.getEmailAlt());
                stmt.setString(13, pn.getEmailAlt());
                stmt.setString(14, pn.getObservacao());
                listaControle.add(pn);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaControle;
        } catch (Exception ex) {
            Logger.getLogger(ParceiroNegocioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    public static List<ParceiroNegocio> pesquisar(ParceiroNegocio pn) throws SQLException {
        try {
            List<ParceiroNegocio> listaParceiro = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select PN.* from TB_ParceiroNegocio as PN where PN.fantasia like'"+pn.getFantasia()+"%' order by PN.fantasia";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                ParceiroNegocio p = new ParceiroNegocio();
                p.setId(rs.getInt("PN.ID"));
                p.setFantasia(rs.getString("PN.fantasia"));
                p.setRazaoSocial(rs.getString("PN.razaoSocial"));
                p.setCpf_cnpj(rs.getString("PN.cpf_cnpj"));
                p.setEstado(rs.getString("PN.estado"));
                p.setCidade(rs.getString("PN.cidade"));
                p.setBairro(rs.getString("PN.bairro"));
                p.setRua(rs.getString("PN.rua"));
                p.setNumero(rs.getInt("PN.numero"));
                p.setTelefone(rs.getString("PN.telefone"));
                p.setTelefoneAlt(rs.getString("PN.telefoneAlt"));
                p.setCelular(rs.getString("PN.celular"));
                p.setEmail(rs.getString("PN.email"));
                p.setEmailAlt(rs.getString("PN.emailAlt"));
                p.setObservacao(rs.getString("PN.Observacoes"));
                listaParceiro.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            return listaParceiro;
        } catch (Exception ex) {
            Logger.getLogger(ParceiroNegocioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
}
