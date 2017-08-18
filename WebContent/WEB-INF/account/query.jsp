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
	
	function reset(){
		document.getElementById("account").value="";
		document.getElementById("name").value="";
		document.getElementById("result").innerHTML="";
	}
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/account' action='queryAccount'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/account" action="query"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','帳號', '姓名', '電話','電子信箱','權限'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'func',index:'func', width:180, align:'center' },
	            {name:'account',index:'account', width:150,align:'center'},
	            {name:'name',index:'name', width:80,align:'center'},
	            {name:'phone',index:'phone', width:120, align:"center"},
	            {name:'email',index:'email', width:230, align:"left", formatter:"email"},
	            {name:'roleCodes',index:'roleCodes', width:130, align:"center"}
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢帳號&nbsp;&nbsp;<span class='tip'>[權限:MIS(系統管理人員),AC(行政辦事員),VS(審核人員),PP(產品規劃),LD(主管階級)]</span>",
			rowNum:10,
			rowList:[10,15,20,25],
			height: 298,
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
				var se = '<input type="button" onclick="update('+id+')" value="編輯">';
				var se1 = '<input type="button" onclick="resetPassword('+id+')" value="重置密碼">';
				var se2 = '<input type="button" onclick="deleteUser('+id+')" value="刪除">';
				var func = se+"&nbsp;"+se1+"&nbsp;"+se2;
//  			if (typeof(btnPlugin) == 'function') {
// 					act += btnPlugin(jqGrid, cl);
// 				}
				jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
			}
	    };
		jQuery('#resultTable').jqGrid(gridOption);
	});
		
	function update(id){
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/account/update"/>';
 	    form.setAttribute("action", path);
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "id");
		hiddenField.setAttribute("value",id);
		form.appendChild(hiddenField);
		document.body.appendChild(form);
	    form.submit();
	}
	
	function resetPassword(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/account/resetPassword"/>';
 	    form.setAttribute("action", path);
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "id");
		hiddenField.setAttribute("value",id);
		form.appendChild(hiddenField);
		document.body.appendChild(form);
	    form.submit();
	}
	
	function deleteUser(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/account/delete"/>';
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
	<s:form method="get" namespace="/account" action="queryAccount" theme="simple" id="queryForm">
		<tr>	
			<td>
				帳號:<s:textfield name="account" id="account"/>&nbsp;
				姓名:<s:textfield name="name" id="name" size="15px"/>&nbsp;
				<s:textfield name="officeID" id="officeID" value="${session.login.officeID}" cssClass="hide" readonly="true" />&nbsp;
			</td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
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