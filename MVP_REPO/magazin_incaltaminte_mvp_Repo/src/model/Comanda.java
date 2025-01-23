package model;

import state.ComandaState;
import observer.Observer;
import state.PlasataState;

import java.util.ArrayList;
import java.util.List;

// Clasa care reprezintă o comandă
public class Comanda {
    private Client client;
    private List<Produs> produse;
    private ComandaState state;
    private List<Observer> observers = new ArrayList<>();

    public Comanda(Client client, List<Produs> produse) {
        this.client = client;
        this.produse = produse;
        this.state = new PlasataState();  // Starea initiala
    }

    public Client getClient() {
        return client;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public String getStare() {
        return state.getState();
    }

    public void setState(ComandaState state) {
        this.state = state;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
