/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

/**
 *
 * @author UsueUrruela
 */
public class UsuarioBD {
    
    private static char tipo;
    private static String dni;
    /**
     * Compara el usuario y la contraseña introducidos en la ventana Login con la base de datos
     * @param usuario Objeto Usuario
     * @return correcto booleano
     * @throws java.sql.SQLException
     */
    public static boolean comprobarLoginBD(Usuario usuario) throws SQLException{
        boolean correcto=false;
        GenericBD.conectar();
        String select = "select * from usuarios where usuario=?";
        PreparedStatement ps = GenericBD.getCon().prepareStatement(select);
        ps.setString(1, usuario.getNombre());
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            if(rs.getString("password").compareToIgnoreCase(usuario.getPassw())==0){
                correcto = true;
            }else{
                correcto = false;
            }
            if(rs.getString("dnia")!=null){
                tipo = 'a';
                dni = rs.getString("dnia");
            }  
            if(rs.getString("dnil")!=null){
                tipo = 'l';
                dni = rs.getString("dnil");
            }
        }
        GenericBD.desconectar();
        return correcto;
    }
    /**
     * Devuelve el tipo de trabajador que ha iniciado sesión
     * @return tipo char
     */
    public static char getTipo(){
        return tipo;
    }
    
    /**
     * Devuelve el dni del trabajador que ha iniciado sesión
     * @return dni String
     */
    public static String getDni(){
        return dni;
    }
}
