package com.trg.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  @Autowired
  ChatBot bot;



  @Autowired
  ChatBotDaoImpl chatbotDAO;

  public Response isRegisteredCustomer(String name, String contact) {

    final Response response = new Response();
    if(chatbotDAO.isExistingCustomer(contact,name)) {
      response.setContent("existing");
      bot.getUser().setName(name);
      bot.getUser().setContactnumber(contact);
    }else {
      response.setContent("new");
    }

    return response;
  }

  public void registerUser(Customer customer) {
    chatbotDAO.addNewCustomer(customer);
  }
}
