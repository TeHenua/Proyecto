/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Modelo.GenericBD;
import static Parser.Parser.getStringFromDocument;
import java.sql.*;
import org.w3c.dom.Document;

/**
 *
 * @author UsueUrruela
 */
public class ParserBD {
    private static int id;
    public static void guardarBD(Document document, String mes) throws SQLException{
        GenericBD.conectar();
        Statement ps = GenericBD.getCon().createStatement();
        ps.executeUpdate("DELETE FROM partesXML where mes='"+mes+"'");
        Statement stmt = GenericBD.getCon().createStatement();
        //Realizamos la inserción. Utilizamos el método getStringFromDocument para
        //obtener un String del document generado anteriormente
        
        stmt.executeUpdate("INSERT INTO partesXML(parte,mes) VALUES(XMLType('"+getStringFromDocument(document)+"'),'"+mes+"')");
        //cerramos la conexión
        stmt.close();
        GenericBD.desconectar();
    }

    public static int buscarId(String mes) throws SQLException{
        GenericBD.conectar();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("SELECT id FROM partesxml where mes=?");
        ps.setString(1, mes);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int id = rs.getInt("id"); 
        GenericBD.desconectar();
        return id;
    }
    
    public static String getXMLBD(String mes) throws SQLException{
        Document oracleDocument = null;
        int id = buscarId(mes);
        GenericBD.conectar();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("SELECT p.parte.getStringVal() FROM partesxml p where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String resultado="" ;
        while(rs.next()){
            resultado = rs.getString(1);
        }
        
        
        GenericBD.desconectar();
        return resultado;
    }
    
}
