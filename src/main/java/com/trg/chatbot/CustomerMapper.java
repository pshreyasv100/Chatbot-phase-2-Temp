package com.trg.chatbot;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {

  @Override
  public Customer mapRow(ResultSet rs, int num) throws SQLException {

    final Customer customer = new Customer();
    customer.setContact(rs.getString(1));
    customer.setName(rs.getString(2));
    customer.setLocation(rs.getString(3));
    customer.setEmail(rs.getString(4));
    return customer;
  }

}
