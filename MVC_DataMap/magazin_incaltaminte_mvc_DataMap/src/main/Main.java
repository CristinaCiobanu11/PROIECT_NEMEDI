package main;

import controller.ComandaController;
import model.Comanda;
import model.Client;
import model.Produs;
import state.PreluataState;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Comanda> comenzi = citesteComenziDinFisier("data.txt");

        // Inițializare View și Controller
        ConsoleView consoleView = new ConsoleView();
        ComandaController controller = new ComandaController(consoleView);

        // Interfața cu utilizatorul
        boolean running = true;
        while (running) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Vizualizează comenzile");
            System.out.println("2. Modifică starea unei comenzi");
            System.out.println("3. Caută comenzi");
            System.out.println("4. Ieșire");
            System.out.print("Alege opțiunea (1-4): ");

            Scanner scanner = new Scanner(System.in);
            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline-ul

            switch (optiune) {
                case 1:
                    consoleView.afiseazaComenzi(comenzi);
                    break;

                case 2:
                    consoleView.modificaStareComanda(comenzi);
                    break;

                case 3:
                    consoleView.cautaComanda(comenzi);
                    break;

                case 4:
                    salveazaComenziInFisier(comenzi, "data.txt");
                    running = false;
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
            }
        }

        System.out.println("Aplicația a fost închisă.");
    }

    // Metoda pentru citirea comenzilor din fișier
    private static List<Comanda> citesteComenziDinFisier(String numeFisier) {
        List<Comanda> comenzi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] date = linie.split(", ");
                if (date.length > 3) {
                    String numeClient = date[0];
                    String emailClient = date[1];
                    String adresa = date[2];

                    List<Produs> produse = new ArrayList<>();
                    for (int i = 3; i < date.length; i += 2) {
                        String numeProdus = date[i];
                        double pretProdus = Double.parseDouble(date[i + 1]);
                        produse.add(new Produs(numeProdus, pretProdus));
                    }

                    Client client = new Client(numeClient, emailClient);
                    Comanda comanda = new Comanda(client, produse, adresa, new PreluataState());
                    comenzi.add(comanda);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
        return comenzi;
    }

    // Metoda pentru salvarea comenzilor în fișier
    private static void salveazaComenziInFisier(List<Comanda> comenzi, String numeFisier) {
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(numeFisier))) {
            for (Comanda comanda : comenzi) {
                StringBuilder linie = new StringBuilder();
                linie.append(comanda.getClient().getNume()).append(", ")
                        .append(comanda.getClient().getEmail()).append(", ")
                        .append(comanda.getAdresa());
                for (Produs produs : comanda.getProduse()) {
                    linie.append(", ").append(produs.getNume())
                            .append(", ").append(produs.getPret());
                }
                bw.write(linie.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la salvarea fișierului: " + e.getMessage());
        }
    }
}
