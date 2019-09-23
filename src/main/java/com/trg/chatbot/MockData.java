package com.trg.chatbot;

import java.util.ArrayList;
import java.util.List;

public class MockData {
  List<PMS> mockList=new ArrayList< >();
  List<PMS> expected=new ArrayList< >();
  List<PMS> provideMockList(){
    final PMS pms=new PMS();
    pms.setType("Efficia");
    pms.setModel("CM10");
    pms.setScreensize("10");
    pms.setTouch("10");
    pms.setMasimorainbow("no");
    pms.setPhilipsSpo2("opt");
    pms.setCardiacoutput("no");
    pms.setMaxNoOfWaveforms("8");
    pms.setLeadECG12("no");

    final PMS pms1=new PMS();
    pms1.setType("Intellivue");
    pms1.setModel("MX400");
    pms1.setScreensize("9");
    pms1.setTouch("yes");
    pms1.setMasimorainbow("yes");
    pms1.setPhilipsSpo2("yes");
    pms1.setCardiacoutput("yes");
    pms1.setMaxNoOfWaveforms("12");
    pms1.setLeadECG12("yes");

    mockList.add(pms);
    mockList.add(pms1);
    expected.add(pms);
    return mockList;
  }

  List<PMS> provideExpected(){
    return expected;
  }
}
