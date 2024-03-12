package by.bulaukin.orderservice.model;

import lombok.Data;

@Data
public class OrdersEvent {

    private Long id;

    private String product;

    private Integer quantity;

}
