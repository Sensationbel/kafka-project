package by.bulaukin.orderservice.controller;

import by.bulaukin.orderservice.AbstractTest;
import by.bulaukin.orderservice.web.response.ResponseMessage;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrdersControllerTest extends AbstractTest {

    @Test
    void whenCallOrdersController_thenGetMessage() throws Exception {
        String expectedMessage = objectMapper.writeValueAsString(new ResponseMessage("Order sent to kafka-orders-topic"));

        String actualMessage = mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(ordersMapper.requestToOrdersEvent(upsertOrderRequest))))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

                JsonAssert.assertJsonEquals(expectedMessage, actualMessage);
    }



}