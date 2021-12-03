
package Logica;
import Dominio.*;

public class SistemaImpl implements Sistema 
{
    private ListaAsignaturas listaasignaturas;
    private ListaPersonas listapersonas;
    private ListaParalelos listaparalelos;
    
    public SistemaImpl () 
    {
        listaasignaturas = new ListaAsignaturas(1000);
        listapersonas = new ListaPersonas(1000);
        listaparalelos = new ListaParalelos(1000);
    }

    @Override
    public boolean ingresarEstudiante(String rut, String correo,int nivel, String contraseña) 
    {
        Persona estudiante = new Estudiante(rut,correo,contraseña,nivel);
        boolean ingresar = listapersonas.ingresarPersona(estudiante);
        return ingresar;
    }

    @Override
    public boolean ingresarAsignaturaObligatoria(int codigo, String nombre, int creditos, String tipo, int nivel) 
    {
        Asignatura obligatoria = new Obligatoria(codigo,nombre,creditos,tipo,nivel);
        boolean ingresar = listaasignaturas.ingresarAsignatura(obligatoria);
        return ingresar;
    }

    @Override
    public boolean ingresarAsignaturaOpcional(int codigo, String nombre, int creditos, String tipo, int prerrequisito) 
    {
        Asignatura opcional = new Opcional(codigo,nombre,creditos,tipo,prerrequisito);
        boolean ingresar = listaasignaturas.ingresarAsignatura(opcional);
        return ingresar;
    }

    @Override
    public boolean ingresarProfesor(String rut, String correo, String contraseña, int salario) 
    {
        Persona profesor = new Profesor(rut,correo,contraseña,salario);
        boolean ingresar = listapersonas.ingresarPersona(profesor);
        return ingresar;
    }

    @Override
    public boolean ingresarParalelo(int numero) 
    {
        Paralelo paralelo = new Paralelo(numero);
        boolean ingresar = listaparalelos.ingresarParalelo(paralelo);
        return ingresar;
    }

    @Override
    public void asociarAsignaturaEstudianteCursada(String rutEstudiante, int codigo, double nota) 
    {
        Persona persona = listapersonas.buscarPersona(rutEstudiante);
        Asignatura asignatura = listaasignaturas.buscarAsignatura(codigo);
        
        if(asignatura != null) 
        {
            if(persona != null) 
            {
                Estudiante estudiante = (Estudiante) persona;
                asignatura.setNota(nota);
                estudiante.getLs().ingresarAsignatura(asignatura);
            }
            else 
            {
                throw new NullPointerException("No se encontro el estudiante");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la asignatura");
        }
    }
    
    @Override
    public void asociarAsignaturaEstudianteInscrita(String rutEstudiante, int codigo, int paralelo) 
    {
        Persona persona = listapersonas.buscarPersona(rutEstudiante);
        Asignatura asignatura = listaasignaturas.buscarAsignatura(codigo);
        

        if(persona != null) 
        {
            Estudiante estudiante = (Estudiante) persona;
            Paralelo p = listaparalelos.buscarParalelo(paralelo);
            if(p!=null) 
            {
                if(asignatura != null) 
                {
                    if(asignatura instanceof Obligatoria) 
                    {
                        Obligatoria obligatoria = (Obligatoria) asignatura;
                        obligatoria.setParalelo(p);
                        estudiante.getLs().ingresarAsignatura(asignatura);
                        p.getLp().ingresarPersona(estudiante);
                    }
                    else 
                    {
                        Opcional opcional = (Opcional) asignatura;
                        opcional.setParalelo(p);
                        estudiante.getLs().ingresarAsignatura(asignatura);
                        p.getLp().ingresarPersona(estudiante);
                    }
                }
                else 
                {
                    throw new NullPointerException("No se encontro la asignatura");
                }
            }
            else 
            {
                throw new NullPointerException("No se encontro el paralelo");
            }
                
        }
        else 
        {
            throw new NullPointerException("No se encontro el estudiante");
        }

    }

    @Override
    public void asociarAsignaturasPrerrequisitos(int codigoAsignatura, int codigoPrerrequisito) 
    {
        Obligatoria obligatoria = (Obligatoria) listaasignaturas.buscarAsignatura(codigoAsignatura);
        Asignatura asignatura = listaasignaturas.buscarAsignatura(codigoPrerrequisito);
        if(obligatoria != null && asignatura != null) 
        {
            obligatoria.getLs().ingresarAsignatura(asignatura);
        }
        else 
        {
            throw new NullPointerException("No se encontro la asignatura");
        }
    }

    @Override
    public void asociarParalelo(int numero, int codigoAsignatura, String rutProfesor) 
    {
        Paralelo paralelo = listaparalelos.buscarParalelo(numero);
        Profesor profesor = (Profesor) listapersonas.buscarPersona(rutProfesor);
        Asignatura asignatura = listaasignaturas.buscarAsignatura(codigoAsignatura);
        
        if(paralelo!=null) 
        {
            if(profesor!= null) 
            {
                if(asignatura!=null) 
                {
                    if(asignatura instanceof Obligatoria) 
                    {
                        Obligatoria obligatoria = (Obligatoria) asignatura;
                        obligatoria.getLp().ingresarParalelo(paralelo);
                        profesor.getLs().ingresarAsignatura(asignatura);
                    }
                    else 
                    {
                        Opcional opcional = (Opcional) asignatura;
                        opcional.setParalelo(paralelo);
                        profesor.getLs().ingresarAsignatura(asignatura);
                    }
                        
                }
                else 
                {
                    throw new NullPointerException("No se encontro la asignatura");
                }
            }
            else 
            {
                throw new NullPointerException("No se encontro el profesor");
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el paralelo");
        }
    }

    @Override
    public String obtenerEstudiantes() 
    {
        String salida = "";
        int cant1 = 0;
        int cant2 = 0;
        String cursadas = "";
        String inscritas = "";
        
        for(int i=0;i<listapersonas.getCant();i++) 
        {
            Persona persona = listapersonas.getLista()[i];
            if(persona instanceof Estudiante) 
            {
                Estudiante estudiante  = (Estudiante) persona;
                salida += estudiante.getRut() + "," + estudiante.getCorreo() + "," + estudiante.getNivel() + "," + estudiante.getContraseña();

                for(int f=0;f<estudiante.getLs().getCant();f++) 
                {
                    Asignatura asignatura = estudiante.getLs().getLista()[f];
                    if(asignatura.getNota() != 0) 
                    {
                        cursadas += "\n" + asignatura.getCodigo() + "," + asignatura.getNota();
                        cant1++;
                    }
                    else 
                    {
                        inscritas += "\n" + asignatura.getCodigo() + "," + asignatura.getParalelo();
                        cant2++;
                    }
                }
                salida += "\n" + cant1 + cursadas + "\n" + cant2 + inscritas;
            }
        }
        
        return salida;
    }

    @Override
    public boolean verificarInicioSesion(String rut, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAsignaturasparaInscripcion(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obtenerParalelosporAsignatura(int codigoAsignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inscripcionAsignatura(String rut, int codigoAsignatura, int numeroParalelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAsignaturasInscritas(String rut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminacionAsignatura(String rut, int codigoAsignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerParalelosProfesor(String rutProfesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAlumnosParalelos(int numeroParalelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAsignaturasProfesor(String rutProfesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerAlumnosAsignatura(String nombreAsignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void introducirNotaFinal(String rutAlumno, String nombreAsignatura, double nota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerEstudiantesEgresados() 
    {
        String salida = "";
        for(int i=0;i<listapersonas.getCant();i++) 
        {
            int indicador = 0;
            Persona persona = listapersonas.getLista()[i];
            if(persona instanceof Estudiante) 
            {
                Estudiante estudiante  = (Estudiante) persona;
                
                if(estudiante.getNivel() == 10) 
                {
                    for(int f=0;f<estudiante.getLs().getCant();f++) 
                    {
                        Asignatura asignatura = estudiante.getLs().getLista()[f];
                        
                        if(asignatura.getNota() < 3.95) 
                        {
                            indicador = -1;
                            break;
                        }
                    }
                }
                if (indicador != -1) 
                {
                    salida += estudiante.getRut() + "\n";
                    listapersonas.eliminarPersona(estudiante.getRut());
                }
            }
        }    
        
        return salida;
    }

}
