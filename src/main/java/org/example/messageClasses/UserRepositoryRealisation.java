package org.example.messageClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserRepositoryRealisation implements UserRepository {
  private final Map<String, Message> allMessages;

  public UserRepositoryRealisation() {
    allMessages = Collections.synchronizedMap(new HashMap<>());
  }

  @Override
  public Message findByMsisdn(String msisdn) {
    return allMessages.get(msisdn);
  }

  @Override
  public synchronized void updateUserByMsisdn(String msisdn, Message message) {
    allMessages.put(msisdn, message);
  }
}
