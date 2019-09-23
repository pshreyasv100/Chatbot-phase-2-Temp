package com.trg.chatbot;

import java.util.List;

public interface UserAssister {

  List<PMS> setUpSuggestions(List<PMS> existingSuggestion,String currentResponse,int countOfQuestion);
  boolean byPassQuestions(int n);
  List<String> setUpOptionList(List<PMS> existingSuggestion,int countOfQuestion);
  String fn(List<PMS> existingSuggestion,List<String> optionList,int countOfQuestion);
}
