package org.example.messageClasses;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class EnrichmentServiceTest {
  @Test
  public void testEnrichmentServiceEnrich() {
    Map<String, String> input = new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "88005553535");

    Message message = new Message();
    message.setContent(input);
    message.setEnrichmentType(Message.EnrichmentType.MSISDN);

    EnrichmentService enrichmentService = new EnrichmentService();
    Message result = enrichmentService.enrich(message);

    input.put("firstName", "Vasya");
    input.put("lastName", "Ivanov");
    message.setContent(input);
    assertEquals(result, message);
  }

  @Test
  void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws InterruptedException {
    Map<String, String> input = new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "88005553535");

    Message message = new Message();
    message.setContent(input);
    message.setEnrichmentType(Message.EnrichmentType.MSISDN);

    EnrichmentService enrichmentService = new EnrichmentService();
    Message result = enrichmentService.enrich(message);

    List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    List<Message> messages = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);
    for (int i = 0; i < 5; i++) {
      executorService.submit(() -> {
        enrichmentResults.add(
                enrichmentService.enrich(message) // message где-то создается
        );
        latch.countDown();     // уменьшаем значение latch на 1
      });
    }
    latch.await(); // ждем, пока latch не станет равным 0, то есть пока не закончат работу все джобы в цикле

    input.put("firstName", "Vasya");
    input.put("lastName", "Ivanov");
    message.setContent(input);
    for (int i = 0; i < 5; i++) {
      messages.add(message);
    }
    // проверяем валидность полученных сообщений в enrichmentResult
    assertEquals(messages, enrichmentResults);
  }
}