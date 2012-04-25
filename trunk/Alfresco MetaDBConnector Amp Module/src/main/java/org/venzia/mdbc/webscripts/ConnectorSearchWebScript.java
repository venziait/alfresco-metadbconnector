
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

package org.venzia.mdbc.webscripts;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.venzia.mdbc.service.MDBCService;

public class ConnectorSearchWebScript extends DeclarativeWebScript {
	Log log = LogFactory.getLog(ConnectorSearchWebScript.class);
	MDBCService mdbcService;
	
	public void setBusService(MDBCService busService) {
		this.mdbcService = busService;
	}

	public Map<String, Object> executeImpl(WebScriptRequest request,
			Status status) {
		try {
			String connector = request.getParameter("connector");
			String search = request.getParameter("search");
			Map<String, Object> model = new HashMap<String, Object>();
			if(connector==null)
				throw new WebScriptException("not found parameter connector");
			
			String  result = mdbcService.searchInConnector(connector, search);
			
			model.put("result", result);
			return model;
		} catch (Exception e) {
			log.equals(e);
			throw new WebScriptException("Unable to serialize JSON");
		}
	}
}
