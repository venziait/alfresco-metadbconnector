<#assign el=args.htmlid?html>
<script type="text/javascript">//<![CDATA[
   new Venzia.dashlet.MDBConnector("${el}").setMessages(
      ${messages}
   );
//]]></script>

<div id="${el}-body" class="mdbc">
 	<div class="yui-g">
	 <div class="yui-u first">
	    <div class="title"><label for="${el}-search-text">${msg("tool.mdbc.label")}</label></div>
	 </div>
	 <div class="yui-u align-right">
	 </div>
      </div>

     <div class="results" id="${el}-datatable"></div>
 </div>	


