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

package org.venzia.mdbc.scripts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.processor.BaseProcessorExtension;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;
import org.apache.commons.beanutils.BeanUtils;
import org.jdom.JDOMException;
import org.venzia.mdbc.connector.Connector;
import org.venzia.mdbc.exception.NotExistConnectorException;
import org.venzia.mdbc.service.MDBCService;
import org.venzia.mdbc.utils.Column;

public class MDBCRegistryProxy extends BaseProcessorExtension {

	protected StoreRef storeRef = new StoreRef("workspace://SpacesStore");
	private MDBCService mdbcService;

	public void setMdbcService(MDBCService mdbcService) {
		this.mdbcService = mdbcService;
	}

	private ServiceRegistry impl;

	public void setServiceRegistry(final ServiceRegistry impl) {
		this.impl = impl;
	}

	public String getAspect(String connector) throws NotExistConnectorException {
		String aspect = mdbcService.getAspect(connector);
		return aspect;
	}

	public Object getConnector(String name) throws NotExistConnectorException {
		return mdbcService.getConnector(name);
	}

	public Object[] getConnectors() throws NotExistConnectorException {
		List<Connector> entry = mdbcService.getConnectors();
		return entry.toArray();
	}

	public String searchDataConnector(String connectorName, String search) throws NotExistConnectorException {
		String result = mdbcService.searchInConnector(connectorName, search);
		return result;
	}

	public Object[] getColumnsDetails() throws NotExistConnectorException {
		List<Connector> connectorList = mdbcService.getConnectors();
		Object[] columnsDetails = new Object[connectorList.size()];
		int i = 0;
		for (Connector connector : connectorList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", connector.getTitle());
			columnsDetails[i] = map;
			i++;
		}

		return columnsDetails;
	}

	public Collection<Column> getColumnsDetails(String connectorName) throws NotExistConnectorException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Connector connector = (Connector) mdbcService.getConnector(connectorName);
		Collection<Column> columns = (Collection<Column>) connector.getColumns().values();
		List<Column> list = new ArrayList<Column>();
		list.addAll(columns);
		Collections.sort(list);
		return list;
	}

	public String[] getAspectDetailValue(String connectorName, String xmlData) throws IOException, JDOMException, NotExistConnectorException {
		String valor = mdbcService.getConnector(connectorName).getAspectDetailValue(xmlData);
		return valor.split(";");
	}

	public Object[] getColumnsDetails(String nodeId, String uri) throws NotExistConnectorException, JDOMException, IOException {
		List<Connector> entryList = mdbcService.getConnectors();
		List<Object> list = new ArrayList<Object>();
		Object[] columnsDetails = null;
		for (Connector connector : entryList) {
			String[] aspectName = connector.getAspect().split(":");
			QName qname = QName.createQName(uri, aspectName[1]);
			NodeRef nodeRef = new NodeRef(storeRef, nodeId);
			if (impl.getNodeService().hasAspect(nodeRef, qname)) {
				// valor de aspecto
				String xmlData = (String) impl.getNodeService().getProperty(nodeRef, qname);

				String valor = connector.getAspectDetailValue(xmlData);

				String[] register_values = valor.split(";");

				for (int i = 0; i < register_values.length; i++) {
					if ((register_values[i] != null) && (!register_values[i].equals(""))) {
						Map<String, String> map_childs = new HashMap<String, String>();
						map_childs.put("name", connector.getTitle());
						map_childs.put("value", register_values[i]);

						list.add(map_childs);
					}
				}
			}
		}

		columnsDetails = list.toArray();

		return columnsDetails;
	}

}
