/* HelloWorld.java
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Introdueix el temps que vols simular: (per exemple: 1440)");
        int tempsSim = parseInt(myObj.nextLine());  // Read user input

        Caixer c1 = new Caixer();
        Caixer c2 = new Caixer();
        Caixer c3 = new Caixer();
        Caixer c4 = new Caixer();

        Operari op = new Operari();

        List<Event> events = new ArrayList<>();

        Queue q = new Queue(events);
        q.setTempsSimulacio(tempsSim);

        Collections.sort(events, new SortbyTime());

        while (!events.isEmpty()) {
            System.out.println("-----------Events-----------");
            for (int i = 0; i < events.size(); ++i) {
                System.out.println(events.get(i).getNom() + " " + events.get(i).getObjecte() + " " + events.get(i).getTemps());
            }
            System.out.println("----------------------------");
            System.out.println();
            Event aux = events.get(0);
            aux.tractarEvent(c1, c2, c3, c4, op, q, events);
        }
        System.out.println("--------------------------Estadistics--------------------------");
        System.out.println("Temps de simulacio: " + tempsSim + " minuts");
        System.out.println("Total clients: " + q.getTotalClients());
        System.out.print("Mitjana de clients esperant en la cua: ");
        System.out.printf("%.2f", q.getSumaElemsCua()/q.getTotalCanvisCua());
        System.out.println();
        System.out.print("Mitjana temps esperat en la cua: ");
        System.out.printf("%.2f", q.getTotalTempsCua()/q.getNumeroTempsCua());
        System.out.println(" minuts");
        System.out.println("Clients que marxen despres d'esperar 15 minuts: " + q.getClientsMarxats());
        System.out.println();
        System.out.println("Nombre de clients que processa el caixer 1: " + c1.getPServerX_Elems());
        System.out.print("Percentatge del temps que el caixer 1 esta processant: ");
        System.out.printf("%.2f", ((double)c1.getTempsTreball()/(double)q.getTempsSimulacio())*100);
        System.out.println("%");
        System.out.println("Nombre de clients que processa el caixer 2: " + c2.getPServerX_Elems());
        System.out.print("Percentatge del temps que el caixer 2 esta processant: ");
        System.out.printf("%.2f", ((double)c2.getTempsTreball()/(double)q.getTempsSimulacio())*100);
        System.out.println("%");
        System.out.println("Nombre de clients que processa el caixer 3: " + c3.getPServerX_Elems());
        System.out.print("Percentatge del temps que el caixer 3 esta processant: ");
        System.out.printf("%.2f", ((double)c3.getTempsTreball()/(double)q.getTempsSimulacio())*100);
        System.out.println("%");
        System.out.println("Nombre de clients que processa el caixer 4: " + c4.getPServerX_Elems());
        System.out.print("Percentatge del temps que el caixer 4 esta processant: ");
        System.out.printf("%.2f", ((double)c4.getTempsTreball()/(double)q.getTempsSimulacio())*100);
        System.out.println("%");
        System.out.println();
        System.out.println("Nombre de clients amb dubtes que l'operari ha ajudat: " + op.getPOperari_Elems());
        System.out.print("Percentatge del temps que l'operari esta processant: ");
        System.out.printf("%.2f", ((double)op.getTempsTreball()/(double)q.getTempsSimulacio())*100);
        System.out.println("%");
        System.out.println("---------------------------------------------------------------");
    }
}
