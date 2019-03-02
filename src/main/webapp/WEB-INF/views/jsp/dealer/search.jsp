<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="broker.search.title"/></h1>

<table class="search">
    <tr>
        <th><fmt:message key="broker.form.brokerCompany"/></th>
    </tr>
<c:forEach var="broker" items="${brokers}" varStatus="status">
    <tr>
        <c:set var="brokerFormId" value="broker${status.index}"/>

        <c:url var="editUrl" value="/broker.html">
            <c:param name="id" value="${broker.id}" />
        </c:url>
        
        <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
            <c:url var="deleteUrl" value="/broker/delete.html"/>
            <form id="${dealerFormId}" action="${deleteUrl}" method="POST">
                <input id="id" name="id" type="hidden" value="${broker.id}"/>
            </form>
        </sec:authorize>

	<td>${broker.firstName}</td>

    	<td>
            <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
            <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
                <a href="javascript:delete_item(document.forms['${dealerFormId}']);"><fmt:message key="button.delete"/></a> 
            </sec:authorize>
        </td>
    </tr>
</c:forEach>
</table>
