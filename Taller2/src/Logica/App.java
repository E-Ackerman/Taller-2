
package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;


public class App 
{
    /**
     * method that read the text file and save the data
     * @param sistema 
     */
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
    
    /**
     * method that read the text file and save the data
     * @param sistema 
     */
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
    
    /**
     * method that read the text file and save the data
     * @param sistema 
     */
    public static void LeerAsignaturas (SistemaImpl sistema) 
    {
        try
        {
            Scanner scanner = new Scanner(new File("asignaturas.txt"));
            
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
    
    /**
     * method that read the text file and save the data
     * @param sistema 
     */
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
        Scanner read = new Scanner(System.in);
        
        System.out.print("Desea cerrar el sistema? (SI) (NO): ");
        String respuesta = "";
        respuesta = read.next().toUpperCase();
        
        while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
        {
            System.out.println("\n--- El texto ingresado es incorrecto ---");
            System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        }
        
        
        LeerAsignaturas (sistema);
        LeerEstudiantes(sistema);
        LeerProfesores(sistema);
        LeerParalelos(sistema);
        
        
        while (respuesta.equals("NO")) 
        {
            System.out.println("\n  INICIO DE SESION\n");
            System.out.print("Ingrese su correo: ");
            String correo = read.next();
            System.out.print("Ingrese su contraseña: ");
            String contraseña = read.next();
            String resp = "SI";
            
            while ((!sistema.verificarInicioSesion(correo, contraseña) && (!correo.equals("Admin") || !contraseña.equals("GHI_789"))) && resp.equals("SI")) 
            {
                System.out.println("\n--- El correo y/o contraseña ingresados son incorrectos ---\n");
                System.out.print("¿Desea volver a intentarlo? (SI) (NO): ");
                resp = read.next().toUpperCase();
                while (!resp.equals("SI") && !resp.equals("NO")) 
                {
                    System.out.println("\n--- El texto ingresado es incorrecto ---\n");
                    System.out.print("¿Desea volver a intentarlo? (SI) (NO): ");
                    resp = read.next().toUpperCase();
                }
                
                if(resp.equals("SI")) 
                {
                    System.out.println("\n  INICIO DE SESION\n");
                    System.out.print("Ingrese su correo: ");
                    correo = read.next();
                    System.out.print("Ingrese su contraseña: ");
                    contraseña = read.next();
                }
            }
            if(resp.equals("NO")) 
            {
                break;
            }
            else 
            {
                String identificador = "";
                if(correo.equals("Admin") && contraseña.equals("GHI_789")) 
                {
                    identificador = "Admin";
                }
                else 
                {
                   try
                    {
                        identificador = sistema.IdentificarUsuario(correo);
                    }
                    catch (Exception e) 
                    {
                        System.out.println();
                    } 
                }
                
                System.out.print("Ingrese la fecha de hoy: ");
                String fecha = read.next();
                
                if(IdentificarPeriodo(fecha).equals("inicio")) 
                {
                    if(identificador.equals("Estudiante")) 
                    {
                        System.out.println("Elija una opción: \n1) Inscripción de asignaturas \n2) Eliminacion de asignaturas\n ");
                        System.out.print("Ingrese la opción: ");
                        String eleccion = read.next();
                        while(!eleccion.equals("1") && !eleccion.equals("2")) 
                        {
                            System.out.println("\n--- La opción ingresada es incorreta ---\n");
                            System.out.print("Ingrese la opción: ");
                            eleccion = read.next();
                        }
                        if(eleccion.equals("1")) 
                        {
                            System.out.println("\n  INSCRIBIR ASIGNATURAS\n");
                            
                            
                            
                            
                            
                            
                        }
                        else 
                        {
                            System.out.println("\n  ELIMINAR ASIGNATURAS\n");
                            try
                            {
                                System.out.println(sistema.obtenerAsignaturasInscritas(correo));
                            }
                            catch (Exception e) 
                            {
                                System.out.println(e.getMessage());
                            }
                            System.out.print("\nIndique el codigo de la asignatura: ");
                            int codigoAsig = read.nextInt();
                            try
                            {
                                if(sistema.eliminacionAsignatura(correo, codigoAsig)) 
                                {
                                    System.out.println("\n  Se a eliminado con exito!");
                                }
                                else 
                                {
                                    System.out.println("\n  La asignatura no se ha encontrado, por lo tanto no se a podido eliminar");
                                }

                            }
                            catch (Exception ex) 
                            {
                                System.out.println(ex.getMessage());
                            }
                        }
                        SobrescribirEstudiantes(sistema);
                    }
                    else if(identificador.equals("Profesor")) 
                    {
                        System.out.println("\n  CHEQUEO ALUMNOS\n");
                        try 
                        {
                            System.out.println(sistema.obtenerParalelosProfesor(correo));
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("Indique el numero del paralelo a mostrar: ");
                        int numParalelo = read.nextInt();
                        try 
                        {
                            System.out.println(sistema.obtenerAlumnosParalelos(numParalelo));
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex.getMessage());
                        }
                        SobrescribirEstudiantes(sistema);
                    }
                    else 
                    {
                        System.out.println("\n  No hay acciones disponibles\n");
                    }
                }
                else if(IdentificarPeriodo(fecha).equals("mitad"))
                {
                    if(identificador.equals("Estudiante")) 
                    {
                        System.out.println("\n  ELIMINAR ASIGNATURAS\n");
                        try
                        {
                            System.out.println(sistema.obtenerAsignaturasInscritas(correo));
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e.getMessage());
                        }
                        System.out.print("\nIndique el codigo de la asignatura: ");
                        int codigoAsig = read.nextInt();
                        try
                        {
                            if(sistema.eliminacionAsignatura(correo, codigoAsig)) 
                            {
                                System.out.println("\n  Se a eliminado con exito!");
                            }
                            else 
                            {
                                System.out.println("\n  La asignatura no se ha encontrado, por lo tanto no se a podido eliminar");
                            }
                                
                        }
                        catch (Exception ex) 
                        {
                            System.out.println(ex.getMessage());
                        }
                        SobrescribirEstudiantes(sistema);
                        
                    }
                    else 
                    {
                        System.out.println("\n  No hay acciones disponibles\n");
                    }
                }
                else if(IdentificarPeriodo(fecha).equals("final"))
                {
                    if(identificador.equals("Profesor")) 
                    {
                        String respNotas = "SI";
                        while(respNotas.equals("SI")) 
                        {
                        
                            System.out.println("\n  INGRESO NOTA FINAL\n");
                            try 
                            {
                                System.out.println(sistema.obtenerAsignaturasProfesor(correo));
                            }
                            catch (Exception e) 
                            {
                                System.out.println(e.getMessage());
                            }
                            System.out.print("\nIndique el codigo de la asignatura: ");
                            int codigoAsig = read.nextInt();
                            try 
                            {
                                System.out.println(sistema.obtenerAlumnosAsignatura(codigoAsig));
                            }
                            catch (Exception ex) 
                            {
                                System.out.println(ex.getMessage());
                            }
                            System.out.print("\nIndique el rut del estudiante seleccionado: ");
                            String rutEstudiante = read.next();
                            System.out.print("\nIndique su nota final: ");
                            double notaFinal = read.nextDouble();
                            try 
                            {
                                sistema.introducirNotaFinal(rutEstudiante, codigoAsig, notaFinal);
                            }
                            catch (Exception exc) 
                            {
                                System.out.println(exc.getMessage());
                            }
                            System.out.print("\n¿Desea seguir ingresando notas? : ");
                            respNotas = read.next().toUpperCase();
                            while (!respNotas.equals("SI") && !respNotas.equals("NO")) 
                            {
                                System.out.println("\n--- El texto ingresado es incorrecto ---");
                                System.out.print("\n¿Desea seguir ingresando notas? : ");
                                respNotas = read.next().toUpperCase();
                            }
                        }    
                        SobrescribirEstudiantes(sistema);
                    }
                    else 
                    {
                        System.out.println("\n  No hay acciones disponibles\n");
                    }
                }
                else if(IdentificarPeriodo(fecha).equals("cierre"))
                {
                    if(identificador.equals("Admin")) 
                    {
                        EscribirEgresados(sistema);     
                        break;
                    }    
                }
                else 
                {
                    System.out.println(IdentificarPeriodo(fecha));
                }
    
            }    

            System.out.println("\nHa cerrado sesion con exito!\n");
            System.out.print("Desea cerrar el sistema? (SI) (NO): ");
            respuesta = read.next().toUpperCase();
        
            while (!respuesta.equals("SI") && !respuesta.equals("NO")) 
            {
                System.out.println("\n--- El texto ingresado es incorrecto ---");
                System.out.print("\nDesea cerrar el sistema? (SI) (NO): ");
                respuesta = read.next().toUpperCase();
            }
            
        }  // CIERRE DEL SISTEMA  
        
        System.out.println("\n Sistema Cerrado Correctamente :)");
        
        
    }
    /**
     * method that overwrites the data in a text file
     * @param sistema 
     */
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
    
    /**
     * method that writes the data to a new text file
     * @param sistema 
     */
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
    /**
     * method that the period according to the date
     * @param fecha
     * @return 
     */
    public static String IdentificarPeriodo (String fecha) 
    {
        String salida = "";
        String[] partes = fecha.split("/");
        int dia = Integer.valueOf(partes[0]); int mes = Integer.valueOf(partes[1]); int año = Integer.valueOf(partes[2]);   
        
        if(año < 2021 || año > 2021) 
        {
            salida += "\n   Disfrute sus vacaciones\n";
        }
        else 
        {
            if(mes >= 3 && mes <=5) 
            {
                if(mes == 3 && (dia>=8 && dia <=31)) 
                {
                    salida += "inicio";
                }
                else if(mes == 4 && (dia>=1 && dia<=30)) 
                {
                    salida += "inicio";
                }
                else if(mes == 5 && (dia>=1 && dia <=2)) 
                {
                    salida += "inicio";
                }
                
            }
            else if(mes >= 5 && mes <=7) 
            {
                if (mes == 5 && (dia>=3 && dia <=31)) 
                {
                    salida += "mitad";
                }
                else if(mes == 6 && (dia>=1 && dia<=30)) 
                {
                    salida += "mitad";
                }
                else if (mes == 7 && (dia>=1 && dia <=11)) 
                {
                    salida += "mitad";
                }
            }
            else if(mes == 7) 
            {
                if((dia>=12 && dia <=25)) 
                {
                    salida += "final";
                }
                else if (dia == 26) 
                {
                    salida += "cierre";
                }
                else 
                {
                    salida += "\n   Disfrute sus vacaciones\n";
                }
            }
            else 
            {
                salida += "\n   Disfrute sus vacaciones\n";
            }
        }

        return salida;
    }
    
}
