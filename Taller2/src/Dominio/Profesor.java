
package Dominio;
import Logica.*;

public class Profesor extends Persona
{
    private int salario;
    private ListaAsignaturas ls;

    public Profesor(String rut, String correo, String contraseña, int salario) {
        super(rut, correo, contraseña);
        this.salario = salario;
        ls = new ListaAsignaturas(4);
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public ListaAsignaturas getLs() {
        return ls;
    }

    public void setLs(ListaAsignaturas ls) {
        this.ls = ls;
    }

}
