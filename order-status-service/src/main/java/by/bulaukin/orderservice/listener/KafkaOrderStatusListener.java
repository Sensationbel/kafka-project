package by.bulaukin.orderservice.listener;

import by.bulaukin.orderservice.model.OrdersEvent;
import by.bulaukin.orderservice.model.OrdersStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaOrderStatusListener {

    private final KafkaTemplate<String, OrdersStatusEvent> kafkaTemplate;

    @Value("${app.kafka.kafka-order-status-topic}")
    private String topicName;

    @KafkaListener(topics = "${app.kafka.kafka-order-topic}",
            groupId = "${app.kafka.kafka-order-groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload OrdersEvent event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received Kafka message {}", event);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}.", key, partition, topic, timestamp);

        kafkaTemplate.send(topicName, OrdersStatusEvent.create());
    }
}
