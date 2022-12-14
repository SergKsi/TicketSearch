import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket[] ticket = new Ticket[0];

    Ticket ticket1 = new Ticket(12, 1200, "DME", "EGO", 50);
    Ticket ticket2 = new Ticket(8, 900, "EGO", "DME", 55);
    Ticket ticket3 = new Ticket(13, 4200, "LED", "KUF", 350);
    Ticket ticket4 = new Ticket(1, 12200, "OGZ", "EGO", 320);
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
        Ticket ticket6 = new Ticket(35, 23200, "DME", "KUF", 450);
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

    // Сортировка в порядке возрастания стоимости билета
    //  2 совпадения по рейсам. Второй больше первого
    @Test
    public void testFindAirportSortTicketsFirst() {
        Ticket ticket6 = new Ticket(35, 700, "EGO", "DME", 450);
        manager.add(ticket6);
        Ticket[] expected = {ticket6, ticket2};
        Ticket[] actual = manager.findAll("EGO", "DME");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Сортировка в порядке возрастания стоимости билета
    //  2 совпадения по рейсам.
    @Test
    public void testFindAirportSortTicketsSecond() {
        Ticket ticket6 = new Ticket(35, 700, "OGZ", "EGO", 450);
        Ticket ticket7 = new Ticket(39, 1500, "OGZ", "EGO", 400);
        manager.add(ticket6);
        manager.add(ticket7);
        Ticket[] expected = {ticket6, ticket7, ticket4};
        Ticket[] actual = manager.findAll("OGZ", "EGO");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Сортировка в порядке возрастания стоимости билета
    // Стоимость билетов равна
    @Test
    public void testFindAirportSortTicketsEqual() {
        Ticket ticket6 = new Ticket(35, 900, "EGO", "DME", 450);
        manager.add(ticket6);
        Ticket[] expected = {ticket2, ticket6};
        Ticket[] actual = manager.findAll("EGO", "DME");
        Assertions.assertArrayEquals(expected, actual);
    }

    // находится ровно один билет
    @Test
    public void testFindAirportSortTicketsOne() {
        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.findAll("EGO", "DME");
        Assertions.assertArrayEquals(expected, actual);
    }

    // находится 0 билетов
    @Test
    public void testFindAirportSortTicketsZero() {
        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("EGO", "OME");
        Assertions.assertArrayEquals(expected, actual);
    }

    // находится несколько билетов
    @Test
    public void testFindAirportSortTicketsSeveral() {
        Ticket ticket6 = new Ticket(35, 210, "OGZ", "EGO", 150);
        Ticket ticket7 = new Ticket(39, 300, "OGZ", "EGO", 200);
        manager.add(ticket6);
        manager.add(ticket7);
        Ticket[] expected = {ticket6, ticket7, ticket4};
        Ticket[] actual = manager.findAll("OGZ", "EGO");
        Assertions.assertArrayEquals(expected, actual);
    }
}


