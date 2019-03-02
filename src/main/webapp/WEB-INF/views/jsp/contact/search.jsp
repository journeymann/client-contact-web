<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="person.search.title"/></h1>

<table class="search">
    <tr>
	<th><fmt:message key="person.form.title"/></th>						
        <th><fmt:message key="person.form.firstName"/></th>
        <th><fmt:message key="person.form.lastName"/></th>
	<th><fmt:message key="person.form.primaryphone"/></th>
	<th><fmt:message key="person.form.cellphone"/></th>
	<th><fmt:message key="person.form.primaryfax"/></th>
	<th><fmt:message key="person.form.jobtitle"/></th>						
        
    </tr>
<c:forEach var="person" items="${persons}" varStatus="status">
    <tr>
        <c:set var="personFormId" value="person${status.index}"/>

        <c:url var="editUrl" value="/person.html">
            <c:param name="id" value="${person.id}" />
        </c:url>
        
        <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
            <c:url var="deleteUrl" value="/person/delete.html"/>
            <form id="${personFormId}" action="${deleteUrl}" method="POST">
                <input id="id" name="id" type="hidden" value="${person.id}"/>
            </form>
        </sec:authorize>

	<td>${person.title}</td>
	<td>${person.firstName}</td>
	<td>${person.lastName}</td> 							
	<td>${person.primaryphone}</td>
	<td>${person.cellphone}</td>
	<td>${person.primaryfax}</td>	
	<td>${person.jobtitle}</td>

    	<td>
            <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
	        <a href='<c:out value="${editUrl}"/>'><fmt:message key="button.edit"/></a>
                <a href="javascript:delete_item(document.forms['${personFormId}']);"><fmt:message key="button.delete"/></a> 
            </sec:authorize>
        </td>
    </tr>
</c:forEach>
</table>
