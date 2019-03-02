<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>

	<script type="text/javascript" charset="utf-8">

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactsBD').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": false,
				"bAutoWidth": false,					
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0,4 ] }
				]
			} );

		});
	</script>


</head>

<h1><fmt:message key="contact.type.broker.dealer.title"/></h1>
<br>
		<div id="container">
			<div class="full_width big">
				<c:if test="${user.department=='Compliance'}">						
				    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
					    <a href="<c:url value="/dealer.html"/>"><img src="/contacts/images/add.png" width="20" border="0" title="Click to add broker dealer"><fmt:message key="button.create"/></a> 
				    </sec:authorize>
				</c:if>				    				    
			</div>
			<br>	

			<h1>List of Broker Dealers</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsBD">
					<thead> 
					<tr> 
						<th nowrap align="left" width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.brokerDealerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.phone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.fax"/></th>
						<!--<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.contactPerson"/></th>-->
						<th width="40">&nbsp;</th>
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="dealer" items="${dealers}" varStatus="status">
						    <tr>
							<c:set var="dealerFormId" value="dealer${status.index}"/>

							<c:url var="editUrl" value="/dealer.html">
							    <c:param name="id" value="${dealer.brokerDealerid}" />
							</c:url>

							<c:url var="detailUrl" value="/dealer/detail.html">
							    <c:param name="id" value="${dealer.brokerDealerid}" />
							</c:url>

                    					<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <c:url var="deleteUrl" value="/dealer/delete.html"/>
							    <form id="${dealerFormId}" action="${deleteUrl}" method="POST">
								<input id="id" name="id" type="hidden" value="${dealer.brokerDealerid}"/>
							    </form>
							</sec:authorize>

							<td align="center">
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">							
								    <input type="button" value="Edit" class="greyButton" onClick="location.href='<c:out value="${editUrl}"/>'" title="Click to edit Broker Dealer"/>							    
							    </sec:authorize> 								
							</td>
							<td align="left"><a href='<c:out value="${detailUrl}"/>'>${dealer.brokerDealerName}</a></td> 							
							<td align="left">${dealer.phone}</td>
							<td align="left">${dealer.fax}</td>
							<td align="center">
							<c:if test="${user.department=='Compliance'}">																				
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    	<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${dealerFormId}'], 'BROKER_DEALER');" title="Click to delete Broker Dealer"/>                    					    
							    </sec:authorize> 
							</c:if>
							</td>							
						    </tr>
						</c:forEach>
					</tbody> 
					<tfoot> 
					<tr> 
						<th nowrap align="left" class="td-header" width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.brokerDealerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.phone"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.fax"/></th>
						<!--<th nowrap align="left" class="td-header"><fmt:message key="broker.dealer.form.contactPerson"/></th>-->
						<th nowrap align="left" class="td-header" width="40">&nbsp;</th>
					</tr> 
					</tfoot> 

				</table> 	
				<br>	
			</div>
			
			<div class="spacer"></div>			
			
		</div>
