package com.trg.chatbot;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;

public class UserTest {

  MockData data=new MockData();

  @Test
  public void testDecideAcuity() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, JSONException {
    final User user=new User();
    final String actual=user.decideAcuity("pune", 234);
    assertEquals("high", actual);
    final String actual1=user.decideAcuity("pune", 23);
    assertEquals("low", actual1);
    final String actual2=user.decideAcuity("calicut", 23);
    assertEquals("low", actual2);
    final String actual3=user.decideAcuity("calicut", 234);
    assertEquals("low", actual3);
  }
  @Test
  public void test_yesorno() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, JSONException {
    final User user=new User();
    final List<String> expected=new ArrayList<>();
    expected.add("Yes");
    expected.add("No");
    List<String> actual=new ArrayList<>();
    actual=user.getYesORNoList();
    assertEquals(expected, actual);
  }

}
