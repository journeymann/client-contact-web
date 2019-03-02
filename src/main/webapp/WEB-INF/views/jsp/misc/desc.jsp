<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 style="font-size:13px"><center><fmt:message key="field.definition.title"/></center></h2>
	<div style="border:1px solid black; padding:4px">
		<center><font style="font-weight:bold"><c:out value="${field.name}" /></font></center>
	</div>
	<div style="border:1px solid black; padding:4px">
		<c:out value="${field.desc}" />
	</div>


