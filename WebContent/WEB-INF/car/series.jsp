<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("query").addEventListener("click",tetGrid,false);
		document.getElementById("reset").addEventListener("click",reset,false);
	}
	
	function reset(){
		document.getElementById("code").value="";
		document.getElementById("localName").value="";
		document.getElementById("brandCode").value="";
		document.getElementById("type").value=0;
	}
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/car' action='queryCarseries'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/car" action="series"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','代碼','名稱','類型', '廠牌代碼', '廠牌名稱'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'func',index:'func', width:'20%', align:'center' },
	            {name:'code',index:'code', width:'15%',align:'center'},
	            {name:'localName',index:'localName', width:'25%', align:"center"},
	            {name:'type',index:'type', width:'25%', align:"center"},
	            {name:'brand.code',index:'brand.code', width:'15%',align:'center'},
	            {name:'brand.localName',index:'brand.localName', width:'15%',align:'center'}
	            ],
	    	pager:'#pager',
	    	caption:"查詢車系",
			rowNum:10,
			rowList:[10,15,20,25],
			height: 280.6,
			autowidth: true,
			sortname: 'account',
			viewrecords: true,
			sortorder: "asc",
			jsonReader:{"root":"rows","page":"page","total":"total","records":"records","repeatitems":false,"cell":"cell","id":"id"},
			emptyrecords: "暫無資料",
			loadonce:true
	    };
	//客制化功能按鈕
	gridOption.gridComplete = function() {
		var ids = jQuery('#resultTable').jqGrid('getDataIDs');
		for (var i=0; i<ids.length; i++) {
			var cl = ids[i];
			var id = jQuery('#resultTable').jqGrid('getCell', cl,"id");
			var se = '<input type="button" onclick="editorCarseries('+id+')" value="編輯">';
			var se1 = '<input type="button" onclick="deleteCarseries('+id+')" value="刪除">';
			var func = se+"&nbsp;"+se1;
			jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
				}
			};
			jQuery('#resultTable').jqGrid(gridOption);
		});
	//編輯
	function editorCarseries(id){
 		//alert(id); //看有沒有抓到這值的測試方法
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/editorCarseries"/>';
 	    form.setAttribute("action", path);
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "id");
		hiddenField.setAttribute("value",id);
		form.appendChild(hiddenField);
		document.body.appendChild(form);
	    form.submit();
	}
	
	//刪除
	function deleteCarseries(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/deleteCarseries"/>';
 	    form.setAttribute("action", path);
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "id");
		hiddenField.setAttribute("value",id);
		form.appendChild(hiddenField);
		document.body.appendChild(form);
	    form.submit();
	}
</script>

<style>
 	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 12%;}
 	
 	#result {
 		width: 500px;
 		position: relative;  
 		left: 150px;}
	
</style>

<table class="myTable">
	<s:form method="get" namespace="/car" action="queryCarseries" theme="simple" id="queryForm">
		<tr>
			<td>
				代碼:<s:textfield name="code" id="code" size="5" />&nbsp;
				名稱:<s:textfield name="localName" id="localName" />&nbsp;
				廠牌代碼:<s:textfield name="brandCode" id="brandCode" size="5" />&nbsp;
				類型:<s:select name="type" list="#{'機車':'機車','小客車':'小客車','小貨車':'小貨車','大客車':'大客車','大貨車':'大貨車','小型特種車':'小型特種車','大型特種車':'大型特種車','曳引車':'曳引車','客貨兩用車':'客貨兩用車'}" headerKey="0" headerValue="所有類型" id="type"/>&nbsp;
			</td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
				<button type="button"
					onclick="location.href='<c:url value="/car/addSeries"/>'"
					class="btn btn-primary">新增</button>
			</td>
		</tr>
	</s:form>
	<tr>
		<td colspan="2">
			<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>
		</td>
	</tr>
</table>
<br><br>
<div class="myJqGrid">
	<table id="resultTable"></table>
	<div id="pager"></div>
</div>