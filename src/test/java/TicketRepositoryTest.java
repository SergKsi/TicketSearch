import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TicketRepositoryTest {

    TicketRepository repo = new TicketRepository();
    Ticket[] ticket = new Ticket[0];

    Ticket ticket1 = new Ticket(12, 1200, "DME", "EGO", 50);
    Ticket ticket2 = new Ticket(8, 900, "EGO", "DME", 55);
    Ticket ticket3 = new Ticket(13, 4200, "LED", "KUF", 350);
    Ticket ticket4 = new Ticket(1, 12200, "OGZ", "EGO", 320);
    Ticket ticket5 = new Ticket(4, 4500, "DME", "FRU", 500);

    @BeforeEach
    public void setup() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.save(ticket4);
        repo.save(ticket5);
    }

    // вывод всех билетов
    @Test
    public void testGetAllElements() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Ticket[] actual = repo.getTickets();
        Assertions.assertArrayEquals(expected, actual);
    }

    // доавление билета
    @Test
    public void testAddOneElement() {
        Ticket ticket6 = new Ticket(15, 3300, "DME", "LED", 120);
        repo.save(ticket6);
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = repo.getTickets();
        Assertions.assertArrayEquals(expected, actual);
    }

    // удаление билета по id
    @Test
    public void testRemoveById() {
        repo.removeById(13);
        Ticket[] expected = {ticket1, ticket2, ticket4, ticket5};
        Ticket[] actual = repo.getTickets();
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
    // через TicketCostComparator
    @Test
    public void testSortTicketsComparator() {
        TicketCostComparator costComparator = new TicketCostComparator();
        Ticket[] tickets = {ticket1, ticket2, ticket3, ticket4, ticket5};
        Arrays.sort(tickets, costComparator);
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket5, ticket4};
        Ticket[] actual = tickets;
        Assertions.assertArrayEquals(expected, actual);
    }


}
