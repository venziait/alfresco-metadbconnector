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

public interface Connector {

	public abstract void setAspect(String aspect);

	public abstract void setName(String name);

	public abstract void setTitle(String title);

	public abstract void setDescription(String description);

	public abstract GenericDAO getDao();

	public abstract String getAspect();

	public abstract String getName();

	public abstract String getTitle();

	public abstract String getDescription();

	public abstract Map<String, Column> getColumns();

	public abstract void setDao(GenericDAO dao);

	public abstract String getPrimaryKey();

	public abstract void setColumns(Map<String, Column> columns);

	public abstract void setColumnDetail(String columnDetail);

	public abstract String getColumnDetail();

	public abstract String getAspectDetailValue(String xmlDoc)
			throws IOException, JDOMException;

	public abstract List<Map<String, Object>> search(String filter);

	public abstract Map<String, Object> findRow(String id);

}