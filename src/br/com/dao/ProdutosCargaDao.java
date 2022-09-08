
package br.com.dao;

import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.ProdutosCarga;
import br.com.conexao.Conexao;
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
   
    public static List<ProdutosCarga> listar() throws SQLException {
        List<ProdutosCarga> listaProdCarga = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select PR.*, PR.*, PE.* from TB_ProdutosCarga as PC inner join TB_Produto as PR on PC.ID_produto=PR.ID inner join TB_Pesagem as PE on PE.ID=PC.ID_pesagem order by PC.ID_produto";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
            Pesagem pe = new Pesagem();
            pe.setId(rs.getInt("PE.ID"));
            /*VERIFICAR ERRO*/
            
            Produtos prod = new Produtos();
            prod.setId(rs.getInt("PR.ID"));
            prod.setProduto(rs.getString("PR.produto"));
            prod.setUnidMed(rs.getString("PR.unidMed"));
            prod.setObservacoes(rs.getString("PR.observacao"));
            
            
            ProdutosCarga pc = new ProdutosCarga();
            pc.setId(rs.getInt("PC.ID"));
            
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
