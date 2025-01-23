package repository;

import model.Comanda;
import java.util.ArrayList;
import java.util.List;

public class ComandaRepository {
    private List<Comanda> comenzi = new ArrayList<>();

    public void addComanda(Comanda comanda) {
        comenzi.add(comanda);
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }
}
