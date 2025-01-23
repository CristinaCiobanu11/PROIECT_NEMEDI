package state;

import model.Solicitare;

public class RespinsaState implements SolicitareState{
    @Override
    public String getState() {
        return "Respinsa";
    }

    @Override
    public void handle(Solicitare solicitare) {
        System.out.println("Solicitarea nu este urgentă. Clientul poate veni singur la clinică.");
    }

    @Override
    public String toString() {
        return "Respinsa";
    }
}
