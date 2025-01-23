package models;

import java.util.List;
import java.util.Objects;

// ReclamatiiMagazin proiect - respectand cerintele din imagine

// Importuri necesare
import java.io.*;
import java.util.*;

public class Comanda {
    private int id;
    private String produse;
    private int suma;

    public Comanda(int id, String produse, int suma) {
        this.id = id;
        this.produse = produse;
        this.suma = suma;
    }

    public int getId() {
        return id;
    }

    public String getProduse() {
        return produse;
    }

    public int getSuma() {
        return suma;
    }

    public static void saveAllComenziToFile(List<Comanda> comenzi, String numeFisier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
            for (Comanda comanda : comenzi) {
                writer.write(comanda.getId() + "," +
                        String.join("|", comanda.getProduse()) + "," +
                        comanda.getSuma() + "\n");
            }
        }
    }

    public static Comanda fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        int id = Integer.parseInt(parts[0]);
        String produse = parts[1];
        int suma = Integer.parseInt(parts[2]);
        return new Comanda(id, produse, suma);
    }
}
