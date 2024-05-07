package ar.edu.unju.fi.ejercicio05.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio05.model.*;
import ar.edu.unju.fi.ejercicio01.model.*;
import ar.edu.unju.fi.ejercicio01.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio01.model.Producto.OrigenFabricacion;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> productos = precargarProductos();

        int opcion;
        do {
            System.out.println("1 - Mostrar productos");
            System.out.println("2 - Realizar compra");
            System.out.println("3 - Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarProductos(productos);
                    break;
                case 2:
                    realizarCompra(productos, scanner);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static ArrayList<Producto> precargarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        
        productos.add(new Producto("001", "Producto 1", 100.0, OrigenFabricacion.ARGENTINA, Categoria.TELEFONIA, true));
        productos.add(new Producto("002", "Producto 2", 150.0, OrigenFabricacion.CHINA, Categoria.INFORMATICA, true));
        
        return productos;
    }

    private static void mostrarProductos(ArrayList<Producto> productos) {
        System.out.println("Lista de productos:");
        for (Producto producto : productos) {
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: " + producto.getPrecioUnitario());
            System.out.println("Origen de fabricación: " + producto.getOrigenFabricacion());
            System.out.println("Categoría: " + producto.getCategoria());
            System.out.println("-----------------------------------");
        }
    }

    private static void realizarCompra(ArrayList<Producto> productos, Scanner scanner) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar.");
            return;
        }

        mostrarProductos(productos);

        ArrayList<Producto> productosComprados = new ArrayList<>();
        double totalCompra = 0.0;
        boolean continuarCompra = true;
        while (continuarCompra) {
            System.out.println("Seleccione un producto para comprar (ingrese el código) o 0 para finalizar la compra:");
            String codigoProducto = scanner.nextLine();
            if (codigoProducto.equals("0")) {
                continuarCompra = false;
            } else {
                Producto productoSeleccionado = null;
                for (Producto producto : productos) {
                    if (producto.getCodigo().equals(codigoProducto)) {
                        productoSeleccionado = producto;
                        break;
                    }
                }
                if (productoSeleccionado != null) {
                    if (productoSeleccionado.isDisponible()) {
                        productosComprados.add(productoSeleccionado);
                        totalCompra += productoSeleccionado.getPrecioUnitario();
                        productoSeleccionado.setDisponible(false);
                        System.out.println("Producto agregado a la compra.");
                    } else {
                        System.out.println("El producto seleccionado no está disponible.");
                    }
                } else {
                    System.out.println("No se encontró ningún producto con el código ingresado.");
                }
            }
        }

        if (productosComprados.isEmpty()) {
            System.out.println("No se han seleccionado productos para comprar.");
            return;
        }


        System.out.println("Seleccione el método de pago:");
        System.out.println("1 - Pago efectivo");
        System.out.println("2 - Pago con tarjeta");
        int opcionPago = scanner.nextInt();
        scanner.nextLine();

        switch (opcionPago) {
            case 1:
                realizarPagoEfectivo(totalCompra);
                break;
            case 2:
                realizarPagoTarjeta(totalCompra);
                break;
            default:
                System.out.println("Opción de pago inválida. Se cancela la compra.");
                for (Producto producto : productosComprados) {
                    producto.setDisponible(true);
                }
                break;
        }
    }

    private static void realizarPagoEfectivo(double totalCompra) {
        double montoPagado = totalCompra * 0.9;
        
        PagoEfectivo pagoEfectivo = new PagoEfectivo(montoPagado, LocalDate.now());
        
        System.out.println("Recibo de pago en efectivo:");
        System.out.println("Fecha de pago: " + pagoEfectivo.getFechaPago());
        System.out.println("Monto pagado: " + pagoEfectivo.getMontoPagado());
    }

    private static void realizarPagoTarjeta(double totalCompra) {
        double montoPagado = totalCompra * 1.15;
        
        PagoTarjeta pagoTarjeta = new PagoTarjeta("4021123454786010", LocalDate.now(), montoPagado);
       
        System.out.println("Recibo de pago con tarjeta:");
        System.out.println("Número de tarjeta: " + pagoTarjeta.getNumeroTarjeta());
        System.out.println("Fecha de pago: " + pagoTarjeta.getFechaPago());
        System.out.println("Monto pagado: " + pagoTarjeta.getMontoPagado());
    }
}
