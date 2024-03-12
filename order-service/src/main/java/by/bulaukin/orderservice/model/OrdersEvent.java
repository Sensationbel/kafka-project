package by.bulaukin.orderservice.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OrdersEvent {


    private Long id;

    private String product;

    private Integer quantity;

}
