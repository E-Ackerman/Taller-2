
package Dominio;


public class Obligatoria extends Asignatura
{
    private int nivel;
    
    public Obligatoria(int codigo, String nombre, int creditos, String tipo, int nivel) {
        super(codigo, nombre, creditos, tipo);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
