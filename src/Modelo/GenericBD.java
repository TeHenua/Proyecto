/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author UsueUrruela
 */
public class GenericBD {
    private static Connection con;
    
    
    /**
     * Conecta con la base de datos
     * Cambiar los valores login, passw y url por los correspondientes a la base de datos actual
     */
    public static void conectar(){
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            String login = "system";
            String passw = "oracle";
            String url = "jdbc:oracle:thin:@10.10.10.9:1521:db12102";
            con = DriverManager.getConnection(url,login ,passw);
            con.setAutoCommit(true);
        }catch(ClassNotFoundException | SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null ,"Ha ocurrido un problema \n"+e.getMessage());
        }
    }
    
     /**
     * Cierra la conexión con la base de datos
     * @throws java.sql.SQLException
     */
    public static void desconectar() throws SQLException{
        con.close();
    }
    
     /**
     * Devuelve los parametros de la conexión
     * @return con Connecion
     */
    public static Connection getCon(){
        return con;
    }

    
}
