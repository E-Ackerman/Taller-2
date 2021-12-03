
package Dominio;
import Logica.*;

public class Paralelo 
{
    private int numero;
    private ListaPersonas lp;

    public Paralelo(int numero) {
        this.numero = numero;
        lp = new ListaPersonas(100);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ListaPersonas getLp() {
        return lp;
    }

    public void setLp(ListaPersonas lp) {
        this.lp = lp;
    }
    
}
