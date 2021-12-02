
package Logica;


public interface Sistema 
{
    public boolean ingresarEstudiante (String rut, String correo, int nivel, int contraseña);
    public boolean ingresarAsignaturaObligatoria (String codigo, String nombre, int creditos, String tipo ,int nivel, int nota);
    public boolean ingresarAsignaturaOpcional (String codigo, String nombre, int creditos, String tipo,int nota, int prerrequisito);
    public boolean ingresarProfesor (String rut, String correo, String contraseña, int salario);
    public boolean ingresarParalelo (int numero);
    public void asociarAsignaturaEstudiante (String rutEstudiante, int codigo, int nota);
    public void asociarAsignaturasPrerrequisitos (int codigoAsignatura, int codigoPrerrequisito);
    public void asociarParalelo (int numero, int codigoAsignatura, String rutProfesor);
    public String obtenerEstudiantes();
    public boolean verificarInicioSesion (String rut, String contraseña);
    public String obtenerAsignaturasparaInscripcion (String rut);
    public void obtenerParalelosporAsignatura (int codigoAsignatura);
    public void inscripcionAsignatura (String rut, int codigoAsignatura, int numeroParalelo);
    public String obtenerAsignaturasInscritas (String rut);
    public boolean eliminacionAsignatura (String rut, int codigoAsignatura);
    public String obtenerParalelosProfesor (String rutProfesor);
    public String obtenerAlumnosParalelos (int numeroParalelo);
    public String obtenerAsignaturasProfesor (String rutProfesor);
    public String obtenerAlumnosAsignatura (String nombreAsignatura);
    public void introducirNotaFinal (String rutAlumno, String nombreAsignatura, int nota);
    public String obtenerEstudiantesEgresados ();
  
}