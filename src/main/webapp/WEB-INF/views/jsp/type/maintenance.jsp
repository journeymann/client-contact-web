<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
	<meta http-equiv='cache-control' content='no-cache'> 
	<meta http-equiv='expires' content='0'> 
	<meta http-equiv='pragma' content='no-cache'> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<style type="text/css">
		tr { backgorund:white; }
		tr.odd { background:silver; }	
	</style>

	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() {
		  $('table tbody tr:odd').addClass('odd');
		});
	</script>


	<script type="text/javascript" charset="utf-8">

	<c:url value="/ajax/maint.html" var="ddlAjaxUrl"/>

	function editTable()
	{
		 var content = document.getElementById("resultPane");
		 var table_id = $('#selectorid').val();
	
		 $.ajax({
			   type: "GET",
			   url: "edit_table.html?searchString="+table_id+"&id=1",
			   async: true,

			   success: function(msg){		

				   $("#resultPane").load("edit_table.html?searchString="+table_id+"&id=1"); 	
				   //content.innerHTML = msg;
				   $("#resultPane").html(msg);


			   },
				failure : function(msg) {
					   $("#resultPane").html(msg);

			   }
		 });                       


	}



	</script>

</head>
<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="contact">
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

<h1><fmt:message key="maintenance.form.title"/></h1>

<form:form modelAttribute="maintenancehelper" method="post">
	<br>
    	<div style="float:left;padding: 1.5ex 1ex 1ex 3.5mm; width:20%;">
	   		
		<table cellpadding="0" cellspacing="0" width="100%" border="0" >
			<thead> 
				<tr> 
					<th align="left" class="td-header">
						<fmt:message key="table.definition.title"/>
					</th>
				</tr> 
			</thead> 
			<tbody> 
				<tr> 			
				    <td  class="td-plain_">	
					<form:select path="tableSelected" multiple='true' size='7' onClick='javascript:editTable()' id='selectorid'>
						<form:options items="${maintenancehelper.tables}" />
					</form:select>
				    </td>	
				</tr> 				
			</tbody> 			    
		</table>		
		    
	    <br>
	</div>
    	<div id="resultPane" title="Table View" style="float:center;padding: 1.5ex 1ex 1ex 3.5mm; width:50%;"></div>

</form:form> 




