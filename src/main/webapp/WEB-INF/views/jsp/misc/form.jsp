<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h1><fmt:message key="field.definition.title"/></h1>


<p>param: <c:out value="${id}" /></p>

<br>

<p>
	<c:out value="${field.name}" /><br><br>
	<c:out value="${field.desc}" />
</p>


