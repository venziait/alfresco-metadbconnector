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
<import resource="classpath:/alfresco/templates/org/alfresco/import/alfresco-util.js">

var mdbConnector = {
	getColumnDetail: function mdbConnector_getColumnDetail()
		{
		   	
			var result = remote.connect("alfresco").get('/mdbc/api/aspect/column-detail?nodeRef=' +  model.nodeRef);
			if (result.status != 200)
			{
			 AlfrescoUtil.error(result.status, 'Could not load meta data ' + model.nodeRef);
			}
			result = eval('(' + result + ')');

		     return  result;
		}
};
function main(){
   AlfrescoUtil.param('nodeRef');
   AlfrescoUtil.param('site', null);
   var documentDetails = AlfrescoUtil.getNodeDetails(model.nodeRef, model.site);
   if (documentDetails)
   {
      model.result =  mdbConnector.getColumnDetail();
      model.allowMetaDataUpdate = documentDetails.item.node.permissions.user["Write"] || false;
   }
};
main();
