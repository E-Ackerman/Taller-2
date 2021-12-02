
package Logica;
import Dominio.*;


public class ListaPersonas 
{
    private Persona[] lista;
    private int cant;
    private int max;
    
    public ListaPersonas(int max)
    {
        lista = new Persona[max];
        this.max = max;
        this.cant = 0;
    }
    
    public boolean IngresarPersona(Persona p) 
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
    
    public Persona buscarPersona (String rut) 
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if(lista[i].getRut().equals(rut)) 
            {
                break;
            }
        }
        
        if(i == cant) 
        {
            return null;
        }
        else 
        {
            return lista[i];
        }
    }

    public Persona[] getLista() {
        return lista;
    }

    public void setLista(Persona[] lista) {
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
