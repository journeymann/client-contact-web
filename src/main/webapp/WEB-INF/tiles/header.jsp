<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div style="float:left;">
	<a href="/contacts"><img src="/contacts/images/LOGO_philadelphia_financial.gif" width="176" alt="Philadelphia Financial Group" border="0" title="Click here to go to the homepage"></a>	
</div>	
<div id="headerTitle">
        <sec:authorize ifAnyGranted="ROLE_ADMIN">
		<div align="right" class="topbar-rt">
		     <a href="<c:url value="/user/search.html"/>"><img src="/contacts/images/wrench.png" alt="Philadelphia Financial Group" border="0" title="Click to administer users"><font size="2"><fmt:message key="site.admin"/></font></a>		     
		</div>		     
	</sec:authorize>		
	<h1><font color="#669933" size="5"><fmt:message key="site.title"/></font></h1>
</div>	
<div id="loginstatus">
	<div align="right" style="padding-bottom:5px">
    		<sec:authorize ifAnyGranted="ROLE_USER,ROLE_WRITE,ROLE_ADMIN">    	
			Welcome - <b><sec:authentication property="principal.fullname" />&nbsp;&nbsp;(<sec:authentication property="principal.department" />)&nbsp;</b>
		</sec:authorize>
	</div>
</div>
<div class="subHeader">
    <div align="right"><a href="<c:url value="/misc/desc_all.html"/>"  target="_blank"><fmt:message key="button.view.definition"/></a></div>
    <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
        <div align="right"><a href="<c:url value="/login.html"/>"><fmt:message key="button.login"/></a></div>
    </sec:authorize>
</div>

<c:choose>
	<c:when test="${tab_selection=='Contact'}">
		<c:set var="contact_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Policy'}">
		<c:set var="policy_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Broker'}">
		<c:set var="broker_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Dealer'}">
		<c:set var="dealer_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Compliance'}">
		<c:set var="compliance_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Query_Policy'}">
		<c:set var="qpolicy_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Query_Person'}">
		<c:set var="qperson_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
	<c:when test="${tab_selection=='Maintenance'}">
		<c:set var="maintenance_selected" value="ui-state-default ui-corner-top ui-tabs-selected ui-state-active" />
	</c:when>
</c:choose>


<div align="left" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
		<ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">			
			<li class="${contact_selected}"><a href="<c:url value="/type/contacts.html"/>">Contact Persons</a></li>
			<li class="${policy_selected}"><a href="<c:url value="/type/policy.html"/>" name="policy">Policy</a></li>
			<li class="${broker_selected}"><a href="<c:url value="/type/broker_company.html"/>" name="broker">Broker Company</a></li>
			<li class="${dealer_selected}"><a href="<c:url value="/type/broker_dealer.html"/>" name="dealer">Broker/Dealer</a></li>
			<li class="${compliance_selected}"><a href="<c:url value="/type/compliance.html"/>" name="compliance">Compliance</a></li>
			<li class="${qpolicy_selected}"><a href="<c:url value="/type/query.html"/>">Query Policy</a></li>
			<li class="${qperson_selected}"><a href="<c:url value="/type/query_person.html"/>">Query Person</a></li>			
			<sec:authorize ifAnyGranted="ROLE_WRITE,ROLE_ADMIN">    	
				<li class="${maintenance_selected}"><a href="<c:url value="/type/maintenance.html"/>">Maintenance</a></li>						
			</sec:authorize>
			
		</ul><div id="ui-tabs-2" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div><div id="ui-tabs-3" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div><div id="ui-tabs-4" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div><div id="ui-tabs-5" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div><div id="ui-tabs-6" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div><div id="ui-tabs-7" class="ui-tabs-panel ui-widget-content ui-corner-bottom ui-tabs-hide"></div>		
		
</div>