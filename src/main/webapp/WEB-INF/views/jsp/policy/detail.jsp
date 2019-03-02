<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_page.css"/>" />			
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_table.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.qtip.css"/>" />
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery.FixedColumns.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.qtip.js" />"></script>			


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

			var oTablePolicy = $('#fixedColumnPolicy').dataTable( {
					"sScrollX": "100%",
					"sScrollY": "400px",										
					"bScrollCollapse": true,					
					"bPaginate": false,
					"bAutoWidth": false,					
					
					"aoColumnDefs": [ 
					     { "bSearchable": true, "aTargets": [ 0,1 ] },					     					  
					     { "bSortable":false, "aTargets":[0,19]}
					     ],
				     	"fnInitComplete": function () {    
					      new FixedColumns( this, {
						     "bOldIE":true,
						     "iLeftColumns":2,
						     "sLeftWidth":"relative",						     
						     "iLeftWidth": 45
					     });    
					}
			} );

			$( '.field_def' ).each( function() {
				var key = $( this ).attr( 'field_key' );

				// create the tooltip for the current element
				$(this).qtip({
				   content: {
					text: 'Loading..',
					ajax:{
				          url: 'desc.html',
				          data: { id: key },
					    loading: false,	
				          type: 'get'
					}
				    },
				    style: { width:200 }, 
				    position : {
					my : 'bottom right',
					at : 'top left',
					adjust : {
					  screen : true
					}
				    },
				    show: 'mouseover',	
				    hide: {
					    fixed: true // Make it fixed so it can be hovered over
				    }				    
				});	
			 } );			

		});
	</script>


</head>


<h1><fmt:message key="policy.form.title"/></h1>
<div align="left" style="padding-left:10px">
<a href='<c:url value="/type/policy.html"/>'>Back to Policy List</a>
</div>
<div id="container">
	<div id="messages">
	    <c:if test="${not empty statusMessageKey}">
	       <p><fmt:message key="${statusMessageKey}"/></p>
	    </c:if>

	    <spring:hasBindErrors name="policy">
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

	<div class="spacer"></div>			    
	<br>
	<div style="width:98%;padding-right:10px;padding-left:15px;">
		<table cellpadding="0" cellspacing="0" border="0" >
			<thead> 
			<tr> 
				<th align="left" class="td-header" width="2%">&nbsp;</th>
				<th width="5%" align="left" class="td-header"><fmt:message key="policy.form.case"/></th>
				<th width="18%" align="left" class="td-header"><fmt:message key="policy.form.formalOwnerName"/></th>
				<th width="8%" align="left" class="td-header"><fmt:message key="policy.form.informalOwnerName"/></th>
				<th width="8%" align="left" class="td-header"><fmt:message key="policy.form.clientGrouping"/></th>
				<th width="5%" align="left" class="td-header"><fmt:message key="policy.form.library"/></th>
				<th width="5%" align="left" class="td-header"><fmt:message key="policy.form.series"/></th>						
				<th width="5%" align="left" class="td-header"><fmt:message key="policy.form.team"/></th>						
				<th width="9%" align="left" class="td-header"><fmt:message key="policy.form.caseManager"/></th>												

			</tr> 
			</thead> 
			<tbody> 
			<tr> 				
				<td align="left" class="td-plain_">&nbsp;</td>
				<td align="left" class="td-plain_">${policy.casecode}</td> 							
				<td align="left" class="td-plain_">${policy.formalOwnerName}</td>
				<td align="left" class="td-plain_">${policy.informalOwnerName}</td>
				<td align="left" class="td-plain_">${policy.clientGroupCode}</td>
				<td align="left" class="td-plain_">${policy.library}</td>
				<td align="left" class="td-plain_">${policy.series}</td>
				<td align="left" class="td-plain_">${policy.team}</td>
				<td align="left" class="td-plain_">${policy.caseManager}</td>							
			<tr> 
			</tbody> 
		</table> 			
	</div>


	<div class="spacer"></div>			
	<br>
	<div style="width:47%;float:left;padding-left:15px;">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead> 
			<tr> 
				<th align="left" class="td-header"><fmt:message key="broker.form.broker"/></th>
			</tr> 
			</thead> 
			<tbody> 
				<c:forEach var="broker" items="${policy.brokers}" varStatus="status">

				    <tr>
					<td  align="left" class="td-plain_">${broker.brokerCompany}</td> 							
				    </tr>
				</c:forEach>
			</tbody> 
		</table> 			
	</div>			
	<div style="width:46%;float:right;padding-right: 19px;">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead> 
			<tr> 
				<th align="left" class="td-header"><fmt:message key="broker.dealer.form.brokerDealerName"/></th>
			</tr> 
			</thead> 
			<tbody> 
				<c:forEach var="dealer" items="${policy.brokerDealers}" varStatus="status">
				    <tr>
					<td align="left" class="td-plain_">${dealer.brokerDealerName}</td> 							
				    </tr>
				</c:forEach>
			</tbody> 
		</table> 			
	</div>			
	<br><br><br><br>
	<div style="float:left;width:90%;padding-left:15px">				
		<div>
		    <label for="notes"><fmt:message key="policy.form.notes"/>:<br></label>
		    <span class="input"><form:textarea path="policy.notes" disabled="true" rows="5" cols="66" /></span>
		</div>
		<!--<div style="width:300;padding: 1.5ex 1ex 1ex 1.5mm;">
		    <a href="<c:url value="/misc/desc_all.html"/>"><fmt:message key="button.view.definition"/></a> 					    
		</div>-->
	</div>	
	<br>

	<div style="float:left; width:300;padding: 1.5ex 1ex 1ex 1.5mm;">			
		<div class="full_width big">
		    <c:if test="${user.department=='CPO'}">			
		        <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
			    <c:url var="createUrl" value="/type/contacts_select.html">
				<c:param name="type" value="P" />
				<c:param name="flow_id" value="${policy.caseid}" />
				<c:param name="flow_desc" value="${policy.casecode} - ${policy.formalOwnerName}" />				
			    </c:url>
			    <a href='<c:out value="${createUrl}"/>'><img src="/contacts/images/add.png" width="20" border="0" title="Click to add contact"><fmt:message key="button.create.contact"/></a> 					    
		        </sec:authorize>
		    </c:if>    
		</div>
	</div>									

	<c:forEach var="contact" items="${policy.contactCases}" varStatus="status">					

	    <c:set var="personFormId" value="contact${status.index}"/>							 

	    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
		<c:url var="deleteUrl" value="/contact/delete.html"/>			    
		<form id="${personFormId}" action="${deleteUrl}" method="POST">
		  <input id="id" name="id" type="hidden" value="${contact.contactcaseid}"/>
		  <input id="flow_id" name="flow_id" type="hidden" value="${policy.caseid}"/>
		  <input id="type" name="type" type="hidden" value="P"/>		  
		</form>
	    </sec:authorize>

	</c:forEach>			

	<br>
	<div id="data" style="padding:8px;width:99%;float:left">						

			<table id="fixedColumnPolicy" cellpadding="0" cellspacing="0" border="0" >
				<thead> 
				<tr> 
					<th align="center" style="width:20px">&nbsp;</th>
					<th align="center" nowrap class="field_def" field_key="22" style="cursor:pointer"><fmt:message key="policy.contact.name"/></th>				
					<th align="center" nowrap class="field_def" field_key="6" style="cursor:pointer"><fmt:message key="policy.contact.typeofcontact"/></th>
					<th align="center" nowrap class="field_def" field_key="20" style="cursor:pointer"><fmt:message key="policy.contact.servicingbroker"/></th>
					<th align="center" nowrap class="field_def" field_key="5" style="cursor:pointer"><fmt:message key="policy.contact.commissionablebroker"/></th>					
					<th align="center" nowrap class="field_def" field_key="3" style="cursor:pointer"><fmt:message key="policy.contact.brokerofrecord"/></th>					
					<th align="center" nowrap class="field_def" field_key="17" style="cursor:pointer"><fmt:message key="policy.contact.primaryservicecontact"/></th>					
					<th align="center" nowrap class="field_def" field_key="16" style="cursor:pointer"><fmt:message key="policy.contact.primarycompliancecontact"/></th>							
					<th align="center" nowrap class="field_def" field_key="14" style="cursor:pointer"><fmt:message key="policy.contact.ownercontact"/></th>
					<th align="center" nowrap class="field_def" field_key="12" style="cursor:pointer"><fmt:message key="policy.contact.keyclientcontact"/></th>
					<th align="center" nowrap class="field_def" field_key="2" style="cursor:pointer"><fmt:message key="policy.contact.authorizedsignatory"/></th>							
					<th align="center" nowrap class="field_def" field_key="19" style="cursor:pointer"><fmt:message key="policy.contact.reportalaccess"/></th>
					<th align="center" nowrap class="field_def" field_key="4" style="cursor:pointer"><fmt:message key="policy.contact.clientreport"/></th>
					<th align="center" nowrap class="field_def" field_key="10" style="cursor:pointer"><fmt:message key="policy.contact.fundlevelreport"/></th>
					<th align="center" nowrap class="field_def" field_key="13" style="cursor:pointer"><fmt:message key="policy.contact.newsletter"/></th>
					<th align="center" nowrap class="field_def" field_key="21" style="cursor:pointer"><fmt:message key="policy.contact.SSsweep"/></th>
					<th align="center" nowrap class="field_def" field_key="8" style="cursor:pointer"><fmt:message key="policy.contact.deathclaimnotification"/></th>
					<th align="center" nowrap class="field_def" field_key="7" style="cursor:pointer"><fmt:message key="policy.contact.customdeliverable"/></th>
					<th align="center" nowrap class="field_def" field_key="23" style="cursor:pointer"><fmt:message key="policy.contact.tls"/></th>							
					<th align="center" nowrap width="30px">&nbsp;</th>		
				</tr> 
				</thead> 
				<tbody> 
					<c:forEach var="contact" items="${policy.contactCases}" varStatus="status">					

					    <c:set var="personFormId" value="contact${status.index}"/>							 

					    <c:url var="editUrl" value="/contact.html">
						<c:param name="id" value="${contact.contactcaseid}" />
						<c:param name="type" value="P" />
						<c:param name="flow_id" value="${policy.caseid}" />												
					    </c:url>

					    <tr height="60px">
						<td align="center" style="padding:6px">
							<input type="button" value="Preview" class="greyButton" onClick='javascript:viewPerson("${contact.contactid}")'/>						
						</td>								
						<td align="center" style="padding:6px;">
							<c:choose>
							    <c:when test="${user.department=='CPO'}">			
								    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">								
									<b><a href='<c:out value="${editUrl}"/>' title="Click to edit contact">${contact.person.lastName},&nbsp;${contact.person.firstName}</b></a><br><font size="1">${contact.person.company}</font>
								    </sec:authorize> 
							    </c:when>	    
							    <c:otherwise>
								<b>${contact.person.lastName},&nbsp;${contact.person.firstName}</b><br><font size="1">${contact.person.company}</font> 							
							    </c:otherwise>
							</c:choose>						    
						</td>														
						<td align="center" style="padding:6px;">${contact.contacttype}</td> 							
						<td align="center" style="padding:6px;">${contact.servicingbroker}</td> 							
						<td align="center" style="padding:6px;">${contact.commissionablebroker}</td> 														
						<td align="center" style="padding:6px;">${contact.brokerrecord}</td> 							
						<td align="center" style="padding:6px;">${contact.primaryservicecontact}</td> 
						<td align="center" style="padding:6px;">${contact.primarycompliancecontact}</td> 															
						<td align="center" style="padding:6px;">${contact.ownercontact}</td>
						<td align="center" style="padding:6px;">${contact.keyclientcontact}</td> 
						<td align="center" style="padding:6px;">${contact.authorizedsignatory}</td> 															
						<td align="center" style="padding:6px;">${contact.reportalaccess}</td> 							
						<td align="center" style="padding:6px;">${contact.clientreport}</td> 							
						<td align="center" style="padding:6px;">${contact.fundlevelreport}</td> 							
						<td align="center" style="padding:6px;">${contact.newsletter}</td> 							
						<td align="center" style="padding:6px;">${contact.SSsweep}</td> 							
						<td align="center" style="padding:6px;">${contact.deathclaimnotification}</td> 							
						<td align="center" style="padding:6px;">${contact.customdeliverable}</td> 															
						<td align="center" style="padding:6px;">${contact.tls}</td> 							
						<td align="center" style="padding:6px;">
						<c:if test="${user.department=='CPO'}">			
						    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
							    <input type="button" value="Delete" class="greyButton" onClick="javascript:delete_item_custom(document.forms['${personFormId}'], 'POLICY_CONTACT');"/>
						    </sec:authorize> 
						</c:if>
						</td>								
					    </tr>
					</c:forEach>
				</tbody> 
			</table> 			

	</div>			
	<div class="spacer"></div>			
</div>			
<div id="resultPane" title="Contact Preview"></div>

