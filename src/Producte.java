abstract public class Producte {
    // Atributs:
    private double preu;
    private String nom;

    // Constructor:
    public Producte(double preu, String nom) {
        this.preu = preu;
        this.nom = nom;
    }

    // Mètodes:
    public abstract double calcularPreu(double quantitat);

    // Mostrar informació del producte:
    @Override
    public String toString() {
        return "Producte: " + this.nom + ", costa:" + this.preu + "€";
    }

    // Setters i Getters:
    public double getPreu() {
        return this.preu;
    }
    public void setPreu(double nouPreu) {
        this.preu = nouPreu;
    }

    public String getNom() {
        return this.nom;
    }
    public void setNom(String nouNom) {
        this.nom = nouNom;
    }

    // Mètode per comparar si dos productes són iguals:
    @Override
    public boolean equals(Object o) {

        if (this == o) { // this = posició de memòria de l'objecte que invoca el mètode.
            return true;
        }

        // si o és un producte -> Si la "o" no es un Producte retorna false. Botiga amb Producte, segur q no són iguals
        if (!(o instanceof Producte)) {
            return false;
        }

        // fem upcasting, pasem de la classe object a Producte (sabem que ho és pq ho hem mirat a dalt)
        // Així, podem accedir a l'atribut nom.
        Producte prod = (Producte) o;
        return this.nom == prod.nom; // Mirem si el dni es el mateix, si ho es, retornem true, sino false,
    }
}
