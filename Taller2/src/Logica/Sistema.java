
package Logica;


public interface Sistema 
{
    public boolean ingresarEstudiante (String rut, String correo, int nivel, String contraseña);
    public boolean ingresarAsignaturaObligatoria (int codigo, String nombre, int creditos, String tipo ,int nivel);
    public boolean ingresarAsignaturaOpcional (int codigo, String nombre, int creditos, String tipo, int prerrequisito);
    public boolean ingresarProfesor (String rut, String correo, String contraseña, int salario);
    public boolean ingresarParalelo (int numero);
    public void asociarAsignaturaEstudianteCursada (String rutEstudiante, int codigo, double nota);
    public void asociarAsignaturaEstudianteInscrita(String rutEstudiante, int codigo, int paralelo);
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
    public void introducirNotaFinal (String rutAlumno, String nombreAsignatura, double nota);
    public String obtenerEstudiantesEgresados ();
  
}
