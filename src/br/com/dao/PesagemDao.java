
package br.com.dao;

import br.com.bean.Operador;
import br.com.bean.ParceiroNegocio;
import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.Veiculos;
import br.com.conexao.Conexao;
import com.sun.xml.internal.bind.v2.TODO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class PesagemDao {
    
    public static void inserir(Pesagem ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Pesagem( ID_parceiro, ID_veiculo, ID_operador, dataHora, tipoPesagem, andamento, nfe, lote, origem, destino, pesoEnt1, pesoEnt2, pesoSai1, pesoSai2, motorista, observacao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ps.getPn().getId());
        stmt.setInt(2, ps.getVeiculo().getId());
        stmt.setInt(3, ps.getOperador().getId());
        stmt.setTime(4, (Time) ps.getDataHora());
        stmt.setString(5, ps.getTipoPesagem());
        stmt.setBoolean(6, ps.getAndamento());
        stmt.setString(7, ps.getNfe());
        stmt.setString(8, ps.getLote());
        stmt.setString(9, ps.getOrigem());
        stmt.setString(10, ps.getDestino());
        stmt.setDouble(11, ps.getPesoEnt1());
        stmt.setDouble(12, ps.getPesoEnt2());
        stmt.setDouble(13, ps.getPesoSai1());
        stmt.setDouble(14, ps.getPesoSai2());
        stmt.setString(15, ps.getMotorista());
        stmt.setString(16, ps.getObservacao());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Pesagem Salva Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Pesagem ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Pesagem where ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ps.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Pesagem Excluida Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Pesagem ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Pesagem SET ID_parceiro=?, ID_veiculo=?, ID_operador=?, dataHora=?, tipoPesagem=?, andamento=?, nfe=?, lote=?, origem=?, destino=?, pesoEnt1=?, pesoEnt2=?, pesoSai1=?, pesoSai2=?, motorista=?, observacao=? WHERE ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ps.getPn().getId());
        stmt.setInt(2, ps.getVeiculo().getId());
        stmt.setInt(3, ps.getOperador().getId());
        stmt.setTime(4, (Time) ps.getDataHora());
        stmt.setString(5, ps.getTipoPesagem());
        stmt.setBoolean(6, ps.getAndamento());
        stmt.setString(7, ps.getLote());
        stmt.setString(8, ps.getOrigem());
        stmt.setString(9, ps.getDestino());
        stmt.setDouble(10, ps.getPesoEnt1());
        stmt.setDouble(11, ps.getPesoEnt2());
        stmt.setDouble(12, ps.getPesoSai1());
        stmt.setDouble(13, ps.getPesoSai2());
        stmt.setString(14, ps.getMotorista());
        stmt.setString(15, ps.getObservacao());
        stmt.setInt(16, ps.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Pesagem Alterada Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    //TODO: REVER ESSE METODO
    public static List<Pesagem> listar() throws SQLException {
        List<Pesagem> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Pesagem order by ID";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pesagem ps = new Pesagem();
            ps.setId(rs.getInt("ID"));
            stmt.setInt(1, ps.getPn().getId());
            stmt.setInt(2, ps.getVeiculo().getId());
            stmt.setInt(3, ps.getOperador().getId());
            stmt.setTime(4, (Time) ps.getDataHora());
            stmt.setString(5, ps.getTipoPesagem());
            stmt.setBoolean(5, ps.getAndamento());
            stmt.setString(5, ps.getNfe());
            stmt.setString(5, ps.getLote());
            stmt.setString(5, ps.getOrigem());
            stmt.setString(5, ps.getDestino());
            stmt.setDouble(5, ps.getPesoEnt1());
            stmt.setDouble(5, ps.getPesoEnt2());
            stmt.setDouble(5, ps.getPesoSai1());
            stmt.setDouble(5, ps.getPesoSai2());
            stmt.setString(5, ps.getMotorista());
            stmt.setString(5, ps.getObservacao());
            
            listaControle.add(ps);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
     

    //TODO: REVER ESTE METODO
     public static List<Pesagem> pesquisar(Pesagem pes) throws SQLException {
        List<Pesagem> listaPesagem = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select PE.*, PN.*, VE.*, OP.*, PR.* from TB_Pesagem as PE inner join TB_ParceiroNegocio as PN on PN.ID=PE.ID_parceiro inner join TB_Veiculos as VE on PN.ID=VE.ID_parceiro  inner join TB_Produto as PR on PE.ID_produto=PR.ID inner join TB_Operador as OP on OP.ID=PE.ID_operador where VE.placa like'"+pes.getVeiculo().getPlaca()+"%' order by pes.getVeiculo().getPlaca()";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
            Produtos prod = new Produtos();
            prod.setId(rs.getInt("PR.ID"));
            prod.setProduto(rs.getString("PR.produto"));
            prod.setUnidMed(rs.getString("PR.unidMed"));
            prod.setObservacoes(rs.getString("PR.observacao"));
            
            
            ParceiroNegocio pn = new ParceiroNegocio();
            pn.setId(rs.getInt("PN.ID"));
            pn.setFantasia(rs.getString("PN.fantasia"));
            pn.setRazaoSocial(rs.getString("PN.razaoSocial"));
            pn.setCpf_cnpj(rs.getString("PN.cpf_cnpj"));
            
            Veiculos ve = new Veiculos();
            ve.setId(rs.getInt("VE.ID"));
            ve.setModelo(rs.getString("VE.modelo"));
            ve.setPlaca(rs.getString("VE.placa"));
            ve.setTara(rs.getDouble("VE.tara"));
            ve.setPn(pn);
            
            Operador op = new Operador();
            op.setId(rs.getInt("OP.ID"));
            op.setNome(rs.getString("OP.nome"));
            
            
            Pesagem p = new Pesagem();
            p.setId(rs.getInt("PE.ID"));
            p.setDataHora(rs.getDate("PE.dataHora"));
            p.setTipoPesagem(rs.getString("PE.tipoPesagem"));
            p.setAndamento(rs.getBoolean("PE.andamento"));
            p.setNfe(rs.getString("PE.nfe"));
            p.setLote(rs.getString("PE.lote"));
            p.setOrigem(rs.getString("PE.origem"));
            p.setDestino(rs.getString("PE.destino"));
            p.setPesoEnt1(rs.getDouble("PE.pesoEnt1"));
            p.setPesoEnt2(rs.getDouble("PE.pesoEnt2"));
            p.setPesoSai1(rs.getDouble("PE."));
            p.setPesoSai2(rs.getDouble("PE.pesoSai1"));
            p.setMotorista(rs.getString("PE.pesoSai2"));
            p.setObservacao(rs.getString("PE.motorista"));
            p.setObservacao(rs.getString("PE.observacao"));
            p.setPn(pn);
            p.setVeiculo(ve);
            p.setOperador(op);
            listaPesagem.add(p);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaPesagem;
     }
     
}
