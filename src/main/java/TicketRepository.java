public class TicketRepository {
// Реализуйте репозиторий для хранения информации о "Билетах"
// (добавить, удалить, получить набор билетов)

    private Ticket[] tickets = new Ticket[0];

    // tickets - массив
    // ticket - элемент массива

    // добавить один билет в конец массива
    public void save(Ticket ticket) {
        Ticket[] tmp = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        tmp[tmp.length - 1] = ticket; // в последнюю ячейку довабляем элемент ticket
        tickets = tmp; // "переносим" элементы в массив ticket
    }

    // удалить по id
    public void removeById(int id) {
        Ticket[] tmp = new Ticket[tickets.length - 1];
        int count = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i].getId() != id) {
                tmp[count] = tickets[i];
                count++;
            }
        }
        tickets = tmp;
    }

    // получить набор билетов
    public Ticket[] getTickets() {
        return tickets;
    }
}
