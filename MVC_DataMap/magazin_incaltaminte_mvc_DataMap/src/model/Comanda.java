package model;

import state.ComandaState;
import state.PlasataState;
import state.PreluataState;

import java.util.ArrayList;
import java.util.List;

// Clasa care reprezintă o comandă
public class Comanda {
    private Client client;
    private List<Produs> produse;
    private String adresa;
    private ComandaState state;

    public Comanda(Client client, List<Produs> produse, String adresa, ComandaState state) {
        this.client = client;
        this.produse = produse;
        this.adresa = adresa;
        this.state = state;
    }

    public Comanda(Client client, List<Produs> produse) {
        this.client = client;
        this.produse = produse;
        this.state = new PreluataState();  // Starea initiala
    }

    public Client getClient() {
        return client;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public String getAdresa() {
        return adresa;
    }

    public ComandaState getState() {
        return state;
    }

    public void setState(ComandaState state) {
        this.state = state;
    }

    public String getStare() {
        return state.getState();
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}



