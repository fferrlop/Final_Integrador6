package AnalisisYOrganizacion1;

public class Ventas {
    private String producto;
    private int cantidad;
    private double precio;

    public Ventas(String producto, int cantidad, double precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}