public class App {
    public static void main(String[] args) {
        // a. Registrar estudiantes
        Estudiante e1 = new Estudiante("E101", "Facundo Illanes");
        Estudiante e2 = new Estudiante("E102", "Lionel Messi");
        Estudiante e3 = new Estudiante("E103", "Emiliano Martinez");

        // b. Construir evento
        EventoUniversitario evento = new EventoUniversitario("EVT-2026", "Jornada de Sistemas", 10000.0, false);

        // c. Asignar sala
        Sala sala1 = new Sala(1, "Aula 2");
        evento.asignarSala(sala1);

        // d. Crear actividades (Charla y Taller)
        evento.crearActividad(1, "Introducción a IA", 30, "Charla", "Dr. Pérez");
        evento.crearActividad(2, "Taller de Paradigmas", 20, "Taller", "true");

        // e. Inscribir estudiantes en las actividades
        Actividad charla = evento.getActividades().get(0);
        Actividad taller = evento.getActividades().get(1);

        charla.inscribir(e1);
        charla.inscribir(e2);

        taller.inscribir(e2);
        taller.inscribir(e3);

        // f. Mostrar resumen de datos
        evento.mostrarDatos();

        // g. Mostrar total de eventos creados
        System.out.println("Total de eventos creados: " + EventoUniversitario.getCantidadEventos());
    }
}