import java.util.List;

public class Operari {
    private double POperari_t;
    private boolean available;
    private int POperari_Elems;

    public Operari () {
        this.POperari_t = 0;
        this.POperari_Elems = 0;
        this.available = true;
    }

    public double getPOperari_t () {
        return POperari_t;
    }

    public boolean IsAvailable () {
        return available;
    }

    public void sumarPOperari_Elems () { ++POperari_Elems; }

    public void changeAvailable (boolean b) {
        this.available = b;
    }
}
