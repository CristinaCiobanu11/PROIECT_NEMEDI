package state;

import model.Solicitare;

public interface SolicitareState {
    String getState();
    void handle(Solicitare solicitare);
}
