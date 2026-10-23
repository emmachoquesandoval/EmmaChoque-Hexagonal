# Reservas de libros — Arquitectura hexagonal

Aplicación de consola escrita en Java para registrar reservas de libros. El proyecto sirve como ejemplo de arquitectura hexagonal: la lógica de reservas trabaja con un puerto (`ReservationRepository`) y no depende de cómo se almacenan los datos.

## Funcionalidad

Al ejecutar la aplicación aparece un menú que permite:

- Crear una reserva indicando el nombre del usuario y el título del libro.
- Mostrar las reservas creadas durante la ejecución actual.
- Salir de la aplicación.

El programa rechaza nombres y títulos vacíos. Las opciones desconocidas muestran un error y el menú vuelve a aparecer.

## Estructura

El código está dentro del paquete base `nf1.fullstack.arquictectures.hexagonal1`:

```text
src/nf1/fullstack/arquictectures/hexagonal1/
├── MainReservationsApp.java
├── application/
│   ├── ControllerReservation.java
│   └── ReservationRepository.java
├── domain/
│   ├── Book.java
│   ├── Reservation.java
│   ├── ReservationService.java
│   └── User.java
└── Infrastructure/adapter/
    ├── InMemoryReservationRepositoryAdapter.java
    └── FileReservationRepositoryAdapter.java
```

### Responsabilidad de cada parte

- **Dominio**: `User`, `Book` y `Reservation` representan los datos del problema. `ReservationService` crea reservas y delega su almacenamiento al repositorio.
- **Aplicación**: `ReservationRepository` es el puerto de salida que define `save` y `findAll`. `ControllerReservation` presenta el menú, lee la entrada y coordina las llamadas al servicio.
- **Infraestructura**: `InMemoryReservationRepositoryAdapter` implementa el puerto con una lista en memoria.
- **Punto de composición**: `MainReservationsApp` instancia y conecta el adaptador, el servicio y el controlador. Es el lugar desde el que se inicia la aplicación.

El sentido de las dependencias es `Infrastructure → application → domain`: el dominio no necesita conocer la consola ni el adaptador concreto. El servicio recibe el puerto por constructor (inyección de dependencias), por lo que puede trabajar con cualquier implementación de `ReservationRepository`.

## Cómo funciona una reserva

1. `ControllerReservation` pide el nombre y el título.
2. El controlador crea los objetos `User` y `Book` y llama a `ReservationService.reserve`.
3. El servicio construye una `Reservation` y la guarda mediante `ReservationRepository`.
4. El controlador muestra la confirmación. Al listar, el servicio obtiene las reservas con `findAll` y el controlador las imprime.

## Ejecutar

Abre el proyecto en IntelliJ IDEA y ejecuta `MainReservationsApp`, situado en `src/nf1/fullstack/arquictectures/hexagonal1/MainReservationsApp.java`.

También se puede compilar y ejecutar desde la raíz del proyecto con un JDK instalado:

```bash
mkdir -p out
javac -d out $(find src -name '*.java')
java -cp out nf1.fullstack.arquictectures.hexagonal1.MainReservationsApp
```

## Persistencia actual

El `main` usa `InMemoryReservationRepositoryAdapter`, así que las reservas solo permanecen mientras la aplicación está abierta. Al salir se pierden.

`FileReservationRepositoryAdapter` está creado como archivo, pero todavía no implementa `ReservationRepository` ni la lectura/escritura. Por tanto, aún no se puede cambiar a persistencia en archivo hasta completar esa implementación.

## Documentación del ejercicio

`TODO.md` contiene la descripción traducida del ejercicio original. Está excluido de Git mediante `.gitignore` y solo está disponible en la copia local del proyecto.
