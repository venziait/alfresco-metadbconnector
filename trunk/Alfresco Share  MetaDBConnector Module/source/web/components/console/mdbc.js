/**
 * Copyright (C) 2005-2010 Venzia Software Limited.
 *
 * This file is part of Venzia Dashlet Components
 *
 * You can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Venzia Module is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Venzia. If not, see <http://www.gnu.org/licenses/>.
 */
 
/**
 * Venzia.dashlet.MDBConnector
 *
 * TODO:Add description to component
 * 
 *
 */

// Ensure Venzia root object exists
if (typeof Venzia == "undefined" || !Venzia)
{
   var Venzia = {};
}

/**
 * Alfresco top-level constants namespace.
 * 
 * @namespace Alfresco
 * @class Alfresco.constants
 */
Venzia.dashlet = Venzia.dashlet || {};

(function()
{

   /**
    * YUI Library aliases
    */
   var Dom = YAHOO.util.Dom,
      Event = YAHOO.util.Event;

    /**
    * Alfresco Slingshot aliases
    */
   var $html = Alfresco.util.encodeHTML,
      $links = Alfresco.util.activateLinks,
      $combine = Alfresco.util.combinePaths;

   /**
    * OpenSocial constructor.
    * 
    * @param {String} htmlId The HTML id of the parent element
    * @return {Alfresco.dashlet.OpenSocial} The new OpenSocial instance
    * @constructor
    */
   Venzia.dashlet.MDBConnector = function MDBConnector_constructor(htmlId)
   {
      Venzia.dashlet.MDBConnector.superclass.constructor.call(this, "Venzia.dashlet.MDBConnector", htmlId);

	 /* Load YUI Components */
      Alfresco.util.YUILoaderHelper.require(["container", "datasource", "datatable", "json"], this.onComponentsLoaded, this);
      
      return this;
   };

   YAHOO.extend(Venzia.dashlet.MDBConnector, Alfresco.component.Base,
   {
	/**
	* Object container for initialization options
	*
	* @property options
	* @type object
	*/
	options:
	{
	 /**
	  * The component id
	  *
	  * @property componentId
	  * @type string
	  * @default ""
	  */
	 componentId: ""
	},  
	
	/**
	* Fired by YUI when parent element is available for scripting.
	* Component initialisation, including instantiation of YUI widgets and event listener binding.
	*
	* @method onReady
	*/
	onReady: function VZ_onReady()
	{
	  var parent = this;

	 // DataTable and DataSource setup
            this.widgets.dataSource = new YAHOO.util.DataSource(Alfresco.constants.PROXY_URI + "mdbc/api/connectors/list",
            {
               responseType: YAHOO.util.DataSource.TYPE_JSON,
               responseSchema:
               {
                  resultsList: "connectors",
                  metaFields:
                  {
                     recordOffset: "startIndex",
                     totalRecords: "totalRecords"
                  }
               }
            });

	  this._setupDataTable();
	},
	 /**
	  * Setup the YUI DataTable with custom renderers.
	  *
	  * @method _setupDataTable
	  * @private
	  */
	 _setupDataTable: function _setupDataTable()
	 {
	 
	    /**
	     * DataTable Cell Renderers
	     *
	     * Each cell has a custom renderer defined as a custom function. See YUI documentation for details.
	     * These MUST be inline in order to have access to the parent instance (via the "parent" variable).
	     */
	    
	    
	    /**
	     * Generic HTML-safe custom datacell formatter
	     */
	    var renderCellSafeHTML = function renderCellSafeHTML(elCell, oRecord, oColumn, oData)
	    {
	       elCell.innerHTML =  $html(oData);
	    };
	    
 	     /**
	     * Generic Check custom datacell formatter
	     */
	    var renderCheck = function renderCellSafeHTML(elCell, oRecord, oColumn, oData)
	    {
               
               // overlay the account enabled/disabled indicator image
               var enabled = (oRecord.getData(oColumn.key)=="true" ? 'on' : 'off');
               elCell.innerHTML = '<img class="indicator"  src="' + Alfresco.constants.URL_RESCONTEXT + 'components/images/enabled-indicator-' + enabled + '-16.png" alt="" />';
	    };
	   
	  
	    // DataTable column defintions
	    var columnDefinitions =
	    [
		{ key: "title", label: this.msg("mdbc.grid.label.title"), sortable: true, formatter: renderCellSafeHTML },
		{ key: "description", label: this.msg("mdbc.grid.label.description"), sortable: true, formatter: renderCellSafeHTML },
		{ key: "aspect", label: this.msg("mdbc.grid.label.aspect"), sortable: true, formatter: renderCellSafeHTML }
	    ];
	    
	    // DataTable definition
	    this.widgets.dataTable = new YAHOO.widget.DataTable(this.id + "-datatable", columnDefinitions, this.widgets.dataSource,
	    {
	       initialLoad: true,
	       renderLoopSize: 32,
	       sortedBy:
	       {
			  key: "title",
			  dir: "asc"
	       },
	       MSG_EMPTY: this.msg("message.empty")
	    });
	 }
   });
})();
