package by.bulaukin.orderservice.web.controller;

import by.bulaukin.orderservice.mapper.OrdersMapper;
import by.bulaukin.orderservice.model.OrdersEvent;
import by.bulaukin.orderservice.web.request.UpsertOrderRequest;
import by.bulaukin.orderservice.web.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    @Value("${app.kafka.kafka-order-topic}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final OrdersMapper mapper;

    @PostMapping
    public ResponseEntity<ResponseMessage> sendOrder(@RequestBody UpsertOrderRequest request) {
        OrdersEvent event = mapper.requestToOrdersEvent(request);
        kafkaTemplate.send(topicName, event);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Order sent to kafka-orders-topic"));
    }

}
