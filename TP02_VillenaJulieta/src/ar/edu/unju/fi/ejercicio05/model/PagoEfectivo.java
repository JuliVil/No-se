package ar.edu.unju.fi.ejercicio05.model;

import java.time.LocalDate;
import ar.edu.unju.fi.ejercicio05.interfaces.*;
public class PagoEfectivo implements Pago {
    private double montoPagado;
    private LocalDate fechaPago;
    public PagoEfectivo(double montoPagado, LocalDate fechaPago) {
        this.montoPagado = montoPagado;
        this.fechaPago = fechaPago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public void realizarPago(double monto) {
        this.montoPagado = monto * 0.90;
    }

    @Override
    public void imprimirRecibo() {
        System.out.println("Fecha de pago: " + fechaPago);
        System.out.println("Monto pagado: " + montoPagado);
    }
}