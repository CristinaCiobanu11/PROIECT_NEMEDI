package observer;

import model.Comanda;

public class ClientObserver implements Observer {
    private String numeClient;

    public ClientObserver(String numeClient) {
        this.numeClient = numeClient;
    }

    @Override
    public void update(Comanda comanda) {
        System.out.println("Notificare pentru clientul " + numeClient + ": Starea comenzii tale a fost actualizată la: " + comanda.getStare());
    }
}
