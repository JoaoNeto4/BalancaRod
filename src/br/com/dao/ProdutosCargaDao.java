
package br.com.dao;

import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.ProdutosCarga;
import br.com.conexao.Conexao;
import com.sun.xml.internal.bind.v2.TODO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosCargaDao {
    
    public static void inserir(ProdutosCarga pc)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_ProdutosCarga( ID_produto, ID_pesagem, observacao) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, pc.getProduto().getId());
        stmt.setInt(2, pc.getPesagem().getId());
        stmt.setString(3, pc.getObservacao());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void excluir(ProdutosCarga pg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_ProdutosCarga where ID=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, pg.getId());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    public static void alterar(ProdutosCarga pc)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_ProdutosCarga SET ID_produto=?, ID_pesagem=?, observacao=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, pc.getProduto().getId());
        stmt.setInt(2, pc.getPesagem().getId());
        stmt.setString(3, pc.getObservacao());
        stmt.setInt(6, pc.getId());
        stmt.execute();
        stmt.close();
        con.close();
    }
   
   // TODO: //VERIFICAR ESTE METODO
            /*VERIFICAR ERRO*/
    public static List<ProdutosCarga> listar() throws SQLException {
        List<ProdutosCarga> listaProdCarga = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select PR.*, PC.*, PE.* from TB_ProdutosCarga as PC inner join TB_Produto as PR on PC.ID_produto=PR.ID inner join TB_Pesagem as PE on PE.ID=PC.ID_pesagem order by PC.ID_produto";
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
         
            Produtos prod = new Produtos();
            prod.setId(rs.getInt("PR.ID"));
            prod.setProduto(rs.getString("PR.produto"));
            prod.setUnidMed(rs.getString("PR.unidMed"));
            prod.setObservacoes(rs.getString("PR.observacao"));
             
            ProdutosCarga pc = new ProdutosCarga();
            pc.setId(rs.getInt("PC.ID"));
            pc.setObservacao(rs.getString("PC.observacao"));
            pc.setProduto(prod);
            pc.setPesagem(p);
            
            stmt.setInt(1, pc.getPesagem().getId());
            stmt.setInt(2, pc.getProduto().getId());
            stmt.setString(3, pc.getObservacao());
            listaProdCarga.add(pc);

        }
        stmt.close();
        rs.close();
        con.close();
        return listaProdCarga;
     }
     
}
