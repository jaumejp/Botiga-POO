import java.util.ArrayList;

public class Venta {

    // Atributs:
    private MetodesDePagament metodeDePagament;
    private Client client;
    private Producte producte;
    private Double quantitat;

    // Constructor:
    public Venta(MetodesDePagament metode, Client client, Producte producte, Double quantitat) {
        this.metodeDePagament = metode;
        this.client = client;
        this.producte = producte;
        this.quantitat = quantitat;
    }

    // Mostrar informació de la venta:
    @Override
    public String toString() {
        return "Venta: " +
                "\n\tClient: " + this.client.getNom() +
                "\n\tProducte: " + this.producte.getNom() +
                "\n\tQuantitat: " + this.quantitat +
                "\n\tMètode de pagament: " + this.metodeDePagament;
    }

    // Retornar el client que ha fet la compra (venta per la botiga).
    public Client getClient() {
        return this.client;
    }

    // Retornar el Producte que s'ha vengut amb aquesta venta.
    public Producte getProducte() {
        return this.producte;
    }

    // Retornar la quantitat de productes que hi ha hagut per aquesta venda:
    public Double getQuantitat() {
        return this.quantitat;
    }

    // Retornar el mètode de pagament amb el qual s'ha fet la venta:
    public MetodesDePagament getMetodeDePagament() {
        return this.metodeDePagament;
    }
}
