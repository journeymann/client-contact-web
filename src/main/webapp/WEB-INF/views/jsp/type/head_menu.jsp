<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div align="center">
	
		<br>
		<div id="messages">
		    <c:if test="${not empty statusMessageKey}">
		       <p><fmt:message key="${statusMessageKey}"/></p>
		    </c:if>

		    <spring:hasBindErrors name="broker">
			<h2>Errors</h2>
			<div class="formerror">
			    <ul>
			    <c:forEach var="error" items="${errors.allErrors}">
				<li>${error.defaultMessage}</li>
			    </c:forEach>
			    </ul>
			</div>
		    </spring:hasBindErrors>
		</div>
		<br>		
						
			<table width="95%" cellspacing="0" cellpadding="0" >
				<tr>
				<td width="15%"></td>
				<td width="85%"></td>
				</tr>
			     	<tr>				
					<td class="td-header" colspan="2"  style="padding:10px">
					<u>Navigation</u>
					</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					Contact Persons:
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Policy:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Broker Company:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Broker/Dealer:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Compliance:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Query Policy:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					<tr>
					<td class="td-plain" align="right">
					<b>Query Person:</b>
					</td>
					<td class="td-plain_">Test</td>
					</tr>
					
				</td>				
			     </tr>
			</table>     
			
			<br><br>
			
		</div>
		<c:set var="tab_selection" value="Home" scope="session" />
