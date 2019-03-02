<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<meta http-equiv='cache-control' content='no-cache'> 
	<meta http-equiv='expires' content='0'> 
	<meta http-equiv='pragma' content='no-cache'> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title><fmt:message key="site.title"/></title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_page.css"/>" />			
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_table.css"/>" />
	

	<style>
	.ui-autocomplete-loading { background: white url(<c:url value="/images/ui-anim_basic_16x16.gif" />) right center no-repeat; }
	#policy { width: 25em; }
	</style>

	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.8.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-1.8.23.custom.min.js" />"></script>	
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery.FixedColumns.min.js" />"></script>	


	<c:url value="/json/query.html" var="ddlJsonUrl"/>
	
	<script type="text/javascript" charset="utf-8">

		function delete_item_custom(myform, type) {
		
			var answer = false;
		
			if(type == "POLICY_CONTACT"){
				answer = confirm("Please confirm that you would like to remove this contact person from this policy.");
			}else if(type == "COMPLIANCE_CONTACT"){
				answer = confirm("Please confirm that you would like to remove this contact person from this policy.");
			}else if(type == "BROKER_CONTACT"){
				answer = confirm("Removing this contact will remove all instances from this Broker Company. Are you sure you want to continue?");						
			}else if(type == "DEALER_CONTACT"){			
				answer = confirm("Removing this contact will remove all instances from this Broker Dealer. Are you sure you want to continue?");						
			}
			
			if(answer){
				myform.submit();
			}
			
		}


		function delete_item(myform) {
		
			var answer = confirm("Please confirm that you want to delete this item.");

			if(answer){
				myform.submit();
			}
			
		}

		function delete_address(url, id) {
		
			var answer = confirm("Please confirm that you want to delete this address.");

			if(answer){
				window.location.href=url+"&_eventId=deleteAddress&addressId="+id;
			}
			
		}

	</script>
	
	
	
	
</head>
<body id="dt_data">
<center>
	<tiles:insertAttribute name="content" />
</center>
	
</body>
</html>
