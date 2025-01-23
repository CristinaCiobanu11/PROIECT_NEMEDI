package models;

import state.Inregistrata;
import state.Solutionata;
import state.StareReclamatie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reclamatie {
 private int id;
 private String mesaj;
 private StareReclamatie stare;
 private Comanda comanda;


    public Reclamatie(int id, String mesaj, Comanda comanda) {
        this.id = id;
        this.mesaj = mesaj;
        this.stare = new Inregistrata();
        this.comanda = comanda;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public StareReclamatie getStare() {
        return stare;
    }

    public void setStare(StareReclamatie stare) {
        this.stare = stare;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }



        public static List<Reclamatie> readReclamatiiFromFile(String numeFisier) throws FileNotFoundException {
            List<Reclamatie> listaReclamatii = new ArrayList<>();
           try( BufferedReader reader = new BufferedReader(new FileReader(numeFisier))){
               String line;
               while(( line = reader.readLine())!=null){
                   String[] parts = line.split(",");
                   int id = Integer.parseInt(parts[0]);
                   String mesaj = parts[1];
                   StareReclamatie stare = new Inregistrata();

                   if(parts[3].equals("Solutionata")){
                       stare = new Solutionata();
                   }else if(parts[3].equals("Inregistrata")){
                       stare = new Inregistrata();
                   }else if(parts[3].equals("Solutionata")){
                       stare = new Solutionata();
                   }
                  Comanda comanda1 = new Comanda(Integer.parseInt(parts[3]), parts[4], Integer.parseInt(parts[5]));

                    Reclamatie reclamatie = new Reclamatie(id, mesaj, comanda1);
                    reclamatie.setStare(stare);
                    listaReclamatii.add(reclamatie);
               }
           } catch (IOException e) {
               throw new RuntimeException(e);
           }

             return listaReclamatii;
        }


        public static void saveAllToFile(List<Reclamatie> reclamatii, String numeFisier) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
                for (Reclamatie reclamatie : reclamatii) {
                    writer.write(reclamatie.getId() + "," +
                            reclamatie.getMesaj() + "," +
                            reclamatie.getStare() + "," +
                            reclamatie.getComanda().getId()+ "," +
                            reclamatie.getComanda().getProduse()+ "," +
                            reclamatie.getComanda().getSuma() +
                            "\n");
                }
            }
        }


//    public static void saveAllToFile(List<Solicitare> solicitari, String numeFisier) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeFisier))) {
//            for (Solicitare solicitare : solicitari) {
//                // Obținem piesele de schimb sub formă de listă separată prin virgulă
//                String piese = String.join(", ", solicitare.getPieseSchimb());
//
//                // Scriem toate informațiile în fișier, inclusiv piesele și tariful
//                writer.write(solicitare.getClient().getNume() + ", " +
//                        solicitare.getProblema() + ", " +
//                        solicitare.getAdresa() + ", " +
//                        solicitare.getState() + ", " +
//                        solicitare.getTimpEstimativ() + ", " +
//                        solicitare.isTeamAvailable() + ", " +
//                        piese + ", " +
//                        solicitare.getTarif() + "\n");
//            }
//        }
//    }
}
