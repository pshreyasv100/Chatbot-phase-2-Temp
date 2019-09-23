package com.trg.chatbot;

import org.junit.Test;

public class MetrosTest {

  @Test
  public void testTrue() {

    assert(Metros.isMetro("bangalore"));
    assert(Metros.isMetro("delhi"));
    assert(Metros.isMetro("bengaluru"));
    assert(Metros.isMetro("mumbai"));
    assert(Metros.isMetro("bangalore"));
    assert(Metros.isMetro("kolkatha"));
    assert(Metros.isMetro("pune"));
    assert(Metros.isMetro("hyderabad"));
    assert(Metros.isMetro("BANGALORE"));
    assert(Metros.isMetro("PUNE"));
    assert(Metros.isMetro("Pune"));
  }


  @Test
  public void testFalse() {

    assert(!Metros.isMetro("bangalore45"));
    assert(!Metros.isMetro("newdelhi"));
    assert(!Metros.isMetro("punee"));
    assert(!Metros.isMetro("bengalore"));
    assert(!Metros.isMetro("COLKATTA"));

  }
}