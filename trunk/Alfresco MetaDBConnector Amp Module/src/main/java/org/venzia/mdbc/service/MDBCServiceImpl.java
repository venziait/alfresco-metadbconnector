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
 */

package org.venzia.mdbc.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.venzia.mdbc.connector.Connector;
import org.venzia.mdbc.connector.ConnectorImpl;
import org.venzia.mdbc.dao.GenericDAO;
import org.venzia.mdbc.exception.NotExistConnectorException;
import org.venzia.mdbc.utils.Column;
import org.venzia.mdbc.utils.XmlUtils;

public class MDBCServiceImpl implements MDBCService {
	protected static Log logger = LogFactory.getFactory().getInstance(
			MDBCServiceImpl.class);

	List<String> connectorsDeclare;
	HashMap<String, Connector> mdbcConnectors;
	String cronExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.venzia.mdbc.dao.service.MDBCService2#init()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void init() throws URISyntaxException {
		this.mdbcConnectors = new HashMap<String, Connector>();

		// loader by xml format
		try {
			for (String connectorDeclare : connectorsDeclare) {

				InputStream connectorStream = getClass().getClassLoader()
						.getResourceAsStream(connectorDeclare);

				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(connectorStream);

				List<Element> connector = doc.getRootElement().getChildren("connector");
				for (int i = 0; i < connector.size(); i++) {
					Element node = (Element) connector.get(i);
					// conexion a bbdd
					DriverManagerDataSource ds = new DriverManagerDataSource();
					ds.setDriverClassName(node
							.getChildText("driver-class-name"));
					ds.setUrl(node.getChildText("url"));
					ds.setUsername(node.getChildText("username"));
					ds.setPassword(node.getChildText("password"));

					GenericDAO dao = new GenericDAO(ds);
					List<Element> columnsNodes = node.getChild("columns")
							.getChildren("column");
					StringBuilder build = new StringBuilder();

					Map<String, Column> columns = new HashMap<String, Column>();
					int totalColumns = 0;
					for (Element column : columnsNodes) {
						totalColumns++;
						Column c = new Column(column);
						columns.put(c.getName(), c);
						if (column.getAttributeValue("primary-key") != null) {
							dao.setPrimaryKey(column.getAttributeValue("name"));
						}
						if (totalColumns == 1) {
							build.append(c.getName());
						} else {
							build.append(" , " + c.getName());
						}
					}

					dao.setQuery("SELECT " + build.toString() + " FROM "
							+ node.getChildText("table"));

					dao.setWhere(" WHERE " + node.getChildText("where"));

					// creamos el Bus Entry
					Connector entryPoint = new ConnectorImpl(dao,
							node.getChildText("name"),
							node.getChildText("aspect"));
					entryPoint.setTitle(node.getChildText("title"));
					entryPoint.setDescription(node.getChildText("description"));

					entryPoint.setColumns(columns);

					entryPoint.setColumnDetail(node
							.getChildText("column-detail"));
					

					this.mdbcConnectors.put(entryPoint.getName(), entryPoint);
				}

			}

		} catch (IOException e) {
			logger.error(e);
		} catch (JDOMException e) {
			logger.error(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#setCronExpression(java.lang.
	 * String)
	 */
	@Override
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.venzia.mdbc.dao.service.MDBCService2#getConnectors()
	 */
	@Override
	public List<Connector> getConnectors() {
		return new ArrayList<Connector>(this.mdbcConnectors.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.venzia.mdbc.dao.service.MDBCService2#getConnectorsDeclare()
	 */
	@Override
	public List<String> getConnectorsDeclare() {
		return this.connectorsDeclare;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#setConnectorsDeclare(java.util
	 * .List)
	 */
	@Override
	public void setConnectorsDeclare(List<String> busEntryDeclare) {
		this.connectorsDeclare = busEntryDeclare;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#searchInConnector(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public String searchInConnector(String key, String filter)
			throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}

		return XmlUtils.generateFormatXml((this.mdbcConnectors.get(key))
				.search(filter));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.venzia.mdbc.dao.service.MDBCService2#getAspect(java.lang.String)
	 */
	@Override
	public String getAspect(String key) throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key).getAspect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#getPrimaryKey(java.lang.String)
	 */
	@Override
	public String getPrimaryKey(String key) throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key).getPrimaryKey();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#getAspectDetailValue(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public String getAspectDetailValue(String key, String xmlDoc)
			throws NotExistConnectorException, IOException, JDOMException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key).getAspectDetailValue(xmlDoc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.venzia.mdbc.dao.service.MDBCService2#getTitle(java.lang.String)
	 */
	@Override
	public String getTitle(String key) throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key).getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#getcolumnDetail(java.lang.String
	 * )
	 */
	@Override
	public String getColumnDetail(String key) throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key).getColumnDetail();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.venzia.mdbc.dao.service.MDBCService2#getConnector(java.lang.String)
	 */
	@Override
	public Connector getConnector(String key) throws NotExistConnectorException {
		if (!this.mdbcConnectors.containsKey(key)) {
			throw new NotExistConnectorException();
		}
		return this.mdbcConnectors.get(key);
	}

}