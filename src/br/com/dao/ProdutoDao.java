
package br.com.dao;

import br.com.bean.Produtos;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProdutoDao {
    
    public static void inserir(Produtos prod)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Produto( produto, unidMed, observacao) VALUES(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, prod.getProduto());
        stmt.setString(2, prod.getUnidMed());
        stmt.setString(3, prod.getObservacoes());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Produto Salvo Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Produtos prod)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM TB_Produto where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, prod.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Produto Excluido Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Produtos prod)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Produto SET produto=?, unidMed=?, observacao=? WHERE id= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, prod.getProduto());
        stmt.setString(2, prod.getUnidMed());
        stmt.setString(3, prod.getObservacoes());
        stmt.setInt(6, prod.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Produto Alterado Com Sucesso!");
        stmt.close();
        con.close();
    }

    public static List<Produtos> listar() throws SQLException {
        List<Produtos> listaProdutos = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Produto order by produto";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produtos prod = new Produtos();
            prod.setId(rs.getInt("ID"));
            stmt.setString(1, prod.getProduto());
            stmt.setString(2, prod.getUnidMed());
            stmt.setString(3, prod.getObservacoes());
            listaProdutos.add(prod);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaProdutos;
     }

     public static List<Produtos> pesquisar(Produtos prod) throws SQLException {
        List<Produtos> listaOperador = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Produto where produto like'"+prod.getProduto()+"%' order by produto";
                 System.out.println(prod.getProduto());
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
    
            Produtos produto = new Produtos();
            produto.setId(rs.getInt("ID"));
            produto.setProduto(rs.getString("produto"));
            produto.setUnidMed(rs.getString("unidMed"));
            produto.setObservacoes(rs.getString("observacao"));
            listaOperador.add(produto);
            
        }
        stmt.close();
        rs.close();
        con.close();
        return listaOperador;
     }

}
