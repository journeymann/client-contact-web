<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="broker.form.title"/></h1>
<div align="right" style="padding-right:7px"><a href="javascript:window.history.go(-1)"><fmt:message key="button.back"/></a></div>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="broker">
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

<form:form modelAttribute="broker">
    <form:hidden path="id" />

    <fieldset>
   
	<c:if test="${user.department!='Compliance'}">
		<c:set var="disable_field" value="${1 == 1}" />
	</c:if>
  	<c:set var="aflow_desc" value="${broker.brokerCompany}" scope="session"/>  	  			
    
       <table cellspacing="0" cellpadding="0" border="0">
     		<tr>
    		<td width="8%"></td>
    		<td width="10%"></td>
    		<td width="8%"></td>
    		<td width="10%"></td>
    		<td width="5%"></td>
    		<td width="10%"></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.brokerCompany"/>:</td>
    		<td class="td-plain_"><form:input path="brokerCompany" disabled="${disable_field}" /></td>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.primaryPhone"/>:</td>
    		<td class="td-plain_"><form:input path="primaryPhone" disabled="${disable_field}"/></td>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.primaryFax"/>:</td>
    		<td class="td-plain_"><form:input path="primaryFax" disabled="${disable_field}"/></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.secondaryPhone"/>:</td>
    		<td class="td-plain_"><form:input path="secondaryPhone" disabled="${disable_field}"/></td>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.secondaryFax"/>:</td>
    		<td class="td-plain_"><form:input path="secondaryFax" disabled="${disable_field}"/></td>
    		<td class="td-plain"  align="right" colspan="2">&nbsp;</td>    		
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="broker.form.notes"/>:</td>
    		<td class="td-plain_" colspan="5"><form:textarea path="notes" rows="5" cols="45" /></td>
    		
    	 </table>
    	 
       <h2 style="height:30px">
        
            <div class="button" align="center">
            	<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">            
			<input type="submit" id="save" class="greyButton" name="_eventId_save" value="<fmt:message key="button.save"/>" 
			    onclick="Spring.remoting.submitForm('save', 'broker', {fragments:'content'}); return false;"/>&#160;
            	</sec:authorize>   
                <input type="submit" name="_eventId_cancel" class="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;     
		<c:if test="${broker.brokerid > 0 && user.department=='Compliance'}">
			<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">            		
                  		<input type="button" name="create" class="greyButton" onclick="javascript:window.location.href='<c:url value="/broker.html" />'" value="<fmt:message key="button.create"/>"/>&#160;
            		</sec:authorize>                     		
              	</c:if>
                
            </div>    
        </h2>
    	 
    	 
</form:form>

<c:if test="${broker.brokerid > 0}">
    <c:if test="${user.department=='Compliance'}">
	    <div style="clear:both;">
		<a href="${flowExecutionUrl}&_eventId=addAddress" ><img src="/contacts/images/add.png" width="20" border="0" title="Click to add address"><fmt:message key="address.form.button.add"/></a>
	    </div>
    </c:if>    
    <div>&nbsp;</div>
    
    <c:if test="${empty broker.addresses}">
        <div>&nbsp;</div>
    </c:if>
    
    <c:if test="${not empty broker.addresses}">
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <th class="td-header" align="left" ><fmt:message key="address.form.primary"/></th>
                <th class="td-header" align="left" ><fmt:message key="address.form.address1"/></th>                
                <th class="td-header" align="left"><fmt:message key="address.form.address2"/></th>                
                <th class="td-header" align="left"><fmt:message key="address.form.city"/></th>
                <th class="td-header" align="left"><fmt:message key="address.form.state"/></th>
                <th class="td-header" align="left"><fmt:message key="address.form.zipPostal"/></th>
                <th class="td-header" align="left"><fmt:message key="address.form.country"/></th>
                <th class="td-header" align="center">Action</th>
            </tr>
        <c:forEach var="address" items="${broker.addresses}">
            <tr>
                <td class="td-plain_">${address.mainaddress}</td>
                <td class="td-plain_">${address.address1}</td>                
                <td class="td-plain_">${address.address2}</td>                
                <td class="td-plain_">${address.city}</td>
                <td class="td-plain_">${address.state}</td>
                <td class="td-plain_">${address.zipPostal}</td>
                <td class="td-plain_">${address.country}</td> 
                <td class="td-plain_" align="center">
                    &nbsp;
		<c:if test="${user.department=='Compliance'}">
		    <input type="button" value="Edit" class="greyButton" onClick='location.href="${flowExecutionUrl}&_eventId=editAddress&addressId=${address.id}"' title="Click to edit address"/> 	
		    <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
			<input type="button" value="Delete" class="greyButton" onClick="javascript:delete_address('${flowExecutionUrl}','${address.id}');" title="Click to delete address" />
		    </sec:authorize>
		</c:if>                                        
                </td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
</c:if>
