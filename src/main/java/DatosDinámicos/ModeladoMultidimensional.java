package DatosDinámicos;

import DatosDinámicos2.ListaPares;
import DatosDinámicos2.Pareja;

import java.util.Collections;
import java.util.Comparator;

public class ModeladoMultidimensional {
    private ListaPares listaPares = new ListaPares();


    public ListaPares getListaPares() {
        return listaPares;
    }

    public void addPareja(Pareja pareja) {
        listaPares.agregarElemento(pareja);
    }

    public void sortDataByFirstNumber() {
        Collections.sort(listaPares.getLista(), new Comparator<Pareja>() {
            @Override
            public int compare(Pareja p1, Pareja p2) {
                return Integer.compare(p1.getPrimero(), p2.getPrimero());
            }
        });
    }

    public void sortDataBySecondNumber() {
        Collections.sort(listaPares.getLista(), new Comparator<Pareja>() {
            @Override
            public int compare(Pareja p1, Pareja p2) {
                return Integer.compare(p1.getSegundo(), p2.getSegundo());
            }
        });
    }
}