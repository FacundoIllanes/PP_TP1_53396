import java.util.ArrayList;
import java.util.List;

public abstract class Actividad {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) {
        if (inscripciones.size() < cupoMaximo) {
            Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
            inscripciones.add(nuevaInscripcion);
            return nuevaInscripcion;
        } else {
            System.out.println("Cupo lleno para la actividad: " + titulo);
            return null;
        }
    }

    public void mostrarInscripciones() {
        System.out.println("   Inscriptos en " + titulo + ":");
        if (inscripciones.isEmpty()) {
            System.out.println("   (No hay alumnos inscriptos)");
        } else {
            for (Inscripcion ins : inscripciones) {
                System.out.println("    - Legajo: " + ins.getEstudiante().getLegajo() +
                        " | Nombre: " + ins.getEstudiante().getNombre() +
                        " | Fecha: " + ins.getFecha());
            }
        }
    }

    // Método final que no puede ser redefinido por las subclases
    public final void mostrarIdentificacion() {
        System.out.println("Actividad ID: " + id + " | Título: " + titulo + " | Tipo: " + getTipo());
    }

    // Métodos abstractos a implementar por Charla y Taller
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
}
