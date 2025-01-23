package state;

import model.Comanda;

public class PlasataState implements ComandaState {
    @Override
    public String getState() {
        return "Plasata";
    }

    @Override
    public void handle(Comanda comanda) {
         // Stare finala
    }

    @Override
    public String toString() {
        return "Plasata";
    }
}
