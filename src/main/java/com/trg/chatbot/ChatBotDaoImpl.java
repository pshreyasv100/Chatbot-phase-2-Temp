package com.trg.chatbot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatBotDaoImpl implements ChatBotDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<PMS> getAllList() {
    return (jdbcTemplate.query("select * from pms", new PmsMapper()));
  }

  @Override
  public int addNewDevice(PMS pms) {
    return jdbcTemplate.update("insert into pms values(?,?,?,?,?,?,?,?,?)", pms.getType(), pms.getModel(),
        pms.getScreensize(), pms.getTouch(), pms.getMasimorainbow(), pms.getPhilipsSpo2(), pms.getCardiacoutput(),
        pms.getMaxNoOfWaveforms(), pms.getLeadECG12());

  }

  @Override
  public int removeExistingDevice(String type, String model) {
    return jdbcTemplate.update("delete from pms where typename=? and modelno=?", type, model);
  }


  @Override
  public int addNewCustomer(Customer customer) {

    return jdbcTemplate.update("INSERT INTO customer VALUES(?,?,?,?)",
        Integer.valueOf(customer.getContact()), customer.getName(), customer.getLocation(),
        customer.getEmail());
  }


  @Override
  public void saveRecommendations(String contact, List<String> recommendations) {

    final long millis = System.currentTimeMillis();
    final Date currentDate = new Date(millis);
    for (final String product : recommendations) {

      if (jdbcTemplate.queryForList(
          "SELECT * FROM recommendation WHERE contact = ? AND suggested_product = ? AND date = ?",
          Integer.valueOf(contact), product, currentDate).isEmpty()) {

        jdbcTemplate.update("insert into recommendation values (?,?)", Integer.valueOf(contact),
            product);
      }

    }
  }


  public boolean isExistingCustomer(String contact, String name) {
    final String query = "SELECT * FROM customer WHERE contact_number = ? AND name = ?";
    return (!(jdbcTemplate.queryForList(query,Integer.valueOf(contact),name).isEmpty()));
  }

  @Override
  public List<String> getCustomerRecommendations(String contact) {

    final List<String> recommendations = new ArrayList<>();
    final String query = "SELECT  suggested_product FROM recommendation WHERE contact = " + contact;
    final List<Response> suggestions = jdbcTemplate.query(query, new PMSTypeMapper());

    for (final Response suggestion : suggestions) {
      recommendations.add(suggestion.getContent());
    }
    return recommendations;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return jdbcTemplate.query("SELECT * FROM customer", new CustomerMapper());
  }

  @Override
  public List<Customer> getInterestedCustomerWithinTimePeriod(String fromDate, String toDate) {

    final String query =
        "SELECT * FROM customer WHERE contact_number IN (SELECT contact FROM recommendation WHERE date BETWEEN "
            + "'" + Date.valueOf(fromDate) + "'" + " AND " + "'" + Date.valueOf(toDate) + "'" + ")";
    return  jdbcTemplate.query(query, new CustomerMapper());
  }



}
