package com.trg.chatbot;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

  @Autowired
  ChatBot chatbot;

  @Autowired
  PMSService pmsService;

  @Autowired
  LoginService loginService;

  @Autowired
  SalesService salesService;

  @GetMapping(value = "/question", produces = "application/json")

  public Response askQuestions(
      @RequestParam(name = "answer", required = false) String ans) {

    chatbot.getUser().setCountOfQnToZero();
    Response qn;

    if (ans == null) {
      qn = chatbot.getUser().generateNextQuestion(ans);
    } else {
      final String[] arrOfStr = ans.split(",");
      qn = chatbot.getUser().generateNextQuestion("");
      for (final String element : arrOfStr) {
        qn = chatbot.getUser().generateNextQuestion(element);
      }
    }
    return qn;
  }


  @PostMapping(value = "/register")
  public void registerUser(@RequestBody Customer customer) {
    loginService.registerUser(customer);
  }


  @GetMapping(value = "/verify/user")
  public Response verifyUser(@RequestParam("name") String name,
      @RequestParam("contact") String contact){
    return loginService.isRegisteredCustomer(name, contact);
  }


  @GetMapping(value = "/suggest", produces = "application/json")
  public Response showSuggestedProducts() {
    return chatbot.getUser().suggest();
  }


  @PostMapping(value = "/save")
  public void saveRecommendations(@RequestBody Recommendation recommendations) {
    salesService.saveRecommendations(recommendations.getProducts());
  }


  @GetMapping(value="/all/users")
  public List<Customer> getAllCustomers(){
    return salesService.getAllCustomers();
  }

  @GetMapping(value="/user/recommendation")
  public Recommendation getUsersRecommendation(@RequestParam("contact") String contact) {
    return salesService.getCustomerInterest(contact);
  }


  @GetMapping(value = "/interested/customer/daterange")
  public List<Customer> getInterestedCustomerWithinTimePeriod(
      @RequestParam(value = "fromDate") String fromDate,
      @RequestParam(value = "toDate") String toDate){

    return salesService.getInterestedCustomerWithinTimePeriod(fromDate,toDate);
  }


  @GetMapping(value = "/chat", produces = "application/json")
  public Response startChat(@RequestParam("location") String location, @RequestParam("beds") String beds) {

    try {
      chatbot.getUser().startChat(location, beds);
    } catch (IllegalArgumentException | SecurityException e) {
      Logger.log("Exceptions");
    }
    final Response welcomeMessage = new Response();
    welcomeMessage.setContent("Hi " + chatbot.getUser().getName()  + " , click on continue to chat..");
    return welcomeMessage;
  }


  @GetMapping(value = "/addnewdevice", produces = "application/json")
  public Response addNewdevice(@RequestParam("type") String type,
      @RequestParam("model") String model, @RequestParam("screensize") String screensize,
      @RequestParam("touch") String touch, @RequestParam("masimorainbow") String masimoRainbow,
      @RequestParam("spO2") String spo2, @RequestParam("cardiacoutput") String cardiacOutput) {
    final int status =
        pmsService.addNewPms(type, model, screensize, touch, masimoRainbow, spo2, cardiacOutput);
    final Response message = new Response();
    if (status > 0) {
      message.setContent("PMS added succesfully");
    } else {
      message.setContent("Something happened , try again..");
    }
    return message;
  }

  @GetMapping(value = "/removedevice", produces = "application/json")
  public Response deleteDevice(@RequestParam("type") String type,
      @RequestParam("model") String model) {
    final Response message = new Response();
    final int status = pmsService.deleteDevice(type, model);
    if (status == 1) {
      message.setContent("Deleted successfully");
    } else {
      message.setContent("try deleting once again");
    }
    return message;
  }



}
