
package Logica;
import Dominio.*;

public class ListaParalelos 
{
    private Paralelo[] lista;
    private int cant;
    private int max;
    
    public ListaParalelos (int max) 
    {
        lista = new Paralelo[max];
        this.max = max;
        this.cant = 0;
    }

    public boolean ingresarParalelo(Paralelo p) 
    {
        if(cant < max) 
        {
            lista[cant] = p;
            cant++;
            return true;
        }
        
        else 
        {
            return false;
        }
    }
    
    public Paralelo buscarParalelo(int num) 
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if(lista[i].getNumero() == num) 
            {
                break;
            }
        }
        
        if(i== cant) 
        {
            return null;
        }
        else 
        {
            return lista[i];
        }
    }
    
    public Paralelo[] getLista() {
        return lista;
    }

    public void setLista(Paralelo[] lista) {
        this.lista = lista;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
}
