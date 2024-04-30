package ar.edu.unju.fi.ejercicio04.main;

import ar.edu.unju.fi.ejercicio04.constantes.*;
import ar.edu.unju.fi.ejercicio04.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1 - Alta de jugador");
            System.out.println("2 - Mostrar todos los jugadores");
            System.out.println("3 - Modificar la posición de un jugador");
            System.out.println("4 - Eliminar un jugador");
            System.out.println("5 - Salir");
            System.out.print("Elija una opción: ");

            opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    altaJugador(jugadores, scanner);
                    break;
                case 2:
                    mostrarJugadores(jugadores);
                    break;
                case 3:
                    modificarPosicionJugador(jugadores, scanner);
                    break;
                case 4:
                    eliminarJugador(jugadores, scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción invalida. Pruebe otra vez.");
                    break;
            }
        } while (opcion != 5);
    }
    private static void altaJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        System.out.println("Alta de jugador");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.next();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.next();
        System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.next());
        System.out.print("Ingrese la nacionalidad: ");
        String nacionalidad = scanner.next();
        System.out.print("Ingrese la estatura (en metros): ");
        double estatura = obtenerDouble(scanner);
        System.out.print("Ingrese el peso (en kilogramos): ");
        double peso = obtenerDouble(scanner);

        System.out.println("Posiciones:");
        for (Posicion posicion : Posicion.values()) {
            System.out.println((posicion.ordinal() + 1) + " - " + posicion);
        }
        System.out.print("Elija la posición del jugador: ");
        int opcionPosicion = obtenerEntero(scanner);
        Posicion posicion = Posicion.values()[opcionPosicion - 1];

        jugadores.add(new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion));
        System.out.println("Jugador añadido correctamente.");
    }

    private static void mostrarJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para mostrar.");
        } else {
            System.out.println("Lista de jugadores:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador.getNombre() + " " + jugador.getApellido() + " - Edad: " + jugador.calcularEdad() +
                        " - Nacionalidad: " + jugador.getNacionalidad() + " - Estatura: " + jugador.getEstatura() +
                        "m - Peso: " + jugador.getPeso() + "kg - Posición: " + jugador.getPosicion());
            }
        }
    }

    private static void modificarPosicionJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para modificar.");
            return;
        }
        System.out.print("Ingrese el nombre del jugador a modificar: ");
        String nombreModificar = scanner.next();
        System.out.print("Ingrese el apellido del jugador a modificar: ");
        String apellidoModificar = scanner.next();

        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombreModificar) && jugador.getApellido().equalsIgnoreCase(apellidoModificar)) {
                System.out.println("Modificando posición de " + jugador.getNombre() + " " + jugador.getApellido());
                System.out.println("Posiciones:");
                for (Posicion posicion : Posicion.values()) {
                    System.out.println((posicion.ordinal() + 1) + " - " + posicion);
                }
                System.out.print("Elija la nueva posición del jugador: ");
                int opcionPosicion = obtenerEntero(scanner);
                Posicion nuevaPosicion = Posicion.values()[opcionPosicion - 1];
                jugador.setPosicion(nuevaPosicion);
                System.out.println("Posición modificada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró un jugador con el nombre y apellido especificados.");
    }

    private static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner scanner) {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores para eliminar.");
            return;
        }
        System.out.print("Ingrese el nombre del jugador a eliminar: ");
        String nombreEliminar = scanner.next();
        System.out.print("Ingrese el apellido del jugador a eliminar: ");
        String apellidoEliminar = scanner.next();

        Iterator<Jugador> iterator = jugadores.iterator();
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getNombre().equalsIgnoreCase(nombreEliminar) && jugador.getApellido().equalsIgnoreCase(apellidoEliminar)) {
                iterator.remove();
                System.out.println("Jugador eliminado correctamente.");
                return;
            }
        }
        System.out.println("No se encontró un jugador con el nombre y apellido especificados.");
    }
    
    private static int obtenerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Introduzca un número: ");
                scanner.next();
            }
        }
    }

    private static double obtenerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Introduzca un número decimal: ");
                scanner.next();
            }
        }
    }
}