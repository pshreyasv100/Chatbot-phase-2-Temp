package com.trg.chatbot;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class QuestionnaireTest {

  MockData data=new MockData();
  @Test
  public void testGenerateQuestionFromOptions() {
    final Questionnaire ques=new Questionnaire();
    String expected="";
    expected += "we have "+"2" + " " + "name" + " to offer , which one do you want?\n";
    expected+="1"+". " +"saleel"+"\n";
    expected+="2"+". " +"venki"+"\n";
    final List<String> ex=new ArrayList<>();
    ex.add("saleel");
    ex.add("venki");
    final String actual=ques.generateQuestionFromOptions(ex, "name");
    assertEquals(expected, actual);
  }
  @Test
  public void testSetOptionList() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final Questionnaire ques=new Questionnaire();
    final List<String> actual=ques.setOptionList(data.provideMockList(), "screensize");
    final List<String> expected=new ArrayList<>();
    expected.add("9");
    expected.add("10");

    assertEquals(expected, actual);
  }

  @Test
  public void testProvideNextQuestion() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final Questionnaire ques=new Questionnaire();
    String expected="";
    expected += "we have "+"2" + " " + "name" + " to offer , which one do you want?\n";
    expected+="1"+". " +"saleel"+"\n";
    expected+="2"+". " +"venki"+"\n";
    final List<String> ex=new ArrayList();
    ex.add("saleel");
    ex.add("venki");
    final String actual=ques.provideNextQuestion(ex, "name");
    assertEquals(expected, actual);
  }
}