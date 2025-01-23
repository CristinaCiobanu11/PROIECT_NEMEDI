package state;

import models.Reclamatie;

public class InAnaliza implements StareReclamatie{
    @Override
    public void handle(Reclamatie reclamatie) {
        reclamatie.setStare(new Solutionata());
    }

    @Override
    public String getStatus() {
        return "In Analiza";
    }
}

