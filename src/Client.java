import java.util.Random;

public class Client {
    //private double tempsEsperat;
    private int ServiceTimeClient;
    private int DubteTimeClient;

    private double tempsEntradaCua;
    private double tempsSortidaCua;

    public double getTempsEntradaCua() {
        return tempsEntradaCua;
    }

    public double getTempsSortidaCua() {
        return tempsSortidaCua;
    }

    public void setTempsEntradaCua(double tempsEntradaCua) {
        this.tempsEntradaCua = tempsEntradaCua;
    }

    public void setTempsSortidaCua(double tempsSortidaCua) {
        this.tempsSortidaCua = tempsSortidaCua;
    }

    public Client() {
        int[] ServiceTime = {10, 20, 30};

        //tempsEsperat = 0;
        int idx = new Random().nextInt(ServiceTime.length);
        ServiceTimeClient = ServiceTime[idx];
        if (ServiceTimeClient == 10) DubteTimeClient = 0;
        else if (ServiceTimeClient == 20) DubteTimeClient = 5;
        else DubteTimeClient = 10;

        tempsEntradaCua = 0;
        tempsSortidaCua = 0;
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
