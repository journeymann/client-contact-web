<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<script type="text/javascript" charset="utf-8">
		
		function submit_form() {
		
			//var answer = confirm("First Name, Last Name Combination already exists. If you want to save this contact, then click on Ok, else click on Cancel");

			//if(answer){
				Spring.remoting.submitForm('continue', 'person', {fragments:'content'}); return false;			
			//}
			
		}
	
	</script>
</head>


<h1><fmt:message key="person.form.page.title"/></h1>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="person">
  	<c:set var="has_errors" value="true" />
        <!--<h2>Errors</h2>-->
        <div class="formerror">
            <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
            </ul>
        </div>
    </spring:hasBindErrors>
</div>

<form:form modelAttribute="person">
    <form:hidden path="id" />


  	<c:set var="ptype" value="${type}" scope="session"/>
  	<c:set var="pflow_id" value="${flow_id}" scope="session"/>  	
  	<c:set var="aflow_desc" value="${person.firstName} ${person.lastName}" scope="session"/>  	  	

	<c:if test="${user.department!='Compliance'}"> 
		<c:set var="disabled" value="${1 == 1}" />
	</c:if>


   <table cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td width="8%"></td>
    			<td width="25%"></td>
    			<td width="8%"></td>
    			<td width="25%"></td>
    			<td width="8%"></td>
    			<td width="25%"></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.title"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="title" size="5"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.firstName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="firstName" size="25"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.lastName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="lastName" size="25"/></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.jobtitle"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="jobtitle" size="100"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.company"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="company" size="100"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.email"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="email" size="60"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>

    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.primaryphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="primaryphone" /></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.secondaryphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="secondaryphone" /></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.cellphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="cellphone" /></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.primaryfax"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="primaryfax" /></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.secondaryfax"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="secondaryfax" /></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.notes"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:textarea path="notes" rows="5" cols="60" /></td>
    			
    		</tr>
    	</table>

        
        
        <h2 style="height:30px">         
        
            <div class="button" align="center">
              	<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN"> 
		    <c:if test="${has_errors=='true'}">
			  <input type="submit" id="continue" name="_eventId_continue" class="greyButton" value="<fmt:message key="button.continue"/>" 
			      onclick="javascript:submit_form();"/>&#160;
              	    </c:if>              	
		    <c:if test="${has_errors!='true'}">
			  <input type="submit" id="save" name="_eventId_save" class="greyButton" value="<fmt:message key="button.save"/>" 
			      onclick="Spring.remoting.submitForm('save', 'person', {fragments:'content'}); return false;"/>&#160;
              	    </c:if>              	
              	</sec:authorize>       
              	<input type="submit" name="_eventId_cancel" class="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;     
		<c:if test="${person.contactid > 0}">
			<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">            				
	                  <input type="button" name="create" class="greyButton" onclick="javascript:window.location.href='<c:url value="/person.html?type=X" />'" value="<fmt:message key="button.create"/>"/>&#160;
              		</sec:authorize>       	                  
              	</c:if>
            </div>    
        
        </h2>
</form:form>

<c:if test="${person.contactid > 0}">
    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
	    <div style="clear:both;">
		<a href="${flowExecutionUrl}&_eventId=addAddress" ><img src="/contacts/images/add.png" width="20" border="0" title="Click to add address"><fmt:message key="address.form.button.add"/></a>
	    </div>
    </sec:authorize>    
    
    <div>&nbsp;</div>
    
    <c:if test="${empty person.addresses}">
        <div>&nbsp;</div>
    </c:if>
    
    <c:if test="${not empty person.addresses}">
         <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <th align="left" class="td-header"><fmt:message key="address.form.primary"/></th>
                <th align="left" class="td-header"><fmt:message key="address.form.address1"/></th>                
                <th align="left" class="td-header"><fmt:message key="address.form.address2"/></th>                
                <th align="left" class="td-header"><fmt:message key="address.form.city"/></th>
                <th align="left" class="td-header"><fmt:message key="address.form.state"/></th>
                <th align="left" class="td-header"><fmt:message key="address.form.zipPostal"/></th>
                <th align="left" class="td-header"><fmt:message key="address.form.country"/></th>
                <th align="left" class="td-header">Action</th>
            </tr>
        <c:forEach var="address" items="${person.addresses}">
            <tr>
                <td class="td-plain_">${address.mainaddress}</td>
                <td class="td-plain_">${address.address1}</td>                
                <td class="td-plain_">${address.address2}</td>                                
                <td class="td-plain_">${address.city}</td>
                <td class="td-plain_">${address.state}</td>
                <td class="td-plain_">${address.zipPostal}</td>
                <td class="td-plain_">${address.country}</td> 
                <td class="td-plain_">
                    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
			<input type="button" value="Edit" class="greyButton" onClick='location.href="${flowExecutionUrl}&_eventId=editAddress&addressId=${address.id}"' title="Click to edit address"/> 	
			<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_address('${flowExecutionUrl}','${address.id}');" title="Click to delete address" />			
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
</c:if>
