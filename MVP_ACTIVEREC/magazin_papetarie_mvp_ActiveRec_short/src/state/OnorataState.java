package state;

import model.Comanda;

public class OnorataState implements ComandaState {
    @Override
    public String getState() {
        return "Onorata";
    }

    @Override
    public void handle(Comanda comanda) {
        // Starea finală, nu mai trece la altă stare
    }
}
