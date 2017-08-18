<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("print").addEventListener("click",print,false);
	}
	
	function print(){
		if(window.print){
			alert("此瀏覽器暫不支援列印功能，請按Ctrl+P來列印");
		}else{
			window.print();
		}
	}
</script>

<style>
	.bottomBody {
		height: 930px}
		
	.title {
		width: 100px;
		position: relative;
		top: 10px;
		left: 49%;}
		
	.myDiv {
 		width: 200px;
 		position: relative;
 		left: 48%;}
 	
 	.hide{
 		display: inline;}
 		
 	.maTable {
		width: 700px;
		border-style:solid;
		font-size: 0.5cm;
		position: relative;
		left: 13%;}
		
	.title2 {
		margin-bottom: 10px;
		font: 0.5cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: 1px;
		color: #228B22;}
		
	#result {
		width: 300px;
 		position: relative;
 		left: 40%;}
</style>

<div class="title">完整保單</div>
<c:if test="${not empty errors.result}">
	<h1 class="warning" id="result">${errors.result[0]}</h1>
</c:if>
<br>
<table class="maTable" border="1">
	<tr>
		<td colspan="2"><span class="title2">保單編號:&nbsp;</span>${code}</td>
	</tr>
	<tr>
		<td class="title2">投保人</td>
		<td>姓名:&nbsp;${applicantName}</td>
	</tr>
	<tr>
		<td>身分證字號:&nbsp;${applicantPin}</td>
		<td>性別:&nbsp;${applicantGender}</td>
	</tr>
	<tr>
		<td>出生日期:&nbsp;<joda:format value="${applicantBirthday}" pattern="yyyy-MM-dd"/></td>
		<td>聯絡電話:&nbsp;${applicantPhone}</td>
	</tr>
	<tr>
		<td colspan="2">戶籍地址:&nbsp;${applicantPermanentAddr}</td>
	</tr>
	<tr>
		<td colspan="2">聯絡地址:&nbsp;${applicantCurrentAddr}</td>
	</tr>
	<tr>
		<td class="title2">被保人</td>
		<td>姓名:&nbsp;${insurantName}</td>
	</tr>
	<tr>
		<td>身分證字號:&nbsp;${insurantPin}</td>
		<td>性別:&nbsp;${insurantGender}</td>
	</tr>
	<tr>
		<td>出生日期:&nbsp;<joda:format value="${insurantBirthday}" pattern="yyyy-MM-dd"/></td>
		<td>聯絡電話:&nbsp;${insurantPhone}</td>
	</tr>
	<tr>
		<td colspan="2">戶籍地址:&nbsp;${insurantPermanentAddr}</td>
	</tr>
	<tr>
		<td colspan="2">聯絡地址:&nbsp;${insurantCurrentAddr}</td>
	</tr>
	<tr>
		<td class="title2" colspan="2">保單</td>
	</tr>
	<tr>
		<td>經辦人:&nbsp;${underwriterName}</td>
		<td>
			投保產品:
			<c:choose>
				<c:when test="${productID==1}">21</c:when>
				<c:when test="${productID==2}">31</c:when>
				<c:when test="${productID==3}">32</c:when>
				<c:when test="${productID==4}">2131</c:when>
				<c:when test="${productID==5}">2132</c:when>
				<c:when test="${productID==6}">3132</c:when>
				<c:when test="${productID==7}">213132</c:when>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>承保日期:&nbsp;<joda:format value="${startDate}" pattern="yyyy-MM-dd"/></td>
		<td>到保日期:&nbsp;<joda:format value="${endDate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>牌照號碼:&nbsp;${carNo}</td>
		<td>出廠日期:&nbsp;<joda:format value="${productionDate}" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>廠牌:&nbsp;${brand}</td>
		<td>車系:&nbsp;${carseries}</td>
	</tr>
	<tr>
		<td colspan="2">車型:&nbsp;(${carmodelCode})&nbsp;${carmodelName}</td>
	</tr>
	<tr>
		<td>用途:&nbsp;${purpose}</td>
		<td>車種:&nbsp;${cartypeLocalName}</td>
	</tr>
	<c:if test="${not empty tonnage || not empty seats}">
		<tr>
			<td>
				<c:if test="${not empty tonnage}">載重數:&nbsp;${tonnage}噸</c:if>
			</td>
			<td>
				<c:if test="${not empty seats}">座位數:&nbsp;${seats}人</c:if>
			</td>
		</tr>
	</c:if>
	<tr>
		<td>
			<c:choose>
				<c:when test="${empty assessorName}">審核人:&nbsp;未審核</c:when>
				<c:otherwise>審核人:&nbsp;${assessorName}</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${empty assessorDate}">審核日期:&nbsp;未審核</c:when>
				<c:otherwise>審核日期:&nbsp;<joda:format value="${assessorDate}" pattern="yyyy-MM-dd"/></c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td class="title2" colspan="2">承保項目</td>
	</tr>
	<c:if test="${productID==1 || productID==4 || productID==5 || productID==7}">
		<tr>
			<td>強制險</td>
			<td>保費:&nbsp;${rate21}元</td>
		</tr>
	</c:if>
	<c:if test="${productID==2 || productID==4 || productID==6 || productID==7}">
		<tr>
			<td colspan="2">第三傷害責任險</td>
		</tr>
		<tr>
			<td>每人/每事故保險金額:&nbsp;<fmt:formatNumber value="${caseMoney31/2}"/>/${caseMoney31}萬元</td>
			<td>保費:&nbsp;${rate31}元</td>
		</tr>
	</c:if>
	<c:if test="${productID==3 || productID==5 || productID==6 || productID==7}">
		<tr>
			<td colspan="2">第三責任財損險</td>
		</tr>
		<tr>
			<td>每事故保險金額:&nbsp;${caseMoney32}萬元</td>
			<td>保費:&nbsp;${rate32}元</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="2">總保費:&nbsp;${totalRate}元</td>
	</tr>
</table>
<c:if test="${not empty errors.verify}">
	<style>
		.bottomBody {
			height: 1130px}
	</style>
	
	<br>
	<table class="maTable" border="1">
		<tr>
			<td>
				<span class="title2">黑名單-事由:</span><br>
				${reason}
			</td>
		</tr>
	</table>
</c:if>
<br>
<c:choose>
	<c:when test="${not empty errors.result}">
		<div class="myDiv">
			<input type="button" id="print" class="btn btn-primary" value="列印">
			<input type="button" onclick="location.href='<c:url value="/policy/create"/>'" class="btn btn-info" value="返回">
		</div>
	</c:when>
	<c:when test="${not empty errors.show}">
		<div class="myDiv">
			<input type="button" id="print" class="btn btn-primary" value="列印">
			<input type="button" onclick="location.href='<c:url value="/policy/query"/>'" class="btn btn-info" value="返回">
		</div>
	</c:when>
	<c:when test="${not empty errors.verify}">
		<div class="myDiv">
			<s:form method="post" namespace="/policy" action="verifyPolicy" theme="simple">
				<s:textfield name="id" id="id" readonly="true" cssClass="hide"/>
				<button type="submit" name="submit" value="approves" class="btn btn-primary">核可</button>
				<button type="submit" name="submit" value="refusal" class="btn btn-danger">拒保</button>
			</s:form>
		</div>
	</c:when>
</c:choose>