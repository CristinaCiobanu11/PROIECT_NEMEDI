package main;

import model.Comanda;
import model.Client;
import model.Produs;
import view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Comanda> comenzi = new ArrayList<>();
        ConsoleView consoleView = new ConsoleView();

        // Citirea datelor din fișier
        consoleView.citesteDinFisier("data.txt", comenzi);

        boolean running = true;
        while (running) {
            // Afișarea meniului și preluarea opțiunii
            consoleView.afiseazaMeniu();
            int optiune = consoleView.preiaOptiune();

            switch (optiune) {
                case 1:
                    // Vizualizarea comenzilor
                    consoleView.afiseazaComenzi(comenzi);
                    break;

                case 2:
                    // Modificarea stării unei comenzi
                    consoleView.modificaStareComanda(comenzi);
                    break;

                case 3:
                    // Căutare/Filtrare comenzi
                    System.out.println("Alege opțiunea de căutare:");
                    System.out.println("1. Căutare după numele clientului");
                    System.out.println("2. Filtrare după stare");
                    int cautareOptiune = consoleView.preiaOptiune();
                    if (cautareOptiune == 1) {
                        consoleView.cautaDupaNumeClient(comenzi);
                    } else if (cautareOptiune == 2) {
                        consoleView.filtreazaDupaStare(comenzi);
                    } else {
                        System.out.println("Opțiune invalidă.");
                    }
                    break;

                case 4:
                    // Ieșirea din aplicație
                    running = false;
                    System.out.println("Aplicatia a fost închisă.");
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
            }
        }
    }
}
