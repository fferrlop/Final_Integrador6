package DatosDinámicos;

import DatosDinámicos2.ListaPares;
import DatosDinámicos2.ListaReales;
import DatosDinámicos2.Pareja;

import java.util.Collections;
import java.util.Comparator;

public class ModeladoMultidimensional {
    private ListaReales listaReales = new ListaReales();
    private ListaPares listaPares = new ListaPares();

    public ListaReales getListaReales() {
        return listaReales;
    }

    public ListaPares getListaPares() {
        return listaPares;
    }

    public void addPareja(Pareja pareja) {
        listaPares.agregarElemento(pareja);
    }

    public void addReal(double real) {
        listaReales.agregarElemento(real);
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