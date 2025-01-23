package state;

import models.Reclamatie;

public class Solutionata implements StareReclamatie{
    @Override
    public void handle(Reclamatie reclamatie) {
        System.out.printf("Reclamatia cu id-ul %d a fost solutionata.\n", reclamatie.getId());
    }

    @Override
    public String getStatus() {
        return "Solutionata";
    }
}
