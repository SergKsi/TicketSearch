import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket[] ticket = new Ticket[0];

    Ticket ticket1 = new Ticket(12, 1200, "LED", "EGO", 350);
    Ticket ticket2 = new Ticket(8, 900, "EGO", "DME", 55);
    Ticket ticket3 = new Ticket(13, 4200, "LED", "KUF", 350);
    Ticket ticket4 = new Ticket(1, 12200, "LED", "KUF", 320);
    Ticket ticket5 = new Ticket(4, 4500, "DME", "FRU", 500);

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    // получать все сохранённые элементы
    @Test
    public void testGetAllElements() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = manager.getAllTickets();
        Assertions.assertArrayEquals(expected, actual);
    }

    // добавление билета в репозиторий
    @Test
    public void testAddTicket() {
        Ticket ticket6 = new Ticket(35, 23200, "DME", "KUF ", 450);
        manager.add(ticket6);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = manager.getAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    // удаление билета в репозиторий
    @Test
    public void testDelTicket() {
        manager.del(13);
        Ticket[] expected = {ticket1, ticket2, ticket4, ticket5};
        Ticket[] actual = manager.getAllTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    // 1 способ. Сортировка в порядке возрастания стоимости билета
    @Test
    public void testSortTickets() {
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Arrays.sort(tickets);
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket5, ticket4};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(expected, actual);
    }

    // 2 способ. Сортировка в порядке возрастания стоимости билета
    // через CostComparator
    @Test
    public void testSortTicketsComparator() {
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket[] tickets = manager.getAllTickets();
        Arrays.sort(tickets, costComparator);
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket5, ticket4};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(expected, actual);
    }

    // 1 способ. Сортировка в порядке возрастания стоимости билета
    // Стоимость билетов равна
    @Test
    public void testSortTicketsEqual() {
        Ticket ticket6 = new Ticket(96, 4200, "LED", "DEM", 355);
        manager.add(ticket6);
        Ticket[] tickets = manager.getAllTickets();
        Arrays.sort(tickets);
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket6, ticket5, ticket4};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(expected, actual);
    }

    // 2 способ. Сортировка в порядке возрастания стоимости билета
    // через TicketCostComparator
    // Стоимость билетов равна - costComparator
    @Test
    public void testSortTicketsComparatorEqual() {
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket ticket6 = new Ticket(13, 4200, "LED", "KUF", 350);
        manager.add(ticket6);
        Ticket[] tickets = manager.getAllTickets();
        Arrays.sort(tickets, costComparator);
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket6, ticket5, ticket4};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(expected, actual);
    }

    // Одно совпадение - costComparator
    @Test
    public void testFindAirportFromToSortOne() {
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket[] expected = {ticket5};
        Ticket[] tmp = manager.findAll("DME", "FRU");
        Arrays.sort(tmp, costComparator);
        Ticket[] actual = tmp;
        Assertions.assertArrayEquals(expected, actual);
    }

    // Несколько совпадений - costComparator
    @Test
    public void testFindAirportFromToSortTwo() {
//        Ticket ticket3 = new Ticket(13, 4200, "LED", "KUF", 350);
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket ticket6 = new Ticket(49, 3100, "LED", "KUF", 700);
        manager.add(ticket6);
        Ticket[] expected = {ticket6, ticket3, ticket4};
        Ticket[] tmp = manager.findAll("LED", "KUF");
        Arrays.sort(tmp, costComparator);
        Ticket[] actual = tmp;
        Assertions.assertArrayEquals(expected, actual);
    }

    // НЕТ совпадений
    @Test
    public void testFindAirportFromToSortZero() {
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket[] expected = {};
        Ticket[] tmp = manager.findAll("LED", "EGA");
        Arrays.sort(tmp, costComparator);
        Ticket[] actual = tmp;
        Assertions.assertArrayEquals(expected, actual);
    }

    // Новый поиск - comparator

    // 2 совпадения по from, to. Время полета одинаковое.
    @Test
    public void testFindEquilsComparator() {
        TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
        Ticket ticket6 = new Ticket(423, 4200, "DME", "FRU", 500);
        manager.add(ticket6);
        Ticket[] expected = {ticket5, ticket6};
        Ticket[] actual = manager.findAllComparator("DME", "FRU", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // 3 совпадения по from, to. Сортировка в порядке увеличения времени
    @Test
    public void testFindSortZeroComparator() {
        TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
        Ticket ticket6 = new Ticket(49, 3100, "LED", "EGO", 50);
        Ticket ticket7 = new Ticket(52, 3300, "LED", "EGO", 150);
        manager.add(ticket6);
        manager.add(ticket7);
        Ticket[] expected = {ticket6, ticket7, ticket1};
        Ticket[] actual = manager.findAllComparator("LED", "EGO", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }


    //     Несколько совпадений по времени - comparator
    @Test
    public void testFindAnyTicketComparator() {
        TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
        Ticket ticket6 = new Ticket(49, 3100, "LED", "KUF", 350);
        Ticket ticket7 = new Ticket(52, 3300, "LED", "KUF", 350);
        manager.add(ticket6);
        manager.add(ticket7);
        Ticket[] expected = {ticket4, ticket3, ticket6, ticket7};
        Ticket[] actual = manager.findAllComparator("LED", "KUF", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // 2 билета с одинаковыми from, to. Сортировка в порядке увеличения времени
    @Test
    public void testFindTwoTicketComparator() {
        TicketByTravelTimeAscComparator comparator = new TicketByTravelTimeAscComparator();
        Ticket[] expected = {ticket4, ticket3};
        Ticket[] actual = manager.findAllComparator("LED", "KUF", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
