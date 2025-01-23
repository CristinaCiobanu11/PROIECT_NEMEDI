package state;

import model.Solicitare;

public class AcceptataState implements SolicitareState{


    @Override
    public String getState() {
        return "Acceptata";
    }

    @Override
    public void handle(Solicitare solicitare) {
        // Inițiem deplasarea echipajului către client
        int timpEstimativ = solicitare.getTimpEstimativ();
        solicitare.setTimpEstimativ(timpEstimativ);  // Setăm timpul estimativ de sosire
        System.out.println("Echipajul pleacă către clientul " + solicitare.getClient().getNume() + ". Timp estimativ: " + timpEstimativ + " minute.");
        solicitare.setState(new EchipajPlecatState());  // Schimbăm starea la „Echipaj plecat”
    }

    @Override
    public String toString() {
        return "Acceptata";
    }

}
