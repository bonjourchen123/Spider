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
		document.getElementById("gender").value=0;
		document.getElementById("age").value=0;
		document.getElementById("tonnage").value=0;
		document.getElementById("seats").value=0;
	}
	
	function tetGrid(){
		$("#resultTable").jqGrid('setGridParam', {url: "<s:url namespace='/rate' action='queryRate21'/>"+"?"+$("#queryForm").serialize(),datatype: 'json'});
		$("#resultTable").jqGrid().trigger('reloadGrid');
	}
	
	$(document).ready(function(){
		var gridOption = {
 			url:'<s:url namespace="/rate" action="rate21"/>',
			datatype: 'json',
			mtype: 'GET', 
	        colNames:['id','車種', '性別', '最小年齡','最大年齡','最小載重數','最大載重數','最小座位數','最大座位數','保費(等級1)','保費(等級2)','保費(等級3)','保費(等級4)','保費(等級5)','保費(等級6)','保費(等級7)','保費(等級8)','保費(等級9)','保費(等級10)'],
	        colModel:[
	            {name:'id',index:'id', width:'10%', align:'center',hidden:true },
	            {name:'cartypeLocalName',index:'cartypeLocalName', width:150,align:'left'},
	            {name:'gender',index:'gender', width:70,align:'center',formatter:customFmatter},
	            {name:'fromAge',index:'fromAge', width:90, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null){
	            			return "無限制";
						}else{
	            			return cellvalue+1;
						}
	            	},sorttype:"int"
	            },
	            {name:'toAge',index:'toAge', width:90, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
						if(cellvalue==null){
	            			return "無限制";
						}
	            		if(cellvalue>100){
	            			return "無限制";
	            		}else{
	            			return cellvalue;
	            		}
	            	},sorttype:"int"	
	            },
	            {name:'tonnageMin',index:'tonnageMin', width:90, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null){
	            			return "無限制";
	            		}
	            		if(cellvalue<3.5){
	            			return 3.5;
	            		}else{
	            			return cellvalue;
	            		}
	            	},sorttype:"float"
	            },
	            {name:'tonnageMax',index:'tonnageMax', width:90, align:"center",formatter:customFmatter,sorttype:"int"},
	            {name:'seatsMin',index:'seatsMin', width:90, align:"center",
	            	formatter:function(cellvalue, options, rowObject){
	            		if(cellvalue==null){
	            			return "無限制";
						}else{
	            			return cellvalue+1;
						}
	            	},sorttype:"int"	
	            },
	            {name:'seatsMax',index:'seatsMax', width:90, align:"center",formatter:customFmatter,sorttype:"int"},
	            {name:'rateLevel1',index:'rateLevel1', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel2',index:'rateLevel2', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel3',index:'rateLevel3', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel4',index:'rateLevel4', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel5',index:'rateLevel5', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel6',index:'rateLevel6', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel7',index:'rateLevel7', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel8',index:'rateLevel8', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel9',index:'rateLevel9', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"},
	            {name:'rateLevel10',index:'rateLevel10', width:110, align:"center",formatter:customFmatterMoney,sorttype:"int"}
	        ],	    	
	    	pager:'#pager',
	    	caption:"查詢強制險",
			rowNum:10,
			rowList:[10,15,20,25],
			height: 298,
			//不自動根據比例縮放 會有水平捲軸
			shrinkToFit:false,
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
	
	function customFmatterMoney(cellvalue, options, rowObject){
		if(cellvalue==null){
			return "無限制";
		}else{
			return cellvalue+"元";
		}
	}
</script>

<style>
 	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 13%;}
 	 	
 	.myTable tr {
 		height: 40px;} 
</style>

<table class="myTable">
	<s:form method="get" namespace="/rate" action="queryRate21" theme="simple" id="queryForm">
		<tr>
			<td>
				車種:<s:select name="cartypeID" list="cartypes" listKey="value" listValue="label" headerKey="0" headerValue="所有車種" id="cartypeID"/>&nbsp;
				被保人性別:<s:select name="gender" list="#{'男':'男','女':'女'}" headerKey="0" headerValue="所有性別" id="gender"/>&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				被保人年紀:<s:select name="age" list="#{'18':'18歲~20歲','21':'21歲~25歲','26':'26歲~30歲','31':'31歲~60歲','61':'61歲以上'}" headerKey="0" headerValue="所有年紀" id="age"/>&nbsp;
				載重數:<s:select name="tonnage" list="#{'9':'3.5噸~9噸(不含)','15':'9噸~15噸(不含)','26':'15噸~26噸(不含)'}" headerKey="0" headerValue="所有噸數" id="tonnage"/>&nbsp;
				座位數:<s:select name="seats" list="#{'10':'10人~20人','21':'21人~30人','31':'31人~50人'}" headerKey="0" headerValue="所有位數" id="seats"/>&nbsp;
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