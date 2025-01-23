package state;

import model.Solicitare;

public class InAnalizaState implements SolicitareState {
    @Override
    public String getState() {
        return "In analiza";
    }

    @Override
    public void handle(Solicitare solicitare) {
        // Dacă solicitarea este urgentă
        if (solicitare.isUrgent()) {
            solicitare.setState(new AcceptataState());
            System.out.println("Solicitarea este urgentă. Echipa va pleca la client.");
        } else {
            solicitare.setState(new RespinsaState());
            System.out.println("Solicitarea nu este urgentă. Clientul poate veni singur la clinică.");
        }
    }

    @Override
    public String toString() {
        return "In analiza";
    }
}