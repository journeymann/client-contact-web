<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1><fmt:message key="user.search.title"/></h1>


<div class="full_width big">
    <br>
    <sec:authorize ifAnyGranted="ROLE_USER,ROLE_WRITE,ROLE_ADMIN">
	    <a href="<c:url value="/user.html"/>"><img src="/contacts/images/add.png" width="20" border="0" title="Click to add user"><fmt:message key="button.create"/></a> 
    </sec:authorize>
</div>
<br>
<div align="left" style="padding-left:10px">
<table cellpadding="0" cellspacing="0">
    <tr>
        <th class="td-header" align="left"><fmt:message key="user.form.username"/></th>
        <th class="td-header" align="left"><fmt:message key="user.form.department"/></th>
		<th class="td-header" align="left"><fmt:message key="user.form.email"/></th>
		<th class="td-header" align="left">Action</th>
    </tr>
<c:forEach var="user" items="${users}" varStatus="status">
    <tr>
        <c:set var="userFormId" value="user${status.index}"/>

        <c:url var="editUrl" value="/user.html">
            <c:param name="id" value="${user.id}" />
            <c:param name="username" value="${user.username}" />
        </c:url>
        
        <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
            <c:url var="deleteUrl" value="/user/delete.html"/>
            <form id="${userFormId}" action="${deleteUrl}" method="POST">
                <input id="id" name="id" type="hidden" value="${user.id}"/>
                <input id="username" name="username" type="hidden" value="${user.username}"/>
            </form>
        </sec:authorize>

	<td class="td-plain_">${user.username}</td>
	<td class="td-plain_">${user.department}</td> 							
	<td class="td-plain_">${user.email}</td>

    	<td  class="td-plain_">
            <a href='<c:out value="${editUrl}"/>'><img src="/contacts/images/edit.png" width="20" border="0" title="Click to edit user"></a>
            <sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">
                <a href="javascript:delete_item(document.forms['${userFormId}']);"><img src="/contacts/images/delete.png" width="20" border="0" title="Click to delete user"></a> 
            </sec:authorize>
        </td>
    </tr>
</c:forEach>
</table>
</div>
