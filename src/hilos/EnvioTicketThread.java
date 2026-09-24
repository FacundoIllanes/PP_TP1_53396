package hilos;
import modelo.EventoUniversitario;
import modelo.Actividad;
import modelo.Inscripcion;

public class EnvioTicketThread extends Thread {
    public EnvioTicketThread(EventoUniversitario evento) {
        this.evento = evento;
    }
    private EventoUniversitario evento;

    public void EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("\n[HILO SECUNDARIO] Iniciando el envío concurrente de tickets...");

        for (Actividad actividad : evento.getActividades()) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                // Verificamos si la inscripción está confirmada y tiene ticket
                if ("Confirmada".equalsIgnoreCase(inscripcion.getEstado()) && inscripcion.getTicket() != null) {
                    try {
                        Thread.sleep(600); // Pausa breve para simular el envío de red
                        inscripcion.getTicket().enviarTicket();
                    } catch (InterruptedException e) {
                        System.out.println("El hilo de envío fue interrumpido: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println("[HILO SECUNDARIO] ¡Todos los tickets han sido enviados con éxito!\n");
    }
}
