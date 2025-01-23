package state;

import models.Reclamatie;

public class Inregistrata implements StareReclamatie{
    @Override
    public void handle(Reclamatie reclamatie) {
        reclamatie.setStare(new InAnaliza());
    }

    @Override
    public String getStatus() {
        return "Inregistrata";
    }

    @Override
    public String toString() {
        return "Inregistrata";
    }
}
