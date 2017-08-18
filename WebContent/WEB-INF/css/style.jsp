<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<!-- 載入bootstrap -->
<link rel="stylesheet" href="<c:url value='/css/bookstrap.css' />">
<!-- 載入jQuery -->
<link rel="stylesheet" href="<c:url value='/css/jquery-ui-1.10.4.custom.css' />" type="text/css">
<script type="text/javascript" src='<c:url value="/js/jquery/jquery.js" />'></script>
<script type="text/javascript" src='<c:url value="/js/jquery/jquery-ui-1.10.4.custom.js" />'></script>
<script type="text/javascript" src='<c:url value="/js/jquery/jquery.ui.datepicker-zh-TW.js" />'></script>
<!-- 載入jqGrid -->
<link rel="stylesheet" href="<c:url value='/js/jquery/jqGrid/css/ui.jqgrid.css' />" type="text/css">
<script type="text/javascript" src='<c:url value="/js/jquery/jqGrid/src/i18n/grid.locale-tw.js"/>'></script>
<script type="text/javascript" src='<c:url value="/js/jquery/jqGrid/js/jquery.jqGrid.src.js" />'></script>
<!-- 載入json -->
<script type="text/javascript" src='<c:url value="/js/json/form2js.js" />'></script>
<script type="text/javascript" src='<c:url value="/js/json/json2.js" />'></script>

<style>
	/*最底層的背景*/
	.bottomBody {
		background-color: #edffff;
		width: 960px;
		/*實際內容在依照需求override掉高度 */
		height: 550px;
		margin: auto;
		margin-top: 10px;
		border-style: ridge;
		border-radius: 15px 15px 15px 15px;
		box-shadow: rgba(0, 0, 0, 0.5) 0px 0px 5px;
		position: relative;
		font: 0.4cm 'News Cycle', "Microsoft YaHei", Arial;}

	/*放內容的地方*/
	.myBody {
 		width: 860px;
		position: absolute;
		margin: 15px;}
	
	/*標題*/
	.title {
		margin-bottom: 10px;
		font: 0.6cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: 1px;
		color: #4169E1;}
	
	/*警告*/
	.warning {
		color: red;
		height: 25px;}
	
	/*提示*/
	.tip {
		font-size: 0.25cm;
		letter-spacing: 1px;
		color: #4169E1;}
	
	/*錯誤提示*/	
	.tipError {
		font-size: 0.25cm;
		letter-spacing: 1px;
		color: red;}
	
	/*隱藏*/
	.hide {
		display: none;}
		
	/*無法使用*/
	.noUse {
		background-color: #b5b5b5;}
	
	/*無法使用*/
	.noUse:hover {
		cursor: not-allowed;}
		
	/*內容置中*/
	.textCenter {
		text-align: center;}
		
	/*內容置右*/
	.textRight {
		text-align: right;}
		
	/*文字敘述*/
	textarea {
		width : 430px;
		height : 200px;
  		overflow-y : auto; /* IE */
  		resize : none; /* Firefox, Chrome */}
  	
  	/*jqGrid 分頁區高度*/
  	#pager {
 		height:30px}
  	
  	/*jqGrid 分頁框大小*/
  	.ui-jqgrid .ui-pg-input {
		height: 18px;}
	
	/*jqGrid 標題文字大小*/
	.ui-jqgrid-title {
		font-size: 20px;}
		
	/*jqGrid 標題高度*/
	.ui-jqgrid-labels {
 		height:25px;}
	
	/*jqGrid 內容文字大小*/
 	.ui-jqgrid .ui-jqgrid-view { 
 		font-size: 15px;}
 	
 	/*jqGrid 內容高度*/
 	.jqgrow {
 		height:28px;}
 	
 	/*jqGrid 位置*/
 	.myJqGrid {
 		position: relative; 
 		left: 4%;}
</style>	