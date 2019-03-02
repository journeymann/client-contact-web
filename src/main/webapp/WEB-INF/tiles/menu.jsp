<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="side-bar">

    <table class="clearText">
    	<tr>
    	   <td><input type="text" value="first name" name="f_name" size="5" onFocus()="javascript:clear();" /></td>
    	   <td><input type="text" value="last name" name="l_name" size="5" onFocus()="javascript:clear();" /></td>
    	   <td valign="center"><img src="/intranet/images/find_button.gif" onClick()="javascript:search();" ><td>
    	</tr>
    </table>
    
    <a href="<c:url value="/"/>">Home</a>
    
    <sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
        <p><fmt:message key="person.form.title"/></p>
            <a href="<c:url value="/person.html"/>"><fmt:message key="button.create"/></a> 
            <a href="<c:url value="/person/search.html"/>"><fmt:message key="button.search"/></a>
    </sec:authorize>
    
    <p><fmt:message key="links.form.pfg"/></p>
    	<a href="<c:url value="/"/>"><fmt:message key="links.form.ourcompany"/></a>
    	<a href="<c:url value="/etc/pfg_orgchart.html"/>"><fmt:message key="links.form.pfg.orgchart"/></a>
    	<a href="<c:url value="/etc/pfas_orgchart.html"/>"><fmt:message key="links.form.pfas.orgchart"/></a>
   	<a href="<c:url value="/etc/documents.html"/>"><fmt:message key="links.form.docs"/></a>   	

    <p><fmt:message key="links.form.title"/></p>
    	<a href="<c:url value="http://www.netbenefits.com/"/>">Netbenefits</a>
    	<a href="<c:url value="http://www.philadelphiafinancial.com/"/>">Philadelphia Fin'l Group</a>
    	<a href="<c:url value="http://dataservices/"/>">Data Services</a>
   	<a href="<c:url value="/etc/applications.html"/>"><fmt:message key="links.form.apps"/></a>
   	<a href="<c:url value="/etc/support.html"/>"><fmt:message key="links.form.support"/></a>   	



</div>
