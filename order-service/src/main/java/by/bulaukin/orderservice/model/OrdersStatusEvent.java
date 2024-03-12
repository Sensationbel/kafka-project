package by.bulaukin.orderservice.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrdersStatusEvent {

    private String status;

    private LocalDate date;
}
