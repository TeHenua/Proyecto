
package Modelo;

/**
 *
 * @author UsueUrruela
 */

import java.util.ArrayList;


public class Centro {
    private int id;
    private String nombre;
    private String calle;
    private String numero;
    private String cp;
    private String ciudad;
    private String provincia;
    private String telefono;
    private ArrayList<Trabajador> trabajadores;

    public Centro(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    
    public Centro(int id, String nombre, String calle, String numero, String cp, String ciudad, String provincia, String telefono, ArrayList<Trabajador> trabajadores) {
        this.id = id;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.trabajadores = trabajadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(Trabajador trabajador) {
        trabajadores.add(trabajador);
    }
    
    
}
