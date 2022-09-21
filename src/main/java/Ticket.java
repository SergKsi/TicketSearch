public class Ticket implements Comparable<Ticket> {
    //    id
//    Стоимость (для упрощения будем считать стоимость единой для всех продавцов)
//    Аэропорт вылета (вы можете использовать IATA-коды)
//    Аэропорт прилёта (вы можете использовать IATA-коды)
//    Время в пути (в минутах)
    int id;
    int cost; // Стоимость
    String departureAirport; // Аэропорт вылета = from
    String arrivalAirport; // Аэропорт прилёта = to
    int travelTime; // Время в пути (в минутах)

    public Ticket(int id, int cost, String departureAirport, String arrivalAirport, int travelTime) {
        this.id = id;
        this.cost = cost;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.travelTime = travelTime;
    }
//
//    public Ticket() {
//    }
//
    public int getId() {
        return id;
    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public int getCost() {
        return cost;
    }
//
//    public void setCost(int cost) {
//        this.cost = cost;
//    }

    public String getDepartureAirport() {
        return departureAirport;
    }
//
//    public void setDepartureAirport(String departureAirport) {
//        this.departureAirport = departureAirport;
//    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }
//
//    public void setArrivalAirport(String arrivalAirport) {
//        this.arrivalAirport = arrivalAirport;
//    }
//
//    public int getTravelTime() {
//        return travelTime;
//    }
//
//    public void setTravelTime(int travelTime) {
//        this.travelTime = travelTime;
//    }

    // После чего идея подсветит вам её красным, нажмите на подсказку и выберите
    // "Implement methods" ("реализовать методы"). Идея сама сгенерирует заглушку
    // для нужного метода из этого интерфейса, но которая всегда возвращает 0.
    // Вам надо переписать тело сгенерированного метода чтобы если билет у которого
    // вызвали метод compareTo дешевле чем тот которого передали через параметр,
    // то возвращалось бы число меньше нуля, если же билет наоборот дороже, то число
    // больше нуля, а если стоимость одинакова, то 0.
    // Дав верную реализацию этому методу вы научите джаву сравнивать объекты этого класса.

    // сортировка в порядке возрастания стоимости билета
    @Override
    public int compareTo(Ticket o) {
        if (this.cost < o.cost) {
            return -1;
        } else {
            if (this.cost > o.cost) {
                return 1;
            } else return 0;
        }
    }
}
