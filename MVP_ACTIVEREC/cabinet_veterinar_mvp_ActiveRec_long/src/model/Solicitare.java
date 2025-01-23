package model;

import state.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solicitare {
    private Client client;
    private Animal animal;
    private String adresa;
    private SolicitareState state;
    private int timpEstimativ;  // Timp estimativ de sosire, în minute
    private boolean isUrgent;   // Atributul pentru urgență

    public Solicitare(Client client, Animal animal, String adresa, boolean isUrgent) {
        this.client = client;
        this.animal = animal;
        this.adresa = adresa;
        this.isUrgent = isUrgent;
        this.state = new InAnalizaState();  // Starea initială este „În analiză”
        this.timpEstimativ = 0;
    }

    public Client getClient() {
        return client;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getAdresa() {
        return adresa;
    }

    public SolicitareState getState() {
        return state;  // Returnează obiectul de stare
    }

    public void setState(SolicitareState state) {
        this.state = state;  // Schimbăm starea
    }

    public int getTimpEstimativ() {
        return timpEstimativ;
    }

    public void setTimpEstimativ(int timpEstimativ) {
        this.timpEstimativ = timpEstimativ;
    }

    public boolean isUrgent() {
        return isUrgent;
    }

    // Salvarea solicitărilor într-un fișier
    public static void saveAllToFile(List<Solicitare> solicitari, String numeFisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Solicitare solicitare : solicitari) {
                writer.write(solicitare.getClient().getNume() + ", " + solicitare.getAnimal().getNume() + ", " +
                        solicitare.getAdresa() + ", " + solicitare.getState() + ", " + solicitare.getTimpEstimativ() + ", " + solicitare.isUrgent() + "\n");
            }
        }
    }

    // Citirea solicitărilor din fișier
    public static List<Solicitare> loadFromFile(String numeFisier) throws IOException {
        List<Solicitare> solicitari = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] date = line.split(", ");
                if (date.length > 1) {
                    String numeClient = date[0];
                    String numeAnimal = date[1];
                    String adresa = date[2];
                    boolean isUrgent = Boolean.parseBoolean(date[5]); // Citim dacă este urgent
                    SolicitareState state = new InAnalizaState();
                    if (date[3].equals("Acceptata")) {
                        state = new AcceptataState();
                    } else if (date[3].equals("Respinsa")) {
                        state = new RespinsaState();
                    } else if (date[3].equals("Echipaj plecat")) {
                        state = new EchipajPlecatState();
                    }
                    int timpEstimativ = Integer.parseInt(date[4]);
                    Client client = new Client(numeClient);
                    Animal animal = new Animal(numeAnimal);
                    Solicitare solicitare = new Solicitare(client, animal, adresa, isUrgent);
                    solicitare.setState(state);
                    solicitare.setTimpEstimativ(timpEstimativ);
                    solicitari.add(solicitare);
                }
            }
        }
        return solicitari;
    }
}