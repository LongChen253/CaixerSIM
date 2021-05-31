import java.util.Comparator;

public class SortbyTime implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getTemps() - o2.getTemps();
    }
}