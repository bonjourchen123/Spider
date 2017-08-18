<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init() {
		document.getElementById("query").addEventListener("click", tetGrid, false);
		document.getElementById("reset").addEventListener("click", reset, false);
	}

	function reset(){
		document.getElementById("code").value="";
		document.getElementById("localName").value="";
		document.getElementById("engName").value="";
 		document.getElementById("result").innerHTML="";
	}
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/car' action='queryBrand'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/car" action="brand"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','代碼', '名稱', '英文名稱','描述'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'func',index:'func', width:'10%', align:'center' },
	            {name:'code',index:'code', width:'5%',align:'center'},
	            {name:'localName',index:'localName', width:'10%',align:'center'},
	            {name:'engName',index:'engName', width:'15%', align:"center"},
	            {name:'descript',index:'descript', width:'30%', align:"center"}
	            ],
	    	pager:'#pager',
	    	caption:"查詢廠牌",
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
				var se = '<input type="button" onclick="editorBrand('+id+')" value="編輯">';
				var ed = '<input type="button" onclick="deleteBrand('+id+')" value="刪除" >';
				var func = se + "&nbsp;"+ed;
				jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
			}
	    };
		jQuery('#resultTable').jqGrid(gridOption);
	});
	
	//測試用
// 	function edit(id){
// 		alert(id);
// 	}
	
	//編輯	
	function editorBrand(id){
// 		alert(id); //看有沒有抓到這值的測試方法
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/editorBrand"/>';
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
	function deleteBrand(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/deleteBrand"/>';
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
 		left: 15%;}
 	
 	#result {
 		position: relative;  
 		left: 130px;}
	
</style>

<table class="myTable">
	<s:form method="get" namespace="/car" action="queryBrand" theme="simple" id="queryForm">														 
		<tr>	
			<td>
				代碼:<s:textfield name="code" id="code" size="8px"/>&nbsp;
				名稱:<s:textfield name="localName" id="localName"/>&nbsp;	
				英文名稱:<s:textfield name="engName" id="engName"/>&nbsp;&nbsp;&nbsp;	
			<td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
				<input type="button" value="新增" onclick="location.href='<c:url value="/car/addBrand"/>'" class="btn btn-primary">
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