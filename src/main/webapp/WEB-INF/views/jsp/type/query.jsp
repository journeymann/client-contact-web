<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<script type="text/javascript" charset="utf-8">

<c:url value="/json/query.html" var="ddlJsonUrl"/>
		$(function () {
		    $('#all_yes').click(function () {
			$('#attributes .cy').attr('checked', this.checked);
		    });
		});

		$(function () {
		    $('#all_no').click(function () {
			$('#attributes .cn').attr('checked', this.checked);
		    });
		});


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


$(function () {
		$('#drop_down_1_id').change(function(){
		
			var drop_down_1_value = $("#drop_down_1_id option:selected").text();
			
			$.ajax({
			  type: 'GET',
			  url : '${ddlJsonUrl}',
			  data : {value : drop_down_1_value},
			  dataType : 'json',
			  async: false,
			  beforeSend: function(x) {
			    if(x && x.overrideMimeType) {
			     x.overrideMimeType("application/j-son;charset=UTF-8");
			    }
 			  },
			  success: function(data){
			    $('#drop_down_2_id').html('');
			    
			    var json = data.query;
			    json = json.substring(1, json.length-1); // need to look into this!
	    
			    var obj = $.parseJSON(json);
		    
			    $.each(obj, function(i, item) {		
			      	$('#drop_down_2_id').append('<option value="'+obj[i].value+'">'+obj[i].value+'</option>');
			    }); // loop
			  }, // success
			  error: function (jqXHR, textStatus, errorThrown) {
			    alert(jqXHR + " : " + textStatus + " : " + errorThrown);
			  }
			});//ajax 
		}); // change function
});
	
	</script>	
</head>
<form:form modelAttribute="queryhelper" method="post" action="/contacts/misc/report.html?output=excel" target="_blank">
<h1><fmt:message key="contact.type.query.title"/></h1>
<h2> 
<sec:authorize ifAnyGranted="ROLE_USER,ROLE_WRITE,ROLE_ADMIN">
	<input type="submit" value="Export Results" class="greyButton"/>	
</sec:authorize>&nbsp;&nbsp;
 <input type="reset" value="Reset" class="greyButton"/>	
</h2>


	<div style="float:left;padding: 1.5ex 1ex 1ex 3.5mm; width:40%;">
	   		
					<table cellpadding="0" cellspacing="0" width="100%" border="0" >
						<thead> 
							<tr> 
								<th align="left" class="td-header"><fmt:message key="query.form.primary.filter"/></th>
								<th align="left" class="td-header"><fmt:message key="query.form.secondary.filter"/></th>
							</tr> 
						</thead> 
						<tbody> 
							<tr> 			
							    <td  class="td-plain_">	
								<form:select path="pval" multiple="true" size="10" id="drop_down_1_id">
									<form:options items="${queryhelper.pfilters}" itemValue="filter" itemLabel="filter" />
								</form:select>
							    </td>	
							    <td  class="td-plain_">	
								<form:select path="sval" multiple="true" size="10" id="drop_down_2_id">	
								<form:options items="${queryhelper.sfilters}" itemValue="value" itemLabel="value" />
								</form:select>
							    </td>						
							</tr> 				
						</tbody> 			    
					</table>		
		    
	    <br>
	    <table cellpadding="0" cellspacing="0" width="100%" border="0" >
			<thead> 
				<tr> 
					<th class="td-header">&nbsp;</th>
					<th align="left" class="td-header"><fmt:message key="query.form.display"/></th>
				</tr> 
			</thead> 
			<tbody> 
				<c:forEach items="${queryhelper.displays}" var="display" varStatus="status"> 
				   <tr> 
					<td class="td-plain_"><form:checkbox class="chkbx" path="disp" value="${display.id}" /></td>	
					<td class="td-plain_">${display.desc}</td>				   	   			   	
				   </tr> 						
				</c:forEach>		
			</tbody> 			    
		</table>						
	</div>	
	
	
	<div class="spacer"></div>			
	
	<div id="attributes" style="float:right;padding: 1.5ex 1ex 1ex 1.5mm;width:50%;">	
		
		<table cellpadding="0" cellspacing="0" border="0" class="display" >
			<thead> 
				<tr> 
					<th align="center" class="td-header"><input type="checkbox" id="all_yes" value="0" ><fmt:message key="query.form.yes"/></th>
					<th align="center" class="td-header"><input type="checkbox" id="all_no" value="1" ><fmt:message key="query.form.no"/></th>
					<th align="left" class="td-header"><fmt:message key="query.form.attribute"/></th>				
				</tr> 
			</thead> 
			<tbody> 
				<c:forEach items="${queryhelper.attributes}" var="attribute" varStatus="status"> 
				   <c:if test="${attribute.id < 50}">
					   <tr> 				   
						<td align="center" class="td-plain_"><form:checkbox path="yes" class="cy" value="${attribute.id}"/></td>	
						<td align="center" class="td-plain_"><form:checkbox path="no" class="cn" value="${attribute.id}"/></td>	
						<td align="left" class="td-plain_">${attribute.attribute}</td>				   	   			   	
					   </tr> 					
				   </c:if> 	   
				</c:forEach>		
			</tbody> 			    
		</table>	
		<p>
		Selecting <font color="red">Yes</font>: Retrieves all persons that are linked to the selected policy and are "Yes" for the attribute<br>
		Selecting <font color="red">No</font>: Retrieves all persons that are linked to the selected policy and are "No" for the attribute<br>
		Selecting <font color="red">Yes</font> and <font color="red">No</font>: Retrieves all persons that are linked to the selected policy and the attribute<br>
		</p>

	</div>	
	
	
	

	
</form:form>