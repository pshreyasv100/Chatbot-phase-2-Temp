package com.trg.chatbot;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;

public class UtilityTest {

  MockData data=new MockData();

  @Test
  public void testNarrowdown() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, JSONException {

    final List<PMS> expected=data.provideExpected();
    final List<PMS> MockList=data.provideMockList();

    final List<PMS> actual=Utility.narrowDownSuggestions(MockList, "type", "Efficia");

    assertEquals(expected, actual);
  }


  @Test
  public void testIsNumeric() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, JSONException {

    assert(Utility.isNumeric("9"));
    assert(!Utility.isNumeric("a"));
    assert(!Utility.isNumeric(","));
    assert(!Utility.isNumeric("-"));
    assert(!Utility.isNumeric("a1"));
  }

}