<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>

	<script type="text/javascript" charset="utf-8">

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactsBC').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": false,
				"bAutoWidth": false,					
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0,6 ] }
				]
			} );

		});
	</script>


</head>

<h1><fmt:message key="contact.type.broker.company.title"/></h1>
<br>
		<div id="container">
			<div class="full_width big">
				<c:if test="${user.department=='Compliance'}">			
				    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
					    <a href="<c:url value="/broker.html"/>"><img src="/contacts/images/add.png" width="20" border="0" title="Click to add broker"><fmt:message key="button.create"/></a> 
				    </sec:authorize>
				</c:if>				    
			</div>
			<br>	

			<h1>List of Broker Companies</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsBC">
					<thead> 
					<tr> 
						<th nowrap align="left"  width="20">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.brokerCompany"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.primaryPhone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.primaryFax"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.secondaryPhone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.secondaryFax"/></th>
						<th nowrap align="left" width="40">&nbsp;</th>						
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="broker" items="${brokers}" varStatus="status">
						    <tr>
							<c:set var="brokerFormId" value="broker${status.index}"/>

							<c:url var="editUrl" value="/broker.html">
							    <c:param name="id" value="${broker.brokerid}" />
							</c:url>

							<c:url var="detailUrl" value="/broker/detail.html">
							    <c:param name="id" value="${broker.brokerid}" />
							</c:url>

							<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <c:url var="deleteUrl" value="/broker/delete.html"/>
							    <form id="${brokerFormId}" action="${deleteUrl}" method="POST">
								<input id="id" name="id" type="hidden" value="${broker.brokerid}"/>
							    </form>
							</sec:authorize>

							<td align="center">
							    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">								    
								    <input type="button" value="Edit" class="greyButton" onClick="location.href='<c:out value="${editUrl}"/>'" title="Click to edit Broker Company"/>
							    </sec:authorize> 							    
							</td>
							<td align="left"><a href='<c:out value="${detailUrl}"/>'>${broker.brokerCompany}</a></td> 							
							<td align="left">${broker.primaryPhone}</td>
							<td align="left">${broker.primaryFax}</td>
							<td align="left">${broker.secondaryPhone}</td>
							<td align="left">${broker.secondaryFax}</td>
							<td align="center">
							<c:if test="${user.department=='Compliance'}">																				
							    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    	<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${brokerFormId}'], 'BROKER_COMPANY');" title="Click to delete Broker Company"/>
							    </sec:authorize> 
							</c:if>    
							</td>							
						    </tr>
						</c:forEach>
					</tbody> 
					<tfoot> 
					<tr> 
						<th width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.brokerCompany"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.primaryPhone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.primaryFax"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.secondaryPhone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.form.secondaryFax"/></th>
						<th  nowrap align="left" class="td-header" width="40">&nbsp;</th>				
					</tr> 
					</tfoot> 

				</table> 	
				<br> 		
			</div>
			
			<div class="spacer"></div>			
			
		</div>
