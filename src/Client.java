import java.util.Random;

public class Client {
    private int id;
    private double tempsEsperat;
    private int ServiceTimeClient;
    private int DubteTimeClient;

    public Client() {}

    public Client(int id) {
        int[] ServiceTime = {10, 20, 30};

        this.id = id;
        tempsEsperat = 0;
        int idx = new Random().nextInt(ServiceTime.length);
        ServiceTimeClient = ServiceTime[idx];
        if (ServiceTimeClient == 10) DubteTimeClient = 0;
        else if (ServiceTimeClient == 20) DubteTimeClient = 5;
        else  DubteTimeClient = 10;
    }

    public int getId() {
        return id;
    }

    public double getTempsEsperat() {
        return tempsEsperat;
    }

    public int getServiceTimeClient() {
        return ServiceTimeClient;
    }

    public int getDubteTimeClient() {
        return DubteTimeClient;
    }
}
