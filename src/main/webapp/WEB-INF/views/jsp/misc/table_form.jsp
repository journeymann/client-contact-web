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

	function isBlank(str) {
	    return (!str || /^\s*$/.test(str));
	}

	function editTableDetail(operation, row_id, old_value, table_name)
	{

		var new_value = new_value=$('#row_'+row_id).val();
		
		if(operation=='I'){
			new_value = $('#row_ins').val();

			if(isBlank(new_value)){
				alert("Nothing was entered! Insert cancelled.");
				return;
			}			
		}

		if(operation=='U'){
			if(new_value == old_value){
				alert("Value was not changed! Update cancelled.");
				return;
			}
			if(isBlank(new_value)){
				alert("Nothing was entered! Insert cancelled.");
				return;
			}			
		}

		$.ajax({
		  type: 'GET',
		  url : '${ddlAjaxUrl}',
		  data : {value : new_value, searchString : table_name, id : row_id, opt : operation },
		  async: true,

		  success: function(data){
			var table_id = $('#selectorid').val();
			$.ajax({
		    	   type: "GET",
			   url: "edit_table.html?searchString="+table_id+"&id=1",  

			   success: function(data){		
			    	   alert("Your changes have been saved successfully!");
				   editTable(); //redraw screen

				   //$("#resultPane").load(url); 	
				   $("#resultPane").html(data);

			   }
			});//ajax 

		  }, // success

		  error: function (jqXHR, textStatus, errorThrown) {
		    alert(jqXHR + " : " + textStatus + " : " + errorThrown);
		  }
		});//ajax 

	}



	</script>
</head>
<!--<h1><fmt:message key="table.form.page.title"/></h1>-->
<div align="right" style="padding-right:7px"></div>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="table">
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

<form:form modelAttribute="maint">
    	<div style="float:left;padding: 1ex 3ex 1ex 1.5mm;width:100%;">	
	    
	    	<table cellpadding="0" cellspacing="0" width="100%" border="0" >
			<thead> 
				<tr> 
					<th align="left" class="td-header" colspan="2"><fmt:message key="table.definition.values"/>-<c:out value="${maint.tableSelected}" /></th>
				</tr> 
			</thead> 
			<tbody> 
				<c:choose>
					<c:when test="${maint.tableSelected=='ClientGroup'}">
						<c:set var="table" value="${maint.clientGroups}" />
					</c:when>
					<c:when test="${maint.tableSelected=='CaseManager'}">
						<c:set var="table" value="${maint.caseManagers}" />
						<c:set var="delete" value="${true}" />						
					</c:when>
					<c:when test="${maint.tableSelected=='Team'}">
						<c:set var="table" value="${maint.teams}" />
					</c:when>
					<c:when test="${maint.tableSelected=='Series'}">
						<c:set var="table" value="${maint.series}" />
					</c:when>
					<c:when test="${maint.tableSelected=='Country'}">
						<c:set var="table" value="${maint.countries}" />
					</c:when>
					<c:when test="${maint.tableSelected=='Library'}">
						<c:set var="table" value="${maint.libraries}" />
					</c:when>
				</c:choose>


				<c:forEach items="${table}" var="row" varStatus="status"> 
				   <tr> 
					<td class="td-plain_"><input type="text" value="${row.value}" size="40" id="row_${row.id}"></input></td>
					<td>
						<input type="button" value="Update" class="greyButton" onClick='javascript:editTableDetail("U",${row.id},"${row.value}","${maint.tableSelected}")'/>&nbsp;&nbsp;&nbsp;
						<c:if test="${delete}">
							<input type="button" value="Delete" class="greyButton" onClick='javascript:editTableDetail("D",${row.id},"${row.value}","${maint.tableSelected}")'/>
						</c:if>	
					</td>
				   </tr> 						
				</c:forEach>		
				<tr> 
				   <td class="td-plain_"><input type="text" value="" size="40" id="row_ins"></input></td>
				   <td class="td-plain_">
					<input type="button" value="Insert" class="greyButton" onClick='javascript:editTableDetail("I",${0},"","${maint.tableSelected}")'/>
				   </td>
				</tr> 						
				
			</tbody> 			    
			
		</table>						
	</div>	

	<br><br>

</form:form>

