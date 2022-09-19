
package br.com.conexao;


import br.com.config.Criptografia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Conexao {
    
    /*
    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BALANCA", "root", "root");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
    */
    
    static String url="jdbc:mysql://localhost/BALANCA"
           + "?useTimezone=true&serverTimezone=UTC";
    public static Connection getConexao() throws SQLException, FileNotFoundException, IOException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException{
        
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("/home/joao/NetBeansProjects/Balanca/src/br/com/config/config.properties");
            prop.load(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try{
          //Caso esteja usando driver mais atual do que 5.1.23 adicionar ".cj" como abaixo  
          //Class.forName("com.mysql.cj.jdbc.Driver");  
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, prop.getProperty("prop.server.login"), Criptografia.decrypt(prop.getProperty("prop.server.password")));
        } catch (ClassNotFoundException e){
            throw new SQLDataException(e.getMessage());
        } 
       
    }
   
    
    public static void main(String[] args){
        try{
            getConexao();
            System.out.println("Conexão realizada com sucesso.");
        }catch (SQLException e){
            System.out.println("Conexão não realizada, verificar log.");
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}