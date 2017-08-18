<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load", init, false);//DOM裡用來新增觸發事件的函數叫AddEventListener

	function init() {
		document.getElementById("query").addEventListener("click", tetGrid, false);
		document.getElementById("reset").addEventListener("click", reset, false);
	}

	function reset() {
		document.getElementById("number").value = "";
		document.getElementById("type").value = 0;//下拉式選單要一開始設定的
		document.getElementById("result").innerHTML="";//抓result的值去修改先的值
	}

	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/setting' action='queryBlacklist'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/setting" action="blacklist"/>',//url:''-->也能抓到預設資料,寫出來只是較保險
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','類別', '號碼', '事由'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'func',index:'func', width:'10%', align:'center' },
	            {name:'type',index:'type', width:'8%',align:'center'},
	            {name:'number',index:'number', width:'10%',align:'center'},
	            {name:'reason',index:'reason', width:'30%', align:"center"}
	            ],
	    	pager:'#pager',
	    	caption:"查詢黑名單",
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
				var se = '<input type="button" onclick="editorBlacklist('+id+')" value="編輯" >';
				var ed = '<input type="button" onclick="deleteBlacklist('+id+')" value="刪除" >';
				var func = se + "&nbsp;"+ed;
				jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
			}
	    };
		jQuery('#resultTable').jqGrid(gridOption);
	});
	
	//編輯	
	function editorBlacklist(id){
// 		alert(id); //看有沒有抓到這值的測試方法
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/editorBlacklist"/>';
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
	function deleteBlacklist(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/deleteBlacklist"/>';
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
 		left: 25%;}
 	
 	#result {
 		position: relative;  
 		left: 130px;}
	
</style>

<table class="myTable">
	<s:form method="get" namespace="/setting" action="queryBlacklist" theme="simple" id="queryForm">
		<tr>	
			<td>類型:<s:select name="type" list="#{'車牌':'車牌','身分證字號':'身分證字號'}"
					headerKey="0" headerValue="所有類別" id="type" />&nbsp; 
				號碼:<s:textfield name="number" id="number" />&nbsp;&nbsp;
			</td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
				<input type="button" value="新增" onclick="location.href='<c:url value="/setting/addBlacklist"/>'" class="btn btn-primary">
			</td>
		</tr>
	</s:form>
	<tr>
		<td colspan="2">
			<h1 class="warning" id="result">${errors.result[0]}</h1>
		</td>
	</tr>
</table>
<br><br>
<div class="myJqGrid">
	<table id="resultTable"></table>
	<div id="pager"></div>
</div>
