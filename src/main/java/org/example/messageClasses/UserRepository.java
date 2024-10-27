package org.example.messageClasses;

public interface UserRepository {

  Message findByMsisdn(String msisdn);

  void updateUserByMsisdn(String msisdn, Message message);
}
