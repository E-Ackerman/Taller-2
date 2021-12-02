
package Dominio;


public class Estudiante extends Persona
{
    private int nivel;
    
    public Estudiante(String rut, String correo, String contraseña, int nivel) {
        super(rut, correo, contraseña);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
