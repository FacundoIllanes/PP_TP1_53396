package modelo;

import certificacion.certificable;

import java.io.Serializable;

public class Taller extends Actividad implements certificable, Serializable{
    private final boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        // $5000 si requiere notebook, $2000 si no
        return requiereNotebook ? 5000.0 : 2000.0;
    }

    @Override
    public String getTipo() {
        return "modelo.Taller (Requiere Notebook: " + (requiereNotebook ? "Sí" : "No") + ")";
    }
    public String GenerarCertificado(Estudiante estudiante){
        return "Certificado emitido por "+ ENTIDAD_EMISORA +" al estudiante "+ estudiante.getNombre();

    }
}