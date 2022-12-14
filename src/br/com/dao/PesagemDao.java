
package br.com.dao;

import br.com.bean.Operador;
import br.com.bean.ParceiroNegocio;
import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.Veiculos;
import br.com.conexao.Conexao;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;


public class PesagemDao {
    
    public static void inserir(Pesagem ps)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Pesagem( ID_parceiro, ID_veiculo, ID_operador, ID_produto, ID_transportador, dataHoraEntrada, dataHoraSaida, tipoPesagem, andamento, nfe, valorNfe, pesoNfe, lote, origem, destino, pesoEnt1, pesoEnt2, pesoSai1, pesoSai2, motorista, foto1, foto2, fotoEntrada, fotoSaida, observacao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ps.getPn().getId());
            stmt.setInt(2, ps.getVeiculo().getId());
            stmt.setInt(3, ps.getOperador().getId());
            stmt.setInt(4, ps.getProduto().getId());
            stmt.setInt(5, ps.getTransportador().getId());
            
            stmt.setString(6, ps.getDataHoraEtrada());
            stmt.setString(7, ps.getDataHoraSaida());
            
            stmt.setString(8, ps.getTipoPesagem());
            stmt.setBoolean(9, ps.getAndamento());
            stmt.setString(10, ps.getNfe());
            stmt.setDouble(11, ps.getValorNfe());
            stmt.setDouble(12, ps.getPesoNfe());
            stmt.setString(13, ps.getLote());
            stmt.setString(14, ps.getOrigem());
            stmt.setString(15, ps.getDestino());
            stmt.setDouble(16, ps.getPesoEnt1());
            stmt.setDouble(17, ps.getPesoEnt2());
            stmt.setDouble(18, ps.getPesoSai1());
            stmt.setDouble(19, ps.getPesoSai2());
            stmt.setString(20, ps.getMotorista());
            stmt.setString(21, ps.getFotoCarga1());
            stmt.setString(22, ps.getFotoCarga2());
            stmt.setString(23, ps.getFotoEntrada());
            stmt.setString(24, ps.getFotoSaida());
            stmt.setString(25, ps.getObservacao());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Pesagem de Entrada Salva Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    //TODO: NAO VAI PRECISAR 
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
    */
    public static void alterar(Pesagem ps)throws SQLException{
        
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Pesagem SET ID_parceiro=?, ID_veiculo=?, ID_operador=?, ID_produto=?, ID_transportador=?, dataHoraEntrada=?, dataHoraSaida=?, tipoPesagem=?, andamento=?, nfe=?, valorNfe=?, pesoNfe=?, lote=?, origem=?, destino=?, pesoEnt1=?, pesoEnt2=?, pesoSai1=?, pesoSai2=?, motorista=?, foto1=?, foto2=?, fotoEntrada=?, fotoSaida=?, observacao=? WHERE ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ps.getPn().getId());
            stmt.setInt(2, ps.getVeiculo().getId());
            stmt.setInt(3, ps.getOperador().getId());
            stmt.setInt(4, ps.getProduto().getId());
            stmt.setInt(5, ps.getTransportador().getId());
            stmt.setString(6, ps.getDataHoraEtrada());
            stmt.setString(7, ps.getDataHoraSaida());
            stmt.setString(8, ps.getTipoPesagem());
            stmt.setBoolean(9, ps.getAndamento());
            stmt.setString(10, ps.getNfe());
            stmt.setDouble(11, ps.getValorNfe());
            stmt.setDouble(12, ps.getPesoNfe());
            stmt.setString(13, ps.getLote());
            stmt.setString(14, ps.getOrigem());
            stmt.setString(15, ps.getDestino());
            stmt.setDouble(16, ps.getPesoEnt1());
            stmt.setDouble(17, ps.getPesoEnt2());
            stmt.setDouble(18, ps.getPesoSai1());
            stmt.setDouble(19, ps.getPesoSai2());
            stmt.setString(20, ps.getFotoCarga1());
            stmt.setString(21, ps.getFotoCarga2());
            stmt.setString(22, ps.getFotoEntrada());
            stmt.setString(23, ps.getFotoSaida());
            stmt.setString(24, ps.getMotorista());
            stmt.setString(25, ps.getObservacao());
            stmt.setInt(26, ps.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Pesagem de Sa??da Salva Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    //TODO: REVER ESSE METODO
    public static List<Pesagem> listar() throws SQLException {
        try {
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
                stmt.setInt(4, ps.getProduto().getId());
                stmt.setInt(5, ps.getTransportador().getId());
                stmt.setString(6, ps.getDataHoraEtrada());
                stmt.setString(7, ps.getDataHoraSaida());
                stmt.setString(8, ps.getTipoPesagem());
                stmt.setBoolean(9, ps.getAndamento());
                stmt.setString(10, ps.getNfe());
                stmt.setString(11, ps.getLote());
                stmt.setString(12, ps.getOrigem());
                stmt.setString(13, ps.getDestino());
                stmt.setDouble(14, ps.getValorNfe());
                stmt.setDouble(15, ps.getPesoNfe());
                stmt.setDouble(16, ps.getPesoEnt1());
                stmt.setDouble(17, ps.getPesoEnt2());
                stmt.setDouble(18, ps.getPesoSai1());
                stmt.setDouble(19, ps.getPesoSai2());
                stmt.setString(20, ps.getFotoCarga1());
                stmt.setString(21, ps.getFotoCarga2());
                stmt.setString(22, ps.getFotoEntrada());
                stmt.setString(23, ps.getFotoSaida());
                stmt.setString(24, ps.getMotorista());
                stmt.setString(25, ps.getObservacao());
                
                listaControle.add(ps);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaControle;
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     

    //TODO: REVER ESTE METODO
    public static List<Pesagem> pesquisar(Pesagem pes) throws SQLException {
        try {
            List<Pesagem> listaPesagem = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select PE.*, PN.*, VE.*, OP.*, PR.* from TB_Pesagem as PE inner join TB_ParceiroNegocio as PN on PN.ID=PE.ID_parceiro inner join TB_Veiculos as VE on PN.ID=VE.ID_parceiro  inner join TB_Produto as PR on PE.ID_produto=PR.ID inner join TB_Operador as OP on OP.ID=PE.ID_operador where VE.placa like'"+pes.getVeiculo().getPlaca()+"%' order by PE.ID";
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
                
                ParceiroNegocio transp = new ParceiroNegocio();
                transp.setId(rs.getInt("PN.ID"));
                transp.setFantasia(rs.getString("PN.fantasia"));
                transp.setRazaoSocial(rs.getString("PN.razaoSocial"));
                transp.setCpf_cnpj(rs.getString("PN.cpf_cnpj"));
                
                
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
                p.setDataHoraEtrada(rs.getString("PE.dataHoraEntrada"));
                p.setDataHoraEtrada(rs.getString("PE.dataHoraSaida"));
                p.setTipoPesagem(rs.getString("PE.tipoPesagem"));
                p.setAndamento(rs.getBoolean("PE.andamento"));
                p.setNfe(rs.getString("PE.nfe"));
                
                p.setValorNfe(rs.getDouble("PE.valorNfe"));
                p.setPesoNfe(rs.getDouble("PE.pesoNfe"));
                p.setLote(rs.getString("PE.lote"));
                
                p.setOrigem(rs.getString("PE.origem"));
                p.setDestino(rs.getString("PE.destino"));
                p.setPesoEnt1(rs.getDouble("PE.pesoEnt1"));
                p.setPesoEnt2(rs.getDouble("PE.pesoEnt2"));
                p.setPesoSai1(rs.getDouble("PE."));
                p.setPesoSai2(rs.getDouble("PE.pesoSai1"));
                p.setMotorista(rs.getString("PE.pesoSai2"));
                p.setObservacao(rs.getString("PE.motorista"));
                p.setFotoCarga1(rs.getString("PE.foto1"));
                p.setFotoCarga2(rs.getString("PE.foto2"));
                p.setFotoEntrada(rs.getString("PE.fotoEntrada"));
                p.setFotoSaida(rs.getString("PE.fotoSaida"));
                p.setObservacao(rs.getString("PE.observacao"));
                p.setPn(pn);
                p.setVeiculo(ve);
                p.setOperador(op);
                p.setProduto(prod);
                p.setTransportador(transp);
                listaPesagem.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            return listaPesagem;
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    public static List<Pesagem> pesquisarSaida(Pesagem pes) throws SQLException {
        try {
            List<Pesagem> listaPesagem = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select PE.*, PN.*, VE.*, OP.*, PR.* from TB_Pesagem as PE inner join TB_ParceiroNegocio as PN on PN.ID=PE.ID_parceiro inner join TB_Veiculos as VE on PN.ID=VE.ID_parceiro inner join TB_Produto as PR on PE.ID_produto=PR.ID inner join TB_Operador as OP on OP.ID=PE.ID_operador where VE.placa like'"+pes.getVeiculo().getPlaca()+"%' and PE.andamento=1 order by PE.ID";
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
                
                ParceiroNegocio transp = new ParceiroNegocio();
                transp.setId(rs.getInt("PN.ID"));
                transp.setFantasia(rs.getString("PN.fantasia"));
                transp.setRazaoSocial(rs.getString("PN.razaoSocial"));
                transp.setCpf_cnpj(rs.getString("PN.cpf_cnpj"));
                
                
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
                p.setDataHoraEtrada(rs.getString("PE.dataHoraEntrada"));
                p.setDataHoraSaida(rs.getString("PE.dataHoraSaida"));
                p.setTipoPesagem(rs.getString("PE.tipoPesagem"));
                p.setAndamento(rs.getBoolean("PE.andamento"));
                p.setNfe(rs.getString("PE.nfe"));
                
                p.setValorNfe(rs.getDouble("PE.valorNfe"));
                p.setPesoNfe(rs.getDouble("PE.pesoNfe"));
                p.setLote(rs.getString("PE.lote"));
                
                p.setOrigem(rs.getString("PE.origem"));
                p.setDestino(rs.getString("PE.destino"));
                p.setPesoEnt1(rs.getDouble("PE.pesoEnt1"));
                p.setPesoEnt2(rs.getDouble("PE.pesoEnt2"));
                p.setPesoSai1(rs.getDouble("PE.pesoSai1"));
                p.setPesoSai2(rs.getDouble("PE.pesoSai2"));
                p.setMotorista(rs.getString("PE.motorista"));
                p.setObservacao(rs.getString("PE.observacao"));
                p.setFotoCarga1(rs.getString("PE.foto1"));
                p.setFotoCarga2(rs.getString("PE.foto2"));
                p.setFotoEntrada(rs.getString("PE.fotoEntrada"));
                p.setFotoSaida(rs.getString("PE.fotoSaida"));
                p.setObservacao(rs.getString("PE.observacao"));
                p.setPn(pn);
                p.setVeiculo(ve);
                p.setOperador(op);
                p.setProduto(prod);
                p.setTransportador(transp);
                listaPesagem.add(p);
                
            }
            stmt.close();
            rs.close();
            con.close();
            return listaPesagem;
        } catch (Exception ex) {
            Logger.getLogger(PesagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /*
    public static Pesagem retornaPesagem(int pes) throws SQLException {
        try {
            
            
            Connection con = Conexao.getConexao();
            String sql = "select PE.*, PN.*, VE.*, OP.*, PR.* from TB_Pesagem as PE inner join TB_ParceiroNegocio as PN on PN.ID=PE.ID_parceiro inner join TB_Veiculos as VE on PN.ID=VE.ID_parceiro inner join TB_Produto as PR on PE.ID_produto=PR.ID inner join TB_Operador as OP on OP.ID=PE.ID_operador where PE.ID="+pes+"";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            Pesagem pesagem = new Pesagem();
            if(rs.next()) {

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
                
                ParceiroNegocio transp = new ParceiroNegocio();
                transp.setId(rs.getInt("PN.ID"));
                transp.setFantasia(rs.getString("PN.fantasia"));
                transp.setRazaoSocial(rs.getString("PN.razaoSocial"));
                transp.setCpf_cnpj(rs.getString("PN.cpf_cnpj"));
                
                
                Veiculos ve = new Veiculos();
                ve.setId(rs.getInt("VE.ID"));
                ve.setModelo(rs.getString("VE.modelo"));
                ve.setPlaca(rs.getString("VE.placa"));
                ve.setTara(rs.getDouble("VE.tara"));
                ve.setPn(pn);
                
                Operador op = new Operador();
                op.setId(rs.getInt("OP.ID"));
                op.setNome(rs.getString("OP.nome"));
                
                
                
                pesagem.setId(rs.getInt("PE.ID"));
                pesagem.setDataHoraEtrada(rs.getString("PE.dataHoraEntrada"));
                pesagem.setDataHoraSaida(rs.getString("PE.dataHoraSaida"));
                pesagem.setTipoPesagem(rs.getString("PE.tipoPesagem"));
                pesagem.setAndamento(rs.getBoolean("PE.andamento"));
                pesagem.setNfe(rs.getString("PE.nfe"));
                
                pesagem.setValorNfe(rs.getDouble("PE.nfe"));
                pesagem.setPesoNfe(rs.getDouble("PE.pesoNfe"));
                pesagem.setLote(rs.getString("PE.lote"));
                
                pesagem.setOrigem(rs.getString("PE.origem"));
                pesagem.setDestino(rs.getString("PE.destino"));
                pesagem.setPesoEnt1(rs.getDouble("PE.pesoEnt1"));
                pesagem.setPesoEnt2(rs.getDouble("PE.pesoEnt2"));
                pesagem.setPesoSai1(rs.getDouble("PE.pesoSai1"));
                pesagem.setPesoSai2(rs.getDouble("PE.pesoSai2"));
                pesagem.setMotorista(rs.getString("PE.motorista"));
                pesagem.setObservacao(rs.getString("PE.observacao"));
                pesagem.setFotoCarga1(rs.getString("PE.foto1"));
                pesagem.setFotoCarga2(rs.getString("PE.foto2"));
                pesagem.setFotoEntrada(rs.getString("PE.fotoEntrada"));
                pesagem.setFotoSaida(rs.getString("PE.fotoSaida"));
                pesagem.setObservacao(rs.getString("PE.observacao"));
                pesagem.setPn(pn);
                pesagem.setVeiculo(ve);
                pesagem.setOperador(op);
                pesagem.setProduto(prod);
                pesagem.setTransportador(transp);
                
                stmt.close();
                rs.close();
                con.close();
                return pesagem;
                
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
     */
}
