<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<head>

<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.8.1.min.js" />"></script>
<script type="text/javascript">

	$(function() {
		//var win = window.open('http://dev-web01:8080/Ntlm/userId.jsp','popup','scrollbars=yes,width=20,height=20');
		//var win = window.open('http://uat-web03:8080/Ntlm/userId.jsp','popup','scrollbars=yes,width=20,height=20');
        var win = window.open('http://localhost:8080/Ntlm/userId.jsp','popup','scrollbars=yes,width=20,height=20');
		//var win = window.open('http://njfpk-java-dev:8090/Ntlm/userId.jsp','popup','scrollbars=yes,width=20,height=20');		
		win.focus();

		setTimeout("if($('#login').val()!=''){loginJ();}",2000);

	});

	function loginJ()
	{
		$("#j_username").val($("#login").val());
		$("#j_password").val($("#login").val());
		$("#submitLogin").click();
	}


</script>



</head>


<h1><fmt:message key="login.title"/></h1>

<div class="section">
	<c:if test="${not empty param.login_error}">
		<div class="errors">
			<fmt:message key="login.msg.failure"/><br /><br />
			<fmt:message key="login.failure.reason"/>: <%= ((AuthenticationException) session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
		</div>
	</c:if>
</div>

<div class="section" style="padding-left:7px;">
	<form id="fLogin" name="fLogin" action="<c:url value="/loginProcess" />" method="post">
		<input type="hidden" id="login" name="login"/>	
		<fieldset>
			<div class="field">
				<br>
				<div class="label"><label for="j_username"><fmt:message key="login.username"/>:</label></div>
				<div class="output">
					<input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}">value="<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY) %>"</c:if> />
				</div>
			</div>
			<div class="field">
				<div class="label"><label for="j_password"><fmt:message key="login.password"/>:</label></div>
				<div class="output">
					<input type="password" name="j_password" id="j_password" />
				</div>
			</div>
			<div class="field">
				<div class="label">
					<input type="checkbox" name="_spring_security_remember_me" id="remember_me" checked />
					<label for="remember_me"><fmt:message key="login.rememberMe"/></label>
				</div>
			</div>
			<br>            
                <div class="button" style="align:left;">
                    <input name="submit" id="submitLogin" type="submit" class="greyButton" value="<fmt:message key="button.login"/>" />                </div>
            
		<br><br>
		</fieldset>
	</form>
</div>
