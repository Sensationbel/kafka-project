package by.bulaukin.orderservice.listener;

import by.bulaukin.orderservice.AbstractTest;
import by.bulaukin.orderservice.model.OrdersStatusEvent;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class KafkaOrderListenerTest extends AbstractTest {
    @Test
    public void whenCallOrdersController_thenSendMessageToOrdersTopic() throws Exception {
        OrdersStatusEvent ordersStatusEvent = new OrdersStatusEvent();
        ordersStatusEvent.setStatus("CREATED");
        ordersStatusEvent.setDate(LocalDate.now());

        kafkaTemplate.send(orderStatusTopic, ordersStatusEvent).get();

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() ->{
                    OrdersStatusEvent event = kafkaOrderListener.getOrdersStatusEvent();
                    System.out.println("event = " + event);
                    assertThat(event).isNotNull();

                    assertThat(event.getStatus()).isEqualTo(ordersStatusEvent.getStatus());
                    assertThat(event.getDate()).isEqualTo(ordersStatusEvent.getDate());
                });
    }
}
