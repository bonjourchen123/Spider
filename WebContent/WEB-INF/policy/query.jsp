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
		document.getElementById("code").value="";
		document.getElementById("carNo").value="";
		document.getElementById("applicantName").value="";
		document.getElementById("insurantName").value="";
	}
	
	function tetGrid(){
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/policy' action='queryPolicy'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/policy" action="query"/>',
			datatype: 'json',
			mtype: 'GET',
 	        colNames:['id','功能','保單編號', '經辦人帳號', '投保人姓名','被保人姓名','被保人性別','被保人出生日期','被保人費率等級','被保人賠款紀錄等級','牌照號碼','車型','車輛用途','車輛出廠日期','載重噸數','座位數','投保產品','承保日期','到保日期','總保費','核保人帳號','審核日期','承保項目'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'func',index:'func', width:60, align:'center' },
	            {name:'code',index:'code', width:150,align:'center'},
 	            {name:'underwriterUser.account',index:'underwriterUser.account', width:150,align:'center'},
 	            {name:'applicantCustomer.name',index:'applicantCustomer.name', width:100,align:'center'},
	            {name:'insurantCustomer.name',index:'insurantCustomer.name', width:100, align:"center"},
	            {name:'insurantCustomer.gender',index:'insurantCustomer.gender', width:90, align:"center"},
	            {name:'insurantCustomer.birthday',index:'insurantCustomer.birthday', width:130, align:"center",formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
	            {name:'insurantCustomer.rateLevel',index:'insurantCustomer.rateLevel', width:130, align:"center",sorttype:"int"},
	            {name:'insurantCustomer.indemnityLevel',index:'insurantCustomer.indemnityLevel', width:150, align:"center",sorttype:"int"},
	            {name:'carNo',index:'carNo', width:90, align:"center"},
	            {name:'carmodelLocalName',index:'carmodelLocalName', width:250, align:"center"},
	            {name:'purpose',index:'purpose', width:130, align:"center"},
	            {name:'productionDate',index:'productionDate', width:110, align:"center" ,formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
	            {name:'tonnage',index:'tonnage', width:90, align:"center",sorttype:"float"
	            },
	            {name:'seats',index:'seats', width:70, align:"center",sorttype:"int"},
	            {name:'productCode',index:'productCode', width:100, align:"center"},
	            {name:'startDate',index:'startDate', width:110, align:"center",formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
	            {name:'endDate',index:'endDate', width:110, align:"center",formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
	            {name:'totalRate',index:'totalRate', width:110, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null){
	            			return "無限制";
						}else{
	            			return cellvalue+"元";
						}
	            	},sorttype:"int"	
	            },
 	            {name:'assessorUser.account',index:'assessorUser.account', width:150, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null){
	            			return "未審核";
						}else{
	            			return cellvalue;
						}
	            	}
	            },
	            {name:'assessorDate',index:'assessorDate', width:110, align:"center",formatter:"date",formatoptions:{newformat:"Y-m-d"},sorttype:"date"},
 	            {name:'policyItemsString',index:'policyItemsString', width:720, align:"left"}
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢保單",
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
		gridOption.gridComplete = function() {
			var ids = jQuery('#resultTable').jqGrid('getDataIDs');
			for (var i=0; i<ids.length; i++) {
				var cl = ids[i];
				var id = jQuery('#resultTable').jqGrid('getCell', cl,"id");
				var se = '<input type="button" onclick="query('+id+')" value="查看">';
				var func = se;
				jQuery('#resultTable').jqGrid('setRowData', ids[i], {func:func});
			}
		};
		jQuery('#resultTable').jqGrid(gridOption);
	});
	
	function query(id){
		var form = document.createElement("form");
 	    form.setAttribute("method", "post");
 	    var path='<c:url value="/policy/showPolicy"/>';
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
 		left: 23%;}
 	
 	.myTable tr {
 		height: 40px;}
</style>

<table class="myTable">
	<s:form method="get" namespace="/policy" action="queryPolicy" theme="simple" id="queryForm">
		<tr>
			<td>			
				保單編號:<s:textfield name="code" id="code" size="8px"/>&nbsp;
				牌照號碼:<s:textfield name="carNo" id="carNo" size="8px"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				投保人姓名:<s:textfield name="applicantName" id="applicantName" size="15px"/>&nbsp;
				被保人姓名:<s:textfield name="insurantName" id="insurantName" size="15px"/>&nbsp;
				<s:textfield name="officeID" id="officeID" value="${session.login.officeID}" cssClass="hide" readonly="true" />&nbsp;
			</td>
			<td>
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
			</td>
		</tr>
	</s:form>
</table>
<br><br>
<div class="myJqGrid">
	<table id="resultTable"></table>
	<div id="pager"></div>
</div>