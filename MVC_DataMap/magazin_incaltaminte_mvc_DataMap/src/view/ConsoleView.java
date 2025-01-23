package view;

import mapper.DataMapper;
import model.Comanda;
import model.Client;
import model.Produs;
import state.PlasataState;
import state.PlatitaState;
import state.PreluataState;
import state.PregatitaState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {

    private Scanner scanner;

    public ConsoleView() {

        this.scanner = new Scanner(System.in);
    }

    @Override
    public void afiseazaComenzi(List<Comanda> comenzi) {
        for (Comanda comanda : comenzi) {
            System.out.println("Comanda pentru clientul " + comanda.getClient().getNume() + " - Stare: " + comanda.getState());
            System.out.println("Email: " + comanda.getClient().getEmail());

            // Afișăm produsele din comandă
            System.out.println("Produse:");
            for (Produs produs : comanda.getProduse()) {
                System.out.println("- " + produs.getNume() + " - " + produs.getPret() + " lei");
            }
        }
    }

    @Override
    public void afiseazaMesaj(String mesaj) {
        System.out.println(mesaj);
    }

    @Override
    public void modificaStareComanda(List <Comanda> comenzi) {
        System.out.print("Introduceti indexul comenzii pentru a modifica starea: \n" +
                "Comanda 1 are indexul 0.\n" +
                "Comanda 2 are indexul 1.\n" +
                "Comanda 3 are indexul 2. \n" +
                "etc. \n"
        );
        int index = scanner.nextInt();
        scanner.nextLine();  // Consuma newline-ul
        if (index >= 0 && index < comenzi.size()) {
            Comanda comanda = comenzi.get(index);
            System.out.println("Starea curenta a comenzii este: " + comanda.getState());

            //String stareCurenta=comanda.getState().getState();
            if (comanda.getState().equals("Pregatita")) {
                comanda.setState(new PregatitaState());
            } else if (comanda.getState().equals("Pregatita")) {
                comanda.setState(new PlatitaState());
            } else if (comanda.getState().equals("Platita")) {
                comanda.setState(new PlasataState());
            } else if (comanda.getState().equals("Plasata")) {
                System.out.println("Comanda este deja în starea finală (Plasată).");
            } else {
                System.out.println("Stare invalidă!");
            }

            try {
                DataMapper.saveComenziToFile(comenzi, "data.txt");
            } catch (IOException e) {
                System.out.println("Eroare la salvarea comenzii: " + e.getMessage());
            }

        } else {
            System.out.println("Index invalid!");
        }
    }

    @Override
    public void cautaComanda(List<Comanda> comenzi) {
        System.out.print("Introduceti numele clientului sau al produsului pentru cautare: ");
        String query = scanner.nextLine().toLowerCase();
        List<Comanda> rezultate = new ArrayList<>();
        for (Comanda comanda : comenzi) {
            if (comanda.getClient().getNume().toLowerCase().contains(query)) {
                rezultate.add(comanda);
            } else {
                for (Produs produs : comanda.getProduse()) {
                    if (produs.getNume().toLowerCase().contains(query)) {
                        rezultate.add(comanda);
                        break;
                    }
                }
            }
        }

        if (rezultate.isEmpty()) {
            System.out.println("Nu s-au găsit comenzi pentru căutarea specificată.");
        } else {
            for (Comanda comanda : rezultate) {
                System.out.println("Comanda pentru clientul " + comanda.getClient().getNume() + " - Stare: " + comanda.getStare());
            }
        }
    }
}

//    // Metoda pentru citirea datelor din fisier
//    public void citesteDinFisier(String numeFisier) {
//        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
//            String linie;
//            while ((linie = br.readLine()) != null) {
//                String[] date = linie.split(", ");
//                if (date.length > 1) {
//                    String numeClient = date[0];
//                    String emailClient = date[1];
//                    List<Produs> produse = new ArrayList<>();
//                    for (int i = 2; i < date.length; i += 2) {
//                        String numeProdus = date[i];
//                        double pretProdus = Double.parseDouble(date[i + 1]);
//                        produse.add(new Produs(numeProdus, pretProdus));
//                    }
//
//                    Client client = new Client(numeClient, emailClient);
//                    Comanda comanda = new Comanda(client, produse);
//
//                    // Setăm starea inițială la "Preluată"
//                    comanda.setState(new PreluataState());
//
//                    comandaRepository.addComanda(comanda);
//
//                    // Creare observator pentru client
//                    ClientObserver clientObserver = new ClientObserver(client.getNume());
//                    comanda.addObserver(clientObserver);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
//        }
//    }

