import java.util.ArrayList;
import java.util.List;

public class Queue {
    private int PQueue_Elems;
    private List<Client> clients;
    private String state;

    public Queue (List<Event> events) {

        this.PQueue_Elems = 0;
        this.clients = new ArrayList<>();
        Client c = new Client();
        clients.add(c);
        Event aux = new Event ("NewArrival", "Queue", 10);
        events.add(aux);
        state = "EMPTY";
    }

    public List<Client> getClients () {
        return clients;
    }

    public String getState() {
        return state;
    }

    public int getPQueue_Elems() {
        return PQueue_Elems;
    }

    public void sumarPQueue_Elems () {
        ++this.PQueue_Elems;
    }

    public void changeState() {
        if (state == "EMPTY") state = "NOEMPTY";
        else state = "EMPTY";
    }
}
