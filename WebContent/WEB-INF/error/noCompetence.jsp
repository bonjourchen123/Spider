<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		$("#car").animate({"width":"400px","left":"280px","top":"130px"},3000);
		$("#stop").fadeOut(0,function(){
			$("#stop").fadeIn(3000);
		});
		setTimeout(function(){
			$("#people").animate({"width":"100px","left":"30px","top":"40px"},2000);
		},1000);
		$("#text").hide(0,function(){
			setTimeout(function(){
				$("#text").show(1000);
			},3000);
		});
	}
</script>

<style>
	#car {
		display: none;
		position: absolute;
		left: 850px;
		top: 5px;}
	
	#stop {
		position: absolute;
		left: 220px;
		top: 270px;}
		
	#people {
 		display: none;
		width: 20px;
		position: absolute;
		left: 220px;
		top: 270px;}
		
	#text {
		position: absolute;
		left: 130px;
		top: 5px;}
</style>

<img id="text" src="../images/noCompetence4.png"/>
<img id="people" src="../images/noCompetence3.gif"/>
<img id="car" src="../images/noCompetence2.gif"/>
<img id="stop" src="../images/noCompetence.gif"/>