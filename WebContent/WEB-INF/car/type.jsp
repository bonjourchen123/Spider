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
		document.getElementById("code").value="";
		document.getElementById("localName").value="";
		document.getElementById("type").value=0;
		document.getElementById("purpose").value=0;
		document.getElementById("cc").value=0;
	}
	
	function tetGrid(){
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/car' action='queryType'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/car" action="type"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','代碼', '名稱','類型','用途','最小cc數','最大cc數(不含)'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'code',index:'code', width:'10%',align:'center',sorttype:"int"},
	            {name:'localName',index:'localName', width:'30%',align:'center'},
	            {name:'type',index:'type', width:'15%', align:"center",formatter:customFmatter},
	            {name:'purpose',index:'purpose', width:'15%',align:'center',formatter:customFmatter},
	            {name:'ccMin',index:'ccMin', width:'15%',align:'center',
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null || cellvalue==0){
	            			return "無限制";
						}else{
	            			return cellvalue;
						}
	            	},sorttype:"integer"	
	            },
	            {name:'ccMax',index:'ccMax', width:'15%',align:'center',
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null || cellvalue>1000){
	            			return "無限制";
						}else{
	            			return cellvalue;
						}
	            	},sorttype:"integer"
	            }
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢車種",
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
	
	function customFmatter(cellvalue, options, rowObject){
		if(cellvalue==null){
			return "無限制";
		}else{
			return cellvalue;
		}
	}
</script>

<style>
 	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 20%;}
 	
 	.myTable tr {
 		height: 40px;} 
</style>

<table class="myTable">
	<s:form method="get" namespace="/car" action="queryType" theme="simple" id="queryForm">
		<tr>
			<td>
				代碼:<s:textfield name="code" id="code" size="8px"/>&nbsp;
				名稱:<s:textfield name="localName" id="localName"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				類型:<s:select name="type" list="#{'機車':'機車','小客車':'小客車','小貨車':'小貨車','大客車':'大客車','大貨車':'大貨車','小型特種車':'小型特種車','大型特種車':'大型特種車','曳引車':'曳引車','客貨兩用車':'客貨兩用車'}" headerKey="0" headerValue="所有類型" id="type"/>&nbsp;
				用途:<s:select name="purpose" list="#{'自用':'自用','營業用':'營業用','營業用一般':'營業用一般','自用一般':'自用一般','租賃用':'租賃用','長期租賃':'長期租賃','個人計程車':'個人計程車','公司行號自用':'公司行號自用','自用櫃貨':'自用櫃貨','營業用櫃貨':'營業用櫃貨'}" headerKey="0" headerValue="所有用途" id="purpose"/>&nbsp;
				cc數:<s:select name="cc" list="#{'1':'未滿50','50':'50~250','250':'250~550','550':'550以上'}" headerKey="0" headerValue="所有範圍" id="cc" />&nbsp;
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