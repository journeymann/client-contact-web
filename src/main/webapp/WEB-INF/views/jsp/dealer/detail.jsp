<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
											  
					   //alert(msg);
	
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
			$('#contactsBDT').dataTable( {
				"bPaginate":false	
			} );

		});		
		
	</script>


</head>

<h1><fmt:message key="broker.dealer.form.title"/></h1>
<div align="left" style="padding-left:10px">
<a href='<c:url value="/type/broker_dealer.html"/>'>Back to Broker Dealer List</a>
</div>

<div id="container">
	<div id="messages">
	    <c:if test="${not empty statusMessageKey}">
	       <p><fmt:message key="${statusMessageKey}"/></p>
	    </c:if>

	    <spring:hasBindErrors name="dealer">
		<h2>Errors</h2>
		<div class="formerror">
		    <ul>
		    <c:forEach var="error" items="${errors.allErrors}">
			<li>${error.defaultMessage}</li>
		    </c:forEach>
		    </ul>
		</div>
	    </spring:hasBindErrors>
	</div>
			<br>
			<div id="data" style="padding-left:7px">
					<table cellpadding="0" cellspacing="0" border="0" width="99%">
				<tr>				
				<td width="23%"></td>
				<td width="15%"></td>
				<td width="15%"></td>
				<td width="11%"></td>
				<td width="7%"></td>
				<td width="5%"></td>
				<td width="6%"></td>
				<td width="10%"></td>
				<td width="10%"></td>
				</tr>
					<thead> 
					<tr> 
						
						<th align="left" class="td-header"><fmt:message key="broker.dealer.form.policies"/></th>																		
						<th align="left" class="td-header"><fmt:message key="broker.dealer.form.brokerDealerName"/></th>
						<th align="left" class="td-header"><fmt:message key="broker.dealer.address.form.address1"/></th>						
						<th align="left" class="td-header"><fmt:message key="broker.dealer.address.form.address2"/></th>						
						<th align="left" class="td-header"><fmt:message key="broker.dealer.address.form.city"/></th>												
						<th align="left" class="td-header"><fmt:message key="broker.dealer.address.form.state"/></th>												
						<th align="left" class="td-header"><fmt:message key="broker.dealer.address.form.zipPostal"/></th>												
						<th align="left" class="td-header"><fmt:message key="broker.dealer.form.phone"/></th>
						<th align="left" class="td-header"><fmt:message key="broker.dealer.form.fax"/></th>
					</tr> 
					</thead> 
					<tbody> 

					<c:set var="display" value="true" />			
					<c:forEach var="address" items="${dealer.addressesNonEmpty}" varStatus="status">										
					<tr> 	
						<c:if test="${display=='true'}">
							<td class="td-plain_">
								<c:forEach var="case" items="${dealer.cases}">
									${case},
								</c:forEach>
							</td>
						</c:if>
						<c:if test="${display!='true'}">						
							<td class="td-plain_">&nbsp;</td> 													
						</c:if>						
						<td class="td-plain_">${dealer.brokerDealerName}</td> 							
						<td class="td-plain_">${address.address1}</td>
						<td class="td-plain_">${address.address2}</td>
						<td class="td-plain_">${address.city}</td>
						<td class="td-plain_">${address.state}</td>
						<td class="td-plain_">${address.zipPostal}</td>
						<td class="td-plain_">${dealer.phone}</td>
						<td class="td-plain_">${dealer.fax}</td>							
						
						<c:set var="display" value="false" />																
					<tr> 
					</c:forEach>							
					</tbody> 
				</table> 		
			</div>
			<br>
			<br>			
			<table width="100%">
			<tr>
				<td width="25%" class="td-plain" align="right"><fmt:message key="broker.dealer.form.notes"/>:</td>
				<td class="td-plain_" colspan="8"><form:textarea path="dealer.notes" rows="5" cols="85" disabled="true" style="width:100%"/></td>
			</tr>
			</table>			
			
			<div style="width:100%;padding: 1.5ex 1ex 1ex 1.5mm;">			
				<div class="full_width big">
				    <c:if test="${user.department=='Compliance'}">													
				         <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
					    <c:url var="createUrl" value="/type/contacts_select.html">
						<c:param name="type" value="D" />
						<c:param name="flow_id" value="${dealer.brokerDealerid}" />
						<c:param name="flow_desc" value="${dealer.brokerDealerName}" />										
					    </c:url>
					    <a href='<c:out value="${createUrl}"/>'><img src="/contacts/images/add.png" width="20" border="0" title="Click to add contact"><fmt:message key="button.create.contact"/></a> 					    
				         </sec:authorize>
				    </c:if>    
				</div>
			</div>									
		
		
			<c:forEach var="contact" items="${dealer.contacts}" varStatus="status">					

			    <c:set var="personFormId" value="contact${status.index}"/>							 

			    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
				<c:url var="deleteUrl" value="/dealer_person/delete.html"/>			    
				<form id="${personFormId}" action="${deleteUrl}" method="POST">
				  <input id="id" name="id" type="hidden" value="${contact.contactid}"/>
		  		  <input id="flow_id" name="flow_id" type="hidden" value="${dealer.brokerDealerid}"/>		  				  		  		  
		  		  <input id="type" name="type" type="hidden" value="D"/>		  				  
				</form>
			    </sec:authorize>

			</c:forEach>			

		
		
				<div style="width:100%;padding: 12px;">
					<table cellpadding="0" cellspacing="0" border="0" class="display" id="contactsBDT" width="100%">
						<thead> 
						<tr> 
							<th align="left">&nbsp;</th>													
							<th align="left"><fmt:message key="broker.dealer.contact.name"/></th>				
							<th align="left"><fmt:message key="broker.dealer.contact.typeofcontact"/></th>
							<th align="left"><fmt:message key="broker.dealer.contact.policies"/></th>
							<th align="left">&nbsp;</th>							
						</tr> 
						</thead> 
						<tbody> 
							<c:forEach var="contact" items="${dealer.contacts}" varStatus="status">					
							    
							    <c:set var="personFormId" value="contact${status.index}"/>							 
							    
							    <c:url var="editUrl" value="/person.html">
							        <c:param name="id" value="${contact.contactid}" />
							        <c:param name="type" value="D" />							    							        
								<c:param name="flow_id" value="${dealer.brokerDealerid}" />							        
							    </c:url>
						
							    <tr>
								<td>
									<input type="button" value="Preview" class="greyButton" onClick='javascript:viewPerson("${contact.contactid}")'/>						
								</td>								
								<td style="line-height:17px">
									<a href='<c:out value="${editUrl}"/>' title="Click to edit person"><b>${contact.contactName}</b></a><br>${contact.dealerName}
								</td> 							
								<td>${contact.contactType}</td> 							
								<td>${contact.casecodes}</td> 							
								<td>
								<c:if test="${user.department=='Compliance'}">			
								    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
									<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${personFormId}'], 'DEALER_CONTACT');"/>								    
								    </sec:authorize> 
								</c:if>
								</td>																
							    </tr>
							</c:forEach>
						</tbody> 
					</table> 			
				</div>			
			<br>
		
</div>			
<div id="resultPane" title="Contact Preview"></div>

