package mapper;

import model.*;
import state.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

    public static List<Comanda> loadComenziFromFile(String numeFisier) throws IOException {
        List<Comanda> comenzi = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] date = line.split(", ");
                if (date.length > 1) {
                    String numeClient = date[0];
                    String emailClient = date[1];

                    // Gestionăm stările
                    ComandaState state = new PreluataState();  // Default state
                    if (date[2].equals("Pregatita")) {
                        state = new PregatitaState();
                    } else if (date[2].equals("Platita")) {
                        state = new PlatitaState();
                    } else if (date[2].equals("Plasata")) {
                        state = new PlasataState();
                    }

                    // Creăm obiectul client
                    Client client = new Client(numeClient, emailClient);

                    List<Produs> produse = new ArrayList<>();
                    for (int i = 3; i < date.length; i += 2) {
                        String numeProdus = date[i];
                        double pretProdus = Double.parseDouble(date[i + 1]);
                        produse.add(new Produs(numeProdus, pretProdus));
                    }

                    // Creăm comanda
                    Comanda comanda = new Comanda(client, produse);
                    comanda.setState(state);
                    comenzi.add(comanda);
                }
            }
        }
        return comenzi;
    }

    public static void saveComenziToFile(List<Comanda> comenzi, String numeFisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Comanda comanda : comenzi) {
                // Scriem starea înainte de produse
                writer.write(comanda.getClient().getNume() + ", " + comanda.getClient().getEmail() + ", ");
                writer.write(comanda.getStare() + ", ");  // Starea este acum salvată înainte de produse

                // Scriem produsele
                for (Produs produs : comanda.getProduse()) {
                    writer.write(produs.getNume() + ", " + produs.getPret() + ", ");
                }

                writer.write("\n");  // Nouă linie pentru fiecare comandă
            }
        }
    }
}
