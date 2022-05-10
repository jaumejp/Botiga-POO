public class ProducteUnitat extends Producte {
    // Atributs
    private double descompte;
    private double preuMinPerDesc;

    // Constructor
    public ProducteUnitat(double preu, String nom, double descompte, double preuMinPerDesc) throws IllegalArgumentException {
        super(preu, nom);
        if (descompte < 0 || descompte > 1) {
            throw new IllegalArgumentException("Error. El descompte ha d'estar entre 0 i 1.");
        }
        this.descompte = descompte;
        this.preuMinPerDesc = preuMinPerDesc;
    }

    // Mètodes
    @Override
    public double calcularPreu(double quantitat) {
        double preuFinal = 0;
        if ((getPreu()*quantitat) >= this.preuMinPerDesc) {
            preuFinal = ((getPreu() * quantitat) - ((getPreu() * quantitat) * this.descompte));
        } else {
            preuFinal = (getPreu() * quantitat);
        }
        return preuFinal;
    }

    // Setters i Getters
    public double getDescompte() {
        return this.descompte;
    }
    public void setDescompte(double nouDescompte) {
        // Verificar igual que a dalt, descompte entre 0-1.
        this.descompte = nouDescompte;
    }

    public double getPreuMinPerDesc() {
        return this.preuMinPerDesc;
    }
    public void setPreuMinPerDesc(double nouPreuMinim) {
        // Verificar que sigui > 0
        this.preuMinPerDesc = nouPreuMinim;
    }
}
