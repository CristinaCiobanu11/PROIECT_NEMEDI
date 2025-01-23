package state;

import model.Comanda;

public class PlatitaState implements ComandaState {
    @Override
    public String getState() {
        return "Platita";
    }

    @Override
    public void handle(Comanda comanda) {
        comanda.setState(new PlasataState());  // Schimbă starea la "Plasată"
    }

    @Override
    public String toString() {
        return "Platita";
    }
}

