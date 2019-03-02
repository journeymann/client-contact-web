<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<script type="text/javascript" charset="utf-8">
		
		$(function () {
		    $('#disp1').click(function () {
			if($('#disp1').attr('checked')){
				$('#disp2').attr('checked', !this.checked);
			}
		    });
		});

		$(function () {
		    $('#disp2').click(function () {
			if($('#disp2').attr('checked')){
				$('#disp1').attr('checked', !this.checked);
			}
		    });
		});


	</script>	
</head>
<form:form modelAttribute="queryhelper" method="post" action="/contacts/misc/report_person.html?output=excel" target="_blank">
<h1><fmt:message key="contact.type.query.title"/></h1>
<h2> 
	<sec:authorize ifAnyGranted="ROLE_USER,ROLE_WRITE,ROLE_ADMIN">
 	    <input type="submit" value="Export Results" class="greyButton"/>	
	</sec:authorize>&nbsp;&nbsp;
	<input type="reset" value="Reset" class="greyButton"/>	
</h2>

	<div style="float:left;padding: 1ex 1ex 1ex 3.5mm; width:40%">
	   	
			<table cellpadding="0" cellspacing="0" border="0" width="100%" >
				<thead> 
					<tr> 
						<th class="td-header"><fmt:message key="query.form.person.filter"/></th>
					</tr> 
				</thead> 
				<tbody> 
					<tr> 			
					    <td class="td-plain_">	
						<form:select path="cval" multiple="true" size="10" id="drop_down_3_id">
							<form:options items="${queryhelper.cfilters}" itemValue="filter" itemLabel="filter" />
						</form:select>
					    </td>						
					</tr> 				
				</tbody> 			    
			</table>		
		   				
	</div>	
	
	
			

	<div style="float:right;padding: 1ex 3ex 1ex 1.5mm;width:50%;">	
		<table cellpadding="0" cellspacing="0" border="0" width="100%" >
			<thead> 
				<tr> 
					<th class="td-header">&nbsp;</th>
					<th class="td-header"><fmt:message key="query.form.display"/></th>
				</tr> 
			</thead> 
			<tbody> 
				<c:forEach items="${queryhelper.displays}" var="display" varStatus="status"> 
				   <tr> 
					<td class="td-plain_"><form:checkbox path="disp" value="${display.id}"/></td>	
					<td class="td-plain_">${display.desc}</td>				   	   			   	
				   </tr> 						
				</c:forEach>		
			</tbody> 			    
		</table>	
	</div>	
	
	
</form:form>	