package state;

import model.Solicitare;

public class TransmisaState implements SolicitareState {
    @Override
    public String getState() {
        return "Transmisa";
    }

    @Override
    public void handle(Solicitare solicitare) {
        // Dacă solicitarea este urgentă
        if (solicitare.isTeamAvailable()) {
            solicitare.setState(new AcceptataState());
            System.out.println("Solicitarea a fost acceptată și echipa este disponibilă.");
        } else {
            solicitare.setState(new RespinsaState());
            System.out.println("Echipa nu este disponibila.");
        }
    }

    @Override
    public String toString() {
        return "Transmisa";
    }
}