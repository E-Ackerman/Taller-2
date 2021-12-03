
package Dominio;
import Logica.*;

public class Obligatoria extends Asignatura
{
    private int nivel;
    private ListaAsignaturas ls;
    private ListaParalelos lp;
    
    public Obligatoria(int codigo, String nombre, int creditos, String tipo, int nivel) {
        super(codigo, nombre, creditos, tipo);
        this.nivel = nivel;
        ls = new ListaAsignaturas(100);
        lp = new ListaParalelos (100);
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

    public ListaParalelos getLp() {
        return lp;
    }

    public void setLp(ListaParalelos lp) {
        this.lp = lp;
    }
    
}
