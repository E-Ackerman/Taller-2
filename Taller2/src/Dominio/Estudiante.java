
package Dominio;
import Logica.*;


public class Estudiante extends Persona
{
    private int nivel;
    private ListaAsignaturas ls;

    public Estudiante(String rut, String correo, String contraseña, int nivel) {
        super(rut, correo, contraseña);
        this.nivel = nivel;
        ls = new ListaAsignaturas(100);
    }
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ListaAsignaturas getLs() {
        return ls;
    }

    public void setLs(ListaAsignaturas ls) {
        this.ls = ls;
    }
}
