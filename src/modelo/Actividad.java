package modelo;

import excepciones.CupoExcedidoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad  implements Serializable {
    private int id;
    protected String titulo;
    protected int cupoMaximo;
    public static final int CUPO_MINIMO = 5;
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size() < cupoMaximo) {
            Inscripcion nuevaInscripcion = new Inscripcion(estudiante);
            inscripciones.add(nuevaInscripcion);
            return nuevaInscripcion;
        } else {
            throw new CupoExcedidoException("CUPO LLEGO PARA LA ACTIVIDAD: "+titulo);

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
        System.out.println("modelo.Actividad ID: " + id + " | Título: " + titulo + " | Tipo: " + getTipo());
    }

    // Métodos abstractos a implementar por modelo.Charla y modelo.Taller
    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public int getId() {
        return id; }
    public String getTitulo() {
        return titulo; }
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
}
