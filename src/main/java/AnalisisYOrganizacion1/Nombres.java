package AnalisisYOrganizacion1;

import java.util.TreeSet;

public class Nombres {
    public static void main(String[] args) {
        TreeSet<String> nombres = new TreeSet<>();
        nombres.add("Carlos");
        nombres.add("Ana");
        nombres.add("Eduardo");
        nombres.add("Berta");

        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}