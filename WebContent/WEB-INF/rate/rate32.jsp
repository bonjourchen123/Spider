<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("query").addEventListener("click",tetGrid,false);
		document.getElementById("reset").addEventListener("click",reset,false);
	}
	
	function reset(){
		document.getElementById("cartypeID").value=0;
		document.getElementById("caseMoney").value=0;
	}
	
	function tetGrid(){
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/rate' action='queryRate32'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/rate" action="rate32"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','車種', '每事故保險金額','保費'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'cartypeLocalName',index:'cartypeLocalName', width:'33%',align:'left'},
	            {name:'caseMoney',index:'caseMoney', width:'33%',align:'center',
	            	formatter:function(cellvalue, options, rowObject){
	            		return cellvalue+"萬元";
	            	},sorttype:"int"
	            },
	            {name:'rate',index:'rate', width:'33%', align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		return cellvalue+"元";
	            	},sorttype:"int"
	            }
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢第三責任財損險",
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
		jQuery('#resultTable').jqGrid(gridOption);
	});
</script>

<style>
 	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 22%;} 
</style>

<table class="myTable">
	<s:form method="get" namespace="/rate" action="queryRate32" theme="simple" id="queryForm">
		<tr>
			<td>
				車種:<s:select name="cartypeID" list="cartypes" listKey="value" listValue="label" headerKey="0" headerValue="所有車種" id="cartypeID"/>&nbsp;
				每事故保險金額:<s:select name="caseMoney" list="#{'5':'5萬','6':'6萬','8':'8萬','10':'10萬','15':'15萬','20':'20萬','30':'30萬','40':'40萬','50':'50萬','60':'60萬','70':'70萬','80':'80萬','90':'90萬','100':'100萬','150':'150萬','200':'200萬','250':'250萬','300':'300萬','350':'350萬','400':'400萬','450':'450萬','500':'500萬'}" 
					headerKey="0" headerValue="所有保額" id="caseMoney"/>&nbsp;
			</td>
			<td>	
				<input type="button" value="查詢" id="query" class="btn btn-success">
				<input type="button" value="清除" id="reset" class="btn btn-info">
			</td>
		</tr>
	</s:form>
</table>
<br><br><br>
<div class="myJqGrid">
	<table id="resultTable"></table>
	<div id="pager"></div>
</div>