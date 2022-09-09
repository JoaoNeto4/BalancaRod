
package br.com.dao;

import br.com.bean.Log;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class LogDao {
    
    public static void inserir(Log log) throws SQLException{
        Connection con = (Connection) Conexao.getConexao();
        String sql = "insert into TB_Log(ID_operador, tabela, tipoRegistro, dataHora) VALUES(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, log.getOperador().getId());
        stmt.setString(2, log.getTabela());
        stmt.setString(3, log.getTipoRegistro());
        stmt.setTime(4, (Time) log.getDataHora());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    /*
    public static void excluir(Log log)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM CONTROLE where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, log.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        stmt.close();
        con.close();
    }
    */
    public static List<Log> listar() throws SQLException {
        List<Log> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from TB_Log order by ID";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Log log = new Log();
            log.setId(rs.getInt("ID"));
            stmt.setInt(1, log.getOperador().getId());
            stmt.setString(2, log.getTabela());
            stmt.setString(3, log.getTipoRegistro());
            stmt.setTime(4, (Time) log.getDataHora());
            listaControle.add(log);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
 

}
