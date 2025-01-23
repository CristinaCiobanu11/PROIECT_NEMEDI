package model;

import state.ComandaState;
import state.PlatitaState;
import state.PreluataState;
import state.PregatitaState;
import state.OnorataState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private Client client;
    private List<Produs> produse;
    private ComandaState state;

    public Comanda(Client client, List<Produs> produse) {
        this.client = client;
        this.produse = produse;
    }

    public Client getClient() {
        return client;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public String getStare() {
        return state.getState();
    }

    public void setState(ComandaState state) {
        this.state = state;
    }

    // Salvare comanda în fișier (Active Record)
    public static void saveAllToFile(List<Comanda> comenzi, String numeFisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Comanda comanda : comenzi) {
                writer.write(comanda.getClient().getNume() + ", " + comanda.getClient().getEmail());
                for (Produs produs : comanda.getProduse()) {
                    writer.write(", " + produs.getNume() + ", " + produs.getPret());
                }
                writer.write(", " + comanda.getStare() + "\n");  // Salvăm și starea comenzii
            }
        }
    }

    // Citire comenzile din fișier (Active Record)
    public static List<Comanda> loadFromFile(String numeFisier) throws IOException {
        List<Comanda> comenzi = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] date = line.split(", ");
                if (date.length > 1) {
                    String numeClient = date[0];
                    String emailClient = date[1];
                    List<Produs> produse = new ArrayList<>();
                    for (int i = 2; i < date.length - 1; i += 2) {
                        String numeProdus = date[i];
                        double pretProdus = Double.parseDouble(date[i + 1]);
                        produse.add(new Produs(numeProdus, pretProdus));
                    }

                    // Citirea stării comenzii din fișier
                    ComandaState state = null;
                    if (date[date.length - 1].equals("Preluata")) {
                        state = new PreluataState();
                    }  else if (date[date.length - 1].equals("Pregatita")) {
                        state = new PregatitaState();
                    } else if (date[date.length - 1].equals("Platita")) {
                        state = new PlatitaState();
                    } else if (date[date.length - 1].equals("Onorata")) {
                        state = new OnorataState();
                    }

                    Client client = new Client(numeClient, emailClient);
                    Comanda comanda = new Comanda(client, produse);
                    comanda.setState(state);
                    comenzi.add(comanda);
                }
            }
        }
        return comenzi;
    }
}
