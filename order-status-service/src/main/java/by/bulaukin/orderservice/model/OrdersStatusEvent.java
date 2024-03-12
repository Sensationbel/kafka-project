package by.bulaukin.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrdersStatusEvent {

    private String status;
    private LocalDate date;

    public  static OrdersStatusEvent create() {
        return new OrdersStatusEvent("CREATED", LocalDate.now());
    }

}
