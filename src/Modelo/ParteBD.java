/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



import static java.lang.Math.toIntExact;
import java.sql.*;
import java.util.ArrayList;



/**
 *
 * @author UsueUrruela
 */
public class ParteBD {
    
    /**
     * Guarda el parte actual en la base de datos
     * @param p Parte
     * @throws java.sql.SQLException
     */
    public static void guardarParteBD(Parte p) throws SQLException{
        GenericBD.conectar();
        String query = "insert into partes(km_principio,km_llegada,cerrado,fecha,gasoil,autopista,dietas,otros,incidencias,logistica_dni,validado)"
                + " values(?,?,1,?,?,?,?,?,?,?,0)";
        PreparedStatement ps = GenericBD.getCon().prepareStatement(query);
        ps.setString(1, p.getKmPrin());
        ps.setString(2, p.getKmLleg());
        ps.setDate(3, p.getFecha());
        ps.setString(4, p.getGasoil());
        ps.setString(5, p.getAutopista());
        ps.setString(6, p.getDietas());
        ps.setString(7, p.getOtros());
        ps.setString(8, p.getIncidencias());
        ps.setString(9, UsuarioBD.getDni());
        ps.executeUpdate();
        
        Statement st = GenericBD.getCon().createStatement();
        ResultSet rs = st.executeQuery("select max(id)as id from partes");
        rs.next();
        int id = rs.getInt("id");
     
        
        for(int n=0;n<p.getAlbaranes().size();n++){
            PreparedStatement ps2 = GenericBD.getCon().prepareStatement("insert into viajes(num_albaran,h_salida,h_llegada,matricula,parte_id)"
                    + " values(?,?,?,?,?) ");
            ps2.setString(1, p.getAlbaranes().get(n).getId());
            ps2.setString(2, p.getAlbaranes().get(n).getHoraSalida());
            ps2.setString(3, p.getAlbaranes().get(n).getHoraLlegada());
            ps2.setString(4, p.getAlbaranes().get(n).getVehiculo());
            int idN = toIntExact(id);
            ps2.setInt(5, idN);
            ps2.executeUpdate();
        }
        
        GenericBD.desconectar();
    }
    
    
    /**
     * Realiza una búsqueda en la base de datos y devuelve un listado con los resultados
     * @param desde java.sql.Date
     * @param hasta java.sql.Date
     * @param trabajador String (dni)
     * @return resultado String
     * @throws java.sql.SQLException
     */    
    public static String buscarPartesBD(java.sql.Date desde, java.sql.Date hasta, String trabajador) throws SQLException{
        GenericBD.conectar();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("select id,validado, fecha from partes where fecha between ? and ? and Logistica_dni like ?");
        ps.setDate(1, desde);
        ps.setDate(2, hasta);
        ps.setString(3, trabajador);
        ResultSet rs = ps.executeQuery();
        String resultado = "";
        
        while(rs.next()){
            String validado = "";
            if(rs.getBoolean("validado")){
                validado = "si";
            }else{
                validado = "no";
            }
            resultado += "ID "+rs.getInt("id")+"   Dni: "+trabajador+"   Fecha "+rs.getString("fecha")+"    Validado: "+validado+"\n";
           
        }
        GenericBD.desconectar();
        return resultado;
    }
    
    /**
     * Cambia el parte seleccionado a "validado" en la base de datos
     * @param n int id del parte
     * @param desde java.sql.Date
     * @param hasta java.sql.Date
     * @param trabajador String (dni)
     * @throws java.sql.SQLException
     */  
    public static void validarPartesBD(int n,java.sql.Date desde, java.sql.Date hasta, String trabajador) throws SQLException{
        GenericBD.conectar();
        String dni = UsuarioBD.getDni();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("update partes set validado=1,administracion_dni=? where id=?");
        ps.setString(1, dni);
        ps.setInt(2, n);
        ps.executeUpdate();
        
        buscarPartesBD(desde,hasta,trabajador);
        GenericBD.desconectar();
    }

    public static Parte editarParteBD(int id) throws SQLException {
        GenericBD.conectar();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("select * from partes where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String kmPrin = rs.getString("km_principio");
        String kmLleg = rs.getString("km_llegada");
        boolean cerrado = rs.getBoolean("cerrado");
        Date fecha = rs.getDate("fecha");
        String gasoil = String.valueOf(rs.getInt("gasoil"));
        String autopista= String.valueOf(rs.getInt("autopista"));
        String dietas= String.valueOf(rs.getInt("dietas"));
        String otros = rs.getString("otros");
        String incidencias = rs.getString("incidencias");
        
        Parte p = new Parte(kmPrin,kmLleg,cerrado,fecha,gasoil,autopista,dietas,otros,incidencias);
        GenericBD.desconectar();
        return p;
    }

    public static ArrayList recogerPartesMes(String mes) throws SQLException {
        ArrayList<Parte> partes = new ArrayList();
        GenericBD.conectar();
        PreparedStatement ps = GenericBD.getCon().prepareStatement("select * from partes where fecha like ?");
        mes = mes.substring(0,2);
        
        ps.setString(1, "%/"+mes+"/%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String kmI = String.valueOf(rs.getInt("km_principio"));
            String kmL = String.valueOf(rs.getInt("km_llegada"));
            boolean cerrado = rs.getBoolean("cerrado");
            boolean validado = rs.getBoolean("validado");           //SET
            java.sql.Date fecha = rs.getDate("fecha");
            String gasoil = String.valueOf(rs.getInt("gasoil"));
            String auto = String.valueOf(rs.getInt("autopista"));
            String dietas = String.valueOf(rs.getInt("dietas"));
            String otros = rs.getString("otros");
            String incidencias = rs.getString("incidencias");
            String trabLog = rs.getString("Logistica_dni");         //SET
            String trabAdm = rs.getString("Administracion_dni");    //SET
            ArrayList<Albaran> albaranes = new ArrayList();
            PreparedStatement ps2 = GenericBD.getCon().prepareStatement("select * from viajes where Parte_id=?");
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                String numAlb = rs2.getString("num_albaran");
                String hoS = rs2.getString("h_salida");
                String hoL = rs2.getString("h_llegada");
                String vehiculo = rs2.getString("matricula");
                Albaran a = new Albaran(numAlb,hoS,hoL,vehiculo);
                albaranes.add(a);
            }
            Parte p = new Parte(kmI,kmL,cerrado,fecha,gasoil,auto,dietas,otros,incidencias);
            p.setAlbaranesArray(albaranes);
            p.setValidado(validado);
            p.setDniAdm(trabAdm);
            p.setDniLog(trabLog);
            p.setId(String.valueOf(id));
            partes.add(p);
        }
        GenericBD.desconectar();
        return partes;
    }
    
    public static ArrayList recogerMeses() throws SQLException{
        ArrayList meses = new ArrayList();
        GenericBD.conectar();
        Statement ps = GenericBD.getCon().createStatement();
        ResultSet rs = ps.executeQuery("SELECT mes FROM partesxml order by mes");
        if(rs.next()){
            meses.add(rs.getString("mes"));
            while(rs.next()){
            meses.add(rs.getString("mes"));
            }
        }else{
            String nada = "No existe ningún parte";
            meses.add(nada);
        }
        GenericBD.desconectar();
        return meses;
    }
}
