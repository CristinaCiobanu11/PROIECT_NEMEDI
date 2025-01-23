package state;

import models.Reclamatie;

public interface StareReclamatie {
    void handle(Reclamatie reclamatie);
    String getStatus();
}
