
package br.com.dao;

import br.com.bean.Cameras;
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
public class CamerasDao {
    
    public static void inserir(Cameras cam)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Cameras( cam01, ativoC1, cam02, ativoC2, camEntrada, ativoEntrada, camSaida, ativoSaida) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cam.getCam01());
            stmt.setBoolean(2, cam.isAtivoC1());
            stmt.setString(3, cam.getCam02());
            stmt.setBoolean(4, cam.isAtivoC2());
            stmt.setString(5, cam.getCamEntrada());
            stmt.setBoolean(6, cam.isAtivoEntrada());
            stmt.setString(7, cam.getCamSaida());
            stmt.setBoolean(8, cam.isAtivoSaida());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cameras Salvo Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CamerasDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    public static void alterar(Cameras cam)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Cameras SET cam01=?, ativoC1=?, cam02=?, ativoC2=?, camEntrada=?, ativoEntrada=?, camSaida=?, ativoSaida=? WHERE ID= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cam.getCam01());
            stmt.setBoolean(2, cam.isAtivoC1());
            stmt.setString(3, cam.getCam02());
            stmt.setBoolean(4, cam.isAtivoC2());
            stmt.setString(5, cam.getCamEntrada());
            stmt.setBoolean(6, cam.isAtivoEntrada());
            stmt.setString(7, cam.getCamSaida());
            stmt.setBoolean(8, cam.isAtivoSaida());
            stmt.setInt(9, cam.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cameras Alteradas Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CamerasDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static List<Cameras> listar() throws SQLException {
        try {
            List<Cameras> listaCam = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Cameras";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cameras cam = new Cameras();
                cam.setId(rs.getInt("ID"));
                cam.setCam01(rs.getString("cam01"));
                cam.setAtivoC1(rs.getBoolean("ativoC1"));
                cam.setCam02(rs.getString("cam02"));
                cam.setAtivoC2(rs.getBoolean("ativoC2"));
                cam.setCamEntrada(rs.getString("camEntrada"));
                cam.setAtivoEntrada(rs.getBoolean("ativoEntrada"));
                cam.setCamSaida(rs.getString("camSaida"));
                cam.setCamSaida(rs.getString("camSaida"));
                listaCam.add(cam);
            }
            
            stmt.close();
            rs.close();
            con.close();
            return listaCam;
        } catch (Exception ex) {
            Logger.getLogger(CamerasDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
     }
    
}
