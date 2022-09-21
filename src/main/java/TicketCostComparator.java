import java.util.Comparator;

public class TicketCostComparator implements Comparator<Ticket> {

    // 2 способ. Сортировка в порядке возрастания стоимости билета
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if (o2.getCost() > o1.getCost()) {
            return -1;
        } else if (o2.getCost() < o1.getCost()) {
            return 1;
        } else {
            return 0;
        }
    }
}