package view;

import model.Comanda;
import model.Client;
import model.Produs;
import observer.ClientObserver;
import repository.ComandaRepository;
import state.LivrataState;
import state.PregatitaState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {
    private ComandaRepository comandaRepository;
    private Scanner scanner;

    public ConsoleView(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void afiseazaComenzi() {
        List<Comanda> comenzi = comandaRepository.getComenzi();
        for (Comanda comanda : comenzi) {
            System.out.println("Comanda pentru clientul " + comanda.getClient().getNume() + " - Stare: " + comanda.getStare());
            for (Produs produs : comanda.getProduse()) {
                System.out.println("  Produs: " + produs.getNume() + ", Pret: " + produs.getPret());
            }
        }
    }

    @Override
    public void afiseazaMesaj(String mesaj) {
        System.out.println(mesaj);
    }

    @Override
    public void modificaStareComanda() {
        System.out.print("Introduceti indexul comenzii pentru a modifica starea: \n" +
                "Comanda 1 are indexul 0.\n" +
                "Comanda 2 are indexul 1.\n" +
                "Comanda 3 are indexul 2. \n" +
                "etc. \n"
        );
        int index = scanner.nextInt();
        scanner.nextLine();  // Consuma newline-ul
        if (index >= 0 && index < comandaRepository.getComenzi().size()) {
            Comanda comanda = comandaRepository.getComenzi().get(index);
            System.out.println("Starea curenta a comenzii este: " + comanda.getStare());
            comanda.setState(comanda.getStare().equals("Inregistrata") ? new PregatitaState() : new LivrataState());
        } else {
            System.out.println("Index invalid!");
        }
    }

    // Metoda pentru citirea datelor din fisier
    public void citesteDinFisier(String numeFisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] date = linie.split(", ");
                if (date.length > 1) {
                    String numeClient = date[0];
                    String emailClient = date[1];
                    List<Produs> produse = new ArrayList<>();
                    for (int i = 2; i < date.length; i += 2) {
                        String numeProdus = date[i];
                        double pretProdus = Double.parseDouble(date[i + 1]);
                        produse.add(new Produs(numeProdus, pretProdus));
                    }

                    Client client = new Client(numeClient, emailClient);
                    Comanda comanda = new Comanda(client, produse);
                    comandaRepository.addComanda(comanda);

                    // Creare observator pentru client
                    ClientObserver clientObserver = new ClientObserver(client.getNume());
                    comanda.addObserver(clientObserver);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
    }
}
