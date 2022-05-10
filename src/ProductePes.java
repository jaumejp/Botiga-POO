public class ProductePes extends Producte {
    // Atributs

    // Constructor
    public ProductePes(double preu, String nom) {
        super(preu, nom);
    }

    // Mètodes
    @Override
    public double calcularPreu(double quantitat) {
        return getPreu() * quantitat;
    }
}
