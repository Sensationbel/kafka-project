package by.bulaukin.orderservice.web.request;

import lombok.Data;

@Data
public class UpsertOrderRequest {

    private String product;
    private Integer quantity;

}
