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
import java.net.URISyntaxException;
import java.util.List;

import org.jdom.JDOMException;
import org.venzia.mdbc.connector.Connector;
import org.venzia.mdbc.exception.NotExistConnectorException;

public interface MDBCService {

	public abstract void init() throws URISyntaxException;

	public abstract void setCronExpression(String cronExpression);

	public abstract List<Connector> getConnectors();

	public abstract List<String> getConnectorsDeclare();

	public abstract void setConnectorsDeclare(List<String> connectorDeclare);

	public abstract String searchInConnector(String key, String filter)
			throws NotExistConnectorException;

	public abstract String getAspect(String key)
			throws NotExistConnectorException;

	public abstract String getPrimaryKey(String key)
			throws NotExistConnectorException;

	public abstract String getAspectDetailValue(String key, String xmlDoc)
			throws NotExistConnectorException, IOException, JDOMException;

	public abstract String getTitle(String key)
			throws NotExistConnectorException;

	public abstract String getColumnDetail(String key)
			throws NotExistConnectorException;

	public abstract Connector getConnector(String key)
			throws NotExistConnectorException;

}