package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fecha;
    private String estado;
    protected Estudiante estudiante;
    protected TicketDeAcceso ticket;

    public Inscripcion(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.fecha = LocalDate.now();
        this.estado = "Confirmada";
        this.ticket = new TicketDeAcceso();
    }


        public LocalDate getFecha () {
            return fecha;
        }

        public String getEstado () {
            return estado;
        }

        public Estudiante getEstudiante () {
            return estudiante;
        }
        public TicketDeAcceso getTicket() {
        return ticket;
        }



        public class TicketDeAcceso implements Serializable {
            private static final long serialVersionUID = 1L;
            private String codigoTicket;

            public TicketDeAcceso() {
                this.codigoTicket = "TICK-" + System.currentTimeMillis();
            }

            public void enviarTicket() {
                System.out.println("-> [TICKET ENVIADO] Código: " + codigoTicket + " | Estudiante: " + estudiante.getNombre());
            }

            public String getCodigoTicket() {
                return codigoTicket;
            }
        }
    }


