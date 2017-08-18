<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<!-- Google Chart -->
<script type="text/javascript" src='<c:url value="/js/google/jsapi.js"/>'></script>
<script type="text/javascript">
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",query,false);
		document.getElementById("reset").addEventListener("click",reset,false);
		$("#startDate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-10:+0"
		}).datepicker('option', $.datepicker.regional['zh-TW']);
		$("#endDate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-10:+0"
		}).datepicker('option', $.datepicker.regional['zh-TW']);
	}
	
	function query(){
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
		drawVisualization();
	}
	
	function reset(){
		document.getElementById("role").value=0;
		document.getElementById("startDate").value="";
		document.getElementById("endDate").value="";
		document.getElementById('tip').innerHTML="";
	}

 	google.load('visualization', '1', {packages: ['corechart']}); 

 	function drawVisualization() { 
 		var role=document.getElementById("role").value;
 		var startDate=document.getElementById("startDate").value;
 		var endDate=document.getElementById("endDate").value;
		document.getElementById('tip').innerHTML="";
		document.getElementById('visualization').innerHTML="";
		$.ajax({
			type:'GET',
			contentType:'application/json; charset=utf-8',		
			url:"querySupervise?officeID="+"${session.login.officeID}"+"&role="+role+"&startDate="+startDate+"&endDate="+endDate,
			success: function(data) {
				if(data==null){
					document.getElementById('tip').innerHTML="<h1>查無資料</h1>";
				}else{
					var array=new Array(data.length);
					array[0]=["員工帳號:姓名","保單數"];
					for(var i=0;i<data.length;i++){
						array[i+1]=[data[i]["label"],data[i]["count"]];
					}
					var googleData = google.visualization.arrayToDataTable(array);
					var options = { 
						title:"處理保單數", 
						is3D: true, 
						width:700, 
						height:400
					};
                    new google.visualization.PieChart(document.getElementById('visualization')).draw(googleData,options);
				}
				document.getElementById("submitTip").setAttribute("class","hide");
		 		document.getElementById("waitImg").setAttribute("class","hide");
			}
		});
 	}
</script>
    
<style>
	.title {
		width: 100px;
		position: relative;
		top: 10px;
		left: 49%;}
	
	.myDiv {
		width: 700px;
		position: relative;
		left: 22%;}
	
	#visualization {
		width: 700px;
		position: relative;
		left: 13%;}
	
	.warning {
		width: 200px;
		position: relative;
		left: 46%;
		color: red;}
</style>

<div class="title">管理報表</div>
<br>
<div class="myDiv">
	對象:<s:select name="role" list="#{'0':'行政辦事員','1':'審核人員'}" id="role"/>&nbsp;
	時間:<s:textfield name="startDate" id="startDate" size="15px"/>
	~<s:textfield name="endDate" id="endDate" size="15px"/>&nbsp;
	<button type="button" id="submit" class="btn btn-success">
		查詢<span id="submitTip" class="hide">中</span>
		<img class="hide" src="../images/wait.gif" id="waitImg">
	</button>
	<input type="button" value="清除" id="reset" class="btn btn-info">
</div>
<div class="warning" id="tip"></div>
<div id="visualization"></div>