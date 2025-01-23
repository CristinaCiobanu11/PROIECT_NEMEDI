import models.Comanda;
import models.Reclamatie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    ReclamatieController controller = new ReclamatieController("reclamatii.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selectati o optiune: \n1. Adauga reclamatie \n2. Schimba starea unei reclamatii \n3. Afiseaza reclamatii \n4. Iesire");
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    controller.adaugaReclamatie();
                    break;
                case 2:
                    controller.schimbaStareaReclamatiei();
                    break;
                case 3:
                    controller.afiseazaReclamatii();
                    break;
                case 4:
                    System.out.println("Aplicatia se inchide.");
                    return;
                default:
                    System.out.println("Optiune invalida.");
            }
        }

    }
}