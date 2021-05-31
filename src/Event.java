import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Event {
    private String nom;
    private String objecte;
    private int temps;

    public Event() {}

    public Event(String nom, String objecte, int temps) {
        this.nom = nom;
        this.objecte = objecte;
        this.temps = temps;
    }

    public void tractarEvent(Caixer c1, Caixer c2, Caixer c3, Caixer c4, Operari op, Queue q, List<Event> events) {
        if (nom == "NewArrival") {
            if (temps < 50) {
                Client c = new Client();
                q.getClients().add(c);
                events.add(new Event(nom, objecte, temps + 10));
                System.out.println(nom + " temps: " + temps);
            }
            events.remove(0);
            if (q.getState() == "EMPTY") {
                System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                if (c1.IsAvailable()) {
                    c1.changeAvailable();
                    c1.setClient(q.getClients().get(0));
                    events.add(new Event("NewService1", "Server1", temps));
                    System.out.println("NewService1 temps---------------> " + temps);
                    q.getClients().remove(0);

                }
                else if (c2.IsAvailable()) {
                    c2.changeAvailable();
                    c2.setClient(q.getClients().get(0));
                    events.add(new Event("NewService2", "Server2", temps));
                    System.out.println("NewService2 temps---------------> " + temps);
                    q.getClients().remove(0);
                }
                else if (c3.IsAvailable()) {
                    c3.changeAvailable();
                    c3.setClient(q.getClients().get(0));
                    events.add(new Event("NewService3", "Server3", temps));
                    System.out.println("NewService3 temps---------------> " + temps);
                    q.getClients().remove(0);
                }
                else if (c4.IsAvailable()) {
                    c4.changeAvailable();
                    c4.setClient(q.getClients().get(0));
                    events.add(new Event("NewService4", "Server4", temps));
                    System.out.println("NewService4 temps---------------> " + temps);
                    q.getClients().remove(0);
                }
                else {
                    q.sumarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    q.changeState();
                }
            }
            else if (q.getState() == "NOEMPTY") {
                q.sumarPQueue_Elems();
                System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
            }

            Collections.sort(events, new SortbyTime());
        }
        else if (nom == "NewService1" || nom == "NewService2" || nom == "NewService3" || nom == "NewService4") {
            events.remove(0);
        }

    }

    public int getTemps() {
        return temps;
    }

}
