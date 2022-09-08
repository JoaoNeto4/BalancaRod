
package br.com.dao;

import br.com.bean.Pesagem;
import br.com.bean.Registro;
import br.com.conexao.Conexao;
import com.sun.xml.internal.bind.v2.TODO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class RegistroDao {
    
    public static void inserir(Registro rg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Registro( ID_pesagem, dataHora, tipo, foto1, foto2, ativo) VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rg.getPesagem().getId());
        stmt.setDate(2, (Date) rg.getDataHora());
        stmt.setString(3, rg.getTipo());
        stmt.setString(4, rg.getFoto1());
        stmt.setString(5, rg.getFoto2());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void excluir(Registro rg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Registro where ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rg.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Registro Excluido Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Registro rg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Registro SET ID_pesagem=?, dataHora=?, tipo=?, foto1=?, foto2=?, ativo=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rg.getPesagem().getId());
        stmt.setDate(2, (Date) rg.getDataHora());
        stmt.setString(3, rg.getTipo());
        stmt.setString(4, rg.getFoto1());
        stmt.setString(5, rg.getFoto2());
        stmt.setBoolean(6, rg.getAtivo());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Registro Alterado Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Registro> listar() throws SQLException {
        List<Registro> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Registro order by ID";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Registro op = new Registro();
            op.setId(rs.getInt("ID"));
            stmt.setInt(1, op.getPesagem().getId());
            stmt.setDate(2, (Date) op.getDataHora());
            stmt.setString(3, op.getTipo());
            stmt.setString(4, op.getFoto1());
            stmt.setString(5, op.getFoto2());
            stmt.setBoolean(6, op.getAtivo());
            listaControle.add(op);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
     
    //TODO: REVER ESTE METODO ALTERACOES : like por =
     public static List<Registro> pesquisar(Registro reg) throws SQLException {
        List<Registro> listaRegistro = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select RE.*, PE.* from TB_Registro as RE inner join TB_Pesagem as PE on PE.ID=RE.ID_pesagem where RE.ID like'"+reg.getId()+"%' order by RE.ID";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
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
            
            Registro re = new Registro();
            re.setId(rs.getInt("RE.ID"));
            re.setDataHora(rs.getDate("RE.dataHora"));
            re.setTipo(rs.getString("RE.tipo"));
            re.setFoto1(rs.getString("RE.foto1"));
            re.setFoto2(rs.getString("RE.foto2"));
            re.setAtivo(rs.getBoolean("RE.ativo"));
            re.setPesagem(p);

            listaRegistro.add(re);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaRegistro;
     }
     
}
