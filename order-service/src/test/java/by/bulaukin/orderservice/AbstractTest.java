package by.bulaukin.orderservice;

import by.bulaukin.orderservice.controller.KafkaOrderListener;
import by.bulaukin.orderservice.mapper.OrdersMapper;
import by.bulaukin.orderservice.web.request.UpsertOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Testcontainers
public class AbstractTest  {

    @Autowired
    protected MockMvc mockMvc;

    @Container
    protected static final KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.3.3")
    );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    protected KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    protected OrdersMapper ordersMapper;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected KafkaOrderListener kafkaOrderListener;

    protected UpsertOrderRequest upsertOrderRequest;

    @Value("${app.kafka.order-status-topic-test}")
    protected String orderStatusTopic;

    @BeforeEach
    public void before() throws Exception {

        upsertOrderRequest = new UpsertOrderRequest();
        upsertOrderRequest.setProduct("test");
        upsertOrderRequest.setQuantity(5);
    }

}
