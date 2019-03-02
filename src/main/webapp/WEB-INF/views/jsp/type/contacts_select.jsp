<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>

	<script type="text/javascript" charset="utf-8">

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactSelect').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": false,
				"bAutoWidth": false,
				"aaSorting": [],				
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0 ] }
				]
			} );

		});
	</script>


</head>

<h1><fmt:message key="contact.type.persons.title"/>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value='${param.flow_desc}'/></h1>	

<c:choose>
	<c:when test="${param.type=='P'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/policy/detail.html?id=${param.flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${param.type=='C'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/compliance/detail.html?id=${param.flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${param.type=='B'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/broker/detail.html?id=${param.flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${param.type=='D'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/dealer/detail.html?id=${param.flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
</c:choose>	
<br>
		<div id="container">
			<h1>List of Contacts</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactSelect">
					<thead> 
					<tr> 
						<th width="40">&nbsp;</th>
						<th><fmt:message key="person.form.firstName"/></th>
						<th><fmt:message key="person.form.lastName"/></th>
						<th><fmt:message key="person.form.company"/></th>
						<th><fmt:message key="person.form.jobtitle"/></th>						
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="person" items="${persons}" varStatus="status">
						    <tr>
							<c:set var="personFormId" value="person${status.index}"/>

							<c:if test="${param.type=='P' || param.type=='C'}">
								<c:url var="editUrl" value="/contact.html">
								    <c:param name="add" value="true" />								
								    <c:param name="type" value="${param.type}" />
								    <c:param name="p_id" value="${person.contactid}" />
								    <c:param name="flow_id" value="${param.flow_id}" />
								</c:url>
							</c:if>

							<c:if test="${param.type=='B'}">
								<c:url var="editUrl" value="/broker/detail.html">
								    <c:param name="type" value="${param.type}" />
								    <c:param name="p_id" value="${person.contactid}" />								    
								    <c:param name="id" value="${param.flow_id}" />
								</c:url>
							</c:if>


							<c:if test="${param.type=='D'}">
								<c:url var="editUrl" value="/dealer/detail.html">
								    <c:param name="type" value="${param.type}" />
								    <c:param name="p_id" value="${person.contactid}" />								    
								    <c:param name="id" value="${param.flow_id}" />
								</c:url>
							</c:if>

							<td>
							    <input type="button" value="Select" class="greyButton" onClick="location.href='<c:out value="${editUrl}"/>'" title="Click to select person"/>							    							
							</td>
							<td>${person.firstName}</td>
							<td>${person.lastName}</td> 							
							<td>${person.company}</td> 							
							<td>${person.jobtitle}</td> 														
						    </tr>
						</c:forEach>
					</tbody> 
					<tfoot> 
					<tr> 
						<th width="40">&nbsp;</th>
						<th><fmt:message key="person.form.firstName"/></th>
						<th><fmt:message key="person.form.lastName"/></th>
						<th><fmt:message key="person.form.company"/></th>
						<th><fmt:message key="person.form.jobtitle"/></th>						
					</tr> 
					</tfoot> 
					
				</table> 			
			</div>
			<div class="spacer"></div>			
		</div>

