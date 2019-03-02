<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>

	<script type="text/javascript" charset="utf-8">

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactsComp').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": false,
				"bAutoWidth": false,					
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0,10 ] }
				]
			} );

		});
	</script>


</head>

<h1><fmt:message key="contact.type.compliance.title"/></h1>
<br>
		<div id="container">
			<h1>List of Policies</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsComp">
					<thead> 
					<tr> 
						<th nowrap align="left"  width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.case"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.formalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.informalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.clientGrouping"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.library"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.series"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.team"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.caseManager"/></th>												
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.ppm"/></th>
						<th nowrap align="left"  width="40">&nbsp;</th>
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="policy" items="${policys}" varStatus="status">
						    <tr>
							<c:set var="policyFormId" value="policy${status.index}"/>

							<c:url var="editUrl" value="/compliance.html">
							    <c:param name="id" value="${policy.caseid}" />
							</c:url>

							<c:url var="detailUrl" value="/compliance/detail.html">
							    <c:param name="id" value="${policy.caseid}" />
							</c:url>

                    					<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <c:url var="deleteUrl" value="/compliance/delete.html"/>
							    <form id="${policyFormId}" action="${deleteUrl}" method="POST">
								<input id="id" name="id" type="hidden" value="${policy.caseid}"/>
							    </form>
							</sec:authorize>

							<td align="center">
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">							
							    	<input type="button" value="Edit" class="greyButton" onClick="location.href='<c:out value="${editUrl}"/>'" title="Click to edit Policy information"/>
							    </sec:authorize> 							    	
							</td>
							<td align="left"><a href='<c:out value="${detailUrl}"/>'>${policy.casecode}</a></td> 							
							<td align="left">${policy.formalOwnerName}</td>
							<td align="left">${policy.informalOwnerName}</td>
							<td align="left">${policy.clientGroupCode}</td>
							<td align="left">${policy.library}</td>
							<td align="left">${policy.series}</td>
							<td align="left">${policy.team}</td>
							<td align="left">${policy.caseManager}</td>							
							<td align="left">${policy.ppm}</td>
							<td align="center">
							<c:if test="${user.department=='Compliance' && policy.caseid != 200}">													
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    	<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${policyFormId}'], 'POLICY');" title="Click to delete Policy"/>                    					                        					    
							    </sec:authorize> 
							</c:if>    
							</td>								
						    </tr>
						</c:forEach>
					</tbody> 
				</table> 	
				<br> 		
			</div>
			
			<div class="spacer"></div>			
			
		</div>

