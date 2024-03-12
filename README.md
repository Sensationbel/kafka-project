  KAFKA PROJECT
  - Настройка Kafka в приложениях;
  - Отправка сообщений в Kafka;
  - Принятие и обработка сообщений от Kafka.

Для запуска приложения выполнить команду:
 - для систем на базе Windows - docker-start.cmd

В данном проекте два приложения.

Первое приложение Order Service:
   -  Имеет эндпоинт, на который приходит POST запрос с сущностью UpsertOrderRequest.
    Эндпоинт принимает сущность и отправляет в Kafka событие OrdersEvent в топик 
    order-topic. Ещё это приложение имеет KafkaOrderListener, который слушает события по
    топику order-status-topic. Этот слушатель выводит в консоль информацию о событии в
    следующем формате:

    log.info("Received message: {}", message);
    log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);


Второе приложение Order Status Service:

   - Имеет KafkaOrderStatusListener, который слушает топик order-topic. 
    Когда в слушатель приходит событие, происходит отправка другого события в топик order-status-service.