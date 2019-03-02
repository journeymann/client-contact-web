<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="contact.form.title"/></h1>
<c:choose>
	<c:when test="${type=='P'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/policy/detail.html?id=${flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${type=='C'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/compliance/detail.html?id=${flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${type=='B'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/broker/detail.html?id=${flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
	<c:when test="${type=='D'}">
		<div align="right" style="padding-right:7px"><a href='<c:url value="/dealer/detail.html?id=${flow_id}"/>'><fmt:message key="button.back"/></a></div>
	</c:when>
</c:choose>	

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="contact">
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

<form:form modelAttribute="contact">
    <form:hidden path="id" />
  	
  	<c:set var="ptype" value="${type}" scope="session"/>
  	<c:set var="pflow_id" value="${flow_id}" scope="session"/>  	
  	
    	<c:if test="${user.department=='CPO'}">
    		<c:set var="disable_compliance" value="${1 == 1}" />
    	</c:if>

    	<c:if test="${user.department=='Compliance'}">
    		<c:set var="disable_policy" value="${1 == 1}" />
    	</c:if>
    	
       <table cellpadding="0" cellspacing="0" width="100%">
    		<tr>
    			<td width="10%"></td>
    			<td width="13%"></td>
    			<td width="8%"></td>
    			<td width="13%"></td>
    			<td width="8%"></td>
    			<td width="13%"></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="contact.person.form.title"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="person.title" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="contact.person.form.firstName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="person.firstName" disabled="true"/></td>
    			<td align="right" class="td-plain"><fmt:message key="contact.person.form.lastName"/>:</td>
    			<td align="left" class="td-plain_"><form:input path="person.lastName" disabled="true"/></td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.broker"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="brokercompanyid" items="${selectionLists.brokers}" itemValue="key" itemLabel="value" disabled="true" ><option value="">Select</option></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.dealer"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="brokerdealerid" items="${selectionLists.dealers}" itemValue="key" itemLabel="value" disabled="true"><option value="">Select</option></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.policy"/>:</td>
    			<td align="left" class="td-plain_">
				<form:select path="caseid" items="${selectionLists.policys}" itemValue="key" itemLabel="value" disabled="true"><option value="">Select</option></form:select>
    			</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.contacttype"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="contacttype" items="${selectionLists.contacttypes}" itemValue="type" itemLabel="type"><option value="">Select</option></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.servicingbroker"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="servicingbroker" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.commissionablebroker"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="commissionablebroker" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
    		</tr>
    		<tr>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.brokerrecord"/>:</td>
    			<td align="left" class="td-plain_" colspan="5">
    				<form:select path="brokerrecord" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
    		</tr>
    	
    	
    <c:if test="${type=='P'}">
		<tr>
			<td align="right" class="td-plain"><fmt:message key="contact.form.primaryservicecontact"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="primaryservicecontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}" ><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.ownercontact"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="ownercontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}" ><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.keyclientcontact"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="keyclientcontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
		<tr>
				<td align="right" class="td-plain"><fmt:message key="contact.form.authorizedsignatory"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="authorizedsignatory" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.reportalaccess"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="reportalaccess" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.clientreport"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="clientreport" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
		<tr>
				<td align="right" class="td-plain"><fmt:message key="contact.form.fundlevelreport"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="fundlevelreport" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.newsletter"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="newsletter" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.SSsweep"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="SSsweep" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
		<tr>
				<td align="right" class="td-plain"><fmt:message key="contact.form.deathclaimnotification"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="deathclaimnotification" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.customdeliverable"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="customdeliverable" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.tls"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="tls" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
       
       

    </c:if>
    <c:if test="${type=='C'}">
		<tr>
				<td align="right" class="td-plain"><fmt:message key="contact.form.primarycompliancecontact"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="primarycompliancecontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.primaryservicecontact"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="primaryservicecontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.authorizedsignatory"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="authorizedsignatory" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
		<tr>
				<td align="right" class="td-plain"><fmt:message key="contact.form.ownercontact"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="ownercontact" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.reportalaccess"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="reportalaccess" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_policy}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.electronicreport"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="electronicreport" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
		<tr>
			<td align="right" class="td-plain"><fmt:message key="contact.form.ppmamendment"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="ppmamendment" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.annualsemireport"/>:</td>
    			<td align="left" class="td-plain_">
					<form:select path="annualsemireport" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
    			<td align="right" class="td-plain"><fmt:message key="contact.form.fundprospectussupplement"/>:</td>
    			<td align="left" class="td-plain_">
    				<form:select path="fundprospectussupplement" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
		</tr>
       
       <tr>
       			<td align="right" class="td-plain"><fmt:message key="contact.form.privacynotice"/>:</td>
    			<td align="left" class="td-plain_" colspan="5">
    				<form:select path="privacynotice" items="${selectionLists.yesno}" itemValue="key" itemLabel="value" disabled="${disable_compliance}"><form:option value="Select one"/></form:select>
    			</td>
       </tr> 
       
       

    </c:if>
    
</table>

       <h2 style="height:30px">
            <div class="button" align="center">
		<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">                        
			<input type="submit" id="save" class="greyButton" name="_eventId_save" value="<fmt:message key="button.save"/>" 
			    onclick="Spring.remoting.submitForm('save', 'contact', {fragments:'content'}); return false;"/>&#160;
              	</sec:authorize>                           
                <input type="submit" name="_eventId_cancel" class="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;     
            </div>    
        
        </h2>
    
</form:form> 

