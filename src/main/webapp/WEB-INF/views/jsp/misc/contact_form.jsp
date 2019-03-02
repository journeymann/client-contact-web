<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="person.form.page.title"/></h1>
<div align="right" style="padding-right:7px"></div>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="person">
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

<form:form modelAttribute="person">
    <form:hidden path="id" />

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
    			<td align="left" class="td-plain_"><form:input path="title" size="5" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.firstName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="firstName" size="25" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.lastName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="lastName" size="25" disabled="true"/></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.jobtitle"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="jobtitle" size="100" disabled="true"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.company"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="company" size="100" disabled="true"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.email"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:input path="email" size="60" disabled="true"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>

    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.primaryphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="primaryphone" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.secondaryphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="secondaryphone" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.cellphone"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="cellphone" disabled="true"/></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="person.form.primaryfax"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="primaryfax" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.secondaryfax"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="secondaryfax" disabled="true"/></td>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="left" class="td-plain_">&nbsp;</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain">&nbsp;</td>
    			<td align="right" class="td-plain"><fmt:message key="person.form.notes"/>:</td>
    			<td align="left" class="td-plain_" colspan="4"><form:textarea path="notes" rows="5" cols="60" disabled="true"/></td>
    			
    		</tr>
    	</table>

        
        
</form:form>

<c:if test="${person.contactid > 0}">
    
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
                <th align="left" class="td-header">&nbsp;</th>
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
                <td class="td-plain_">&nbsp;</td>
            </tr>
        </c:forEach>
        </table>
    </c:if>
</c:if>
