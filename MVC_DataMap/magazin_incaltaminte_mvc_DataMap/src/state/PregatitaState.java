package state;

import model.Comanda;

public class PregatitaState implements ComandaState {
    @Override
    public String getState() {
        return "Pregatita";
    }

    @Override
    public void handle(Comanda comanda) {
        comanda.setState(new PlatitaState());  // Schimbă starea la "Plasată"
    }

    @Override
    public String toString() {
        return "Pregatita";
    }
}

