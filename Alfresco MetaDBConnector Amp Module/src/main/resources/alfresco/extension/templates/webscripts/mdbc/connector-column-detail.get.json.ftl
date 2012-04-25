<#escape x as jsonUtils.encodeJSONString(x)>
{ "data":[
	<#list columnDetails as conn>
	   	 {
		 "title":"${conn.title}",
		 "values":[<#list con.values as value>"${value?string}"<#if value_has_next>,</#if></#list>]
		 }<#if conn_has_next>,</#if>
	</#list>
	]
}
</#escape>
