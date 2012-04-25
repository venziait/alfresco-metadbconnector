<#escape x as jsonUtils.encodeJSONString(x)>
{ "connectors":[
	<#list connectors as conn>
	   	 {
	     "name":"${conn.name}",
		 "title":"${conn.title}",
		 "description":"${conn.description}",
		 "aspect":"${conn.aspect}"
		 }<#if conn_has_next>,</#if>
	</#list>
	]
}
</#escape>
