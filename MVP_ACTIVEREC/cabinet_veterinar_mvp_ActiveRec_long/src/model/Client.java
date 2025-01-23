package model;

// Clasa care reprezintă un client
public class Client {
    private String nume;
    private String email;

    public Client(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public Client(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }
}
