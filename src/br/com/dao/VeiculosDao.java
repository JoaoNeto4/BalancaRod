
package br.com.dao;

import br.com.bean.Operador;
import br.com.bean.ParceiroNegocio;
import br.com.bean.Veiculos;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class VeiculosDao {
    
    public static void inserir(Veiculos vei)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Veiculos( ID_parceiro, marca, modelo, anoFabricacao, placa, tara, tipo, ativo, observacoes) VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, vei.getPn().getId());
        stmt.setString(2, vei.getMarca());
        stmt.setString(3, vei.getModelo());
        stmt.setInt(4, vei.getAno());
        stmt.setString(5, vei.getPlaca());
        stmt.setDouble(6, vei.getTara());
        stmt.setString(7, vei.getTipo());
        stmt.setBoolean(8, vei.getAtivo());
        stmt.setString(9, vei.getObservacoes());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Veículo Salvo Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Veiculos vei)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Veiculos where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, vei.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Veículo Excluido Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Veiculos vei)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Veiculos SET ID_parceiro=?, marca=?, modelo=?, anoFabricacao=?, placa=?, tara=?, tipo=?, ativo=?, observacoes=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, vei.getPn().getId());
        stmt.setString(2, vei.getMarca());
        stmt.setString(3, vei.getModelo());
        stmt.setInt(4, vei.getAno());
        stmt.setString(5, vei.getPlaca());
        stmt.setDouble(6, vei.getTara());
        stmt.setString(7, vei.getTipo());
        stmt.setBoolean(8, vei.getAtivo());
        stmt.setString(9, vei.getObservacoes());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Veículo Alterada Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Veiculos> listar() throws SQLException {
        List<Veiculos> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Veiculos order by modelo";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Veiculos vei = new Veiculos();
            vei.setId(rs.getInt("ID"));
            stmt.setInt(1, vei.getPn().getId());
            stmt.setString(2, vei.getMarca());
            stmt.setString(3, vei.getModelo());
            stmt.setInt(4, vei.getAno());
            stmt.setString(5, vei.getPlaca());
            stmt.setDouble(6, vei.getTara());
            stmt.setString(7, vei.getTipo());
            stmt.setBoolean(8, vei.getAtivo());
            stmt.setString(9, vei.getObservacoes());
            listaControle.add(vei);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
     
     public static List<Veiculos> pesquisarPlaca(Veiculos vei) throws SQLException {
        List<Veiculos> listaOperador = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select PN.*, V.* from TB_Veiculos as V  inner join TB_ParceiroNegocio as PN on V.ID_parceiro=PN.ID where V.placa like'"+vei.getPlaca()+"%' order by V.ID";
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
            
            Veiculos veiculos = new Veiculos();
            veiculos.setId(rs.getInt("V.ID"));
            veiculos.setMarca(rs.getString("V.marca"));
            veiculos.setModelo(rs.getString("V.modelo"));
            veiculos.setAno(rs.getInt("V.anoFabricacao"));
            veiculos.setPlaca(rs.getString("V.placa"));
            veiculos.setTara(rs.getDouble("V.tara"));
            veiculos.setTipo(rs.getString("V.tipo"));
            veiculos.setAtivo(rs.getBoolean("V.ativo"));
            veiculos.setObservacoes(rs.getString("V.observacoes"));
            veiculos.setPn(p);
            listaOperador.add(veiculos);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaOperador;
     }
     
     public static List<Veiculos> pesquisarParceiroNegocio(Veiculos vei) throws SQLException {
        List<Veiculos> listaOperador = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select PN.*, V.* from TB_Veiculos as V  inner join TB_ParceiroNegocio as PN on V.ID_parceiro=PN.ID where PN.fantasia like'"+vei.getPn().getFantasia()+"%' ";
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
            
            Veiculos veiculos = new Veiculos();
            veiculos.setId(rs.getInt("V.ID"));
            veiculos.setMarca(rs.getString("V.marca"));
            veiculos.setModelo(rs.getString("V.modelo"));
            veiculos.setAno(rs.getInt("V.anoFabricacao"));
            veiculos.setPlaca(rs.getString("V.placa"));
            veiculos.setTara(rs.getDouble("V.tara"));
            veiculos.setTipo(rs.getString("V.tipo"));
            veiculos.setAtivo(rs.getBoolean("V.ativo"));
            veiculos.setObservacoes(rs.getString("V.observacoes"));
            veiculos.setPn(p);
            listaOperador.add(veiculos);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaOperador;
     }
     
}
