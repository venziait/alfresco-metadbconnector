/*
 *    Copyright (C) 2012 Venzia, S.L. All rights reserved.
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 *    
 */

/**
 * @author horelvis castillo mendoza
 *
 */
package org.venzia.mdbc.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class GenericDAO extends SimpleJdbcDaoSupport
{
  private String query;
  private String where;
  private String primaryKey;

  public GenericDAO(DataSource dataSource)
  {
    setDataSource(dataSource);
  }


public List<Map<String, Object>> search(String filter) throws IOException
  {
    String sql = this.query;
    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    if ((filter != null) && (!filter.trim().equals(""))) {
      sql = sql + " " + this.where.replace("{0}", filter);
    }
    result = getSimpleJdbcTemplate().query(sql, new ConnectorRowMapper(), new Object[0]);

    return result;
  }

  public Map<String, Object> findRow(String id)
  {
    String sql = this.query;
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      int idInt = Integer.parseInt(id);
      sql = sql + " Where " + this.primaryKey + "=" + idInt;
    } catch (Exception ex) {
      sql = sql + " Where " + this.primaryKey + "='" + id + "'";
    }

    result = (Map<String, Object>)getSimpleJdbcTemplate().query(sql,new ConnectorRowMapper(), new Object[0]).get(0);

    return result;
  }
  public void setWhere(String where) {
    this.where = where;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public List<String> getColumns() {
    return null;
  }

  public String getPrimaryKey() {
    return this.primaryKey;
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }
  
  public class ConnectorRowMapper implements RowMapper<Map<String, Object>>{

	  public Map<String, Object> mapRow(ResultSet rs, int arg1) throws SQLException {
		  Map<String, Object> row = new HashMap<String, Object>();
		  ResultSetMetaData md = rs.getMetaData();
	        for (int i = 1; i <= md.getColumnCount(); i++) {
	          row.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
	        }
		  return row;
	  }

}


}