package org.example.messageClasses;

import java.util.Map;

public class Message {

  private Map<String, String> content;
  private EnrichmentType enrichmentType;

  public Map<String, String> getContent() {
    return content;
  }

  public EnrichmentType getEnrichmentType() {
    return enrichmentType;
  }

  public void setContent(Map<String, String> content) {
    this.content = content;
  }

  public void setEnrichmentType(EnrichmentType enrichmentType) {
    this.enrichmentType = enrichmentType;
  }

  public enum EnrichmentType {
    MSISDN
  }
}
