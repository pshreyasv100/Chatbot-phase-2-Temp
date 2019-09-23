package com.trg.chatbot;

import java.util.List;

public interface ChatBotDao {
  List<PMS> getAllList();

  int addNewDevice(PMS pms);

  int removeExistingDevice(String type, String model);

  int addNewCustomer(Customer customer);

  void saveRecommendations(String contact, List<String> recommendations);

  List<String> getCustomerRecommendations(String contact);

  List<Customer> getAllCustomers();

  List<Customer> getInterestedCustomerWithinTimePeriod(String fromDate, String toDate);
}
