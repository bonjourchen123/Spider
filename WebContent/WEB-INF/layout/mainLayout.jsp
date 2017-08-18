<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Spider車險投保系統</title>
<head>	
	<link rel="Shortcut Icon" type="image/x-icon" href="<c:url value="/images/trademarkTitle.ico"/>" />
	<tiles:insertAttribute name="css"/>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="menu"/>
	<div class="bottomBody">
		<div class="myBody">
			<tiles:insertAttribute name="content"/>
		</div>
	</div>
	<tiles:insertAttribute name="footer"/>
</body>
</html>