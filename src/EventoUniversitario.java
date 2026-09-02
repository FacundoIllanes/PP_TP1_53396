import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id + "_copia";
        this.titulo = otro.titulo + " (Copia)";
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupo, String tipo, String datoExtra) {
        Actividad nuevaActividad = null;
        if (tipo.equalsIgnoreCase("Charla")) {
            nuevaActividad = new Charla(id, titulo, cupo, datoExtra);
        } else if (tipo.equalsIgnoreCase("Taller")) {
            boolean requiereNotebook = Boolean.parseBoolean(datoExtra);
            nuevaActividad = new Taller(id, titulo, cupo, requiereNotebook);
        }

        if (nuevaActividad != null) {
            this.actividades.add(nuevaActividad);
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double costoActividades = 0.0;
        for (Actividad act : actividades) {
            costoActividades += act.calcularCostoMateriales();
        }
        return (costoBase + costoActividades) * 1.21;
    }

    public void mostrarDatos() {
        System.out.println("=========================================");
        System.out.println("Evento ID: " + id + " | Título: " + titulo);
        System.out.println("Gratuito: " + (gratuito ? "Sí" : "No") + " | Costo Estimado: $" + calcularCostoEstimado());
        System.out.println("Sala: " + (sala != null ? sala.getNombre() : "Sin sala asignada"));
        System.out.println("Actividades del evento:");
        if (actividades.isEmpty()) {
            System.out.println("   (No hay actividades registradas)");
        } else {
            for (Actividad act : actividades) {
                System.out.print(" - ");
                act.mostrarIdentificacion();
                act.mostrarInscripciones();
            }
        }
        System.out.println("=========================================");
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }
}