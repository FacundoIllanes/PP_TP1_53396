package modelo;
import certificacion.certificable;
import java.io.Serializable;


public class Curso extends Actividad implements certificable, Serializable {
    private static final long serialVersionUID = 1L;

    private int duracionSemanas; // O los atributos que consideres necesarios para un curso

    public Curso(int id, String titulo, int cupoMaximo, int duracionSemanas) {
        super(id, titulo, cupoMaximo);
        this.duracionSemanas = duracionSemanas;
    }

    @Override
    public double calcularCostoMateriales() {
        return 500.0; // O el cálculo que prefieras para los cursos
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String GenerarCertificado(Estudiante estudiante) {
        return "Certificado emitido por " + ENTIDAD_EMISORA + " al estudiante " + estudiante.getNombre() + " por aprobar el curso: " + this.titulo;
    }
}
