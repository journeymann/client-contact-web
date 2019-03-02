<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<style type="text/css">
		tr { backgorund:white; }
		tr.odd { background:silver; }	
	</style>

	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
		  $('table tbody tr:odd').addClass('odd');
		});
	</script>

</head>


<h1><fmt:message key="field.definition.title"/></h1>
<!--<div align="right" style="padding-right:7px"><a href="javascript:history.go(-1);"><fmt:message key="button.back"/></a></div>-->

<p> Field Definition Glossary </p>
<br>

	<table border="2" width="99%">
		<thead>
			<tr>
				<th align="left" class="td-header" width="100">Field</th>
				<th align="left" class="td-header" width="200">Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="field" items="${fields}" varStatus="status">
				<tr>
					<td><c:out value="${field.name}" /></td>
					<td><c:out value="${field.desc}" /></td>
				</tr>		
			</c:forEach>
		</tbody>		
	</table>

<br><br>