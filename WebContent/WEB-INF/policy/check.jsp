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
		document.getElementById("submit").addEventListener("click",wait,false);
	}
	
	function wait(){
		document.getElementById("waitImg").removeAttribute("class");
	}
</script>

<style>
	.bottomBody {
		height: 830px}
		
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
</style>

<div class="title">確認保單</div>
<br>
<table class="maTable" border="1">
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
		<td>經辦人:&nbsp;${underwriter.name}</td>
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
		<td>廠牌:&nbsp;${carmodel.brandLocalName}</td>
		<td>車系:&nbsp;${carmodel.carseriesLocalName}</td>
	</tr>
	<tr>
		<td colspan="2">車型:&nbsp;(${carmodelCode})&nbsp;${carmodel.localName}</td>
	</tr>
	<tr>
		<td>用途:&nbsp;${purpose}</td>
		<td>車種:&nbsp;${cartype.localName}</td>
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
<br>
<s:form method="post" namespace="/policy" action="createPolicy" theme="simple">
	<div class="hide">
		<s:textfield name="officeID" id="officeID" readonly="true"/>
		<s:textfield name="applicantPin" id="applicantPin" readonly="true"/>
		<s:textfield name="applicantName" id="applicantName" readonly="true"/>
		<s:radio name="applicantGender" id="applicantGender" list="#{'男':'男','女':'女'}" readonly="true"/>
		<s:textfield name="applicantBirthday" id="applicantBirthday" readonly="true"/>
		<s:textfield name="applicantPhone" id="applicantPhone" readonly="true"/>
		<s:textfield name="applicantPermanentAddr" id="applicantPermanentAddr" readonly="true"/>
		<s:textfield name="applicantCurrentAddr" id="applicantCurrentAddr" readonly="true"/>
		<s:textfield name="insurantPin" id="insurantPin" readonly="true"/>
		<s:textfield name="insurantName" id="insurantName" readonly="true"/>
		<s:radio name="insurantGender" id="insurantGender" list="#{'男':'男','女':'女'}" readonly="true"/>
		<s:textfield name="insurantBirthday" id="insurantBirthday" readonly="true"/>
		<s:textfield name="insurantPhone" id="insurantPhone" readonly="true"/>
		<s:textfield name="insurantPermanentAddr" id="insurantPermanentAddr" readonly="true"/>
		<s:textfield name="insurantCurrentAddr" id="insurantCurrentAddr" readonly="true"/>
		<s:textfield name="underwriterAccount" id="underwriterAccount"  readonly="true"/>
		<s:textfield id="underwriterName" name="underwriterName" size="18px" readonly="true"/>
		<s:textfield name="underwriterID" id="underwriterID" readonly="true" value="${underwriter.id}"/>
		<s:select name="productID" list="#{'1':'21','2':'31','3':'32','4':'2131','5':'2132','6':'3132','7':'213132'}" headerKey="0" headerValue="請選擇" id="productID" readonly="true"/>
		<s:textfield name="startDate" id="startDate" readonly="true"/>
		<s:textfield name="carNo" id="carNo" readonly="true"/>
		<s:textfield name="productionDate" id="productionDate" readonly="true"/>
		<s:textfield name="carmodelID" id="carmodelID" readonly="true" value="${carmodel.id}"/>
		<s:textfield name="carmodelCode" id="carmodelCode" readonly="true"/>
		<s:textfield id="carmodelName" name="carmodelName" readonly="true"/>
		<s:textfield id="brand" name="brand" readonly="true"/>
		<s:textfield id="carseries" name="carseries" readonly="true"/>
		<s:select name="purpose" list="#{'自用':'自用','營業用':'營業用','營業用一般':'營業用一般','自用一般':'自用一般','租賃用':'租賃用','長期租賃':'長期租賃','個人計程車':'個人計程車','公司行號自用':'公司行號自用','自用櫃貨':'自用櫃貨','營業用櫃貨':'營業用櫃貨'}" headerKey="0" headerValue="請選擇" id="purpose" readonly="true"/>
		<s:textfield id="cartypeLocalName" name="cartypeLocalName" readonly="true"/>
		<s:textfield name="tonnage" id="tonnage" readonly="true"/>
		<s:textfield name="seats" id="seats" readonly="true"/>
		<s:select name="caseMoney31" list="#{'60':'30萬/60萬','70':'35萬/70萬','80':'40萬/80萬','90':'45萬/90萬','100':'50萬/100萬','110':'55萬/110萬','120':'60萬/120萬','130':'65萬/130萬','140':'70萬/140萬','150':'75萬/150萬','160':'80萬/160萬','170':'85萬/170萬','180':'90萬/180萬','190':'95萬/190萬','200':'100萬/200萬','210':'105萬/210萬','220':'110萬/220萬','230':'115萬/230萬','240':'120萬/240萬','250':'125萬/250萬','260':'130萬/260萬','270':'135萬/270萬','280':'140萬/280萬','290':'145萬/290萬','300':'150萬/300萬','310':'155萬/310萬','320':'160萬/320萬','330':'165萬/330萬','340':'170萬/340萬','350':'175萬/350萬','360':'180萬/360萬','370':'185萬/370萬','380':'190萬/380萬','390':'195萬/390萬','400':'200萬/400萬','500':'250萬/500萬','600':'300萬/600萬','700':'350萬/700萬','800':'400萬/800萬','900':'450萬/900萬','1000':'500萬/1000萬','1100':'550萬/1100萬','1200':'600萬/1200萬','1300':'650萬/1300萬','1400':'700萬/1400萬','1500':'750萬/1500萬','1600':'800萬/1600萬','1700':'850萬/1700萬','1800':'900萬/1800萬','1900':'950萬/1900萬','2000':'1000萬/2000萬'}" 
						headerKey="0" headerValue="請選擇" id="caseMoney31" readonly="true"/>
		<s:select name="caseMoney32" list="#{'5':'5萬','6':'6萬','8':'8萬','10':'10萬','15':'15萬','20':'20萬','30':'30萬','40':'40萬','50':'50萬','60':'60萬','70':'70萬','80':'80萬','90':'90萬','100':'100萬','150':'150萬','200':'200萬','250':'250萬','300':'300萬','350':'350萬','400':'400萬','450':'450萬','500':'500萬'}" 
						headerKey="0" headerValue="請選擇" id="caseMoney32" readonly="true"/>
		<s:textfield name="endDate" id="endDate" readonly="true"/>
		<s:textfield name="rate21" id="rate21" readonly="true"/>
		<s:textfield name="rate31" id="rate31" readonly="true"/>
		<s:textfield name="rate32" id="rate32" readonly="true"/>
		<s:textfield name="totalRate" id="totalRate" readonly="true"/>
	</div>
	<div class="myDiv">
		<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">
			確認<img class="hide" src="../images/wait.gif" id="waitImg">
		</button>
		<button type="submit" name="submit" value="back" class="btn btn-info">修改</button>
	</div>
</s:form>