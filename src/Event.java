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
        if (objecte == "Queue") {
            q.tractarEventQueue(nom, temps, c1, c2, c3, c4, op, q, events);
        }
        else if (objecte == "Server1") {
            c1.tractarEventServer(nom, temps, op, q, events);
        }
        else if (objecte == "Server2") {
            c2.tractarEventServer(nom, temps, op, q, events);
        }
        else if (objecte == "Server3") {
            c3.tractarEventServer(nom, temps, op, q, events);
        }
        else if (objecte == "Server4") {
            c4.tractarEventServer(nom, temps, op, q, events);
        }
    }

    public String getNom() {
        return nom;
    }

    public String getObjecte() {
        return objecte;
    }

    public int getTemps() {
        return temps;
    }

}
