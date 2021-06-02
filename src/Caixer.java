import java.util.Collections;
import java.util.List;

public class Caixer{
    private int PServerX_t;
    private int PServerX_Elems;

    private int time;
    private int timeLeft;

    private boolean esperant_operariX;
    private boolean available;
    private String state;

    private Client client;

    public Caixer () {
        this.PServerX_t = 0;
        this.PServerX_Elems = 0;
        this.time = 0;
        this.timeLeft = 0;
        this.esperant_operariX = false;
        this.available = true;
        state = "IDLE";
    }

    public boolean IsAvailable () {
        return available;
    }

    public void changeAvailable (boolean b) {
        available = b;
    }

    public void setClient (Client x) {
        this.client = x;
    }

    public Client getClient() {
        return client;
    }

    public int getPServerX_Elems() {
        return PServerX_Elems;
    }

    private void changeState (String estat) {
        this.state = estat;
    }

    public void tractarEventServer(String nom, int temps, Operari op, Queue q, List<Event> events) {
        String event = nom.substring(0, nom.length()-1);
        char server = nom.charAt(nom.length()-1);

        if (event.equals("NewService")) {
            this.PServerX_t = client.getServiceTimeClient();
            if (server == '1') events.add(new Event("EndService1", "Server1", temps + PServerX_t));
            else if (server == '2') events.add(new Event("EndService2", "Server2", temps + PServerX_t));
            else if (server == '3') events.add(new Event("EndService3", "Server3", temps + PServerX_t));
            else if (server == '4') events.add(new Event("EndService4", "Server4", temps + PServerX_t));

            this.time = temps;
            if (client.getDubteTimeClient() != 0) {     //client té dubtes
                int delayDubte = (int) (Math.random() * (PServerX_t - 1 - 1)) + 1;
                if (server == '1') events.add(new Event("NewDubte1", "Server1", temps + delayDubte));
                else if (server == '2') events.add(new Event("NewDubte2", "Server2", temps + delayDubte));
                else if (server == '3') events.add(new Event("NewDubte3", "Server3", temps + delayDubte));
                else if (server == '4') events.add(new Event("NewDubte4", "Server4", temps + delayDubte));
            }
            changeState("BUSY");
        }
        else if (event.equals("EndService") && !esperant_operariX) {
            if (state == "BUSY") {
                ++PServerX_Elems;
                if (server == '1') {
                    System.out.println("PServer1_Elems: " + PServerX_Elems);
                    events.add(new Event("FinishService1", "Queue", temps));
                }
                else if (server == '2') {
                    System.out.println("PServer2_Elems: " + PServerX_Elems);
                    events.add(new Event("FinishService2", "Queue", temps));
                }
                else if (server == '3') {
                    System.out.println("PServer3_Elems: " + PServerX_Elems);
                    events.add(new Event("FinishService3", "Queue", temps));
                }
                else if (server == '4') {
                    System.out.println("PServer4_Elems: " + PServerX_Elems);
                    events.add(new Event("FinishService4", "Queue", temps));
                }
                changeState("IDLE");
            }
        }
        else if (event.equals("NewDubte") && !esperant_operariX) {
            timeLeft = PServerX_t - (temps - time);
            if (op.IsAvailable()) {
                if (server == '1') events.add(new Event("NewDubte1", "Operari", temps));
                else if (server == '2') events.add(new Event("NewDubte2", "Operari", temps));
                else if (server == '3') events.add(new Event("NewDubte3", "Operari", temps));
                else if (server == '4') events.add(new Event("NewDubte4", "Operari", temps));
                this.esperant_operariX = false;
                op.changeAvailable(false);
                changeState("ASKING");
            } else {
                this.esperant_operariX = true;
            }
        }
        else if (event.equals("FinishDubte")) {
            if (state == "BUSY" && esperant_operariX) {
                if (op.IsAvailable()) {
                    if (server == '1') events.add(new Event("NewDubte1", "Operari", temps));
                    else if (server == '2') events.add(new Event("NewDubte2", "Operari", temps));
                    else if (server == '3') events.add(new Event("NewDubte3", "Operari", temps));
                    else if (server == '4') events.add(new Event("NewDubte4", "Operari", temps));
                    this.esperant_operariX = false;
                    op.changeAvailable(false);
                    changeState("ASKING");
                }
            } else if (state == "ASKING") {
                int aux = temps + timeLeft - client.getDubteTimeClient();
                if (server == '1') {
                    events.removeIf(t -> t.getNom() == "EndService1" && t.getObjecte() == "Server1" && t.getTemps() == aux);
                    events.add(new Event("EndService1", "Server1", temps + timeLeft));
                }
                else if (server == '2') {
                    events.removeIf(t -> t.getNom() == "EndService2" && t.getObjecte() == "Server2" && t.getTemps() == aux);
                    events.add(new Event("EndService2", "Server2", temps + timeLeft));
                }
                else if (server == '3') {
                    events.removeIf(t -> t.getNom() == "EndService3" && t.getObjecte() == "Server3" && t.getTemps() == aux);
                    events.add(new Event("EndService3", "Server3", temps + timeLeft));
                }
                else if (server == '4') {
                    events.removeIf(t -> t.getNom() == "EndService4" && t.getObjecte() == "Server4" && t.getTemps() == aux);
                    events.add(new Event("EndService4", "Server4", temps + timeLeft));
                }
                changeState("BUSY");
            }
        }

        events.remove(0);
        Collections.sort(events, new SortbyTime());
    }

}