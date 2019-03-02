<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="section">
	<h1><fmt:message key="logout.title"/></h1>
	<p><fmt:message key="logout.msg.success"/></p>
	
	<br><br>
	<p>Please <a href="<c:url value="/"/>">click</a> here to login</p>
	<br><br>	
</div>
