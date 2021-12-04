
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
    public boolean verificarInicioSesion (String correo, String contraseña);
    public String IdentificarUsuario(String correo);
    public String obtenerAsignaturasparaInscripcion (String correo);
    public String obtenerParalelosporAsignatura (int codigoAsignatura);
    public void inscripcionAsignatura (String correo, int codigoAsignatura, int numeroParalelo);
    public String obtenerAsignaturasInscritas (String correo);
    public boolean eliminacionAsignatura (String correo, int codigoAsignatura);
    public String obtenerParalelosProfesor (String correoProfesor);
    public String obtenerAlumnosParalelos (int numeroParalelo);
    public String obtenerAsignaturasProfesor (String correoProfesor);
    public String obtenerAlumnosAsignatura (int codigoAsignatura);
    public void introducirNotaFinal (String rutAlumno, int codigoAsignatura, double nota);
    public String obtenerEstudiantesEgresados ();
  
}
