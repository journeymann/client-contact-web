<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="policy.form.title"/></h1><div align="right" style="padding-right:7px"><a href="javascript:window.history.go(-1)"><fmt:message key="button.back"/></a></div>

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

<form:form modelAttribute="policy">
    <form:hidden path="id" />


	<c:if test="${user.department!='CPO'}">
		<c:set var="disable_field" value="${1 == 1}" />
	</c:if>
	<c:if test="${user.department!='Compliance'}">
		<c:set var="disable_comp_field" value="${1 == 1}" />
	</c:if>

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
    		<td class="td-plain" align="right"><fmt:message key="policy.form.case"/>:</td>
    		<td class="td-plain_"><form:input path="casecode" disabled="${disable_field}"/></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.formalOwnerName"/>:</td>
    		<td class="td-plain_"><form:input path="formalOwnerName" disabled="${disable_field}"/></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.informalOwnerName"/>:</td>
    		<td class="td-plain_"><form:input path="informalOwnerName" disabled="${disable_field}"/></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.clientGrouping"/>:</td>
    		<td class="td-plain_"><form:select path="clientGroupCode" disabled="${disable_field}" items="${selectionLists.client_groups}" itemValue="key" itemLabel="value"></form:select></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.library"/>:</td>
    		<td class="td-plain_"><form:select path="library" disabled="${disable_field}" items="${selectionLists.system}" itemValue="key" itemLabel="value"></form:select></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.series"/>:</td>
    		<td class="td-plain_"><form:select path="series" disabled="${disable_field}" items="${selectionLists.series}" itemValue="key" itemLabel="value"></form:select></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.team"/>:</td>
    		<td class="td-plain_"><form:select path="team" disabled="${disable_field}" items="${selectionLists.teams}" itemValue="key" itemLabel="value"></form:select></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.ppm"/>:</td>
    		<td class="td-plain_"><form:input path="ppm" disabled="${disable_comp_field}" /></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.status"/>:</td>
    		<td class="td-plain_"><form:input path="status" disabled="${disable_field}" /></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.caseManager"/>:</td>
    		<td class="td-plain_"><form:select path="caseManager" disabled="${disable_field}" items="${selectionLists.casemanagers}" itemValue="key" itemLabel="value"></form:select></td>
    		<td class="td-plain" align="right"><fmt:message key="policy.form.notes"/>:</td>
    		<td class="td-plain_" colspan="3"><form:textarea path="notes" rows="5" cols="45" /></td>
    		
    		</tr>
     </table>
    <fieldset>
       
       <h2 style="height:30px">         
            <div class="button" align="center">
            	<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">            
			<input type="submit" id="save" class="greyButton" name="_eventId_save" value="<fmt:message key="button.save"/>" 
			    onclick="Spring.remoting.submitForm('save', 'policy', {fragments:'content'}); return false;"/>&#160;
            	</sec:authorize>   
                <input type="submit" name="_eventId_cancel"  class="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;     
            </div>    
        
        </h2>
    </fieldset>
</form:form>

