import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Botiga {
    // Atributs:
    private ArrayList<Producte> llistaProductes;
    private ArrayList<Venta> llistaVentes;
    private ArrayList<Client> llistaClients;

    // Constructor:
    public Botiga() {
        // Inicialitzem les llistes. No hi haurà res, però existiran en memòria.
        llistaProductes = new ArrayList<>();
        llistaVentes = new ArrayList<>();
        llistaClients = new ArrayList<>();
    }

    // Getters & Setters:
    // Per saber si la llista de productes està empty o accedir a la llista, desde el main:
    public ArrayList<Producte> getLlistaProductes() {
        return this.llistaProductes;
    }
    public ArrayList<Venta> getLlistaVentes() {
        return this.llistaVentes;
    }
    public ArrayList<Client> getLlistaClients() {
        return this.llistaClients;
    }

    // Mètodes:

    // Part A:
    /**
     * Aquest mètode, verifica si el producte que li estem passant existeix ja en la llista de productes que té la botiga
     * L'identificador únic és el nom, per tant si existeix un nom, retornarà false i no afegirà el producte
     * Si no troba cap producte amb el nom que li hem passat, afegirà aquest producte i retornarà true.
     * @param p - Objecte Producte
     * @return true si ha pogut afegir el Producte() p a la llista de la botiga i false, si no ha pogut.
     */
    public boolean afegirProducte(Producte p) {
        /*
        // Mira si existeix aquest producte, si hi és, l'afegeix i return true.
        // Else return false
        // La condició es que no hi hagi el mateix nom
        // Així desde el main ja ho podem fer facil if aquest metode missatge ok

        // Afagem el nom del producte que entra com a paràmetre:
        String nomAux = p.getNom();

        for (Producte producteLLista : llistaProductes) {
            if (nomAux.equals(producteLLista.getNom())) {
                System.out.println("Producte Existent! No s'ha afegit a la llista de Productes!");
                return false;
            }
        }

        llistaProductes.add(p);
        System.out.println("Producte afegit correctament!");

        return true;

         */

        // Podem fer un override del mètode equals, ja que contains necesita el equals per saber si hi és.
        if (llistaProductes.contains(p)) {
            System.out.println("Producte Existent! No s'ha afegit a la llista de Productes!");
            return false;
        }

        llistaProductes.add(p);
        System.out.println("Producte afegit correctament!");

        return true;
    }

    /**
     * Aquest mètode mostra per pantalla tots els productes que hi hàgin a la llista de Productes
     * amb: el seu nom i preu.
     */
    public void imprimirProductes() {
        for (Producte p : llistaProductes) {
            System.out.println(p);
        }
    }

    /**
     * Aquest mètode. Retorna el producte donat el seu nom si aquest existeix. Altrament, retornarà null
     * @param nom - nom del producte que volem
     * @return Si existeix - l'objecte / si no existeix - null
     */
    public Producte obtenirProducte(String nom) {

        // Per cada producte de la llista (for each) mirem si el seu nom és igual a la entrada.
        for (Producte p : llistaProductes) {
            if (p.getNom().equals(nom)) {
                return p;
            }
        }

        return null;
    }


    // Part B:

    /**
     * Afageix la venta v, que entra com a paràmetre a la llista de ventes.
     * @param v
     */
    public void afegirVenta(Venta v) {
        // Afemig l'objecte venta a la llista:
        llistaVentes.add(v);
    }

    /**
     * Mètode que mostrarà totes les ventes que hi hagin en la llista de ventes.
     */
    public void imprimirVentes() {
        // Mostra llista de ventes:
        for (Venta v : llistaVentes) {
            System.out.println(v);
        }
    }

    /**
     * Mètode que retornarà el cost total de totes les compres que ha fet el client amb el nom de objecte Client() passat com a param.
     * @param c
     * @return valor del total de les compres del client c
     */
    public double calcularTotalVentesClient(Client c) {
        // Nom del client:
        String nomClient = c.getNom();

        // Variable on guardarem el total de les compres del client.
        double total = 0;

        // Recorrem la llista de ventes i les que estiguin a nom del client, les multipliquem per la quantitat i ho sumem.
        for (Venta v : llistaVentes) {
            // Nom del client de la venta que està recorrent el bucle:
            String nomClientAux = v.getClient().getNom();

            // és el que estem buscant?
            if (nomClient.equals(nomClientAux)) {
                // Quin valor té aquesta compra? -> Valor x Quantitat veguda (Ho fa el mètode:)
                Double quantitat = v.getQuantitat();
                Double valorVentaPersona = v.getProducte().calcularPreu(quantitat);

                // Ho afegim al total:
                total += valorVentaPersona;
            }
        }

        return total;
    }

    /**
     * Retornarà el cost total de les ventes del producte 'p'.
     * @param p
     * @return total en ventes del producte.
     */
    public double calcularTotalVentaProducte(Producte p) {
        // Nom del producte:
        String nomProducte = p.getNom();

        // Variable on guardarem el total de ventes fetes pel producte:
        double total = 0;

        // Recorrem la llista de ventes i les que estiguin a nom de la venta, les multipliquem per la quantitat i ho sumem.
        for (Venta v : llistaVentes) {
            // Nom del client de la venta que està recorrent el bucle:
            String nomProductetAux = v.getClient().getNom();

            // és el que estem buscant?
            if (nomProducte.equals(nomProductetAux)) {
                // Quin valor té aquesta compra? -> Valor x Quantitat veguda
                Double quantitat = v.getQuantitat();
                Double valorVentaProducte = v.getProducte().calcularPreu(quantitat);

                // Ho afegim al total:
                total += valorVentaProducte;
            }
        }

        return total;


    }

    /**
     * Retornarà el cost de totes les ventes en el qual s'hagi utilitzat el mètode de pagament entrat per paràmetre.
     * @param metode - metode de pagament
     * @return total en ventes per el mètode de pagament
     */
    public double calcularTotalVentaSegonsMetodePagament(MetodesDePagament metode) {
        // Total = 0;
        // for (Venta v : llista)
        // if Vente és del metode -> Total = preu * quantitat de la venta.

        // Nom del metode de pagament:
        MetodesDePagament nomMetode = metode;

        // Variable on guardarem el total de ventes fetes pel metode de pagament:
        double total = 0;

        // Recorrem la llista de ventes i les que s'hagin fet amb el mètode de pagament, les multipliquem per la quantitat i ho sumem.
        for (Venta v : llistaVentes) {
            // Nom del metode de la venta que està recorrent el bucle:
            String nomProductetAux = v.getClient().getNom();
            MetodesDePagament nomMetodeAux = v.getMetodeDePagament();

            // és el que estem buscant?
            if (nomMetode.equals(nomMetodeAux)) {
                // Quin valor té aquesta compra? -> Valor x Quantitat veguda
                Double quantitat = v.getQuantitat();
                Double valorVentaProducte = v.getProducte().calcularPreu(quantitat);

                // Ho afegim al total:
                total += valorVentaProducte;
            }
        }

        return total;


    }

    /**
     * Retornarà el producte que més ingressos reporta per la botiga.
     * @return objecte producte més venut
     */
    public Producte obtenirProducteMesVengut() {

        // hashmap per guardar cada element i quants cops apareix:
        // Producte : Valor total en ventes del producte.
        Map<Producte, Double> map = new HashMap<Producte, Double>();

        for (Venta v : llistaVentes) {
            // Producte que estem iterant de la llista de ventes (producte que hem vengut):
            Producte producte = v.getProducte();

            // Diners que ens ha aportat vendre aquest producte: preu X quantitat:
            Double quantitat = v.getQuantitat();
            Double ingressos = producte.calcularPreu(quantitat);

            // Mirem quin valor dins el hash map correspon a aquest producte si no existeix serà null
            Double valor = map.get(producte);

            // Si el producte no hi era fins ara, tindrem un null, llavors, posem un 1. Sinó, +1 valor anterior

            if (valor == null) {
                map.put(producte, ingressos);
            } else {
                map.put(producte, valor + ingressos);
            }

        }

        // Acabem de crear un hash map amb tots els productes i les respectives ventes, ara hem de agafar el valor màxim.
        // Fem que el valor sigui el pirmer, així tindrem com comparar:
        Producte topVentes = (Producte) map.keySet().toArray()[0];
        Double ingresosProducte = map.get(topVentes);

        // Iterem per tot el map que hem creat abans, ara nomès tenim nomProducte únic i valor en ventes.
        // Agafem el més gran.
        for (Map.Entry<Producte, Double> set : map.entrySet()) {
            // Ventes de l'element en qüestió:
            Double ingressosAux = set.getValue();

            // Si el producte s'ha bengut més, el posem com a topVentes.
            if (ingresosProducte < ingressosAux) {
                ingresosProducte = ingressosAux;
                topVentes = set.getKey();
            }
        }

        System.out.println("El best Seller és: " + topVentes.getNom() + " amb uns ingressos de: " + ingresosProducte);

        return topVentes;

    }

    /**
     * Retornarà el client que ha gastat més a la botiga.
     * @return objecte client que més ha gastat.
     */
    public Client obtenirClientQueHaGastatMes() {

        // hashmap per guardar cada client i quant ha gastat
        // Client : gasto total en la botiga.
        Map<Client, Double> map = new HashMap<Client, Double>();

        for (Venta v : llistaVentes) {
            // Client que estem iterant de la llista de ventes (client que ha comprat):
            Client client = v.getClient();

            // Producte que ha comprat el client, producte que s'ha efectuat amb la la venta que estem iterant.
            Producte producte = v.getProducte();

            // Diners que ha gastat el client en la botiga: preu del producte X quantitat:
            Double quantitat = v.getQuantitat();
            Double ingressos = producte.calcularPreu(quantitat);

            // Mirem quin valor dins el hash map correspon a aquest client si no existeix serà null
            Double valor = map.get(client);

            // Si el comprador no hi era fins ara, tindrem un null, llavors, posem un 1. Sinó, +1 valor anterior

            if (valor == null) {
                map.put(client, ingressos);
            } else {
                map.put(client, valor + ingressos);
            }

        }

        // Acabem de crear un hash map amb tots els clients i les respectives compres, ara hem de agafar el valor màxim.
        // Fem que el valor sigui el pirmer, així tindrem com comparar:
        Client topComprador = (Client) map.keySet().toArray()[0];
        Double gastoClient = map.get(topComprador);

        // Iterem per tot el map que hem creat abans, ara nomès tenim nomProducte únic i valor en ventes.
        // Agafem el més gran.
        for (Map.Entry<Client, Double> set : map.entrySet()) {
            // Ventes de l'element en qüestió:
            Double gastoAux = set.getValue();

            // Si el producte s'ha bengut més, el posem com a topVentes.
            if (gastoClient < gastoAux) {
                gastoClient = gastoAux;
                topComprador = set.getKey();
            }
        }

        System.out.println("El top Comprador és: " + topComprador.getNom() + " amb uns ingressos de: " + gastoClient);


        return topComprador;
    }


    /**
     * Mètode que afegeix un client a la llista de clients si aquest és nou, altrament retorna false perque ja existeix.
     * @param c
     * @return
     */
    public boolean afegirNouClient(Client c) {
        if (llistaClients.contains(c)) {
            System.out.println("Client existent, no s'ha afeti a la llista de clients");
            return false;
        }

        llistaClients.add(c);
        System.out.println("client afegit correctament!");

        return true;
    }



    /**
     * Mètode que donat un nom de client, retorna l'objecte de la llista que concideix amb ell.
     * @param nomClient
     * @return
     */
    public Client obtenirObjecteClient(String nomClient) {
        for (Client c : llistaClients) {
            if (c.getNom().equals(nomClient)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Mètode que donat un nom de client, retorna l'objecte de la llista que concideix amb ell.
     * @param nomProducte
     * @return
     */
    public Producte obtenirObjecteProducte(String nomProducte) {
        for (Producte p : llistaProductes) {
            if (p.getNom().equals(nomProducte)) {
                return p;
            }
        }
        return null;
    }




}
