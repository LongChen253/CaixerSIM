public class Caixer{
    private double PServerX_t;
    private int PServerX_Elems;

    private double time;
    private double timeLeft;

    private boolean esperant_operariX;
    private boolean available;

    private Client c;


    public Caixer () {
        this.PServerX_t = 0;
        this.PServerX_Elems = 0;
        this.time = 0;
        this.timeLeft = 0;
        this.esperant_operariX = false;
        this.available = true;
    }

    public boolean IsAvailable () {
        return available;
    }

    public void changeAvailable () {
        if (available) available = false;
        else available = true;
    }

    public void setClient (Client x) {
        this.c = x;
    }

}
