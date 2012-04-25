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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

@SuppressWarnings("unchecked")
public class XmlUtils
{
  public static final String ELEMENT_ROOT = "mdbc";
  public static final String ELEMENT_ROWS = "rows";
  public static final String ELEMENT_ROW = "row";
  public static final String ELEMENT_TOTAL = "total";
  public static final String COLUMN_SEPARATOR = ";";
  public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

  public static String generateFormatXml(List<Map<String, Object>> list) {
    Document document = new Document();
    Element root = new Element(ELEMENT_ROOT);
    document.setRootElement(root);
    root.addContent(new Element(ELEMENT_TOTAL).setText(String.valueOf(list.size())));
    Element rows = new Element(ELEMENT_ROWS);
    root.addContent(rows);
    for (Map<String, Object> map : list) {
      Element child = new Element(ELEMENT_ROW);
      for (Entry<String, Object> entry : map.entrySet()) {
        Element column = new Element(((String)entry.getKey()).toLowerCase());
        if (entry.getValue() == null)
          column.setText("");
        else if ((entry.getValue() instanceof String))
          column.setContent(new CDATA(entry.getValue().toString()));
        else if ((entry.getValue() instanceof Date))
          column.setText(df.format(entry.getValue()));
        else {
          column.setText(entry.getValue().toString());
        }
        child.addContent(column);
      }
      rows.addContent(child);
    }
    return new XMLOutputter().outputString(document);
  }
  
  public static String getValueXmlNode(String xmlDoc, String column) throws JDOMException, IOException
  {
    StringBuffer buffer = new StringBuffer();
    SAXBuilder builder = new SAXBuilder();
    Reader in = new StringReader(xmlDoc);
    Document doc = builder.build(in);
    Element root = doc.getRootElement();
    if (root == null)
      return "";
    Element registers = root.getChild(ELEMENT_ROWS);
    if (registers == null)
      return "";

	List<Element> register = registers.getChildren(ELEMENT_ROW);
    if (register == null)
      return "";
    for (Element element : register) {
      Element node = element.getChild(column.toLowerCase());
      if (node != null) {
        if (register.lastIndexOf(element) != register.size() - 1)
          buffer.append(node.getTextNormalize()+ COLUMN_SEPARATOR);
        else {
          buffer.append(node.getTextNormalize());
        }
      }
    }
    return buffer.toString();
  }
}