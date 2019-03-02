<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
	
	<script type="text/javascript" charset="utf-8">
	
		function viewPerson(contactid)
		{
			 var content = document.getElementById("resultPane");
			 $.ajax({
				   type: "GET",
				   url: "view_contact.html?id="+contactid+"&type=P",  
				   
				   success: function(msg){		
											  
					   //content.innerHTML = msg;
					   $("#resultPane").html(msg);
					  
						 $("#resultPane").show().dialog({
							bgiframe: true,
							zIndex:30,
							height: 700,
							width: 900,
							modal: true,
							buttons: {								
								'Ok': function() {
									$(this).dialog('destroy');								
								}
							}
						});
					
					
						
				
					
				   },
					failure : function(msg) {
						   $("#resultPane").html(msg);
						   $("#resultPane").show().dialog({
								bgiframe: true,
								height: 100,
								width: 360,
								modal: true,
								buttons: {								
									'Ok': function() {
										$(this).dialog('destroy');								
									}
								}
							});
					
				}
			 });                       
		

		}

		$(document).ready(function() {
			/* Build the DataTable with third column using our custom sort functions */
			$('#contactsCP').dataTable( {
				"sScrollX": "100%",
				"sScrollY": "400px",										
				"bScrollCollapse": true,					
				"bPaginate": true,
				"bAutoWidth": false,
				"aaSorting": [],
				"aoColumnDefs": [
					{ "bSortable": false, "aTargets": [ 0,7 ] }
				]
			} );

		});

	</script>


</head>

<h1><fmt:message key="contact.type.persons.title"/></h1>
<br>


		<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">							
			<c:set var="hasPermission" value="true"/>
		</sec:authorize>
		
		<div id="container">
			<div class="full_width big">
			    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
				    <a href="<c:url value="/person.html?type=X"/>"><img src="/contacts/images/add.png" width="20" border="0" title="Click to add person"><fmt:message key="button.create"/></a> 
			    </sec:authorize>
			</div>
			<br>	
			
			
			<h1>List of Contacts</h1>
			<br>
			<div id="data">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsCP">
					<thead> 
					<tr> 
						<th width="5%">&nbsp;</th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.firstName"/></th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.lastName"/></th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.workphone"/></th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.cellphone"/></th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.company"/></th>
						<th nowrap width="10%" align="left" class="td-header"><fmt:message key="person.form.jobtitle"/></th>						
						<th width="5%">&nbsp;</th>
					</tr> 
					</thead> 
					<tbody> 
						<c:forEach var="person" items="${persons}" varStatus="status">
						    <tr>
							<c:set var="personFormId" value="person${status.index}"/>

							<c:url var="editUrl" value="/person.html">
							    <c:param name="id" value="${person.contactid}" />
							    <c:param name="type" value="X" />							    
							</c:url>

							<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <c:url var="deleteUrl" value="/person/delete.html"/>
							    <form id="${personFormId}" action="${deleteUrl}" method="POST">
								<input id="id" name="id" type="hidden" value="${person.contactid}"/>
							    </form>
							</sec:authorize>

							<td>
							    <input type="button" value="Preview" class="greyButton" onClick='javascript:viewPerson("${person.contactid}")'/>
							</td>
							<c:choose>
							    <c:when test="${hasPermission=='true'}" >	
								<td>
									 <a href='<c:out value="${editUrl}"/>' title="Click to edit person">${person.firstName}</a> 
								</td> 
								<td>
									 <a href='<c:out value="${editUrl}"/>' title="Click to edit person">${person.lastName}</a>
								</td> 				
							    </c:when>								
							    <c:otherwise>
								<td>
									 ${person.firstName}
								</td> 
								<td>
									 ${person.lastName}
								</td> 	
							    </c:otherwise>								
							</c:choose> 														
							
							<td>${person.primaryphone}</td>
							<td>${person.cellphone}</td>
							<td>${person.company}</td>	
							<td>${person.jobtitle}</td>
							<td>
                    					    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
								 <input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item(document.forms['${personFormId}']);"/>
							    </sec:authorize> 
							</td>							
						    </tr>
						</c:forEach>
					</tbody> 
					<tfoot> 
					<tr> 
						<th width="40" class="td-header">&nbsp;</th>
						<th class="td-header" nowrap align="left" style="padding:8px"><fmt:message key="person.form.firstName"/></th>
						<th class="td-header" nowrap align="left"><fmt:message key="person.form.lastName"/></th>
						<th class="td-header" nowrap align="left"><fmt:message key="person.form.workphone"/></th>
						<th class="td-header" nowrap align="left"><fmt:message key="person.form.cellphone"/></th>
						<th class="td-header" nowrap align="left"><fmt:message key="person.form.company"/></th>
						<th class="td-header" nowrap align="left"><fmt:message key="person.form.jobtitle"/></th>						
						<th width="40" class="td-header">&nbsp;</th>					
					</tr> 
					</tfoot> 
					
				</table> 	
				<br>		
			</div>
			<div class="spacer"></div>			
		</div>

		<div id="resultPane" title="Contact Preview"></div>

