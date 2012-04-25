<#macro renderConnectorPickerHTML controlId>
   <#assign pickerId = controlId + "-picker">
   <div id="${pickerId}" class="picker yui-panel">
   <div id="${pickerId}-head" class="hd">${msg("form.control.object-picker.header")}</div>

   <div id="${pickerId}-body" class="bd">
      <div class="picker-header">
         <div id="${pickerId}-folderUpContainer" class="folder-up"><button id="${pickerId}-folderUp"></button></div>
         <div id="${pickerId}-navigatorContainer" class="navigator">
            <button id="${pickerId}-navigator"></button>
            <div id="${pickerId}-navigatorMenu" class="yuimenu">
               <div class="bd">
                  <ul id="${pickerId}-navigatorItems" class="navigator-items-list">
                     <li>&nbsp;</li>
                  </ul>
               </div>
            </div>
         </div>
         <div id="${pickerId}-searchContainer" class="search">
            <input type="text" class="search-input" name="-" id="${pickerId}-searchText" value="" maxlength="256" />
            <span class="search-button"><button id="${pickerId}-searchButton">${msg("form.control.object-picker.search")}</button></span>
         </div>
      </div>
      <div class="yui-g">
         <div id="${pickerId}-left" class="yui-u first panel-left">
            <div id="${pickerId}-results" class="picker-items">
               <#nested>
            </div>
         </div>
         <div id="${pickerId}-right" class="yui-u panel-right">
            <div id="${pickerId}-selectedItems" class="picker-items"></div>
         </div>
      </div>
      <div class="bdft">
         <button id="${controlId}-ok" tabindex="0">${msg("button.ok")}</button>
         <button id="${controlId}-cancel" tabindex="0">${msg("button.cancel")}</button>
      </div>
   </div>

</div>
</#macro>


<#assign controlId = fieldHtmlId + "-cntrl">

<script type="text/javascript">//<![CDATA[
(function()
{

   var mdbc = new Venzia.MDBConnectorFinder("${controlId}", "${fieldHtmlId}").setOptions(
   {
   <#if form.mode == "view" || (field.disabled && !(field.control.params.forceEditable?? && field.control.params.forceEditable == "true"))>disabled: true,</#if>
      field: "${field.name}",
      compactMode: true,
   <#if field.mandatory??>
      mandatory: ${field.mandatory?string},
   <#elseif field.endpointMandatory??>
      mandatory: ${field.endpointMandatory?string},
   </#if>
   <#if context.properties.nodeRef??>
      nodeRef: "${context.properties.nodeRef?js_string}",
   <#elseif form.mode == "edit" && args.itemId??>
      nodeRef: "${args.itemId?js_string}",
   <#elseif form.mode=="view">
      nodeRef: "${args.itemId?js_string}",
     </#if>
      currentValue: "<#if field.value??>true</#if>",
      <#if field.control.params.valueType??>valueType: "${field.control.params.valueType}",</#if>
      <#if renderPickerJSSelectedValue??>selectedValue: "${renderPickerJSSelectedValue}",</#if>
      <#if field.control.params.selectActionLabelId??>selectActionLabelId: "${field.control.params.selectActionLabelId}",</#if>
      selectActionLabel: "${field.control.params.selectActionLabel!msg("button.select")}",
      minSearchTermLength: ${field.control.params.minSearchTermLength!'1'},
      maxSearchResults: ${field.control.params.maxSearchResults!'1000'}
   }).setMessages(
      ${messages}
   );

   mdbc.setOptions(
   {
      multipleSelectMode: true,
      itemFamily: "mdbc",
      displayMode:"<#if form.mode == "edit">list<#else>tags</#if>",
      maintainAddedRemovedItems: false,
      connectorName:"${field.control.params.connector}",
      aspectName:"${field.control.params.aspect}"
   });

})();
//]]></script>

<div class="form-field">
   <#if form.mode == "view">
      <div id="${controlId}" class="viewmode-field">
         <#if field.endpointMandatory && field.value == "">
            <span class="incomplete-warning"><img src="${url.context}/res/components/form/images/warning-16.png" title="${msg("form.field.incomplete")}" /><span>
         </#if>
         <span class="viewmode-label">${field.label?html}:</span>
         <span id="${controlId}-currentValueDisplay" class="viewmode-value current-values"></span>
      </div>
   <#else>
      <label for="${controlId}">${field.label?html}:<#if field.endpointMandatory><span class="mandatory-indicator">${msg("form.required.fields.marker")}</span></#if></label>
      
      <div id="${controlId}" class="mdbc-finder">
         
         <div id="${controlId}-currentValueDisplay" class="current-values"></div>
         
         <#if field.disabled == false>
            <input type="hidden" id="${fieldHtmlId}" name="${field.name}" value="${field.value?html}" />
            <div id="${controlId}-itemGroupActions" class="show-picker"></div>

            <@renderConnectorPickerHTML controlId />
         </#if>
      </div>
   </#if>
</div>
