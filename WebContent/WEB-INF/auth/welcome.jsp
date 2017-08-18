<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<style>
	marquee {
		width: 920px;
		color: #228B22;}
		
	.myImg {
		width: 70px;
		position: relative;
		top: -5px;}
</style>

<marquee scrollamount="8">
	<h1><img class="myImg" src="<c:url value="/images/welcome.gif"/>">為了各位同仁的健康，提醒您每使用電腦50~60分鐘，應休息10~15分鐘，資策會-保險公司關心您</h1>
</marquee>
<br><br>
<fieldset>
	<legend>最新消息</legend>
	<ul>
		<li>
			<a href='#'><span class='date'>2014-05-13</span>&nbsp;&nbsp;<span>母親節「樂活媽咪，健康富足」活動開始囉！</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-08</span>&nbsp;&nbsp;<span>2014夏日捐血活動開跑囉!!</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-08</span>&nbsp;&nbsp;<span>揪你逗陣行─歡喜來趣淘</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-08</span>&nbsp;&nbsp;<span>平安遊玩趣-月月抽M8</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-05</span>&nbsp;&nbsp;<span>業績大評比，每週前100名即可獲得摩斯電子禮券喔！</span></a>
		</li>
	</ul>
	
	<div class="more"><a href="#">更多最新消息...<i class="icon-caret-right"></i></a></div>
</fieldset>
<br>
<fieldset>
	<legend>重要公告</legend>
	<ul>
		<li>
			<a href='#'><span class='date'>2014-05-22</span>&nbsp;&nbsp;<span>讚！資策會囊括第六屆保險卓越獎4項大獎！</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-11</span>&nbsp;&nbsp;<span>資策會連續二十八年榮獲「提高國人保險保障方案」績優產險公司</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-05-02</span>&nbsp;&nbsp;<span>「大紅大利專案」中獎名單</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-04-18</span>&nbsp;&nbsp;<span>2014年清寒家庭子女獎助學金 即日起開放申請</span></a>
		</li>
		<li>
			<a href='#'><span class='date'>2014-04-02</span>&nbsp;&nbsp;<span>財富人生-雙B專案中獎名單公告</span></a>
		</li>
	</ul>
	<div class="more"><a href="#">更多重要公告...<i class="icon-caret-right"></i></a></div>
</fieldset>