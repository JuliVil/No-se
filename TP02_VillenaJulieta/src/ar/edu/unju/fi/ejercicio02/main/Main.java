package ar.edu.unju.fi.ejercicio02.main;

import ar.edu.unju.fi.ejercicio02.constantes.*;
import ar.edu.unju.fi.ejercicio02.model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Efemeride> efemerides = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1 - Crear efeméride");
            System.out.println("2 - Mostrar efemérides");
            System.out.println("3 - Eliminar efeméride");
            System.out.println("4 - Modificar efeméride");
            System.out.println("5 - Salir");
            System.out.print("Elija una opción: ");

            opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    crearEfemeride(efemerides, scanner);
                    break;
                case 2:
                    mostrarEfemerides(efemerides);
                    break;
                case 3:
                    eliminarEfemeride(efemerides, scanner);
                    break;
                case 4:
                    modificarEfemeride(efemerides, scanner);
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

    private static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        System.out.println("Creando Efeméride");
        System.out.print("Ingrese el código: ");
        String codigo = scanner.next();
        System.out.print("Ingrese el mes (1-12): ");
        int mesNumero = obtenerEntero(scanner);
        while (mesNumero < 1 || mesNumero > 12) {
            System.out.print("Ingrese un número válido para el mes (1-12): ");
            mesNumero = obtenerEntero(scanner);
        }
        Mes mes = Mes.values()[mesNumero - 1];
        System.out.print("Ingrese el día: ");
        int dia = obtenerEntero(scanner);
        System.out.print("Ingrese el detalle: ");
        String detalle = scanner.next();

        efemerides.add(new Efemeride(codigo, mes, dia, detalle));
        System.out.println("Efeméride creada exitosamente.");
    }

    private static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para mostrar.");
        } else {
            System.out.println("Lista de efemérides:");
            for (Efemeride efemeride : efemerides) {
                System.out.println("Código: " + efemeride.getCodigo() + " - Mes: " + efemeride.getMes() + " - Día: " + efemeride.getDia() + " - Detalle: " + efemeride.getDetalle());
            }
        }
    }

    private static void eliminarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para eliminar.");
            return;
        }

        mostrarEfemerides(efemerides);
        System.out.print("Ingrese el índice de la efeméride que desea eliminar: ");
        int indice = obtenerEntero(scanner);

        if (indice >= 1 && indice <= efemerides.size()) {
            efemerides.remove(indice - 1);
            System.out.println("Efeméride eliminada correctamente.");
        } else {
            System.out.println("Índice inválido. No se eliminó ninguna efeméride.");
        }
    }

    private static void modificarEfemeride(ArrayList<Efemeride> efemerides, Scanner scanner) {
        if (efemerides.isEmpty()) {
            System.out.println("No hay efemérides para modificar.");
            return;
        }

        mostrarEfemerides(efemerides);
        System.out.print("Ingrese el índice de la efeméride que desea modificar: ");
        int indice = obtenerEntero(scanner);

        if (indice >= 1 && indice <= efemerides.size()) {
            Efemeride efemeride = efemerides.get(indice - 1);
            System.out.println("Modificando efeméride: " + efemeride.getCodigo());
            System.out.print("Ingrese el nuevo código: ");
            efemeride.setCodigo(scanner.next());
            System.out.print("Ingrese el nuevo mes (1-12): ");
            int mesNumero = obtenerEntero(scanner);
            while (mesNumero < 1 || mesNumero > 12) {
                System.out.print("Ingrese un número válido para el mes (1-12): ");
                mesNumero = obtenerEntero(scanner);
            }
            efemeride.setMes(Mes.values()[mesNumero - 1]);
            System.out.print("Ingrese el nuevo día: ");
            efemeride.setDia(obtenerEntero(scanner));
            System.out.print("Ingrese el nuevo detalle: ");
            efemeride.setDetalle(scanner.next());
            System.out.println("Efeméride modificada correctamente.");
        } else {
            System.out.println("Índice inválido. No se modificó ninguna efeméride.");
        }
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
}