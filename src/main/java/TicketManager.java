public class TicketManager {
    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public TicketManager() {
    }

    private TicketRepository repo;

    // добавить один билет в конец массива
    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    // удалить по id
    public void del(int id) {
        repo.removeById(id);
    }

    // получить набор билетов
    public Ticket[] getAllTickets() {
        return repo.getTickets();
    }

    // поиск возвращает массив только с теми билетами, что соответствуют условиям поиска
    // менеджера поиска по аэропорту вылета и аэропорту прилёта (даты не учитывайте)
    public Ticket[] findAll(String from, String to) {
        Ticket[] tickets = getAllTickets();
        int count = 0;
        // подсчет размерности массива tmp (согласно условию)
        for (int i = 0; i < getAllTickets().length; i++) {
            if ((tickets[i].getDepartureAirport().equals(from)) & (tickets[i].getArrivalAirport().equals(to))) {
                count++;
            }
        }
        // сборка массива tmp размером count
        Ticket[] result = new Ticket[count];
        int iCount = 0;
        for (int i = 0; i < getAllTickets().length; i++) {
            if ((tickets[i].getDepartureAirport().equals(from)) & (tickets[i].getArrivalAirport().equals(to))) {
                result[iCount] = tickets[i];
                iCount ++;
            }
        }
        return result;
    }
}