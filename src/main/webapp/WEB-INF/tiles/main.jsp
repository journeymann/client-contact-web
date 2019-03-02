<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
	<meta http-equiv='cache-control' content='no-cache'> 
	<meta http-equiv='expires' content='0'> 
	<meta http-equiv='pragma' content='no-cache'> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Refresh" content="43200; URL=/contacts/timedout.html">
	<title><fmt:message key="site.title"/></title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/global.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/js/jquery/ui/theme/ui.all.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_page.css"/>" />			
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/dt_table.css"/>" />
	
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-1.8.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-custom.min.js" />"></script>	
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery.dataTables.min.js" />"></script>

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
			}else if(type == "BROKER_COMPANY"){			
				answer = confirm("You are about to remove a Broker Company. Are you sure you want to continue?");						
			}else if(type == "BROKER_DEALER"){			
				answer = confirm("You are about to remove a Broker Dealer. Are you sure you want to continue?");						
			}else if(type == "POLICY"){			
				answer = confirm("You are about to remove a Policy. Are you sure you want to continue?");						
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
		
		function getQueryParams(qs) {
		    qs = qs.split("+").join(" ");
		    var params = {},
			tokens,
			re = /[?&]?([^=]+)=([^&]*)/g;

		    while (tokens = re.exec(qs)) {
			params[decodeURIComponent(tokens[1])]
			    = decodeURIComponent(tokens[2]);
		    }

		    return params;
		}
	
		

		
	</script>
	
		
</head>
<body>
<center>
	<div class="page-bg">
		<center>
			<div id="header">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</center>
	</div>
</center>
</body>
</html>


