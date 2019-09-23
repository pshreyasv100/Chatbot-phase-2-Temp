package com.trg.chatbot;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class QuestionnaireForLowAcuityTest {

  MockData data=new MockData();

  @Test
  public void testSetOptionsForParameters() {
    final QuestionnaireForLowAcuity ques=new QuestionnaireForLowAcuity();
    final List<String>actual= ques.setOptionsForParameters();
    final List<String> optionList=new ArrayList<>();
    optionList.add("Philips SpO2");
    optionList.add("Philips SpO2 , Cardiac output");
    optionList.add("Philips SpO2 , Masimo Rainbow");
    optionList.add("Philips SpO2 , Cardiac Output , Masimo Rainbow");
    assertEquals(optionList, actual);

  }
  @Test
  public void testSetSuggestionBasedOnParameters() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final QuestionnaireForLowAcuity ques=new QuestionnaireForLowAcuity();
    final List<PMS> actual=ques.setSuggestionBasedOnParameters(data.provideMockList(), "Philips SpO2");

  }}