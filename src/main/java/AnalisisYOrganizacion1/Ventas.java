package AnalisisYOrganizacion1;

import java.time.LocalDate;

public class Ventas {
    private String nombreComprador;
    private double cantidad;
    private LocalDate fecha;

    public Ventas(String nombreComprador, double cantidad, LocalDate fecha) {
        this.nombreComprador = nombreComprador;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public double getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}