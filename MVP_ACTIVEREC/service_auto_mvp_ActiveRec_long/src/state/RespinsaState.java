package state;

import model.Solicitare;

public class RespinsaState implements SolicitareState{
    @Override
    public String getState() {
        return "Respinsa";
    }

    @Override
    public void handle(Solicitare solicitare) {
        System.out.println("Nu exista nicio exhipa disponibila.");
    }

    @Override
    public String toString() {
        return "Respinsa";
    }
}
