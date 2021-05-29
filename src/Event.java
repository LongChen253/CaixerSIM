import java.util.Random;

public class Event {
    private String nom;
    private String objecte;
    private double temps;

    public Event() {}

    public Event(String nom, String objecte, double temps) {
        this.nom = nom;
        this.objecte = objecte;
        this.temps = temps;
    }

    public void tractarEvent() {
        if (nom == "NewArrival") {

        }
    }
}
