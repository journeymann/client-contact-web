<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<p>You are being redirected to the relevant page</p>
<p> type= <c:out value="${ptype}"/></p>
<p> pflow_id= <c:out value="${pflow_id}"/></p>
<p> param type= <c:out value="${param.ptype}"/></p>
<p> param pflow_id= <c:out value="${param.pflow_id}"/></p>


<c:if test="${param.ptype != null}">
	<c:set var="ptype" value="${param.ptype}" />
	<c:set var="pflow_id" value="${param.pflow_id}" />
</c:if>

<c:choose>
	<c:when test="${ptype=='C'}">
		<p>You are here again</p>
		<c:set var="tab_selection" value="Compliance" scope="session"/>
		<c:redirect url="../compliance/detail.html">
			<c:param name="id" value="${pflow_id}" ></c:param>
		</c:redirect>
	</c:when>	
	<c:when test="${ptype=='B'}">
		<c:set var="tab_selection" value="Broker" scope="session"/>
		<c:redirect url="../broker/detail.html">
			<c:param name="id" value="${pflow_id}" ></c:param>
		</c:redirect>
	</c:when>	
	<c:when test="${ptype=='D'}">
		<c:set var="tab_selection" value="Dealer" scope="session"/>
		<c:redirect url="../dealer/detail.html">
			<c:param name="id" value="${pflow_id}" ></c:param>
		</c:redirect>
	</c:when>	
	<c:when test="${ptype=='P'}">
		<c:set var="tab_selection" value="Policy" scope="session"/>
		<c:redirect url="../policy/detail.html">
			<c:param name="id" value="${pflow_id}" ></c:param>
		</c:redirect>
	</c:when>	
	<c:otherwise>
		<c:set var="tab_selection" value="Contact" scope="session"/>
		<c:redirect url="/type/contacts.html"/>	
	</c:otherwise>
</c:choose>
