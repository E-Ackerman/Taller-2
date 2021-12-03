
package Logica;
import Dominio.*;

public class ListaAsignaturas 
{
    private Asignatura[] lista;
    private int cant;
    private int max;
    
    public ListaAsignaturas (int max) 
    {
        lista = new Asignatura[max];
        this.max = max;
        this.cant = 0;
    }

    public boolean ingresarAsignatura (Asignatura a) 
    {
        if(cant < max) 
        {
            lista[cant] = a;
            cant++;
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public Asignatura buscarAsignatura (int codigo)
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if(lista[i].getCodigo() == codigo) 
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
    
    public boolean eliminarAsignatura (int codigo)
    {
        int i;
        for(i=0;i<cant;i++) 
        {
            if(lista[i].getCodigo() == codigo) 
            {
                break;
            }
        }
        if ( i == cant) 
        {
            return false;
        }
        else 
        {
            for (int j = i; j< (cant -1); j++) 
            {
                lista[j] = lista[j+1];
            }
            cant --;
            return true;
        }
    }    
    
    public Asignatura[] getLista() {
        return lista;
    }

    public void setLista(Asignatura[] lista) {
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
