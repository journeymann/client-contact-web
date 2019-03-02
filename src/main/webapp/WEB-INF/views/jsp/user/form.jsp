<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="user.form.title"/></h1><div align="right" style="padding-right:7px"><a href="javascript:window.history.go(-1)"><fmt:message key="button.back"/></a></div>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="user">
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

<form:form modelAttribute="user">
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
    		<td class="td-plain" align="right"><fmt:message key="user.form.username"/>:</td>
    		<td class="td-plain_"><form:input path="username" /></td>
    		<td class="td-plain" align="right"><fmt:message key="user.form.fullname"/>:</td>
    		<td class="td-plain_"><form:input path="fullname" /></td>
    		<td class="td-plain" align="right"><fmt:message key="user.form.password"/>:</td>
    		<td class="td-plain_"><form:password path="password" /></td>
    		</tr>
    		<tr>
	    		<td class="td-plain" align="right"><fmt:message key="user.form.department"/>:</td>
	    		<td class="td-plain_"><form:input path="department" /></td>
	    		<td class="td-plain" align="right"><fmt:message key="user.form.email"/>:</td>
	    		<td class="td-plain_"><form:input path="email" style="width:100%"/></td>
	    		<td class="td-plain" align="right">&nbsp;</td>
	    		<td class="td-plain_">&nbsp;</td>
    		</tr>
    		</table>
    </div>
      
      	<h2 style="height:30px">
            <div class="button" align="center">
                <input type="submit" id="save" style="greyButton" name="_eventId_save" value="<fmt:message key="button.save"/>" 
                    onclick="Spring.remoting.submitForm('save', 'user', {fragments:'content'}); return false;"/>&#160;
                <input type="submit" name="_eventId_cancel" style="greyButton" value="<fmt:message key="button.cancel"/>"/>&#160;     
            </div>    
        </h2>
</form:form>

