package org.example.messageClasses;

public class EnrichmentService {

  public Message enrich(Message message) {
    Enrichment enrichment = new Enrichment();
    if (message.getEnrichmentType() == Message.EnrichmentType.MSISDN) {
      if (message.getContent().get("msisdn") != null) {
        message.setContent(enrichment.enrichMsisdn(message.getContent()));
      }
    }
    return message;
  }
}
