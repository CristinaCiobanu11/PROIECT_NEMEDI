package main;

import view.ConsoleView;
import presenter.ComandaPresenter;
import repository.ComandaRepository;
import observer.ClientObserver;
import model.Comanda;
import model.Client;
import model.Produs;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creare repository, view și presenter
        ComandaRepository comandaRepository = new ComandaRepository();
        ConsoleView consoleView = new ConsoleView(comandaRepository);
        ComandaPresenter presenter = new ComandaPresenter(consoleView, comandaRepository);

        // Citirea datelor din fișierul 'data.txt'
        consoleView.citesteDinFisier("data.txt");

        // Interfața cu utilizatorul
        boolean running = true;
        while (running) {
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Vizualizează comenzile");
            System.out.println("2. Modifică starea unei comenzi");
            System.out.println("3. Ieșire");
            System.out.print("Alege opțiunea (1-3): ");

            Scanner scanner = new Scanner(System.in);
            int optiune = scanner.nextInt();
            scanner.nextLine();  // Consuma newline-ul

            switch (optiune) {
                case 1:
                    // Vizualizarea comenzilor
                    presenter.gestioneazaComenzi();
                    break;

                case 2:
                    // Modificarea stării unei comenzi
                    presenter.modificaStareComanda();
                    break;

                case 3:
                    // Ieșirea din aplicație
                    running = false;
                    break;

                default:
                    System.out.println("Opțiune invalidă.");
            }
        }

        System.out.println("Aplicatia a fost închisă.");
    }
}
