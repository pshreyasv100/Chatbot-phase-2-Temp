package com.trg.chatbot;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class User {


  private String name;
  //  private String location;
  int nofbeds;
  String acuity;
  private String response;
  private  List<PMS> suggestion;
  //  private String centralPMS;
  //  private  String additionalParams;
  private  String contactnumber;
  //  private String email;
  //  private String address;
  //  private List<String> responseSequence;
  private String lastAskedQuestion = "";
  String productSeries = "";
  private int n;
  private List<String> optionList = new ArrayList<>();

  User() {}


  @Autowired
  PMSService pmsService;

  UserAssister userAssister;

  public void seUpUserAssister(String acuity) {
    if ("low".equals(acuity)) {
      this.userAssister = new UserAssisterForLowAcuity();
    } else {
      this.userAssister = new UserAssisterForHighAcuity();
    }
  }


  public void setCountOfQnToZero() {
    this.n = 0;
    this.suggestion = pmsService.retrieveAllPMS();
    response = productSeries;
  }

  public int printN() {
    return this.n;

  }


  public Response askOnceAgain() {
    final Response echoOfLastQuestion = new Response();
    echoOfLastQuestion.setContent("What you have entered is not a valid option\n" + this.lastAskedQuestion);
    return echoOfLastQuestion;
  }

  private boolean checkIfResponseIsInvalid(String ans) {
    return (!(Utility.isNumeric(ans)) || (Integer.parseInt(ans) > this.optionList.size()));
  }

  private String setResponse(String ans) {
    if (this.n == 0) {
      return this.response;
    } else {
      return this.optionList.get(Integer.parseInt(ans) - 1);
    }
  }

  public Response generateNextQuestion(String ans) {
    if (this.n != 0 && checkIfResponseIsInvalid(ans)) {
      return askOnceAgain();
    }
    this.response = setResponse(ans);
    try {
      this.suggestion = userAssister.setUpSuggestions(this.suggestion, this.response, this.n);
    } catch (IllegalArgumentException | SecurityException e) {
      Logger.log("Exceptions   happened");
    }

    if (userAssister.byPassQuestions(this.n)) {
      final Response concludingMessage = new Response();
      concludingMessage.setContent("I have arrived at a suggestion for you");
      return concludingMessage;
    }


    try {
      this.optionList = userAssister.setUpOptionList(this.suggestion, this.n);
    } catch (IllegalArgumentException | SecurityException e) {
      Logger.log("Exceptions  happened");
    }

    String question = "";

    try {
      question = userAssister.fn(this.suggestion, this.optionList, this.n);
    } catch (IllegalArgumentException | SecurityException e) {
      Logger.log("Exceptions happened");
    }
    this.n++;
    this.lastAskedQuestion = question;
    final Response qnAsObject = new Response();
    qnAsObject.setContent(question);
    return qnAsObject;
  }



  public static String decideAcuity(String location, int beds) {
    String acuity = "low";
    if (location != null && Metros.isMetro(location) && (beds > 200)) {
      acuity = "high";
    }
    return acuity;
  }

  public List<String> getYesORNoList() {
    final List<String> result = new ArrayList<>();
    result.add("Yes");
    result.add("No");
    return result;
  }

  public Response suggest() {
    final Response suggestionObj = new Response();
    final StringBuilder message = new StringBuilder();
    if (this.suggestion.size() == 1) {
      message.append("The product that is suitable for you is \n");
    } else {
      message.append("Our products those will suit your recquirements are \n");
    }
    for (int i = 0; i < this.suggestion.size(); i++) {
      message.append(
          this.suggestion.get(i).getType().toUpperCase() + " " + this.suggestion.get(i).getModel() + "\n");
    }
    this.n = 5;
    suggestionObj.setContent(message.toString());
    optionList = getYesORNoList();
    return (suggestionObj);
  }


  public void startUserSession(String name, String contact) {
    this.setName(name);
    this.setContactnumber(contact);
  }

  public void startChat(String location, String beds) {

    this.nofbeds = Integer.parseInt(beds);
    this.acuity = decideAcuity(location, nofbeds);
    this.suggestion = pmsService.retrieveAllPMS();

    if ("low".equals(this.acuity)) {
      productSeries = "Efficia";
      response = productSeries;
    }

    else {
      productSeries = "Intellivue";
      response = productSeries;
    }
    seUpUserAssister(this.acuity);
  }

  public List<PMS> getSuggestion() {
    return suggestion;
  }

  //  public void populateFurtherDetails(String email, String phone, String shippingAddress,
  //      String centralPMS, String additionalParams) {
  //    this.email = email;
  //    this.setContactnumber(phone);
  //    this.address = shippingAddress;
  //    this.centralPMS = centralPMS;
  //    this.additionalParams = additionalParams;
  //  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContactnumber() {
    return contactnumber;
  }

  public void setContactnumber(String contactnumber) {
    this.contactnumber = contactnumber;
  }



}
