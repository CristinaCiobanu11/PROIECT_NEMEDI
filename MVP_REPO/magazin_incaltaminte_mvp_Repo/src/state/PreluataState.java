package state;

import model.Comanda;

public class PreluataState implements ComandaState {
    @Override
    public String getState() {
        return "Preluata";
    }

    @Override
    public void handle(Comanda comanda) {
        comanda.setState(new PregatitaState());
    }
}
