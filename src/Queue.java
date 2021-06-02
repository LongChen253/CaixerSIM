import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queue {
    private int PQueue_Elems;
    private List<Client> clients;
    private String state;

    private double sumaElemsCua;
    private double totalCanvisCua;

    private int totalClients;

    private double totalTempsCua;
    private double numeroTempsCua;

    public Queue(List<Event> events) {

        this.PQueue_Elems = 0;
        this.clients = new ArrayList<>();
        Client c = new Client();
        clients.add(c);
        Event aux = new Event("NewArrival", "Queue", 10);
        events.add(aux);
        state = "EMPTY";
        sumaElemsCua = 0;
        totalCanvisCua = 1;

        totalClients = 0;

        totalTempsCua = 0;
        numeroTempsCua = 0;
    }

    public List<Client> getClients() {
        return clients;
    }

    public String getState() {
        return state;
    }

    public int getPQueue_Elems() {
        return PQueue_Elems;
    }

    public double getSumaElemsCua() {
        return sumaElemsCua;
    }

    public double getTotalCanvisCua() {
        return totalCanvisCua;
    }

    public int getTotalClients() {
        return totalClients;
    }

    public double getTotalTempsCua() {
        return totalTempsCua;
    }

    public double getNumeroTempsCua() {
        return numeroTempsCua;
    }

    public void sumarPQueue_Elems() {
        ++this.PQueue_Elems;
        ++totalCanvisCua;
        sumaElemsCua += PQueue_Elems;
    }

    public void restarPQueue_Elems() {
        --this.PQueue_Elems;
        ++totalCanvisCua;
        sumaElemsCua += PQueue_Elems;
    }

    public void changeState(String state) {
        this.state = state;
    }

    public void tractarEventQueue(String nom, int temps, Caixer c1, Caixer c2, Caixer c3, Caixer c4, Operari op, Queue q, List<Event> events) {
        if (nom == "NewArrival") {
            if (temps < 1440) {
                events.add(new Event(nom, "Queue", temps + 10));
            }

            Client c = new Client();
            q.getClients().add(c);
            q.getClients().get(0).setTempsEntradaCua(temps);
            ++totalClients;


            if (q.getState() == "EMPTY") {
                System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                if (c1.IsAvailable()) {
                    c1.changeAvailable(false);
                    c1.setClient(q.getClients().get(0));
                    events.add(new Event("NewService1", "Server1", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);;
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                } else if (c2.IsAvailable()) {
                    c2.changeAvailable(false);
                    c2.setClient(q.getClients().get(0));
                    events.add(new Event("NewService2", "Server2", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                } else if (c3.IsAvailable()) {
                    c3.changeAvailable(false);
                    c3.setClient(q.getClients().get(0));
                    events.add(new Event("NewService3", "Server3", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                } else if (c4.IsAvailable()) {
                    c4.changeAvailable(false);
                    c4.setClient(q.getClients().get(0));
                    events.add(new Event("NewService4", "Server4", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                } else {
                    q.sumarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    q.changeState("NOEMPTY");
                }
            } else if (q.getState() == "NOEMPTY") {
                q.sumarPQueue_Elems();
                System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
            }
        } else if (nom == "FinishService1") {
            if (q.getState() == "EMPTY") c1.changeAvailable(true);
            else if (q.getState() == "NOEMPTY") {
                if (q.getPQueue_Elems() == 0) {
                    c1.changeAvailable(true);
                    q.changeState("EMPTY");
                } else {
                    q.restarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    c1.setClient(q.getClients().get(0));
                    events.add(new Event("NewService1", "Server1", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);;
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                }
            }
        } else if (nom == "FinishService2") {
            if (q.getState() == "EMPTY") c2.changeAvailable(true);
            else if (q.getState() == "NOEMPTY") {
                if (q.getPQueue_Elems() == 0) {
                    c2.changeAvailable(true);
                    q.changeState("EMPTY");
                } else {
                    q.restarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    c2.setClient(q.getClients().get(0));
                    events.add(new Event("NewService2", "Server2", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);;
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                }
            }
        } else if (nom == "FinishService3") {
            if (q.getState() == "EMPTY") c3.changeAvailable(true);
            else if (q.getState() == "NOEMPTY") {
                if (q.getPQueue_Elems() == 0) {
                    c3.changeAvailable(true);
                    q.changeState("EMPTY");
                } else {
                    q.restarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    c3.setClient(q.getClients().get(0));
                    events.add(new Event("NewService3", "Server3", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);;
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                }
            }
        } else if (nom == "FinishService4") {
            if (q.getState() == "EMPTY") c4.changeAvailable(true);
            else if (q.getState() == "NOEMPTY") {
                if (q.getPQueue_Elems() == 0) {
                    c4.changeAvailable(true);
                    q.changeState("EMPTY");
                } else {
                    q.restarPQueue_Elems();
                    System.out.println("PQueue_Elems: " + q.getPQueue_Elems());
                    c4.setClient(q.getClients().get(0));
                    events.add(new Event("NewService4", "Server4", temps));
                    q.getClients().get(0).setTempsSortidaCua(temps);;
                    ++numeroTempsCua;
                    totalTempsCua += q.getClients().get(0).getTempsSortidaCua() - q.getClients().get(0).getTempsEntradaCua();
                    q.getClients().remove(0);
                }
            }
        }
        events.remove(0);
        Collections.sort(events, new SortbyTime());
    }
}
