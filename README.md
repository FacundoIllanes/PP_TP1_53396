# Trabajo Práctico 1 - Programación Orientada a Objetos (Java)
**Universidad Tecnológica Nacional - Facultad Regional Mendoza**  
**Asignatura:** Paradigmas de Programación

## Descripción del Proyecto
Sistema orientado a objetos desarrollado en Java para la administración de eventos universitarios, salas, actividades (Charlas y Talleres) e inscripciones de estudiantes.

El modelo evoluciona aplicando los principios fundamentales de la POO:
- **Encapsulamiento:** Control de acceso mediante modificadores privados y getters/setters.
- **Abstracción y Herencia:** Uso de la clase abstracta `Actividad` y sus subclases `Charla` y `Taller`.
- **Polimorfismo:** Tratamiento unificado de actividades y cálculo dinámico de costos.
- **Relaciones entre Clases:**
    - **Agregación:** Entre `EventoUniversitario` y `Sala`.
    - **Composición:** Entre `EventoUniversitario` y `Actividad`.
    - **Asociación:** Entre `Inscripcion` y `Estudiante`.

---

## Archivos de Documentación Requeridos
- `mapa_memoria.png`: Mapa gráfico de memoria Heap y Stack (Ejercicio 4).
- `consola.png`: Captura de pantalla con la ejecución correcta de la clase `App`.
- -----------------------------------------------------------------------------------------------------------------------
Esta **Parte 2** evoluciona el modelo aplicando conceptos avanzados de programación:
* **Persistencia:** Serialización y deserialización de objetos en archivos binarios (`.dat`).
* **Manejo de Excepciones:** Excepciones predefinidas (`IOException`, `ClassNotFoundException`) y personalizadas (`CupoExcedidoException`).
* **Tipado Genérico y Wildcards:** Métodos parametrizados para filtrado de colecciones y uso de comodines (`? extends Actividad`) para cálculo flexible de costos.
* **Clases Anidadas:** Implementación de la clase interna miembro `TicketDeAcceso` dentro de la inscripción.
* **Concurrencia:** Procesamiento asíncrono y paralelo mediante hilos (`EnvioTicketThread`).