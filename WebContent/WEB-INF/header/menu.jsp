<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
		
	function init(){
		var table=document.getElementById("munu").getElementsByTagName("table");
		for(var i=0;i<table.length;i++){
			table[i].addEventListener("mouseover",showMunu,false);
			table[i].addEventListener("mouseout",closeMunu,false);
		}
	}

	function showMunu(){
		var tr=this.getElementsByTagName("tr");
		for(var i=1;i<tr.length;i++){
			tr[i].removeAttribute("class");
		}
	}

	function closeMunu(){
		var tr=this.getElementsByTagName("tr");
		for(var i=1;i<tr.length;i++){
			tr[i].setAttribute("class","notShow");
		}
	}
</script>

<style>
	/*上方選單*/
	.menu{
		width: 960px;
		height: 35px;
		margin: auto;
		border-style: ridge;
		border-top-style: none;
		border-radius: 0px 0px 15px 15px;
		box-shadow: rgba(0, 0, 0, 0.5) 0px 0px 5px;
		background-color: #A4DAF6;
		position: relative;
		z-index: 1;}
	
	.menu table{
		border-collapse: separate;
		margin-top: 1px;
		margin-left: 10px;
		float: left;
		z-index: 2;}
	
	.menu td{
		width: 125px;
		margin-top: 5px;
		text-align: center;
		font: 0.45cm 'News Cycle', "Microsoft YaHei", Arial;
		border-style: groove;
		border-radius: 15px 15px 15px 15px;
		box-shadow: rgba(0, 0, 0, 0.5) 0px 0px 5px;
		background-color: #FFFAFA;}

	/*滑鼠移上去時*/
	.menu td:hover{
		background-color: #CCBBFF;
		cursor: pointer;
		border-style: inset;}
	
	/*權限不足滑鼠移上去時*/
	.menu .notRole:hover{
		background-color: #c9c9c9;
		cursor: not-allowed;}

	/*隱藏選項*/
	.menu .notShow {
		display: none;}
	
	.menu a{
		text-decoration: none;
		color: black;}
		
	/*選單小圖示*/
	.menu img{
		margin-left: 5px;
		margin-right: -10px;
		height: 23px;
		width: 23px;
		float: left;}
</style>

<div class="menu" id="munu">
	<table>
		<tr><td><img alt="帳號設定" title="帳號設定" src="../images/account.png">帳號設定</td></tr>
		<tr class="notShow"><td><a href="../account/change">修改密碼</a></td></tr>
		<c:if test="${not empty session.MIS}">
			<tr class="notShow"><td><a href="../account/create">新建帳號</a></td></tr>
			<tr class="notShow"><td><a href="../account/query">查詢帳號</a></td></tr>
		</c:if>
	</table>
	<table>
		<c:if test="${empty session.AC && empty session.VS}">
			<tr><td class="notRole"><img alt="保單管理" title="保單管理" src="../images/policy.png">保單管理</td></tr>
		</c:if>
		<c:if test="${not empty session.AC || not empty session.VS}">
			<tr><td><img alt="保單管理" title="保單管理" src="../images/policy.png">保單管理</td></tr>
		</c:if>
		<c:if test="${not empty session.AC}">
			<tr class="notShow"><td><a href="../policy/create">新建保單</a></td></tr>
		</c:if>
		<c:if test="${not empty session.AC}">
			<tr class="notShow"><td><a href="../policy/query">查詢保單</a></td></tr>
		</c:if>
		<c:if test="${not empty session.VS}">
			<tr class="notShow"><td><a href="../policy/verify">審核保單</a></td></tr>
		</c:if>
	</table>
	<table>
		<c:if test="${empty session.LD}">
			<tr><td class="notRole"><img alt="報表功能" title="報表功能" src="../images/report.png">報表功能</td></tr>
		</c:if>
		<c:if test="${not empty session.LD}">
			<tr><td><img alt="報表功能" title="報表功能" src="../images/report.png">報表功能</td></tr>
		</c:if>
		<c:if test="${not empty session.LD}">
			<tr class="notShow"><td><a href="../report/supervise">管理報表</a></td></tr>
			<tr class="notShow"><td><a href="../report/policy">業績報表</a></td></tr>
		</c:if>
	</table>
	<table>
		<tr><td><img alt="保費查詢" title="保費查詢" src="../images/select.png">保費查詢</td></tr>
		<tr class="notShow"><td><a href="../rate/rate21">強制險</a></td></tr>
		<tr class="notShow"><td><a href="../rate/rate31">第三傷害責任</a></td></tr>
		<tr class="notShow"><td><a href="../rate/rate32">第三責任財損</a></td></tr>
		<tr class="notShow"><td><a href="../rate/coef">保費相關係數</a></td></tr>
	</table>
	<table>
		<c:if test="${empty session.PP}">
			<tr><td class="notRole"><img alt="車輛設定" title="車輛設定" src="../images/car.png">車輛設定</td></tr>
		</c:if>
		<c:if test="${not empty session.PP}">
			<tr><td><img alt="車輛設定" title="車輛設定" src="../images/car.png">車輛設定</td></tr>
		</c:if>
		<c:if test="${not empty session.PP}">
			<tr class="notShow"><td><a href="../car/type">車種查詢</a></td></tr>
			<tr class="notShow"><td><a href="../car/brand">廠牌設定</a></td></tr>
			<tr class="notShow"><td><a href="../car/series">車系設定</a></td></tr>
			<tr class="notShow"><td><a href="../car/model">車型設定</a></td></tr>
		</c:if>
	</table>
	<table>
		<c:if test="${empty session.AC && empty session.MIS}">
			<tr><td class="notRole"><img alt="相關設定" title="相關設定" src="../images/setting.png">相關設定</td></tr>
		</c:if>
		<c:if test="${not empty session.AC || not empty session.MIS}">
			<tr><td><img alt="相關設定" title="相關設定" src="../images/setting.png">相關設定</td></tr>
		</c:if>
		<c:if test="${not empty session.AC}">
			<tr class="notShow"><td><a href="../setting/customer">客戶管理</a></td></tr>
		</c:if>
		<c:if test="${not empty session.MIS}">
			<tr class="notShow"><td><a href="../setting/office">部門設定</a></td></tr>
		</c:if>
		<c:if test="${not empty session.MIS}">
			<tr class="notShow"><td><a href="../setting/blacklist">黑名單設定</a></td></tr>
		</c:if>
	</table>
</div>