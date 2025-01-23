package state;

import model.Comanda;

public class PregatitaState implements ComandaState {
    @Override
    public String getState() {
        return "Pregătită";
    }

    @Override
    public void handle(Comanda comanda) {
        comanda.setState(new LivrataState());  // Schimbă starea la "Livrată"
    }
}
