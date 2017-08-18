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
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/rate' action='queryRate31'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/rate" action="rate31"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','車種', '每人保險金額', '每事故保險金額','保費'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'cartypeLocalName',index:'cartypeLocalName', width:'25%',align:'left'},
	            {name:'personMoney',index:'personMoney', width:'25%',align:'center',
	            	formatter:function(cellvalue, options, rowObject){
	            		return cellvalue+"萬元";
	            	},sorttype:"int"
	            },
	            {name:'caseMoney',index:'caseMoney', width:'25%',align:'center',
	            	formatter:function(cellvalue, options, rowObject){
	            		return cellvalue+"萬元";
	            	},sorttype:"int"
	            },
	            {name:'rate',index:'rate', width:'25%', align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		return cellvalue+"元";
	            	},sorttype:"int"
	            }
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢第三傷害責任險",
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
 		left: 12%;} 
</style>

<table class="myTable">
	<s:form method="get" namespace="/rate" action="queryRate31" theme="simple" id="queryForm">
		<tr>
			<td>
				車種:<s:select name="cartypeID" list="cartypes" listKey="value" listValue="label" headerKey="0" headerValue="所有車種" id="cartypeID"/>&nbsp;
				每人保險金額/每事故保險金額:<s:select name="caseMoney" list="#{'60':'30萬/60萬','70':'35萬/70萬','80':'40萬/80萬','90':'45萬/90萬','100':'50萬/100萬','110':'55萬/110萬','120':'60萬/120萬','130':'65萬/130萬','140':'70萬/140萬','150':'75萬/150萬','160':'80萬/160萬','170':'85萬/170萬','180':'90萬/180萬','190':'95萬/190萬','200':'100萬/200萬','210':'105萬/210萬','220':'110萬/220萬','230':'115萬/230萬','240':'120萬/240萬','250':'125萬/250萬','260':'130萬/260萬','270':'135萬/270萬','280':'140萬/280萬','290':'145萬/290萬','300':'150萬/300萬','310':'155萬/310萬','320':'160萬/320萬','330':'165萬/330萬','340':'170萬/340萬','350':'175萬/350萬','360':'180萬/360萬','370':'185萬/370萬','380':'190萬/380萬','390':'195萬/390萬','400':'200萬/400萬','500':'250萬/500萬','600':'300萬/600萬','700':'350萬/700萬','800':'400萬/800萬','900':'450萬/900萬','1000':'500萬/1000萬','1100':'550萬/1100萬','1200':'600萬/1200萬','1300':'650萬/1300萬','1400':'700萬/1400萬','1500':'750萬/1500萬','1600':'800萬/1600萬','1700':'850萬/1700萬','1800':'900萬/1800萬','1900':'950萬/1900萬','2000':'1000萬/2000萬'}" 
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