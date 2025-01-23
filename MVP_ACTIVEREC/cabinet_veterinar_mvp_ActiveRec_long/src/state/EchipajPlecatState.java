package state;

import model.Solicitare;

public class EchipajPlecatState implements SolicitareState{


    @Override
    public String getState() {
        return "Echipaj Plecat";
    }

    @Override
    public void handle(Solicitare solicitare) {
        // Inițiem deplasarea echipajului către client
        int timpEstimativ = solicitare.getTimpEstimativ();
        solicitare.setTimpEstimativ(timpEstimativ);  // Setăm timpul estimativ de sosire
        System.out.println("Echipajul a plecat catre clientul " + solicitare.getClient().getNume() + ". si va ajunge in aproximativ: " + timpEstimativ + " minute.");
    }

    @Override
    public String toString() {
        return "Echipaj Plecat";
    }

}
