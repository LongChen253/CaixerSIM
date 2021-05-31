public class Operari {
    private double POperari_t;
    private boolean Ocupat;
    private int POperari_Elems;

    public Operari () {
        this.POperari_t = 0;
        this.POperari_Elems = 0;
        this.Ocupat = false;
    }

    public double getPOperari_t () {
        return POperari_t;
    }

    public boolean IsOcupat () {
        return Ocupat;
    }
}
