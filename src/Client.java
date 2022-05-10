public class Client {
    // Atributs:
    private String nom;
    private String cognom;
    private String DNI;

    // Constructor:
    public Client(String nom, String cognom, String DNI) {
        this.nom = nom;
        this.cognom = cognom;
        this.DNI = DNI;
    }

    // Metodes:
    public String getNom() {
        return this.nom;
    }

    // Mostrar informació del Client:
    @Override
    public String toString() {
        return "Nom: " + this.nom + ", Cognom: " + this.cognom + ", DNI: " + this.DNI;
    }

    // Mètode per comparar si dos clients són iguals:
    @Override
    public boolean equals(Object o) {

        if (this == o) { // this = posició de memòria de l'objecte que invoca el mètode.
            return true;
        }

        // si o és un producte -> Si la "o" no es un Producte retorna false. Botiga amb Producte, segur q no són iguals
        if (!(o instanceof Client)) {
            return false;
        }

        // fem upcasting, pasem de la classe object a Producte (sabem que ho és pq ho hem mirat a dalt)
        // Així, podem accedir a l'atribut nom.
        Client c = (Client) o;
        return this.nom.equals(c.nom); // Mirem si el dni es el mateix, si ho es, retornem true, sino false,
    }
}
