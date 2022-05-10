import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    // Posem la botiga d'animals amb hard-code.
    static Botiga tendaAnimals = new Botiga();

    public static void main(String[] args) {
          // Declarem el lector per poder fer lectures per la consola.
        Scanner lector = new Scanner(System.in);

        // Bucle que ens permet anar fent les diferents opcións del menú mentre l'usuari no vulgui sortir.
        // Mostrem el menú Principal i fem Switch amb la opció:
        String opcio = "";
        do {
            mostrarMenu(infoMenuPrincipal());
            opcio = lector.nextLine();
            switch (opcio) {
                case "1": // Crear un producte i afegir-lo a la llista
                    demanarProducte();
                    break;
                case "2": // Veure tots els productes
                    if (tendaAnimals.getLlistaProductes().isEmpty()) {
                        System.out.println("Mínim has d'entrar un producte primer.");
                    } else {
                        tendaAnimals.imprimirProductes();
                    }
                    break;
                case "3": // Veure preu d'un producte
                    if (tendaAnimals.getLlistaProductes().isEmpty()) {
                        System.out.println("Mínim has d'entrar un producte primer.");
                    } else {
                        calcularPreu();
                    }
                    break;
                case "4": // Crear venta nova i afegir-la a la llista de ventes de la botiga.
                    if (tendaAnimals.getLlistaProductes().isEmpty() || tendaAnimals.getLlistaClients().isEmpty()) {
                        System.out.println("Error. Primer has d'entrar productes i clients");
                    } else {
                        crearVenta();
                    }
                    break;
                case "5": // Mostrar totes les ventes.
                    if (tendaAnimals.getLlistaVentes().isEmpty()) {
                        System.out.println("Mínim has d'entrar una venta al sistema");
                    } else {
                        tendaAnimals.imprimirVentes();
                    }
                    break;
                case "6": // Compres d'un client.
                    Client c = demanarClient();
                    Double gastoClient = tendaAnimals.calcularTotalVentesClient(c);
                    System.out.println("En " + c.getNom() + "Ha gastat " + gastoClient);
                    break;
                case "7": // Compres d'un producte.
                    Producte p = demanarNomProducte();
                    if (p == null) {
                        System.out.println("Producte no existent, creal primer!");
                    } else {
                        Double ingressosProducte = tendaAnimals.calcularTotalVentaProducte(p);
                        System.out.println("El producte: " + p.getNom() + "Ha suposat " + ingressosProducte + " €");
                    }
                    break;
                case "8": // El mateix amb 1 mètode de pagament.
                    MetodesDePagament metode = demanarMetodePagament();
                    Double ingressosMetodePagament = tendaAnimals.calcularTotalVentaSegonsMetodePagament(metode);
                    System.out.println("El metode: " + metode + "Ha suposat " + ingressosMetodePagament + " €");
                    break;
                case "9": // Producte més venut i cost total. Ho imprimeix el mètode.
                    if (tendaAnimals.getLlistaVentes().isEmpty()) {
                        System.out.println("Error. Entrar ventes primer");
                    } else {
                        Producte topVentes = tendaAnimals.obtenirProducteMesVengut();
                    }
                    break;
                case "10": // Imprimir el client que ha gastat més i també el total del seus costos. Ho fa el mètode.
                    Client topComprador = tendaAnimals.obtenirClientQueHaGastatMes();
                    break;
                case "11":
                    Client c_aux = demanarClient();
                case "0":
                    System.out.println("Gracies per utilitzar el programa de gestió de la botiga!");
                    break;
                default:
                    System.out.println("Has d'entrar una opció del menú correcte.");
            }
            } while (!opcio.equals("0"));
        }

    /**
     * Demana per pantalla el nom del producte que volem veure el preu i invoca els metodes necessàris per veure'l.
     */
    public static void calcularPreu() {
        // Donat un nom de producte i una quantitat imprimir-ne el preu final (fer us de la funció calcularPreu).

        // Scanner lector per llegir per pantalla:
        Scanner lector = new Scanner(System.in);

        // Marca que ens permetrà determinar si el nom que ha entrat és correcte.
        // Recordem que per entrar en aquesta part de programa, han d'existir productes a la llista.
        boolean nom_OK = false;

        System.out.println("Entra el nom del producte.");
        System.out.println("Pots escriure Nom: ¿ per veure tots els noms.");

        while (!nom_OK) {
            System.out.print("Nom: ");
            String nom = lector.nextLine();

            // Posem el resultat de cirdar el metode en una variable:
            Producte p = tendaAnimals.obtenirProducte(nom);

            if (nom.equals("¿")) {
                tendaAnimals.imprimirProductes();
            } else if (p == null) {
                // El producte no existeix en la llista de productes de la botiga.
                System.out.println("El nom entrat no existeix, entra un nom correcte.");
                System.out.println("Escriu ¿ per veure'ls tots.");
            } else {
                // nom correcte, tenim l'objecte amb el nom que li hem passat.
                // Demanem la quantitat d'aquest producte:
                System.out.println("Entra la quantitat de " + nom);
                double quantitat = Double.parseDouble(lector.nextLine());

                // Calculem el preu i ho mostrem per pantalla:
                System.out.println(p.calcularPreu(quantitat));;

                // Donem per bona la lectura per pantalla:
                nom_OK = true;
            }

        }

    }

    /**
     * Crear un producte nou i afegir-lo a la llista de productes de la botiga.
     * (S’haurà de demanar a l’usuari la informació necessària per omplir el producte.
     * S’imprimirà un error si el producte ja existia.
     */
    public static void demanarProducte() {
        // Crear un producte nou i afegir-lo a la llista de productes de la botiga.
        // (S’haurà de demanar a l’usuari la informació necessària per omplir el producte.
        // S’imprimirà un error si el producte ja existia.

        // Declarem l'scanner.
        Scanner lector = new Scanner(System.in);
        // Creem un booleà, que controlarà quan hem de sortir del bucle.
        boolean fiPrograma = false;

        // Mostrem el menú del Producte i fem Switch amb la opció:
        String opcio = "";
        do {
            mostrarMenu(infoMenuProducte());
            opcio = lector.nextLine();
            switch (opcio) {
                case "1":
                    demanarProductePes();
                    break;
                case "2":
                    demanarProducteUnitats();
                case "0":
                    System.out.println("Sortint del menú de entrar productes...");
                default:
                    System.out.println("Has d'entrar una opció del menú correcte.");
            }
            if (opcio.equals("0")) {
                fiPrograma = true;
            }
        } while (!fiPrograma);

    }

    /**
     * Mètode que demana a l'usuari un producte que es cobra per pes i l'intenta afegir a la llista.
     */
    public static void demanarProductePes() {
        // Scanner:
        Scanner lector = new Scanner(System.in);

        // Demanem les dades per crear un producte que es cobrarà per pes
        System.out.println("Introdueix les dades del producte:");
        System.out.println("==================================");
        System.out.println("Nom:");
        String nom = lector.nextLine();
        System.out.println("Preu per Kg:");
        double preu = Double.parseDouble(lector.nextLine());

        ProductePes prodAuxPes = new ProductePes(preu, nom);

        // Intentem afegir el producte a la llista:
        // Si es fa o no, ja es mostrarà per pantalla i en segon pla es farà.
        afegirProducte(prodAuxPes);
    }

    /**
     * Mètode que demana les dades d'un producte que es cobri per unitats i l'intenta afegir a la llista.
     */
    public static void demanarProducteUnitats() {
        // Lector:
        Scanner lector = new Scanner(System.in);

        // Per verificar si ha saltat la throw exeption i es posa el descompte bé o no.
        // No sería més fàcil que ho verifiques el main ¿?
        boolean dades_objecte_OK = false;

        // Creem el producte aquí perque estigui fora de l'scope del while:
        ProducteUnitat prodAuxUnitari = null;

        while (!dades_objecte_OK) {
            // Demanem les dades per crear un producte que es cobrarà per unitats
            System.out.println("Introdueix les dades del producte:");
            System.out.println("==================================");
            System.out.println("Nom:");
            String nom = lector.nextLine();
            System.out.println("Preu Unitari:");
            double preu = Double.parseDouble(lector.nextLine());
            System.out.println("Entra el preu mínim per aplicar el descompte:");
            double preuMinPerDesc = Double.parseDouble(lector.nextLine());
            System.out.println("Descompte Ex: 0.1:");
            double descompte = Double.parseDouble(lector.nextLine());

            try {
                prodAuxUnitari = new ProducteUnitat(preu,nom,descompte,preuMinPerDesc);
                dades_objecte_OK = true;

            } catch (Exception e) {
                System.out.println("Error. Entra un descompte entre 0 i 1.");
            }
        }

        // Un cop les dades són correctes, intentem posar aquest producte a l'array list:
        // Aquesta verificació la fa el metode botiga:
        afegirProducte(prodAuxUnitari);
    }

    /**
     * Mètode que crida el mètode de afegir el producte a la botiga i mostre els possibles resultats de l'operació.
     * @param p - producte que volem introduir a la llista.
     */
    public static void afegirProducte(Producte p) {
        if (tendaAnimals.afegirProducte(p)) {
            System.out.println("Producte Afegit");
        } else {
            System.out.println("No s'ha pogut crear el producte perque ja existeix!");
        }
    }

    /**
     * Mètode que donat un menú en forma de String, el mostra per pantalla:
     */
    public static void mostrarMenu(String menu) {
        System.out.println(menu);
    }

    /**
     * Mètode que retorna la informació del menú principal.
     * @return
     */
    public static String infoMenuPrincipal() {
        return "======= Gestió Botiga =======\n1.Crear un producte i afegir-lo a la llista.\n2.Veure tots els productes.\n3.Veure preu\n4.Crear nova Venta\n5.Mostrar Vendes\n6.Compres d'un Client per Nom\n7.Compres d'un producte\n8.Compres efectuades amb metode de pagament...\n9.Top Ventes\n10.Top Comprador\n11.Afegir Client\n0.Sortir\n===============================\nOpció: ";
    }

    /**
     * Mètode que retorna la informació del menú de producte unitari VS pes.
     * @return
     */
    public static String infoMenuProducte() {
        return "Vols entrar un producte per pes o per unitats?\n1.- Producte per Pes\n2.- Producte per Unitats\n0.- Sortir\nOpcio:";
    }

    // Mètodes Part B:

    /**
     * Mètode que demana la informació de la venta efectuada i l'afegeix a la llista de ventes de la botiga.
     */
    public static void crearVenta() {
        // Demanar info + botiga.afegirVenta(Venta v)

        Scanner lector = new Scanner(System.in);

        System.out.println("\nEntra les dades de la venta: ");
        // Demanem les dades:

        MetodesDePagament metode = demanarMetodePagament();

        Client client = demanarClient();
        Producte producte = demanarNomProducte();

        System.out.println("Quantitat venguda: ");
        Double quantitat = Double.parseDouble(lector.nextLine());

        // Creem la vena:
        Venta v = new Venta(metode, client, producte, quantitat);

        // Afegim la venta a la llista:
        tendaAnimals.afegirVenta(v);

    }

    /**
     * Demanem a l'usuari un nom de client i retornem l'objecte.
     * @return
     */
    public static Client demanarClient() {
        // Demanar un client per nom i retornar l'objecte (que està dins la llista botiga)

        Scanner lector = new Scanner(System.in);

        //boolean nom_OK = false;

        while (true) { // Iterem mentre no tinguem un client correcte. El break es fa amb el return.
            System.out.println("Entra el nom del client o escriu ¿ per veure'ls tots: ");

            System.out.println("Nom: ");

            String nom = lector.nextLine();

            if (nom.equals("¿")) {
                // Mostrar tots els noms
                if (tendaAnimals.getLlistaClients().isEmpty()) {
                    System.out.println("No hi han clients al sistema");
                } else {
                    for (Client c : tendaAnimals.getLlistaClients()) {
                        System.out.println(c);
                    }
                }

            } else if (tendaAnimals.obtenirObjecteClient(nom) == null) {
                // El client no existeix
                // En creem un de nou amb aquest nom. Demanem les altres dades.
                System.out.println("Aquest nom no pertany al nostre sistema. Creem un nou client amb aquest nom...");
                System.out.println("Entra el cognom del client: ");
                String cognom = lector.nextLine();
                System.out.println("Entra el DNI del client: ");
                String DNI = lector.nextLine();

                // Creem l'objecte, l'afegim a la llista i el retornem:
                Client c_aux = new Client(nom, cognom, DNI);

                if (tendaAnimals.afegirNouClient(c_aux)) {
                    System.out.println("Creat i Afegit correctament");
                } else {
                    System.out.println("Hi ha hagut algun error.");
                }

                return c_aux;

            } else {
                // Nom existeix, retornem aquest objecte.
                return tendaAnimals.obtenirObjecteClient(nom);

            }
        }

    }

    /**
     * Mètode que demana per pantalla un nom de producte i retorna l'objecte de la llista de botiga.
     * @return
     */
    public static Producte demanarNomProducte() {
        // Demanar un producte per nom i retornar l'objecte (que està dins la llista botiga)

        Scanner lector = new Scanner(System.in);

        //boolean nom_OK = false;

        while (true) { // Iterem mentre no tinguem un producte correcte. El break es fa amb el return.
            System.out.println("Entra el nom del producte o escriu ¿ per veure'ls tots: ");

            System.out.println("Nom: ");

            String nom = lector.nextLine();

            if (nom.equals("¿")) {
                // Mostrar tots els noms
                for (Producte p : tendaAnimals.getLlistaProductes()) {
                    System.out.println(p);
                }
            } else if (tendaAnimals.obtenirObjecteProducte(nom) == null) {
                // Producte no existeix torna al menú principal i creal.
                return null;

            } else {
                // Nom existeix, retornem aquest objecte.
                return tendaAnimals.obtenirObjecteProducte(nom);

            }
        }

    }

    /**
     * Mètode que llegeix un metode de pagament i el retorna
     * @return
     */
    public static MetodesDePagament demanarMetodePagament() {
        // Mostrar els metodes de pagament que tenim i que l'usuari tri quin vol veure.
        Scanner lector = new Scanner(System.in);

        System.out.println("Mètode de Pagament: ");
        String metode = lector.nextLine();

        MetodesDePagament metodePagament = null;

        switch (metode) {
            case "tarjeta":
                metodePagament = MetodesDePagament.tarjeta;
                break;
            case "efectiu":
                metodePagament = MetodesDePagament.efectiu;
                break;
            case "online":
                metodePagament = MetodesDePagament.online;
                break;
            default:
                System.out.println("tipus de pagament incorrecte.");
        }

        return metodePagament;
    }

}
