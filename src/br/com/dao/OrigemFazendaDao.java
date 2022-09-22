
package br.com.dao;

import br.com.bean.OrigemFazenda;
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

/**
 *
 * @author joao
 */
public class OrigemFazendaDao {
    
    public static void inserir(OrigemFazenda orig)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_OrigemFazenda( ID_parceiro, origem, localizacao, cidade, estador, observacao) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,  orig.getParceiro().getId());
            stmt.setString(2, orig.getOrigem());
            stmt.setString(3, orig.getLocalizacao());
            stmt.setString(4, orig.getCidade());
            stmt.setString(5, orig.getEstado());
            stmt.setString(6, orig.getObservacao());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Origem Salva Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public static void excluir(OrigemFazenda orig)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_OrigemFazenda where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orig.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Origem Excluida Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    public static void alterar(OrigemFazenda orig)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_OrigemFazenda SET ID_parceiro=?, origem=?, localizacao=?, cidade=?, estador=?, observacao=? WHERE ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orig.getParceiro().getId());
            stmt.setString(2, orig.getOrigem());
            stmt.setString(3, orig.getLocalizacao());
            stmt.setString(4, orig.getCidade());
            stmt.setString(5, orig.getEstado());
            stmt.setString(6, orig.getObservacao());
            stmt.setInt(7, orig.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Origem Alterado Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<OrigemFazenda> listar() throws SQLException {
        try {
            List<OrigemFazenda> listaProdutos = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_OrigemFazenda order by produto";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrigemFazenda orig = new OrigemFazenda();
                orig.setId(rs.getInt("ID"));
                stmt.setInt(1, orig.getParceiro().getId());
                stmt.setString(2, orig.getOrigem());
                stmt.setString(3, orig.getLocalizacao());
                stmt.setString(4, orig.getCidade());
                stmt.setString(5, orig.getEstado());
                stmt.setString(6, orig.getObservacao());
                listaProdutos.add(orig);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaProdutos;
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }

     public static List<OrigemFazenda> pesquisar(OrigemFazenda orig) throws SQLException {
        try {
            List<OrigemFazenda> listaOperador = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select OF.*, P.* from TB_OrigemFazenda as OF inner join TB_ParceiroNegocio as P on OF.ID_parceiro=P.ID where OF.origem like'"+orig.getOrigem()+"%' order by ID";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ParceiroNegocio pn = new ParceiroNegocio();
                pn.setId(rs.getInt("P.ID"));
                pn.setFantasia(rs.getString("P.fantasia"));
                pn.setObservacao(rs.getString("P.cpf_cnpj"));
                
                OrigemFazenda origem = new OrigemFazenda();
                origem.setId(rs.getInt("P.ID"));
                origem.setOrigem(rs.getString("P.origem"));
                origem.setLocalizacao(rs.getString("P.localizacao"));
                origem.setCidade(rs.getString("P.cidade"));
                origem.setEstado(rs.getString("P.estado"));
                origem.setObservacao(rs.getString("P.observacao"));
                origem.setParceiro(pn);
                listaOperador.add(origem);
                
            }
            stmt.close();
            rs.close();
            con.close();
            return listaOperador;
        } catch (Exception ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }

    
}
