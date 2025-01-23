package model;

import state.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solicitare {
    private Client client;
    private String problema;
    private String adresa;
    private SolicitareState state;
    private int timpEstimativ;  // Timp estimativ de sosire, în minute
    private boolean isTeamAvailable;   // Atributul pentru urgență
    private List<String> pieseSchimb; // Piese de schimb necesare
    private double tarif;            // Tariful estimat pentru intervenție

    public Solicitare(Client client, String problema, String adresa, boolean isTeamAvailable) {
        this.client = client;
        this.problema = problema;
        this.adresa = adresa;
        this.isTeamAvailable = isTeamAvailable;
        this.state = null;  // Starea inițială este "Solicitare transmisă"
        this.timpEstimativ = 0;
        this.pieseSchimb = new ArrayList<>();
        this.tarif = 0.0;
    }

    public Client getClient() {
        return client;
    }

    public String getProblema() {
        return problema;
    }

    public String getAdresa() {
        return adresa;
    }

    public SolicitareState getState() {
        return state;
    }

    public void setState(SolicitareState state) {
        this.state = state;
    }

    public int getTimpEstimativ() {
        return timpEstimativ;
    }

    public void setTimpEstimativ(int timpEstimativ) {
        this.timpEstimativ = timpEstimativ;
    }

    public boolean isTeamAvailable() {
        return isTeamAvailable;
    }

    public List<String> getPieseSchimb() {
        return pieseSchimb;
    }

    public void addPiesaSchimb(String piesa) {
        pieseSchimb.add(piesa);
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    // Salvarea solicitărilor într-un fișier
    public static void saveAllToFile(List<Solicitare> solicitari, String numeFisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Solicitare solicitare : solicitari) {
                // Obținem piesele de schimb sub formă de listă separată prin virgulă
                String piese = String.join(", ", solicitare.getPieseSchimb());

                // Scriem toate informațiile în fișier, inclusiv piesele și tariful
                writer.write(solicitare.getClient().getNume() + ", " +
                        solicitare.getProblema() + ", " +
                        solicitare.getAdresa() + ", " +
                        solicitare.getState() + ", " +
                        solicitare.getTimpEstimativ() + ", " +
                        solicitare.isTeamAvailable() + ", " +
                        piese + ", " +
                        solicitare.getTarif() + "\n");
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
                    String problema = date[1];
                    String adresa = date[2];
                     // Citim dacă echipa este disponibilă
                    SolicitareState state = new TransmisaState();
                    if (date[3].equals("Acceptata")) {
                        state = new AcceptataState();
                    } else if (date[3].equals("Respinsa")) {
                        state = new RespinsaState();
                    } else if (date[3].equals("Echipaj plecat")) {
                        state = new EchipajPlecatState();
                    }

                    int timpEstimativ = Integer.parseInt(date[4]);

                    boolean isTeamAvailable = Boolean.parseBoolean(date[5]);
                    // Citirea pieselor de schimb
                    String[] piese = { date[6], date[7] };

                    // Citirea tarifului
                    double tarif = Double.parseDouble(date[8]);

                    // Crearea obiectului Client și Solicitare
                    Client client = new Client(numeClient);
                    Solicitare solicitare = new Solicitare(client, problema, adresa, isTeamAvailable);
                    solicitare.setState(state);
                    solicitare.setTimpEstimativ(timpEstimativ);
                    solicitare.setTarif(tarif);

                    // Adăugăm piesele de schimb în solicitare
                    for (String piesa : piese) {
                        solicitare.addPiesaSchimb(piesa.trim());
                    }

                    solicitari.add(solicitare);
                }
            }
        }
        return solicitari;
    }

}