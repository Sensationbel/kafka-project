package by.bulaukin.orderservice.controller;

import by.bulaukin.orderservice.model.OrdersStatusEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class KafkaOrderListener {

    private OrdersStatusEvent ordersStatusEvent;

    @KafkaListener(topics = "${app.kafka.kafka-order-status-topic}",
            groupId = "${app.kafka.kafka-order-groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload OrdersStatusEvent event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp){
        log.info("Received Kafka message {}", event);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}.", key, partition, topic, timestamp);
        setOrderStatusEvent(event);
    }

    private void setOrderStatusEvent(OrdersStatusEvent event) {
        ordersStatusEvent = event;
    }


}
