<#escape x as jsonUtils.encodeJSONString(x)>
 {
 "name":"${connector.name}",
 "title":"${connector.title}",
 "description":"${connector.description}",
 "aspect":"${connector.aspect}",
 "primaryKey":"${connector.primaryKey?lower_case}",
 "columnDetails":"${connector.columnDetail?lower_case}",
 "columns":[
	<#list columns as column>
	{
	  "name":"${column.name?lower_case}",
	  "label":"${column.label?string}",
	  "width":"${column.width}",
	  "visible":"${column.visible?string}"
	}<#if column_has_next>,</#if>
	</#list>
  ]
 }
</#escape>
