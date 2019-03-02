<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>

	<script type="text/javascript" charset="utf-8">

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactsP').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": false,
				"bAutoWidth": false,					
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0 ] }
				]
			} );

		});
	</script>


</head>

<h1><fmt:message key="contact.type.policy.title"/></h1>
<br>
		<div id="container">
			<div class="full_width big">
			    <c:if test="${user.department=='CPO'}">			
				    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
					    <a href="<c:url value="/policy.html"/>"><img src="/contacts/images/add.png" width="20" border="0" title="Click to add policy"><fmt:message key="button.create"/></a> 
				    </sec:authorize>
			    </c:if>				    
			</div>
			<br>	

			<h1>List of Policies</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsP">
					<thead> 
					<tr> 
						<th nowrap align="left"  width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header" ><fmt:message key="policy.form.case"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.formalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.informalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.clientGrouping"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.library"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.series"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.team"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.caseManager"/></th>												
						<th nowrap align="left"  width="40">&nbsp;</th>
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="policy" items="${policys}" varStatus="status">
						    <tr>
							<c:set var="policyFormId" value="policy${status.index}"/>

							<c:url var="editUrl" value="/policy.html">
							    <c:param name="id" value="${policy.caseid}" />
							</c:url>

							<c:url var="detailUrl" value="/policy/detail.html">
							    <c:param name="id" value="${policy.caseid}" />
							</c:url>

                    					<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <c:url var="deleteUrl" value="/policy/delete.html"/>
							    <form id="${policyFormId}" action="${deleteUrl}" method="POST">
								<input id="id" name="id" type="hidden" value="${policy.caseid}"/>
							    </form>
							</sec:authorize>

							<td>
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">							
								    <input type="button" value="Edit" class="greyButton" onClick="location.href='<c:out value="${editUrl}"/>'" title="Click to edit Policy information"/>
							    </sec:authorize>
							</td>
							<td><a href='<c:out value="${detailUrl}"/>'>${policy.casecode}</a></td> 							
							<td>${policy.formalOwnerName}</td>
							<td>${policy.informalOwnerName}</td>
							<td>${policy.clientGroupCode}</td>
							<td>${policy.library}</td>
							<td>${policy.series}</td>
							<td>${policy.team}</td>
							<td>${policy.caseManager}</td>							
							<td>
							<c:if test="${user.department=='CPO' && policy.caseid != 200}">			
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    	<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${policyFormId}'], 'POLICY');" title="Click to delete Policy"/>                    					                        					    
							    </sec:authorize> 
							</c:if>    
							</td>							
						    </tr>
						</c:forEach>
					</tbody> 
					<tfoot> 
					<tr> 
						<th nowrap align="left" class="td-header" width="40">&nbsp;</th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.case"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.formalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.informalOwnerName"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.clientGrouping"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.library"/></th>
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.series"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.team"/></th>						
						<th nowrap align="left" class="td-header"><fmt:message key="policy.form.caseManager"/></th>												
						<th nowrap align="left" class="td-header" width="40">&nbsp;</th>
					</tr> 
					</tfoot> 

				</table> 	
				<br>		
			</div>
			
			<div class="spacer"></div>			
			
		</div>

