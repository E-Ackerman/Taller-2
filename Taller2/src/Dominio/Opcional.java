
package Dominio;


public class Opcional extends Asignatura
{
    private int prerrequisitos; // creditos de prerrequisito
    public Opcional(int codigo, String nombre, int creditos, String tipo, int prerrequisitos) {
        super(codigo, nombre, creditos, tipo);
        this.prerrequisitos = prerrequisitos;
    }

    public int getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(int prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }
    
    
}
