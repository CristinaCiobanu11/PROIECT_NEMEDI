package state;

import model.Comanda;

public class InregistrataState implements ComandaState {
    @Override
    public String getState() {
        return "Inregistrata";
    }

    @Override
    public void handle(Comanda comanda) {
        comanda.setState(new PregatitaState());  // Schimbă starea la "Pregătită"
    }
}
