<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
// 	function wait(){
// 		document.getElementById("submitTip").removeAttribute("class");
// 		document.getElementById("waitImg").removeAttribute("class");
// 	}
	
	function reset(){
		document.getElementById("code").value="";
		document.getElementById("localName").value="";
		document.getElementById("engName").value="";
		document.getElementById("result").innerHTML="";
	}
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/setting' action='queryOffice'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/setting" action="office"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','代碼', '名稱','英文名稱'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true},
	            {name:'func',index:'func', width:'10%', align:'center' },
	            {name:'code',index:'code', width:'10%',align:'center'},
	            {name:'localName',index:'localName', width:'30%',align:'center'},
	            {name:'engName',index:'engName', width:'35%', align:'center'},
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢部門",
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
				var se = '<input type="button" onclick="editorOffice('+id+')" value="編輯">';
				var ed = '<input type="button" onclick="deleteOffice('+id+')" value="刪除" >';
				var func = se + "&nbsp;"+ed;
/* 				if (typeof(btnPlugin) == 'function') {
					act += btnPlugin(jqGrid, cl);
				} */
				jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
			}
	    };
		jQuery('#resultTable').jqGrid(gridOption);
	});
		
// 	function edit(id){
// 		alert(id);
// 	}
	
	function editorOffice(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/editorOffice"/>';
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
	function deleteOffice(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/deleteOffice"/>';
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
 		left: 220px;}
</style>

<table class="myTable">
<s:form method="get" namespace="/setting" action="queryOffice" theme="simple" id="queryForm">
	<tr>
		<td>
			代碼:<s:textfield name="code" id="code" size="8px"/>&nbsp;
			名稱:<s:textfield name="localName" id="localName"/>&nbsp;
			英文名稱:<s:textfield name="engName" id="engName"/>&nbsp;&nbsp;
		</td>
		<td>
			<input type="button" value="查詢" id="query" class="btn btn-success">
			<input type="button" value="清除" id="reset" class="btn btn-info">
			<button type="button" class="btn btn-primary" onclick="location.href='<c:url value="/setting/addOffice"/>'">新增</button>
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
<!-- 舊版無分頁 -->
<%-- <c:if test="${not empty officeBeans}"> --%>
<!-- 	<table border="1" style="width: 930px"> -->
<!-- 		<tr> -->
<!-- 			<th>代碼</th> -->
<!-- 			<th>名稱</th> -->
<!-- 			<th>英文名稱</th> -->
<!-- 		</tr> -->
<%-- 	<c:forEach var="officeBean" items="${officeBeans}"> --%>
<!-- 		<tr> -->
<%-- 			<td>${officeBean.code}</td> --%>
<%-- 			<td>${officeBean.localName}</td> --%>
<%-- 			<td>${officeBean.engName}</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- 	</table> -->
<%-- </c:if> --%>