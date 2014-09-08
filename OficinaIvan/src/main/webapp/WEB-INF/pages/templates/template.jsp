<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<c:url value="/recursos/js/jquery-1.10.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/recursos/js/jquery-ui-1.10.4.custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/recursos/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
<script type="text/javascript" src="<c:url value="/recursos/js/oficina.js"/>"></script>

<link rel="stylesheet" type="text/css"  href="<c:url value="/recursos/css/oficina.css"/>" />
<link rel="stylesheet" type="text/css"  href="<c:url value="/recursos/css/redmond/jquery-ui-1.10.4.custom.css"/>" />

<title>Oficina v1.0</title>
</head>
<body>
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>

	<div class="content">
		<div class="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		<div class="content2">	
			<div class="corpo">
				<tiles:insertAttribute name="content" />
			</div>
		</div>
	</div>

	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>