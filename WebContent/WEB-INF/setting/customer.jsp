<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load", init, false);

	function init() {
		document.getElementById("query").addEventListener("click",tetGrid,false);
		document.getElementById("reset").addEventListener("click", reset, false);
	}

	function reset() {
		document.getElementById("name").value = "";
		document.getElementById("pin").value = "";
	}
	
	function tetGrid(){
		document.getElementById("result").innerHTML="";
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/setting' action='queryCustomer'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/setting" action="customer"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','功能','姓名','身分證字號', '性別', '出生日期','電話','戶籍地址','聯絡地址','費率等級','賠款紀錄等級'],
	        colModel:[
	            {name:'id',index:'id', width:'50%', align:'center',hidden:true },
	            {name:'func',index:'func', width:'100%', align:'center' },
	            {name:'name',index:'name', width:'80%',align:'center'},
	            {name:'pin',index:'pin', width:'150%',align:'center'},
	            {name:'gender',index:'gender', width:'50%', align:"center"},
				{name:'birthday',index:'birthday', width:'180%', align:"center", formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
				{name:'phone',index:'phone', width:'120%', align:"center"},
				{name:'permanentAddr',index:'permanentAddr', width:'300%', align:"center"},
				{name:'currentAddr',index:'currentAddr', width:'300%', align:"center"},
				{name:'rateLevel',index:'rateLevel', width:'150%', align:"center"},
				{name:'indemnityLevel',index:'indemnityLevel', width:'150%', align:"center"}
	            ],
	    	pager:'#pager',
	    	caption:"查詢客戶",
			rowNum:10,
			rowList:[10,15,20,25],
			height: 300,
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
			var se = '<input type="button" onclick="editorCustomer('+id+')" value="編輯">';
			var se1 = '<input type="button" onclick="deleteCustomer('+id+')" value="刪除">';
			var func = se+"&nbsp;"+se1;
			jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
				}
			};
			jQuery('#resultTable').jqGrid(gridOption);
		});
	
	//編輯	
	function editorCustomer(id){
 		//alert(id); //看有沒有抓到這值的測試方法
 		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/editorCustomer"/>';
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
	function deleteCustomer(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/setting/deleteCustomer"/>';
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
 		left: 20%;}
 	#result {
 	 	width: 500px;
 		position: relative;  
 		left: 110px;}
</style>

<table class="myTable">
	<s:form method="get" namespace="/setting" action="queryCustomer" theme="simple" id="queryForm">
		<tr>	
			<td>
				姓名:<s:textfield name="name" id="name" />&nbsp;
				身分證字號:<s:textfield name="pin" id="pin" />
				<s:textfield name="officeID" id="officeID" value="${session.login.officeID}" cssClass="hide" readonly="true" />&nbsp;
			</td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
				<button type="button" onclick="location.href='<c:url value="/setting/addCustomer"/>'" class="btn btn-primary">新增</button>
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