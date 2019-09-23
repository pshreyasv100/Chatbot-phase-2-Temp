package com.trg.chatbot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesService{

  @Autowired
  ChatBotDao chatbotDAO;

  @Autowired
  ChatBot chatBot;

  public void saveRecommendations(List<String> recommendations) {

    final String contact = chatBot.getUser().getContactnumber();
    chatbotDAO.saveRecommendations(contact, recommendations);
  }


  public Recommendation getCustomerInterest(String contact){

    final Recommendation recommendations = new Recommendation();
    recommendations.setProducts(chatbotDAO.getCustomerRecommendations(contact));
    return recommendations;
  }


  public List<Customer> getAllCustomers(){
    return chatbotDAO.getAllCustomers();
  }


  public List<Customer> getInterestedCustomerWithinTimePeriod(String fromDate, String toDate) {

    return chatbotDAO.getInterestedCustomerWithinTimePeriod(fromDate, toDate);
  }







}


