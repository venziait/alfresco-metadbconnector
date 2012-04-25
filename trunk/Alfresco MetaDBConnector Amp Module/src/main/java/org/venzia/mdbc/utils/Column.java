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

package org.venzia.mdbc.utils;

import org.jdom.Element;

public class Column implements Comparable<Column>{
	String name;
	String label;
	String width;
	Integer order = 1;
	boolean visible = true;

	public Column(Element column) {
		this.setName(column.getAttributeValue("name"));
		this.setLabel(column.getAttributeValue("label"));
		this.setWidth(column.getAttributeValue("width"));
		if (column.getAttributeValue("order") != null) {
			this.setOrder(Integer.parseInt(column.getAttributeValue("order")));
		}
		if (column.getAttributeValue("visible") != null) {
			this.setVisible(Boolean.parseBoolean(column.getAttributeValue("visible")));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public int compareTo(Column o) {
		return this.order.compareTo(o.getOrder());
	}

}
