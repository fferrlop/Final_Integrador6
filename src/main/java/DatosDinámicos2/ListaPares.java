package DatosDinámicos2;

import java.util.ArrayList;
import java.util.List;

public class ListaPares {
    private List<Pareja> lista = new ArrayList<>();

    public void agregarElemento(Pareja pareja) {
        lista.add(pareja);
    }

    public List<Pareja> getLista() {
        return lista;
    }
}