package view;

import model.Comanda;
import model.Client;
import model.Produs;
import observer.ClientObserver;
import state.OnorataState;
import state.PlatitaState;
import state.PregatitaState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // Metoda pentru afisarea comenzilor
    public void afiseazaComenzi(List<Comanda> comenzi) {
        if (comenzi.isEmpty()) {
            System.out.println("Nu exista comenzi de afisat.");
        } else {
            for (Comanda comanda : comenzi) {
                System.out.println("Comanda pentru clientul " + comanda.getClient().getNume() + " - Stare: " + comanda.getStare());
                for (Produs produs : comanda.getProduse()) {
                    System.out.println("  Produs: " + produs.getNume() + ", Pret: " + produs.getPret());
                }
            }
        }
    }

    // Metoda pentru modificarea starii comenzii
    public void modificaStareComanda(List<Comanda> comenzi) {
        System.out.print("Introduceti indexul comenzii pentru a modifica starea: ");
        int index = scanner.nextInt();
        scanner.nextLine();  // Consuma newline-ul
        if (index >= 0 && index < comenzi.size()) {
            Comanda comanda = comenzi.get(index);
            System.out.println("Starea curenta a comenzii este: " + comanda.getStare());

            // Modificarea starii comenzii
            if (comanda.getStare().equals("Preluata")) {
                comanda.setState(new PregatitaState());
            } else if (comanda.getStare().equals("Pregatita")) {
                comanda.setState(new PlatitaState());
            } else if (comanda.getStare().equals("Platita")) {
                comanda.setState(new OnorataState());
            } else if (comanda.getStare().equals("Onorata")) {
                System.out.println("Comanda este deja in starea finala (Onorata).");
            }

            // Salvarea comenzii in fisier dupa modificare
            try {
                Comanda.saveAllToFile(comenzi, "data.txt");  // Actualizam fisierul cu toate comenzile
            } catch (IOException e) {
                System.out.println("Eroare la salvarea comenzii: " + e.getMessage());
            }

            System.out.println("Noua stare a comenzii este: " + comanda.getStare());
        } else {
            System.out.println("Index invalid!");
        }
    }

    // Citirea datelor din fisier
    public void citesteDinFisier(String numeFisier, List<Comanda> comenzi) {
        try {
            comenzi.addAll(Comanda.loadFromFile(numeFisier));
        } catch (Exception e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
    }

    // Afișarea meniului
    public void afiseazaMeniu() {
        System.out.println("\n--- Meniu ---");
        System.out.println("1. Vizualizează comenzile");
        System.out.println("2. Modifică starea unei comenzi");
        System.out.println("3. Căutare/Filtrare comenzi");
        System.out.println("4. Ieșire");
    }

    // Preluarea opțiunii alese de utilizator
    public int preiaOptiune() {
        System.out.print("Alege opțiunea: ");
        return scanner.nextInt();
    }

    // Căutare comenzi după numele clientului
    public void cautaDupaNumeClient(List<Comanda> comenzi) {
        System.out.print("Introduceti numele clientului pentru cautare: ");
        scanner.nextLine();  // Consuma newline-ul
        String numeClient = scanner.nextLine();
        List<Comanda> rezultate = new ArrayList<>();

        for (Comanda comanda : comenzi) {
            if (comanda.getClient().getNume().toLowerCase().contains(numeClient.toLowerCase())) {
                rezultate.add(comanda);
            }
        }

        if (rezultate.isEmpty()) {
            System.out.println("Nu s-au gasit comenzi pentru clientul " + numeClient);
        } else {
            afiseazaComenzi(rezultate);
        }
    }

    // Filtrare comenzi după stare
    public void filtreazaDupaStare(List<Comanda> comenzi) {
        System.out.print("Introduceti starea comenzii pentru filtrare (Preluata, Pregatita, Platita, Onorata): ");
        scanner.nextLine();  // Consuma newline-ul
        String stare = scanner.nextLine();
        List<Comanda> rezultate = new ArrayList<>();

        for (Comanda comanda : comenzi) {
            if (comanda.getStare().toLowerCase().equals(stare.toLowerCase())) {
                rezultate.add(comanda);
            }
        }

        if (rezultate.isEmpty()) {
            System.out.println("Nu s-au gasit comenzi cu starea " + stare);
        } else {
            afiseazaComenzi(rezultate);
        }
    }
}
