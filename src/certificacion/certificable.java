package certificacion;
import modelo.Estudiante;

public interface certificable {
    String ENTIDAD_EMISORA ="UTN Facultad Regional de Mendoza";
    String GenerarCertificado(Estudiante estudiante);
}
