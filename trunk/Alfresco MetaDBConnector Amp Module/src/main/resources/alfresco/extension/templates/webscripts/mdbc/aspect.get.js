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

function main()
{
   var xmldoc = "";
   // allow for content to be loaded from id
   if (args["nodeRef"] != null && args["aspectName"] != null)
   {
   	var nodeRef = args["nodeRef"];
	var aspectName = args["aspectName"];
	
   	var node = search.findNode(nodeRef);
   	
   	if (node != null)
   	{
   	  	var aspectValue = node.properties[aspectName];
		if(aspectValue!=null){
			xmldoc = aspectValue;
		}
	}
   }
   
   // store node onto model
   model.xmldoc = xmldoc;
}

main();
