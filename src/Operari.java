import java.util.Collections;
import java.util.List;

public class Operari {
    private int POperari_t;
    private boolean available;
    private int POperari_Elems;
    private String state;

    public Operari () {
        this.POperari_t = 0;
        this.POperari_Elems = 0;
        this.available = true;
        this.state = "IDLE";
    }

    public double getPOperari_t () {
        return POperari_t;
    }

    public int getPOperari_Elems() {
        return POperari_Elems;
    }

    public boolean IsAvailable () {
        return available;
    }

    public void changeAvailable (boolean b) {
        this.available = b;
    }

    public void changeState (String state) {
        this.state = state;
    }

    public void tractarEventOperari(String nom, int temps, Caixer c1, Caixer c2, Caixer c3, Caixer c4, List<Event> events) {
        String event = nom.substring(0, nom.length() - 1);
        char server = nom.charAt(nom.length() - 1);

        if (event.equals("NewDubte")) {
            if (server == '1') {
                POperari_t = c1.getClient().getDubteTimeClient();
                events.add(new Event("FinishDubte1", "Operari", temps + POperari_t));
            }
            else if (server == '2') {
                POperari_t = c2.getClient().getDubteTimeClient();
                events.add(new Event("FinishDubte2", "Operari", temps + POperari_t));
            }
            else if (server == '3') {
                POperari_t = c3.getClient().getDubteTimeClient();
                events.add(new Event("FinishDubte3", "Operari", temps + POperari_t));
            }
            else if (server == '4') {
                POperari_t = c4.getClient().getDubteTimeClient();
                events.add(new Event("FinishDubte4", "Operari", temps + POperari_t));
            }
            changeState("BUSY");
        } else if (event.equals("FinishDubte")) {
            ++POperari_Elems;
            System.out.println("POperari_Elems: " + POperari_Elems);
            events.add(new Event("FinishDubte1", "Server1", temps));
            events.add(new Event("FinishDubte2", "Server2", temps));
            events.add(new Event("FinishDubte3", "Server3", temps));
            events.add(new Event("FinishDubte4", "Server4", temps));
            this.available = true;
            changeState("IDLE");
        }

        events.remove(0);
        Collections.sort(events, new SortbyTime());
    }
}
