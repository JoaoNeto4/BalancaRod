
package br.com.dao;

import br.com.bean.Operador;
import br.com.bean.Permissao;
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


public class OperadorDao {
    
    public static void inserir(Operador op)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "insert into TB_Operador( ID_Permissao, nome, senha, funcao, observacoes) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, op.getPermissao().getId());
            stmt.setString(2, op.getNome());
            stmt.setString(3, op.getSenha());
            stmt.setString(4, op.getFuncao());
            stmt.setString(5, op.getObservacao());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Operador Salvo Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(Operador op)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "DELETE FROM TB_Operador where ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, op.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Operador Excluido Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void alterar(Operador op)throws SQLException{
        try {
            Connection con = Conexao.getConexao();
            String sql = "UPDATE TB_Operador SET ID_Permissao=?, nome=?, senha=?, funcao=?, observacoes=?, ativo=? WHERE ID= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, op.getPermissao().getId());
            stmt.setString(2, op.getNome());
            stmt.setString(3, op.getSenha());
            stmt.setString(4, op.getFuncao());
            stmt.setString(5, op.getObservacao());
            stmt.setBoolean(6, op.isAtivo());
            stmt.setInt(7, op.getId());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Operador Alterada Com Sucesso!");
            stmt.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Operador> listar() throws SQLException {
        try {
            List<Operador> listaControle = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select * from TB_Operador order by nome";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Operador op = new Operador();
                op.setId(rs.getInt("ID"));
                stmt.setInt(1, op.getPermissao().getId());
                stmt.setString(2, op.getNome());
                stmt.setString(3, op.getSenha());
                stmt.setString(4, op.getFuncao());
                stmt.setString(5, op.getObservacao());
                stmt.setBoolean(6, op.isAtivo());
                listaControle.add(op);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaControle;
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     
     public static List<Operador> pesquisar(Operador op) throws SQLException {
        try {
            List<Operador> listaOperador = new ArrayList<>();
            Connection con = Conexao.getConexao();
            String sql = "select OP.*, P.* from TB_Operador as OP inner join TB_Permissao as P on P.ID=OP.ID_permissao where OP.nome like'"+op.getNome()+"%' order by OP.nome";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                Permissao p = new Permissao();
                p.setId(rs.getInt("P.ID"));
                p.setNivel(rs.getString("P.nivel"));
                p.setObservacao(rs.getString("P.observacao"));
                
                Operador operador = new Operador();
                operador.setId(rs.getInt("OP.ID"));
                operador.setNome(rs.getString("OP.nome"));
                operador.setSenha(rs.getString("OP.senha"));
                operador.setFuncao(rs.getString("OP.funcao"));
                operador.setObservacao(rs.getString("OP.observacoes"));
                operador.setAtivo(rs.getBoolean("OP.ativo"));
                operador.setPermissao(p);
                listaOperador.add(operador);
            }
            stmt.close();
            rs.close();
            con.close();
            return listaOperador;
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
     
     public boolean validaLogin(String login, String senha)throws SQLException{
        try {
            Operador user = new Operador();
            Permissao p = new Permissao();
            boolean check=false;
            Connection con = Conexao.getConexao();
            String sql="select O.*, P.* from TB_Operador as O inner join TB_Permissao as P WHERE O.nome=? AND O.senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                p.setId(rs.getInt("P.ID"));
                p.setNivel(rs.getString("P.nivel"));
                user.setNome(rs.getString("O.nome"));
                user.setSenha(rs.getString("O.senha"));
                user.setPermissao(p);
                check=true;
            }else{
                JOptionPane.showMessageDialog(null, "Login ou Senha incorreto!");
                check=false;
            }
            rs.close();
            con.close();
            return check;
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
    public Operador retornaBean(String login, String senha)throws SQLException{
        try {
            Operador user = new Operador();
            Permissao p = new Permissao();
            Connection con = Conexao.getConexao();
            String sql="select O.*, P.* from TB_Operador as O inner join TB_Permissao as P WHERE O.nome=? AND O.senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                p.setId(rs.getInt("P.ID"));
                p.setNivel(rs.getString("P.nivel"));
                user.setNome(rs.getString("O.nome"));
                user.setSenha(rs.getString("O.senha"));
                user.setPermissao(p);
            }
            rs.close();
            con.close();
            
            return user;
        } catch (Exception ex) {
            Logger.getLogger(OperadorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
