

package com.trg.chatbot;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PMSTypeMapper implements RowMapper<Response> {

  @Override
  public Response mapRow(ResultSet rs, int num) throws SQLException {

    final Response res = new Response();
    res.setContent(rs.getString(1));
    return res;
  }
}


