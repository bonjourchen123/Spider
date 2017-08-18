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
	}
	
	function query(){
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
		drawVisualization();
	}

	function reset(){
		document.getElementById("year").value="${years[0]['value']}";
		document.getElementById('tip').innerHTML="";
	}
	
	//要引用何種圖表-corechart
	google.load('visualization', '1', {packages: ['corechart']});
	
	function drawVisualization(){
		var year=document.getElementById("year").value;
		document.getElementById('tip').innerHTML="";
		document.getElementById('visualization').innerHTML="";
		$.ajax({
			type:'GET',
			contentType:'application/json; charset=utf-8',		
			url:"queryPolicy?year="+year,
			success: function(data) {
				if(data==null){
					document.getElementById('tip').innerHTML="<h1>查無資料</h1>";
				}else{
					var count=data['offices'].length;
					var arrays=new Array(12);
					var arrayTitle=new Array(count+1);
					arrayTitle[0]='月份';
					var array1=new Array(count+1);
					array1[0]='1月';
					var array2=new Array(count+1);
					array2[0]='2月';
					var array3=new Array(count+1);
					array3[0]='3月';
					var array4=new Array(count+1);
					array4[0]='4月';
					var array5=new Array(count+1);
					array5[0]='5月';
					var array6=new Array(count+1);
					array6[0]='6月';
					var array7=new Array(count+1);
					array7[0]='7月';
					var array8=new Array(count+1);
					array8[0]='8月';
					var array9=new Array(count+1);
					array9[0]='9月';
					var array10=new Array(count+1);
					array10[0]='10月';
					var array11=new Array(count+1);
					array11[0]='11月';
					var array12=new Array(count+1);
					array12[0]='12月';
					for(var i=0;i<count;i++){
						arrayTitle[i+1]=data['offices'][i];
						array1[i+1]=data['rate1'][i];
						array2[i+1]=data['rate2'][i];
						array3[i+1]=data['rate3'][i];
						array4[i+1]=data['rate4'][i];
						array5[i+1]=data['rate5'][i];
						array6[i+1]=data['rate6'][i];
						array7[i+1]=data['rate7'][i];
						array8[i+1]=data['rate8'][i];
						array9[i+1]=data['rate9'][i];
						array10[i+1]=data['rate10'][i];
						array11[i+1]=data['rate11'][i];
						array12[i+1]=data['rate12'][i];
					}
					arrays[0]=arrayTitle;
					arrays[1]=array1;
					arrays[2]=array2;
					arrays[3]=array3;
					arrays[4]=array4;
					arrays[5]=array5;
					arrays[6]=array6;
					arrays[7]=array7;
					arrays[8]=array8;
					arrays[9]=array9;
					arrays[10]=array10;
					arrays[11]=array11;
					arrays[12]=array12;
					var googleData = google.visualization.arrayToDataTable(arrays);		
					var options={
						width: 920,
					    height: 400,
					    legend: { position: 'top', maxLines: 3 },
					    bar: { groupWidth: '75%' },
					    title:"各部門月保單金額(元)",
					    hAxis: {title: year+"年"}
					};
					new google.visualization.ColumnChart(document.getElementById('visualization')).draw(googleData, options);
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
		width: 500px;
		position: relative;
		left: 41%;}
		
	.warning {
		width: 200px;
		position: relative;
		left: 46%;
		color: red;}
</style>

<div class="title">業績報表</div>
<br>
<div class="myDiv">
	年份:<s:select name="year" list="years" listKey="value" listValue="label" id="year"/>&nbsp;
	<button type="button" id="submit" class="btn btn-success">
		查詢<span id="submitTip" class="hide">中</span>
		<img class="hide" src="../images/wait.gif" id="waitImg">
	</button>
	<input type="button" value="清除" id="reset" class="btn btn-info">
</div>
<div class="warning" id="tip"></div>
<div id="visualization"></div>