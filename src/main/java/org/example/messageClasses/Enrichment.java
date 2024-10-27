package org.example.messageClasses;

import java.util.HashMap;
import java.util.Map;

public class Enrichment {

  public synchronized Map<String, String> enrichMsisdn(Map<String, String> input) {
    Map<String, String> result = new HashMap<>(input);
    result.put("firstName", "Vasya");
    result.put("lastName", "Ivanov");
    return result;
  }
}
