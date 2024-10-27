package org.example.messageClasses;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnrichmentTest {

  @Test
  public void enrichMsisdn() {
    Map<String, String> input = new HashMap<>();
    input.put("action", "button_click");
    input.put("page", "book_card");
    input.put("msisdn", "88005553535");

    Enrichment enrichment = new Enrichment();
    Map<String, String> result = enrichment.enrichMsisdn(input);
    input.put("firstName", "Vasya");
    input.put("lastName", "Ivanov");
    assertEquals(input, result);
  }
}