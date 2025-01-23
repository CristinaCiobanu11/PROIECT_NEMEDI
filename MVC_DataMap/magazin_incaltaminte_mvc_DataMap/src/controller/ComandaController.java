package controller;

import model.Comanda;
import view.ViewInterface;

import java.util.List;

public class ComandaController {
    private ViewInterface view;

    public ComandaController(ViewInterface view) {
        this.view = view;
    }

    public void gestioneazaComenzi(List<Comanda> comenzi) {
        view.afiseazaComenzi(comenzi);
    }

    public void modificaStareComanda(List<Comanda> comenzi) {
        view.modificaStareComanda(comenzi);
    }
}
