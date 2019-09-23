package com.trg.chatbot;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class UserAssisterForHighAcuityTest {

  MockData data=new MockData();
  @Test
  public void testSetUpSuggestions() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final UserAssisterForHighAcuity user=new UserAssisterForHighAcuity();
    final List<PMS> actual=user.setUpSuggestions(data.provideMockList(), "10",2 );
    final List<PMS> expected=data.provideExpected();
    assertEquals(expected, actual);
  }
  @Test
  public void testBypassQuestions() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final UserAssisterForHighAcuity user=new UserAssisterForHighAcuity();
    final boolean actual= user.byPassQuestions(2);
    assert(actual);
    final boolean actual1= user.byPassQuestions(0);
    assertEquals(false, actual1);
    final boolean actual2= user.byPassQuestions(99);
    assertEquals(false, actual1);
  }
  @Test
  public void testSetUpOptionList() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final UserAssisterForHighAcuity user=new UserAssisterForHighAcuity();
    final List<String> actual=user.setUpOptionList(data.provideMockList(),1);
    final List<String> expected=new ArrayList<>();
    expected.add("9");
    expected.add("10");
    assertEquals(expected, actual);
  }
  @Test
  public void testFn() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    final UserAssisterForHighAcuity user=new UserAssisterForHighAcuity();
    final List<String> expected=new ArrayList<>();
    expected.add("9");
    expected.add("10");
    final String actual=user.fn(data.provideMockList(), expected,1);
    String expected1="";
    expected1 += "we have "+"2" + " " + "screensize" + " to offer , which one do you want?\n";
    expected1+="1"+". " +"9"+"\n";
    expected1+="2"+". " +"10"+"\n";
    assertEquals(expected1, actual);
  }
}