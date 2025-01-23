import models.Comanda;
import models.Reclamatie;
import state.InAnaliza;
import state.Inregistrata;
import state.Solutionata;
import state.StareReclamatie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamatieMapper {
    private List<Reclamatie> listaReclamatii;
    private String fileName;

    public ReclamatieMapper(String fileName) throws IOException {
        this.listaReclamatii = new ArrayList<>();
        this.fileName = fileName;
        this.listaReclamatii = readReclamatiiFromFile(fileName);
    }

    public void adaugaReclamatie(Reclamatie reclamatie) throws IOException {
        listaReclamatii.add(reclamatie);
        saveAllToFile( "reclamatii.txt");
    }

    public void saveAllToFile( String fileName) throws IOException {
       try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
           for (Reclamatie reclamatie : listaReclamatii) {
               writer.write(reclamatie.getId() + "," + reclamatie.getMesaj() + "," + reclamatie.getStare() + "," + reclamatie.getComanda().getId() + "," + reclamatie.getComanda().getProduse() + "," + reclamatie.getComanda().getSuma() + "\n");
           }
       }

    }

    public Reclamatie gasesteReclamatie(int id) {
        for (Reclamatie reclamatie : listaReclamatii) {
            if (reclamatie.getId() == id) {
                return reclamatie;
            }
        }
        return null;
    }

    List<Reclamatie> readReclamatiiFromFile(String fileName) throws IOException {
        List<Reclamatie> listaReclamatii = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String mesaj = parts[1];
                String stare = parts[2];
                StareReclamatie stareReclamatie = null;
                switch (stare) {
                    case "Solutionata": {
                        stareReclamatie = new Solutionata();
                        break;
                    }
                    case "Inregistrata": {
                        stareReclamatie = new Inregistrata();
                        break;
                    }
                    case "InAnaliza": {
                        stareReclamatie = new InAnaliza();
                        break;
                    }
                }

                int idComanda = Integer.parseInt(parts[3]);
                String produse = parts[4];
                int suma = Integer.parseInt(parts[5]);

                Comanda comanda = new Comanda(idComanda, produse, suma);
                Reclamatie reclamatie = new Reclamatie(id, mesaj, comanda);
                reclamatie.setStare(stareReclamatie);
                listaReclamatii.add(reclamatie);
            }
        }

        return listaReclamatii;
    }

}
