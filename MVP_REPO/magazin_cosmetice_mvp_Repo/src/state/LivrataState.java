package state;

import model.Comanda;

public class LivrataState implements ComandaState {
    @Override
    public String getState() {
        return "Livrată";
    }

    @Override
    public void handle(Comanda comanda) {
        // Starea finală, nu mai trece la altă stare
    }
}
