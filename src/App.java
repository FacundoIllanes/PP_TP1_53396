import modelo.*;
import excepciones.CupoExcedidoException;
import java.io.IOException;
import java.util.List;
import hilos.EnvioTicketThread;

public class App {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE EJERCICIO 1 ===");
        // a. Registrar estudiantes
        Estudiante e1 = new Estudiante("E101", "Facundo Illanes");
        Estudiante e2 = new Estudiante("E102", "Lionel Messi");
        Estudiante e3 = new Estudiante("E103", "Emiliano Martinez");

        // b. Construir evento
        EventoUniversitario evento = new EventoUniversitario("EVT-2026", "Jornada de Sistemas", 10000.0, false);

        // c. Asignar sala
        Sala sala1 = new Sala(1, "Aula 2");
        evento.asignarSala(sala1);

        // d. Crear actividades (modelo.Charla y modelo.Taller)
        evento.crearActividad(1, "Introducción a IA", 30, "modelo.Charla", "Dr. Pérez");
        evento.crearActividad(2, "modelo.Taller de Paradigmas", 20, "modelo.Taller", "true");

        // e. Inscribir estudiantes en las actividades
        Actividad charla = evento.getActividades().get(0);
        Actividad taller = evento.getActividades().get(1);

        try {
            System.out.println("\n-- Probando Persistencia del Evento --");
            boolean guardado = evento.PersistirEvento();
            if (guardado) {
                System.out.println("¡Evento serializado y guardado en disco correctamente!");
            }

            System.out.println("\n-- Probando Recuperación del Evento --");
            EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("EVT-2026");
            System.out.println("¡Evento recuperado con éxito desde el archivo binario!");

            // Opcional: mostrar los datos del evento recuperado
            // eventoRecuperado.mostrarDatos();

        } catch (IOException e) {
            System.out.println("[CATCH PERSISTENCIA - IO]: Error de entrada/salida al leer/escribir archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[CATCH PERSISTENCIA - ClassNotFound]: La clase no coincide al deserializar: " + e.getMessage());
        } finally {
            System.out.println("\n[FINALLY]: Bloque final ejecutado. Finalizaron las pruebas del Ejercicio 1.");
        }

        // Total de eventos creados
        System.out.println("\nTotal de eventos creados: " + EventoUniversitario.getCantidadEventos());



        System.out.println("=== PRUEBAS DE EJERCICIO 2 ===");

        // Creamos un Curso (que también es Certificable)
        evento.crearActividad(3, "Curso Avanzado de Java", 15, "Curso", "4"); // Pasamos los parámetros según tu método crearActividad

        // Supongamos que inscribimos a un estudiante en el taller o curso
        Actividad curso = evento.getActividades().get(evento.getActividades().size() - 1); // Tomamos el último creado

        try {
            var inscripcionCurso = curso.inscribir(e1);

            // Verificamos si la actividad implementa Certificable para emitir el certificado
            if (curso instanceof certificacion.certificable) {
                certificacion.certificable actividadCertificable = (certificacion.certificable) curso;
                String certificado = actividadCertificable.GenerarCertificado(e1);
                System.out.println("\n[CERTIFICADO EMITIDO]:\n" + certificado);
            } else {
                System.out.println("Esta actividad no emite certificados.");
            }

        } catch (excepciones.CupoExcedidoException e) {
            System.out.println("Error de cupo: " + e.getMessage());
        }


        System.out.println("\n=== PRUEBAS DE EJERCICIO 3 (FILTROS Y COSTOS) ===");

        // a. Filtrar actividades por tipo concreto (devuelve List<Charla>, List<Taller>, List<Curso>)
        List<modelo.Charla> charlas = evento.filtrarActividadesPorTipo(modelo.Charla.class);
        List<modelo.Taller> talleres = evento.filtrarActividadesPorTipo(modelo.Taller.class);
        List<Curso> cursos = evento.filtrarActividadesPorTipo(modelo.Curso.class);

        System.out.println("Cantidad de Charlas encontradas: " + charlas.size());
        System.out.println("Cantidad de Talleres encontrados: " + talleres.size());
        System.out.println("Cantidad de Cursos encontrados: " + cursos.size());

        // b. Calcular costo de materiales usando wildcards con una sub-lista o con todas las actividades
        double costoTalleres = evento.calcularCostoMateriales(talleres);
        System.out.println("Costo total de materiales de los talleres: $" + costoTalleres);

        double costoTotalGeneral = evento.calcularCostoMateriales(evento.getActividades());
        System.out.println("Costo total de materiales de todas las actividades del evento: $" + costoTotalGeneral);

        System.out.println("\n=== PRUEBAS DE EJERCICIO 4 (HILOS) ===");

        // Creamos e iniciamos el hilo independiente de envío de tickets
        hilos.EnvioTicketThread hiloEnvio = new hilos.EnvioTicketThread(evento);
        hiloEnvio.start(); // Comienza a ejecutarse en paralelo en segundo plano

        // El hilo principal continúa mostrando información sin detenerse
        System.out.println("[HILO PRINCIPAL] El programa principal sigue ejecutándose mientras se envían los tickets...");
        for (int i = 1; i <= 3; i++) {
            System.out.println("[HILO PRINCIPAL] Tarea principal en ejecución - Paso " + i);
            try {
                Thread.sleep(400); // Pequeña pausa para notar cómo se intercalan los mensajes en la consola
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Esperamos a que el hilo secundario termine prolijamente antes de finalizar
        try {
            hiloEnvio.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== ¡TODOS LOS EJERCICIOS COMPLETADOS CON ÉXITO! ===");
    }

}