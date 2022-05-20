package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Order implements Serializable {
    //public int orderID;
    public String username;
    public LocalDate date;
    public LocalTime time;

    private static final long serialVersionUID = 1234567L;

    public Order(String username, LocalDate date, LocalTime time){
        this.username = username;
        this.date = date;
        this.time = time.truncatedTo(ChronoUnit.SECONDS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(username, order.username) && Objects.equals(date, order.date) && Objects.equals(time, order.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, date, time);
    }
}
