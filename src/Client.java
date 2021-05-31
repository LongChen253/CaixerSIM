import java.util.Random;

public class Client {
    //private double tempsEsperat;
    private int ServiceTimeClient;
    private int DubteTimeClient;

    public Client() {
        int[] ServiceTime = {10, 20, 30};

        //tempsEsperat = 0;
        int idx = new Random().nextInt(ServiceTime.length);
        ServiceTimeClient = ServiceTime[idx];
        if (ServiceTimeClient == 10) DubteTimeClient = 0;
        else if (ServiceTimeClient == 20) DubteTimeClient = 5;
        else  DubteTimeClient = 10;
    }

    /*
    public double getTempsEsperat() {
        return tempsEsperat;
    }
    */

    public int getServiceTimeClient() {
        return ServiceTimeClient;
    }

    public int getDubteTimeClient() {
        return DubteTimeClient;
    }
}
