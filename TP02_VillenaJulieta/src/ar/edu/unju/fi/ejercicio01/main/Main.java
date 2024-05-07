package ar.edu.unju.fi.ejercicio01.main;

import ar.edu.unju.fi.ejercicio01.model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1 - Crear Producto");
            System.out.println("2 - Mostrar productos");
            System.out.println("3 - Modificar producto");
            System.out.println("4 - Salir");
            System.out.print("Elija una opción: ");

            opcion = obtenerEntero(scanner);

            switch (opcion) {
                case 1:
                    crearProducto(productos, scanner);
                    break;
                case 2:
                    mostrarProductos(productos);
                    break;
                case 3:
                    modificarProducto(productos, scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción invalida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    private static void crearProducto(ArrayList<Producto> productos, Scanner scanner) {
        System.out.println("Creando Producto");
        System.out.print("Ingrese el código del codigo: ");
        String codigo = scanner.next();
        System.out.print("Ingrese la descripción del codigo: ");
        String descripcion = scanner.next();
        System.out.print("Ingrese el precio unitario: ");
        double precioUnitario = obtenerDouble(scanner);

        System.out.println("------ Origen de fabricación ------");
        for (Producto.OrigenFabricacion origen : Producto.OrigenFabricacion.values()) {
            System.out.println((origen.ordinal() + 1) + " - " + origen);
        }
        System.out.print("Elija una opción: ");
        int origenIndex = obtenerEntero(scanner);
        Producto.OrigenFabricacion origenFabricacion = Producto.OrigenFabricacion.values()[origenIndex - 1];

        System.out.println("------ Categoría ------");
        for (Producto.Categoria categoria : Producto.Categoria.values()) {
            System.out.println((categoria.ordinal() + 1) + " - " + categoria);
        }
        System.out.print("Elija una opción: ");
        int categoriaIndex = obtenerEntero(scanner);
        Producto.Categoria categoria = Producto.Categoria.values()[categoriaIndex - 1];

        productos.add(new Producto(codigo, descripcion, precioUnitario, origenFabricacion, categoria, true));
        System.out.println("Producto creado exitosamente.");
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : productos) {
                System.out.println(producto.getCodigo() + " - " + producto.getDescripcion() + " - $" + producto.getPrecioUnitario() +
                        " - Origen: " + producto.getOrigenFabricacion() + " - Categoría: " + producto.getCategoria());
            }
        }
    }

    private static void modificarProducto(ArrayList<Producto> productos, Scanner scanner) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos para modificar.");
            return;
        }

        mostrarProductos(productos);
        System.out.print("Ingrese el código del producto que desea modificar: ");
        String codigoModificar = scanner.next();

        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigoModificar)) {
                System.out.println("Modificando producto: " + producto.getCodigo());
                System.out.println("1 - Descripción");
                System.out.println("2 - Precio unitario");
                System.out.println("3 - Origen de fabricación");
                System.out.println("4 - Categoría");
                System.out.print("Elija qué desea modificar: ");
                int opcionModificar = obtenerEntero(scanner);

                switch (opcionModificar) {
                    case 1:
                        System.out.print("Ingrese la nueva descripción: ");
                        producto.setDescripcion(scanner.next());
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo precio unitario: ");
                        producto.setPrecioUnitario(obtenerDouble(scanner));
                        break;
                    case 3:
                        System.out.println("------ Origen de fabricación ------");
                        for (Producto.OrigenFabricacion origen : Producto.OrigenFabricacion.values()) {
                            System.out.println((origen.ordinal() + 1) + " - " + origen);
                        }
                        
                        System.out.print("Elija una opción: ");
                        int origenIndex = obtenerEntero(scanner);
                        producto.setOrigenFabricacion(Producto.OrigenFabricacion.values()[origenIndex - 1]);
                        break;
                    case 4:
                        System.out.println("------ Categoría ------");
                        for (Producto.Categoria categoria : Producto.Categoria.values()) {
                            System.out.println((categoria.ordinal() + 1) + " - " + categoria);
                        }
                        System.out.print("Elija una opción: ");
                        int categoriaIndex = obtenerEntero(scanner);
                        producto.setCategoria(Producto.Categoria.values()[categoriaIndex - 1]);
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
                System.out.println("Producto modificado exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró un producto con el código especificado.");
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
                System.out.print("Entrada inválida. Introduzca un número: ");
                scanner.next();
            }
        }
    }
}