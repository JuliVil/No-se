package ar.edu.unju.fi.ejercicio07.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private boolean disponible;

    public Producto(String nombre, String categoria, double precio, boolean disponible) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.disponible = disponible;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = cargarProductos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("1 - Mostrar productos disponibles");
            System.out.println("2 - Mostrar productos no disponibles");
            System.out.println("3 - Incrementar precios en un 20%");
            System.out.println("4 - Mostrar productos de la categoría Electrohogar disponibles");
            System.out.println("5 - Ordenar productos por precio descendente");
            System.out.println("6 - Mostrar nombres de productos en mayúsculas");
            System.out.println("7 - Salir");

            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarProductos(productos, p -> p.isDisponible());
                    break;
                case 2:
                    mostrarProductosNoDisponibles(productos, p -> !p.isDisponible());
                    break;
                case 3:
                    ArrayList<Producto> productosIncrementados = incrementarPrecios(productos);
                    mostrarProductos(productosIncrementados, p -> true);
                    break;
                case 4:
                    mostrarProductos(productos, p -> p.getCategoria().equals("Electrohogar") && p.isDisponible());
                    break;
                case 5:
                    ordenarProductosPorPrecioDescendente(productos);
                    mostrarProductos(productos, p -> true);
                    break;
                case 6:
                    mostrarNombresEnMayusculas(productos);
                    break;
                case 7:
                    System.out.println("¡Adiós!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    scanner.close();
            }
        }
    }

    private static ArrayList<Producto> cargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Lavadora", "Electrohogar", 399.99, true));
        productos.add(new Producto("TV", "Electrohogar", 499.99, true));
        productos.add(new Producto("Horno", "Electrohogar", 199.99, false));
        productos.add(new Producto("Refrigerador", "Electrohogar", 799.99, true));
        productos.add(new Producto("Licuadora", "Electrohogar", 29.99, true));
        productos.add(new Producto("Teléfono", "Electrónica", 299.99, true));
        productos.add(new Producto("Tablet", "Electrónica", 199.99, true));
        productos.add(new Producto("Laptop", "Electrónica", 899.99, false));
        productos.add(new Producto("Secadora", "Electrohogar", 299.99, true));
        productos.add(new Producto("Aspiradora", "Electrohogar", 99.99, true));
        productos.add(new Producto("Microondas", "Electrohogar", 79.99, false));
        productos.add(new Producto("Plancha", "Electrohogar", 49.99, true));
        productos.add(new Producto("Cafetera", "Electrohogar", 39.99, true));
        productos.add(new Producto("Batidora", "Electrohogar", 49.99, false));
        productos.add(new Producto("Tostadora", "Electrohogar", 29.99, true));
        return productos;

    }
    private static void mostrarProductos(ArrayList<Producto> productos, Predicate<Producto> filtro) {
        for (Producto producto : productos) {
            if (filtro.test(producto)) {
                System.out.println(producto);
            }
        }
    }

    private static void mostrarProductosNoDisponibles(ArrayList<Producto> productos, Predicate<Producto> filtro) {
        for (Producto producto : productos) {
            if (filtro.test(producto)) {
                System.out.println(producto);
            }
        }
    }

    private static ArrayList<Producto> incrementarPrecios(ArrayList<Producto> productos) {
        return productos.stream()
                .map(p -> new Producto(p.getNombre(), p.getCategoria(), p.getPrecio() * 1.20, p.isDisponible()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static void ordenarProductosPorPrecioDescendente(ArrayList<Producto> productos) {
        productos.sort(Comparator.comparing(Producto::getPrecio).reversed());
    }

    private static void mostrarNombresEnMayusculas(ArrayList<Producto> productos) {
        productos.stream()
                .map(p -> p.getNombre().toUpperCase())
                .forEach(System.out::println);
    }
}
