<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
		document.getElementById("brandCode").value="";
		document.getElementById("seriesCode").value="";
		document.getElementById("code").value="";
		document.getElementById("localName").value="";
 		document.getElementById("result").innerHTML="";
	}	
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/car' action='queryModel'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
		}
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/car" action="model"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id', '功能', '代碼', '名稱', '廠牌', '車系', 'cc數'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true},
	            {name:'func',index:'func', width:'100px', align:'center' },
	            {name:'code',index:'code', width:'100px',align:'center'},
	            {name:'localName',index:'localName', width:'500px',align:'center'},
	            {name:'brandLocalName',index:'brandLocalName', width:'125px',align:'center'},
	            {name:'carseriesLocalName',index:'carseriesLocalName', width:'280px',align:'center'},
	            {name:'cc',index:'cc', width:'80px', align:'center', sorttype:"int"},
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢車型",
			rowNum:10,
			rowList:[10,15,20,25],
			height: 298,   //DEMO有橫桿就用300
			shrinkToFit:false,
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
				var se = '<input type="button" onclick="editorCarModel('+id+')" value="編輯">';
				var ed = '<input type="button" onclick="deleteCarModel('+id+')" value="刪除" >';
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
	
	//編輯	
	function editorCarModel(id){
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/editorCarModel"/>';
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
	function deleteCarModel(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/car/deleteCarModel"/>';
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
<br>
<table class="myTable">
	<s:form method="get" namespace="/car" action="queryModel" theme="simple" id="queryForm" style="position: relative; top:20px;">
		<div style="position: relative;left: 55px">
		廠牌代碼:<s:textfield name="brandCode" id="brandCode" size="8px"/>&nbsp;
		車系代碼:<s:textfield name="seriesCode" id="seriesCode" size="8px"/>&nbsp;
		代碼:<s:textfield name="code" id="code" size="12px"/>&nbsp;
		名稱:<s:textfield name="localName" id="localName"/>&nbsp;
		<input type="button" value="查詢" id="query" class="btn btn-success">
		<input type="button" value="清除" id="reset" class="btn btn-info">
		<button type="button" class="btn btn-primary" onclick="location.href='<c:url value="/car/addModel"/>'">新增</button>
		</div>		
	</s:form>
</table>	
<center>
	<div>
		<h1 class="warning" id="result">&nbsp;&nbsp;${errors.result[0]}</h1>
	</div>
</center>
		
<br>
<div class="myJqGrid">
	<table id="resultTable"></table>
	<div id="pager"></div>
</div>
<!-- 舊版無分頁 -->
<%-- <c:if test="${not empty modelBeans}"> --%>
<!-- 	<table border="1" style="width: 930px"> -->
<!-- 		<tr> -->
<!-- 			<th>代碼</th> -->
<!-- 			<th>名稱</th> -->
<!-- 			<th>cc數</th> -->
<!-- 		</tr> -->
<%-- 	<c:forEach var="modelBean" items="${modelBeans}"> --%>
<!-- 		<tr> -->
<%-- 			<td>${modelBean.code}</td> --%>
<%-- 			<td>${modelBean.localName}</td> --%>
<%-- 			<td>${modelBean.cc}</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- 	</table> -->
<%-- </c:if> --%>