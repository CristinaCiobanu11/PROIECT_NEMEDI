package view;

import model.Solicitare;
import state.SolicitareState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    // Metoda pentru afisarea solicitarilor
    public void afiseazasolicitari(List<Solicitare> solicitari) {
        if (solicitari.isEmpty()) {
            System.out.println("Nu exista solicitari de afisat.");
        } else {
            for (Solicitare solicitare : solicitari)  {
                System.out.println("Solicitare pentru clientul " + solicitare.getClient().getNume() + " - Stare: " + solicitare.getState());
                System.out.println("Problema: " + solicitare.getProblema());
                System.out.println("Adresa: " + solicitare.getAdresa());
                System.out.println("Timp estimativ: " + solicitare.getTimpEstimativ() + " minute");

                // Afișăm piesele de schimb
                System.out.println("Piese de schimb necesare: " + String.join(", ", solicitare.getPieseSchimb()));

                // Afișăm tariful
                System.out.println("Tarif estimat: " + solicitare.getTarif() + " lei");
            }
        }
    }






    // MODIFICAT TOT PT CA GESTIONEZ DIFERIT SOLICITARILE AICI
public void modificaStareSolicitare(List<Solicitare> solicitari) {
    System.out.print("Introduceti indexul solicitării pentru a modifica starea (indexul incepe de la 0): ");
    int index = scanner.nextInt();
    scanner.nextLine();  // Consuma newline-ul
    if (index >= 0 && index < solicitari.size()) {
        Solicitare solicitare = solicitari.get(index);
        System.out.println("Starea curenta a solicitării este: " + solicitare.getState());
        solicitare.getState().handle(solicitare);
        // Salvăm solicitările în fișier după modificarea stării
        try {
            Solicitare.saveAllToFile(solicitari, "data.txt");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea solicitării: " + e.getMessage());
        }
        System.out.println("Noua stare a solicitării este: " + solicitare.getState());
    } else {
        System.out.println("Index invalid!");
    }
}

// Citirea datelor din fisier
public void citesteDinFisier(String numeFisier, List<Solicitare> solicitari) {
    try {
        solicitari.addAll(Solicitare.loadFromFile(numeFisier));
    } catch (Exception e) {
        System.out.println("Eroare la citirea fisierului: " + e.getMessage());
    }
}

// Afișarea meniului
public void afiseazaMeniu() {
    System.out.println("\n--- Meniu ---");
    System.out.println("1. Vizualizează solicitarile");
    System.out.println("2. Modifică starea unei solicitari");
    System.out.println("3. Căutare/Filtrare solicitari");
    System.out.println("4. Ieșire");
}

// Preluarea opțiunii alese de utilizator
public int preiaOptiune() {
    System.out.print("Alege opțiunea: ");
    return scanner.nextInt();
}

// Căutare solicitari după numele clientului
public void cautaDupaNumeClient(List<Solicitare> solicitari) {
    System.out.print("Introduceti numele clientului pentru cautare: ");
    scanner.nextLine();  // Consuma newline-ul
    String numeClient = scanner.nextLine();
    List<Solicitare> rezultate = new ArrayList<>();

    for (Solicitare solicitare : solicitari) {
        if (solicitare.getClient().getNume().toLowerCase().contains(numeClient.toLowerCase())) {
            rezultate.add(solicitare);
        }
    }

    if (rezultate.isEmpty()) {
        System.out.println("Nu s-au gasit solicitarei pentru clientul " + numeClient);
    } else {
        afiseazasolicitari(rezultate);
    }
}

// Filtrare solicitari după stare
public void filtreazaDupaStare(List<Solicitare> solicitari) {
    System.out.print("Introduceti starea solicitarii pentru filtrare (Transmisa, Acceptata, Echipaj plecat, Respinsa): ");
    scanner.nextLine();  // Consuma newline-ul
    String stare = scanner.nextLine();
    List<Solicitare> rezultate = new ArrayList<>();

    for (Solicitare solicitare : solicitari) {
        if (solicitare.getState().toString().equals(stare)) {
            rezultate.add(solicitare);
        }
    }

    if (rezultate.isEmpty()) {
        System.out.println("Nu s-au gasit solicitari cu starea " + stare);
    } else {
        afiseazasolicitari(rezultate);
    }
}
}
