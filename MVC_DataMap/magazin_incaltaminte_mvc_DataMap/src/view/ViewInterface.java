package view;

import model.Comanda;

import java.util.List;

public interface ViewInterface {
    void afiseazaComenzi(List<Comanda> comenzi);
    void afiseazaMesaj(String mesaj);
    void modificaStareComanda(List<Comanda> comenzi);
    void cautaComanda(List<Comanda> comenzi);
}
