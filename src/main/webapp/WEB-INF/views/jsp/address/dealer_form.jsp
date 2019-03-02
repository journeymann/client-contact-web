<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="address.form.title"/>&nbsp;&nbsp;for&nbsp;&nbsp;<c:out value='${aflow_desc}'/></h1>
<div align="right" style="padding-right:7px"><a href="javascript:window.history.go(-1)"><fmt:message key="button.back"/></a></div>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="address">
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

<form:form modelAttribute="address">
    <form:hidden path="id" />

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
    		<td class="td-plain" align="right"><fmt:message key="address.form.primary"/>:</td>
    		<td class="td-plain_"><form:select path="mainaddress" items="${address.yesnoMap}"></form:select></td>
    		<td class="td-plain_"></td>
    		<td class="td-plain_"></td>
    		<td class="td-plain_"></td>
    		<td class="td-plain_"></td>
		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="address.form.address1"/>:</td>
    		<td class="td-plain_"><form:input path="address1" /></td>
    		<td class="td-plain" align="right"><fmt:message key="address.form.address2"/>:</td>
    		<td class="td-plain_"><form:input path="address2" /></td>
    		<td class="td-plain" align="right"><fmt:message key="address.form.city"/>:</td>
    		<td class="td-plain_"><form:input path="city" /></td>
    		</tr>
    		<tr>
    		<td class="td-plain" align="right"><fmt:message key="address.form.state"/>:</td>
    		<td class="td-plain_"><form:select path="state" items="${selectionLists.states}" itemValue="code" itemLabel="name"></form:select></td>
    		<td class="td-plain" align="right"><fmt:message key="address.form.zipPostal"/>:</td>
    		<td class="td-plain_"><form:input path="zipPostal" /></td>
    		<td class="td-plain" align="right"><fmt:message key="address.form.country"/>:</td>
    		<td class="td-plain_"><form:select path="country" items="${selectionLists.countries}" itemValue="key" itemLabel="value"></form:select></td>
    		</tr>
    </table>
        
    <h2 style="height:30px">
            <div class="button" align="center">
		<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">            
                	<input type="submit" id="save" class="greyButton" name="_eventId_save" value="<fmt:message key="button.save"/>" 
                    		onclick="Spring.remoting.submitForm('save', 'dealer', {fragments:'content'}); return false;"/>&#160;
                </sec:authorize> 
                <input type="submit" name="_eventId_cancel" class="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;   
            </div>    
        </h2>
</form:form>