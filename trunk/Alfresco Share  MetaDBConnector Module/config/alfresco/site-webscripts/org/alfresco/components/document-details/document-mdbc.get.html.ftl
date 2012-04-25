
<#include "../../include/alfresco-macros.lib.ftl" />
<#assign el=args.htmlid?html>
<div id="${el}-body" class="document-tags document-details-panel">

<h2 id="${el}-heading" class="thin dark">
 ${msg("label.bi")}
	<#if allowMetaDataUpdate>
            <span class="alfresco-twister-actions">
               <a href="${siteURL("edit-metadata?nodeRef="+nodeRef?js_string)}" class="edit" title="${msg("label.edit")}">&nbsp;</a>
            </span>
         </#if>
</h2>

<div class="panel-body">
	<#if result.connectors?size == 0>
		 ${msg("label.none")}
	<#else>
	<#list result.connectors as conn >
	 <div class="viewmode-field">
		 <span class="viewmode-label">${conn.title}:</span>
		 <#if conn.values?size == 0>
		    ${msg("label.none")}
		 <#else>
		    <#list conn.values as c>
		       <#if c?length==0>${msg("label.none")}<#else><span class="tag">${c?html}</span></#if>
		    </#list>
		 </#if>
	 </div>
	</#list>
	</#if>
</div>
<script type="text/javascript">//<![CDATA[
 Alfresco.util.createTwister("${el}-heading", "DocumentMDBC");
//]]></script>
</div>
