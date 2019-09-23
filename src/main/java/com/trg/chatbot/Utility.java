package com.trg.chatbot;

import java.util.ArrayList;
import java.util.List;

public class Utility {
  Utility() {
    super();
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (final NumberFormatException e) {
      return false;
    }
  }

  public static List<PMS> narrowDownSuggestions(List<PMS> list1, String property,
      String valueOfProperty) {
    final List<PMS> result = new ArrayList<>();
    final int lengthOfList = list1.size();
    for (int i = 0; i < lengthOfList; i++) {
      try {
        if ((PMS.class.getDeclaredField(property).get(list1.get(i))).equals(valueOfProperty)) {
          result.add(list1.get(i));
        }
      } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
          | SecurityException e) {
        Logger.log("Exceptions happened");
      }
    }
    return result;
  }

}
