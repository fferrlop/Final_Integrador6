package DatosDinámicos;

import DatosDinámicos2.ListaPares;
import DatosDinámicos2.ListaReales;
import DatosDinámicos2.Pareja;

public class ModeladoMultidimensional {
    // Make listaReales and listaPares instance variables
    private ListaReales listaReales = new ListaReales();
    private ListaPares listaPares = new ListaPares();

    public ModeladoMultidimensional() {
        // Populate listaReales and listaPares
        populateData();
    }

    private void populateData() {
        // Create an instance of Pareja
        Pareja pareja = new Pareja(1, 2);

        listaReales.agregarElemento(1.1);
        listaReales.agregarElemento(2.2);
        listaReales.agregarElemento(3.3);

        listaPares.agregarElemento(pareja);
        listaPares.agregarElemento(new Pareja(3, 4));
        listaPares.agregarElemento(new Pareja(5, 6));
    }

    // Add getListaReales and getListaPares methods
    public ListaReales getListaReales() {
        return listaReales;
    }

    public ListaPares getListaPares() {
        return listaPares;
    }
}