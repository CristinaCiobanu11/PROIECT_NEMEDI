package state;

import model.Comanda;

public interface ComandaState {
    String getState();
    void handle(Comanda comanda);
}
