/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UsueUrruela
 */
public class CentroBD {
    private static int idCentro;
    public static ArrayList<Centro> recogerCentros(ArrayList<Centro> centros){
        try {
            GenericBD.conectar();
            PreparedStatement ps = GenericBD.getCon().prepareStatement("SELECT id,nombre FROM CENTROS");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Centro c = new Centro(id,nombre);
                centros.add(c);
            }
            GenericBD.desconectar();
        }catch (SQLException ex) {
            Logger.getLogger(CentroBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return centros;
    }
    
}
