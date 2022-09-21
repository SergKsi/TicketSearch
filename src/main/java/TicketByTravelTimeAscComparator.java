import java.util.Comparator;

// 3 способ. Сортировка в порядке возрастания времени перелета

public class TicketByTravelTimeAscComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if (o2.getTravelTime() > o1.getTravelTime()) {
            return -1;
        } else if (o2.getTravelTime() < o1.getTravelTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}
