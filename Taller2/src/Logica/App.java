
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class App 
{
    public static void LeerParalelos(SistemaImpl sistema) 
    {
        Scanner scanner;
        try 
        {
            scanner = new Scanner(new File("paralelos.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                sistema.ingresarParalelo(Integer.valueOf(partes[0]));
                try 
                {
                    sistema.asociarParalelo(Integer.valueOf(partes[0]), Integer.valueOf(partes[1]), partes[2]);
                }
                catch (Exception e) 
                {
                    System.out.println(e.getMessage());
                }
                
            }
            scanner.close();
        }
        catch(Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }

    }
    
    public static void LeerProfesores(SistemaImpl sistema) 
    {
        Scanner scanner;
        try 
        {
            scanner = new Scanner(new File("profesores.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                sistema.ingresarProfesor(partes[0], partes[1], partes[2], Integer.valueOf(partes[3]));
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void LeerAsignaturas (SistemaImpl sistema) 
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File("asignaturas.txt"));
            
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                
                if(partes[3].equals("obligatoria")) 
                {
                    sistema.ingresarAsignaturaObligatoria(Integer.valueOf(partes[0]), partes[1], Integer.valueOf(partes[2]), partes[3], Integer.valueOf(partes[4]));
                    int cant = Integer.valueOf(partes[5]);
                    for(int i=0;i<cant;i++) 
                    {
                        try
                        {
                            sistema.asociarAsignaturasPrerrequisitos(Integer.valueOf(partes[0]), Integer.valueOf(partes[6+i]));
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                else 
                {
                    sistema.ingresarAsignaturaOpcional(Integer.valueOf(partes[0]), partes[1], Integer.valueOf(partes[2]),  partes[3], Integer.valueOf(partes[4]));
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void LeerEstudiantes(SistemaImpl sistema) 
    {
        Scanner scanner;
        try
        {
            scanner = new Scanner(new File("estudiantes.txt"));
            int cant = 0;  
            int i = 0;
            String rut = "";
            while(scanner.hasNextLine()) 
            {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if(partes.length == 1) 
                {
                    cant = Integer.valueOf(partes[0]);
                    i++;
                }
                else if(partes.length == 2 && cant >0) 
                {
                    try
                    {
                        if (i==1) 
                        {
                            sistema.asociarAsignaturaEstudianteCursada(rut, Integer.valueOf(partes[0]), Double.valueOf(partes[1]));
                        }
                        else 
                        {
                            sistema.asociarAsignaturaEstudianteInscrita(rut, Integer.valueOf(partes[0]), Integer.valueOf(partes[1]));
                        }
                    }
                    catch (Exception e) 
                    {
                        System.out.println(e.getMessage());
                    }
                    cant--;
                }
                else 
                {
                    sistema.ingresarEstudiante(partes[0], partes[1], Integer.valueOf(partes[2]), partes[3]);
                    i = 0;
                    rut = partes[0];
                }
            }
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }

    public static void main(String[] args) 
    {
        SistemaImpl sistema = new SistemaImpl();
        
    }
    
    public static void SobrescribirEstudiantes (SistemaImpl sistema) 
    {
        try
        {
            FileWriter fw = new FileWriter(new File("estudiantes.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerEstudiantes());;   
            write.close();       
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
    public static void EscribirEgresados (SistemaImpl sistema) 
    {
        try
        {
            File archive = new File("egresados.txt");
            archive.createNewFile();
            FileWriter fw = new FileWriter(new File("egresados.txt"));
            PrintWriter write = new PrintWriter(new BufferedWriter(fw));
            write.write(sistema.obtenerEstudiantesEgresados());  
            write.close();       
        }
        catch (Exception ex) 
        {
            System.out.println("No se encontro el archivo de texto");
        }
    }
    
}
