package presenter;

import model.Comanda;
import repository.ComandaRepository;
import view.ViewInterface;

public class ComandaPresenter {
    private ViewInterface view;
    private ComandaRepository comandaRepository;

    public ComandaPresenter(ViewInterface view, ComandaRepository comandaRepository) {
        this.view = view;
        this.comandaRepository = comandaRepository;
    }

    public void gestioneazaComenzi() {
        view.afiseazaComenzi();
    }

    public void modificaStareComanda() {
        view.modificaStareComanda();
    }
}
