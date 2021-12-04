
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
    /**
     * method that the student enters the general list of people
     * @param rut
     * @param correo
     * @param nivel
     * @param contraseña
     * @return 
     */
    @Override
    public boolean ingresarEstudiante(String rut, String correo,int nivel, String contraseña) 
    {
        Persona estudiante = new Estudiante(rut,correo,contraseña,nivel);
        boolean ingresar = listapersonas.ingresarPersona(estudiante);
        return ingresar;
    }

    /**
     * method that enters the compulsory subject to the general list of subjects
     * @param codigo
     * @param nombre
     * @param creditos
     * @param tipo
     * @param nivel
     * @return 
     */
    @Override
    public boolean ingresarAsignaturaObligatoria(int codigo, String nombre, int creditos, String tipo, int nivel) 
    {
        Asignatura obligatoria = new Obligatoria(codigo,nombre,creditos,tipo,nivel);
        boolean ingresar = listaasignaturas.ingresarAsignatura(obligatoria);
        return ingresar;
    }

    /**
     * method that enters the optional subject to the general list of subjects
     * @param codigo
     * @param nombre
     * @param creditos
     * @param tipo
     * @param prerrequisito
     * @return 
     */
    @Override
    public boolean ingresarAsignaturaOpcional(int codigo, String nombre, int creditos, String tipo, int prerrequisito) 
    {
        Asignatura opcional = new Opcional(codigo,nombre,creditos,tipo,prerrequisito);
        boolean ingresar = listaasignaturas.ingresarAsignatura(opcional);
        return ingresar;
    }

    /**
     * method that the teacher enters the general list of people.
     * @param rut
     * @param correo
     * @param contraseña
     * @param salario
     * @return 
     */
    @Override
    public boolean ingresarProfesor(String rut, String correo, String contraseña, int salario) 
    {
        Persona profesor = new Profesor(rut,correo,contraseña,salario);
        boolean ingresar = listapersonas.ingresarPersona(profesor);
        return ingresar;
    }

    /**
     * method that enters the parallel to the general list of parallels
     * @param numero
     * @return 
     */
    @Override
    public boolean ingresarParalelo(int numero) 
    {
        Paralelo paralelo = new Paralelo(numero);
        boolean ingresar = listaparalelos.ingresarParalelo(paralelo);
        return ingresar;
    }

    /**
     * method that associates the subjects to the student together with the grade
     * @param rutEstudiante
     * @param codigo
     * @param nota 
     */
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

    /**
     * method that associates the subjects to the student together with the parallel
     * @param rutEstudiante
     * @param codigo
     * @param paralelo 
     */
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

    /**
     * method that associates the subjects that have another subject as a prerequisite.
     * @param codigoAsignatura
     * @param codigoPrerrequisito 
     */
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

    /**
     * method that assigns the parallel to the particular list of the teacher and the subject
     * @param numero
     * @param codigoAsignatura
     * @param rutProfesor 
     */
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

    /**
     * method that obtain all the information of the students to overwrite it in the text file
     * @return 
     */
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

    /**
     * method that checks the existence of the email and the password and that it belongs to the person.
     * @param correo
     * @param contraseña
     * @return 
     */
    @Override
    public boolean verificarInicioSesion(String correo, String contraseña) 
    {
        Persona persona = listapersonas.buscarCorreo(correo);
        return persona != null && persona.getContraseña().equals(contraseña);
    }

    /**
     * method that identifies if the email belongs to a student or teacher
     * @param correo
     * @return 
     */
    @Override
    public String IdentificarUsuario(String correo) 
    {
        String identificador = "";
        Persona persona = listapersonas.buscarCorreo(correo);
        if(persona != null) 
        {
            if(persona instanceof Estudiante) 
            {
                identificador = "Estudiante";
            }
            else
            {
                identificador = "Profesor";
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la persona");
        }
        
        return identificador;
    }

    /**
     * method obtained by all the subjects in which the student can enroll.
     * @param correo
     * @return 
     */
    @Override
    public String obtenerAsignaturasparaInscripcion(String correo) 
    {
        String salida = "";
        Estudiante estudiante = (Estudiante) listapersonas.buscarCorreo(correo);
        
        if(estudiante != null) 
        {
            for(int a=0;a<estudiante.getLs().getCant();a++) 
            {
                Asignatura asignatura = estudiante.getLs().getLista()[a];
                if(asignatura.getNota() == 0) 
                {
                    
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el estudiante");
        }
        return salida;
    }

    /**
     * method obtained by all available parallels for the indicated subject
     * @param codigoAsignatura
     * @return 
     */
    @Override
    public String obtenerParalelosporAsignatura(int codigoAsignatura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * method that enrolls the student to the subject in the chosen section
     * @param correo
     * @param codigoAsignatura
     * @param numeroParalelo 
     */
    @Override
    public void inscripcionAsignatura(String correo, int codigoAsignatura, int numeroParalelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * method obtained by all the subjects registered by the student so far
     * @param correo
     * @return 
     */
    @Override
    public String obtenerAsignaturasInscritas(String correo) 
    {
        String salida = "\nAsignaturas Inscritas\n";
        Estudiante estudiante = (Estudiante) listapersonas.buscarCorreo(correo);
        
        if(estudiante!=null) 
        {
            int cant=0;
            for(int i=0;i<estudiante.getLs().getCant();i++) 
            {
                Asignatura asignatura = estudiante.getLs().getLista()[i];
                if(asignatura.getNota() == 0) 
                {
                    salida+= "\n- " + asignatura.getNombre() + " "+ asignatura.getCodigo();
                    cant = 1;
                }
            }
            if(cant == 0) 
            {
                salida += "\nNo tiene asignaturas inscritas";
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el estudiante");
        }
        
        
        
        return salida;
    }

    /**
     * method that eliminates the subject chosen by the student
     * @param correo
     * @param codigoAsignatura
     * @return 
     */
    @Override
    public boolean eliminacionAsignatura(String correo, int codigoAsignatura) 
    {
        Estudiante estudiante = (Estudiante) listapersonas.buscarCorreo(correo);
        
        if(estudiante!=null) 
        {
            return estudiante.getLs().eliminarAsignatura(codigoAsignatura);
        }
        else 
        {
            throw new NullPointerException("No se encontro el estudiante");
        }        
    }

    /**
     * method obtained by all the parallels that the teacher must dictate in the semester
     * @param correoProfesor
     * @return 
     */
    @Override
    public String obtenerParalelosProfesor(String correoProfesor) 
    {
        String salida = "";
        Profesor profesor = (Profesor) listapersonas.buscarCorreo(correoProfesor);
        
        if(profesor!=null) 
        {
            for(int a=0;a<profesor.getLs().getCant();a++) 
            {
                Asignatura asignatura = profesor.getLs().getLista()[a];
        
                if(asignatura != null) 
                {
                    if(asignatura instanceof Obligatoria) 
                    {
                        Obligatoria obligatoria = (Obligatoria) asignatura;
                        for(int b=0;b<obligatoria.getLp().getCant();b++) 
                        {
                            Paralelo paralelo = obligatoria.getLp().getLista()[b];
                            salida += "\n Paralelo " + paralelo.getNumero() + "\n";
                        }
                    }
                    else 
                    {
                        Opcional opcional = (Opcional) asignatura;
                        salida += "\n Paralelo " + opcional.getParalelo().getNumero() + "\n";
                    }
                }
                else 
                {
                    throw new NullPointerException("No se encontro la asignatura");
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el profesor");
        }
        
        
        return salida;
    }

    /**
     * method obtained by all students of the indicated section
     * @param numeroParalelo
     * @return 
     */
    @Override
    public String obtenerAlumnosParalelos(int numeroParalelo) 
    {
        String salida = "";
        Paralelo paralelo = listaparalelos.buscarParalelo(numeroParalelo);
        if(paralelo !=null) 
        {
            for(int i=0;i<paralelo.getLp().getCant();i++) 
            {
                Persona persona = paralelo.getLp().getLista()[i];
                
                if(persona instanceof Estudiante) 
                {
                    Estudiante estudiante = (Estudiante) persona;
                    
                    salida += "\n- " + estudiante.getRut() + " correo: " + estudiante.getCorreo();
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el paralelo");
        }
        
        
        return salida;
    }

    /**
     * method obtained by all the subjects that the teacher dictated in the semester
     * @param correoProfesor
     * @return 
     */
    @Override
    public String obtenerAsignaturasProfesor(String correoProfesor) 
    {
        Profesor profesor = (Profesor) listapersonas.buscarCorreo(correoProfesor);
        String salida = "\nAsignaturas: \n";
        if(profesor != null) 
        {
            for(int a=0;a<profesor.getLs().getCant();a++) 
            {
                salida += "\n- " + profesor.getLs().getLista()[a].getNombre() + " " + profesor.getLs().getLista()[a].getCodigo();
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el profesor");
        }
        
        return salida;
    }

    /**
     * method obtained by all students of the indicated subject
     * @param codigoAsignatura
     * @return 
     */
    @Override
    public String obtenerAlumnosAsignatura(int codigoAsignatura) 
    {
        String salida = "";
        Asignatura asignatura = listaasignaturas.buscarAsignatura(codigoAsignatura);
        
        if(asignatura != null) 
        {
            if(asignatura instanceof Obligatoria) 
            {
                Obligatoria obligatoria = (Obligatoria) asignatura;
                for(int a=0;a<obligatoria.getLp().getCant();a++) 
                {
                    Paralelo paralelo = obligatoria.getLp().getLista()[a];
                    salida += "\n Paralelo " + paralelo.getNumero() + "\n";
                    
                    for(int b=0;b<paralelo.getLp().getCant();b++) 
                    {
                        Persona persona = paralelo.getLp().getLista()[b];
                        salida += "\n- " + persona.getRut();
                    }
                }
            }
            else 
            {
                Opcional opcional = (Opcional) asignatura;
                salida += "\n Paralelo " + opcional.getParalelo().getNumero() + "\n";
                for(int i=0;i<opcional.getParalelo().getLp().getCant();i++) 
                {
                    Persona persona = opcional.getParalelo().getLp().getLista()[i];
                    salida += "\n- " + persona.getRut();
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro la asignatura");
        }
        return salida;
    }

    /**
     * method that introduces the final grade to the student of the indicated subject
     * @param rutAlumno
     * @param codigoAsignatura
     * @param nota 
     */
    @Override
    public void introducirNotaFinal(String rutAlumno, int codigoAsignatura, double nota) 
    {
        Estudiante estudiante = (Estudiante) listapersonas.buscarPersona(rutAlumno);
        
        if(estudiante!=null) 
        {
            for(int i=0;i<estudiante.getLs().getCant();i++) 
            {
                Asignatura asignatura = estudiante.getLs().getLista()[i];
                if(asignatura != null) 
                {
                    if(asignatura.getCodigo() == codigoAsignatura) 
                    {
                        asignatura.setNota(nota);
                        break;
                    }
                }
                else 
                {
                    throw new NullPointerException("No se encontro la asignatura");
                }
            }
        }
        else 
        {
            throw new NullPointerException("No se encontro el estudiante");
        }
    }

    /**
     * method that obtain the rout of the students who have all the subjects of the grid passed
     * @return 
     */
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
