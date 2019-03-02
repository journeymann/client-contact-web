<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="broker.search.title"/></h1>

<table class="search">
    <tr>
	<th><fmt:message key="broker.form.brokerCompany"/></th>
	<th><fmt:message key="broker.form.primaryPhone"/></th>
	<th><fmt:message key="broker.form.primaryFax"/></th>
	<th><fmt:message key="broker.form.secondaryPhone"/></th>
	<th><fmt:message key="broker.form.secondaryFax"/></th>
	<th><fmt:message key="broker.form.status"/></th>						
	<th><fmt:message key="broker.form.updatedBy"/></th>						
	<th><fmt:message key="broker.form.updateDate"/></th>
    </tr>
<c:forEach var="broker" items="${brokers}" varStatus="status">
    <tr>
        <c:set var="brokerFormId" value="broker${status.index}"/>

        <c:url var="editUrl" value="/broker.html">
            <c:param name="id" value="${broker.brokerId}" />
        </c:url>
        
        <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
            <c:url var="deleteUrl" value="/broker/delete.html"/>
            <form id="${brokerFormId}" action="${deleteUrl}" method="POST">
                <input id="id" name="id" type="hidden" value="${broker.brokerId}"/>
            </form>
        </sec:authorize>

	<td><a href='<c:out value="${editUrl}"/>'>${broker.brokerCompany}</a></td> 							
	<td>${broker.primaryPhone}</td>
	<td>${broker.primaryFax}</td>
	<td>${broker.secondaryPhone}</td>
	<td>${broker.secondaryFax}</td>
	<td>${broker.status}</td>
	<td>${broker.updatedBy}</td>							
	<td>${broker.updateDate}</td>														
    	<td>
            <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
            <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
                <a href="javascript:delete_item(document.forms['${brokerFormId}']);"><fmt:message key="button.delete"/></a> 
            </sec:authorize>
        </td>
    </tr>
</c:forEach>
</table>
