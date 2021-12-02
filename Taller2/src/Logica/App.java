
package Logica;

class Perro 
{
    private int edad;

    public Perro(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}

public class App 
{

    public static void main(String[] args) 
    {
       Perro a = new Perro(15);
       Perro b = new Perro(12);
       
       Perro c = new Perro(11);
       c = b;
       b=c;
       System.out.println(a.getEdad());
       System.out.println(b.getEdad());
       System.out.println(c.getEdad());
       
       if(a==b) 
       {
           System.out.println("a");
       }
    }
    
}
