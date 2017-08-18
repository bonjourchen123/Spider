<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Spider車險投保系統</title>
<head>
	<link rel="Shortcut Icon" type="image/x-icon" href="<c:url value="/images/trademarkTitle.ico"/>" />
	<script type="text/javascript" src='<c:url value="/js/jquery/jquery.js" />'></script>
	<script>
		window.addEventListener("load",init,false);
		
		function init(){
			setTimeout(function(){
				document.getElementById("captainAmerica").setAttribute("class","hide");
			},2000);
			setTimeout(function(){
				document.getElementById("rift").removeAttribute("class");
			},1000);
			setTimeout(function(){
				document.getElementById("rift").setAttribute("class","hide");
			},3500);
			setTimeout(function(){
				document.getElementById("people").removeAttribute("class");
			},4000);
			setTimeout(function(){
				document.getElementById("text").removeAttribute("class");
			},4000);
			setTimeout(function(){
				$("#index").animate({"width":"800px","left":"40px","top":"480px"},6000);
			},3700);
			setTimeout(function(){
				document.getElementById("goHome").removeAttribute("class");
			},8000);
			document.getElementById("index").addEventListener("click",run,false);
		}
		
		function run(){
			this.animate({"width":"800px","left":"200px","top":"480px"},1000);
		}
	</script>
	
	<style>
		.bottomBody {
			width: 900px;
			margin: auto;}
		
		.myBody {
			position: absolute;
			margin: 15px;}
		
		/*隱藏*/
		.hide {
			display: none;}
		
		#captainAmerica {
			width: 800px;
			position: absolute;
			z-index: 1;}
		
		#rift {
			width: 800px;
			position: absolute;
			z-index: 2;}
		
		#people {
			width: 250px;
			position: absolute;
			left: 0px;
			top: 30px;}
			
		#text {
			position: absolute;
			left: 220px;
			top: 20px;}
			
		#index {
 			width: 200px;
 			position: absolute;
			left: -500px;
			top: 480px;} 
			
		#goHome {
			position: absolute;
			left: 280px;
			top: 380px;}
	</style>
</head>
<body>
	<div class="bottomBody">
		<div class="myBody">
			<img id="captainAmerica" src="<c:url value="/images/errorPage5.gif"/>"/>
			<img id="rift" class="hide" src="<c:url value="/images/errorPage6.png"/>"/>
			<img id="people" class="hide" src="<c:url value="/images/errorPage.gif"/>"/>
			<img id="text" class="hide" src="<c:url value="/images/errorPage2.png"/>"/>
			<br>
			<a href="<c:url value="/auth/index"/>">
				<img id="index" src="<c:url value="/images/errorPage3.gif"/>"/>
			</a>
			<img id="goHome" class="hide" src="<c:url value="/images/errorPage4.png"/>"/>
		</div>
	</div>
</body>
</html>