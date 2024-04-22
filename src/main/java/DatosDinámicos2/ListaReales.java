package DatosDinámicos2;


import java.util.ArrayList;

public class ListaReales {
    private ArrayList<Double> lista;

    public ListaReales() {
        lista = new ArrayList<>();
    }

    public void agregarElemento(double elemento) {
        lista.add(elemento);
    }

    public double obtenerElemento(int indice) {
        return lista.get(indice);
    }
}