/* HelloWorld.java
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Caixer c1 = new Caixer();
        Caixer c2 = new Caixer();
        Caixer c3 = new Caixer();
        Caixer c4 = new Caixer();

        Operari op = new Operari();

        List<Event> events = new ArrayList<>();

        Queue q = new Queue(events);

        Collections.sort(events, new SortbyTime());

        while (!events.isEmpty()) {
            System.out.println("-----------Events-----------");
            for (int i = 0; i < events.size(); ++i) {
                System.out.println(events.get(i).getNom() + " " + events.get(i).getObjecte() + " " + events.get(i).getTemps());
            }
            System.out.println("----------------------------");

            Event aux = events.get(0);
            aux.tractarEvent(c1, c2, c3, c4, op, q, events);
        }
    }
}
