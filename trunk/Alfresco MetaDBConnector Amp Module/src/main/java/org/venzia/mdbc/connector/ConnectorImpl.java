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
package org.venzia.mdbc.connector;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jdom.JDOMException;
import org.venzia.mdbc.dao.GenericDAO;
import org.venzia.mdbc.utils.Column;
import org.venzia.mdbc.utils.XmlUtils;

public class ConnectorImpl implements Connector {
	private GenericDAO dao;
	private String aspect;
	private String name;
	private String title;
	private String description;
	private String columnDetail;
	private Map<String, Column> columns;

	public ConnectorImpl(GenericDAO dao, String nameEntry, String aspect) {
		this.dao = dao;
		this.name = nameEntry;
		this.aspect = aspect;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setAspect(java.lang.String)
	 */
	@Override
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getDao()
	 */
	@Override
	public GenericDAO getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getAspect()
	 */
	@Override
	public String getAspect() {
		return aspect;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getColumns()
	 */
	@Override
	public Map<String, Column> getColumns() {
		return columns;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setDao(org.venzia.mdbc.dao.GenericDAO)
	 */
	@Override
	public void setDao(GenericDAO dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getPrimaryKey()
	 */
	@Override
	public String getPrimaryKey() {
		return this.dao.getPrimaryKey();
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setColumns(java.util.Map)
	 */
	@Override
	public void setColumns(Map<String, Column> columns) {
		this.columns = columns;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#setColumnDetail(java.lang.String)
	 */
	@Override
	public void setColumnDetail(String columnDetail) {
		this.columnDetail = columnDetail;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getColumnDetail()
	 */
	@Override
	public String getColumnDetail() {
		return this.columnDetail;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#getAspectDetailValue(java.lang.String)
	 */
	@Override
	public String getAspectDetailValue(String xmlDoc) throws IOException,
			JDOMException {
		return XmlUtils.getValueXmlNode(xmlDoc, this.columnDetail);
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#search(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> search(String filter) {
		try {
			return this.dao.search(filter);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.venzia.mdbc.connector.Connector#findRow(java.lang.String)
	 */
	@Override
	public Map<String, Object> findRow(String id) {
		return this.dao.findRow(id);
	}
}