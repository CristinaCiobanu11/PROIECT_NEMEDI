import models.Comanda;
import models.Reclamatie;
import state.InAnaliza;
import state.Inregistrata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReclamatieController {
    private ReclamatieMapper reclamatieMapper;

    public ReclamatieController(String fileName) throws IOException {
        this.reclamatieMapper = new ReclamatieMapper(fileName);
    }

    public void adaugaReclamatie() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti id-ul reclamatiei: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Introduceti mesajul reclamatiei: ");
        String detalii = scanner.nextLine();

        System.out.println("Introdu ID-ul comenzii: ");
        int comandaId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introdu produsele comenzii fara virgula: ");
        String produse = scanner.nextLine();
        System.out.println("Introdu suma comenzii: ");
        int suma = scanner.nextInt();
        scanner.nextLine();

        Comanda comanda = new Comanda(comandaId, produse, suma);
        Reclamatie reclamatie = new Reclamatie(id, detalii, comanda);
        reclamatieMapper.adaugaReclamatie(reclamatie);
        System.out.println("Reclamatia a fost adaugata!");;

    }

    public void schimbaStareaReclamatiei() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Inregistrata inregistrata = new Inregistrata();
        System.out.println("Introduceti id-ul reclamatiei: ");
        int id = scanner.nextInt();
        Reclamatie reclamatie = reclamatieMapper.gasesteReclamatie(id);
        if(reclamatie != null){
            System.out.println("Starea curenta a solicitării este: " + reclamatie.getStare());
            reclamatie.getStare().handle(reclamatie);
            reclamatieMapper.saveAllToFile("reclamatii.txt");
        }else{
            System.out.println("Reclamatia nu exista!");
        }
    }

    public void afiseazaReclamatii() throws IOException {
        for (Reclamatie reclamatie : reclamatieMapper.readReclamatiiFromFile("reclamatii.txt")) {
            System.out.println("ID: " + reclamatie.getId() + ", Detalii: " + reclamatie.getMesaj() + ", Stare: " + reclamatie.getStare() + ", Comanda: [ID: " + reclamatie.getComanda().getId() + ", Produse: " + reclamatie.getComanda().getProduse() + ", Suma: " + reclamatie.getComanda().getSuma() + "]");
        }
    }
}
