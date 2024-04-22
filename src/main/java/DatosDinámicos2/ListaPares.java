package DatosDinámicos2;


import java.util.ArrayList;

public class ListaPares {
    private ArrayList<Pareja> lista;

    public ListaPares() {
        lista = new ArrayList<>();
    }

    public void agregarElemento(Pareja elemento) {
        lista.add(elemento);
    }

    public Pareja obtenerElemento(int indice) {
        return lista.get(indice);
    }
}