package AnalisisYOrganizacion1;

import java.time.LocalDate;

public class Fechas {
    private LocalDate fecha;

    public Fechas(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}