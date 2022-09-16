
package br.com.dao;

import br.com.bean.Cameras;
import br.com.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class CamerasDao {
    
    public static void inserir(Cameras cam)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into TB_Cameras( cam01, cam02, camEntrada, camSaida) VALUES(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cam.getCam01());
        stmt.setString(2, cam.getCam02());
        stmt.setString(3, cam.getCamEntrada());
        stmt.setString(4, cam.getCamSaida());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Cameras Salvo Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    
    public static void alterar(Cameras cam)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE TB_Cameras SET cam01=?, cam02=?, camEntrada=?, camSaida=? WHERE ID= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cam.getCam01());
        stmt.setString(2, cam.getCam02());
        stmt.setString(3, cam.getCamEntrada());
        stmt.setString(4, cam.getCamSaida());
        stmt.setInt(5, cam.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Cameras Alteradas Com Sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Cameras> listar() throws SQLException {
        List<Cameras> listaCam = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql="select * from TB_Cameras";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cameras cam = new Cameras();
            cam.setId(rs.getInt("ID"));
            stmt.setString(1, cam.getCam01());
            stmt.setString(2, cam.getCam02());
            stmt.setString(3, cam.getCamEntrada());
            stmt.setString(4, cam.getCamSaida());
            listaCam.add(cam);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaCam;
     }

}
