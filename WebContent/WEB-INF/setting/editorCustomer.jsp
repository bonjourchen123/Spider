<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>

<script>
	window.addEventListener("load", init, false);
	function init() {
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("name").addEventListener("blur", checkName,false);
		document.getElementById("birthday").addEventListener("blur",checkBirthday,false);
		$(function() {
			$("#birthday").datepicker({
				onSelect: checkBirthday,
				changeYear : true,
				changeMonth : true,
				dateFormat : "yy-mm-dd",
				yearRange : "-100:-18",
				defaultDate : (new Date().getFullYear()-24)+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
			}).datepicker('option', $.datepicker.regional['zh-TW']);

		});
		document.getElementById("phone").addEventListener("blur", checkPhone,false);
		document.getElementById("permanentAddr").addEventListener("blur",checkPermanentAddr, false);
		
	}

	function wait() {
		document.getElementById("waitImg").removeAttribute("class");
	}
	
	function checkName(){
		var name=this.value;
		var r=/(\s)+/;
		var nameImg=document.getElementById("nameImg");
		var nameTip=document.getElementById("nameTip");
		if(name.length==0 || name==null){
			nameImg.setAttribute("src","../images/no.png");
			nameImg.removeAttribute("class");
			nameTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入姓名!]";
		}else{
			if(r.test(name)){
				nameImg.setAttribute("src","../images/no.png");
				nameImg.removeAttribute("class");
				nameTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				nameImg.setAttribute("src","../images/yes.png");
				nameImg.removeAttribute("class");
				nameTip.innerHTML="";
			}
		}
	}
	
	function checkBirthday(){
		var birthday=this.value;
		var birthdayImg=document.getElementById("birthdayImg");
		var birthdayTip=document.getElementById("birthdayTip");
		if(birthday.length==0 || birthday==null){
			birthdayImg.setAttribute("src","../images/no.png");
			birthdayImg.removeAttribute("class");
			birthdayTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入出生年月日!]";
		}else{
			birthdayImg.setAttribute("src","../images/yes.png");
			birthdayImg.removeAttribute("class");
			birthdayTip.innerHTML="";
		}
	}
	
	function checkPhone(){
		var phone=this.value;
		var r=/(\s)+/;
		var phoneImg=document.getElementById("phoneImg");
		var phoneTip=document.getElementById("phoneTip");
		if(phone.length==0 || phone==null){
			phoneImg.setAttribute("src","../images/no.png");
			phoneImg.removeAttribute("class");
			phoneTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入電話!]";
		}else{
			if(r.test(phone)){
				phoneImg.setAttribute("src","../images/no.png");
				phoneImg.removeAttribute("class");
				phoneTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				phoneImg.setAttribute("src","../images/yes.png");
				phoneImg.removeAttribute("class");
				phoneTip.innerHTML="";
			}
		}
	}

	function checkPermanentAddr(){
		var permanentAddr=this.value;
		var r=/(\s)+/;
		var permanentAddrImg=document.getElementById("permanentAddrImg");
		var permanentAddrTip=document.getElementById("permanentAddrTip");
		if(permanentAddr.length==0 || permanentAddr==null){
			permanentAddrImg.setAttribute("src","../images/no.png");
			permanentAddrImg.removeAttribute("class");
			permanentAddrTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入戶籍地!]";
		}else{
			if(r.test(permanentAddr)){
				permanentAddrImg.setAttribute("src","../images/no.png");
				permanentAddrImg.removeAttribute("class");
				permanentAddrTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				permanentAddrImg.setAttribute("src","../images/yes.png");
				permanentAddrImg.removeAttribute("class");
				permanentAddrTip.innerHTML="";
			}
		}
	}

</script>


<style>		
	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 25%;}
 		
 	.myTable td {
		width: 300px;}
		
	.myTable img {
		height: 18px;
		padding-bottom: 3px;}
	.uiltIn {
		position:relative; 
		float:left;
		left:59%;}
	.title {
		width: 125px;
		position: relative;
		top: 10px;
		left: 46%;}
</style>

<div class="title">編輯客戶</div>
<table class="myTable">
	<s:form method="post" namespace="/setting" action="renewCustomer" theme="simple">
		<tr>
			<td>
			<img src="../images/tip.gif"> 
			姓名:<s:textfield name="name" id="name" size="15px" /> 
				<c:choose>
					<c:when test="${empty errors.name}">
						<c:choose>
							<c:when test="${empty name}">
								<img id="nameImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="nameImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="nameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<s:textfield cssClass="hide" class="hide" name="officeID" list="offices" id="officeID" value="${session.login.officeID}"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="warning" id="nameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.name}</div>
			</td>
		</tr>
		<tr>
			<td>
			<img src="../images/tip.gif"> 
			身分證字號:<s:textfield name="pin" id="pin" readonly="true" cssClass="noUse"/>
			 <c:choose>
				<c:when test="${empty errors.pin}">
					<c:choose>
						<c:when test="${empty pin}">
							<img id="pinImg" class="hide" src="../images/no.png">
						</c:when>
						<c:otherwise>
							<img id="pinImg" src="../images/yes.png">
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<img id="pinImg" src="../images/no.png">
				</c:otherwise>
			</c:choose>
			</td>
		
			<td>
			<img src="../images/tip.gif">
			性別: <s:select name="gender" list="#{'男':'男','女':'女'}" headerKey="0" id="gender" />
				<c:choose>
					<c:when test="${empty errors.gender}">
						<c:choose>
							<c:when test="${empty gender}">
								<img id="genderImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="genderImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="genderImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="pinTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.pin}</div>
			</td>
			<td>
				<div class="warning" id="genderTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.gender}</div>
			</td>
		</tr>

		<tr>
			<td>
			<img src="../images/tip.gif"> 
			出生年月日:<s:textfield name="birthday" id="birthday" />
				<c:choose>
					<c:when test="${empty errors.birthday}">
						<c:choose>
							<c:when test="${empty birthday}">
								<img id="birthdayImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="birthdayImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="birthdayImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			
			<td>
			<img src="../images/tip.gif"> 
			電話:<s:textfield name="phone" id="phone" />
				<c:choose>
					<c:when test="${empty errors.phone}">
						<c:choose>
							<c:when test="${empty phone}">
								<img id="phoneImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="phoneImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="phoneImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="birthdayTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.birthday}</div>
			</td>
			<td>
				<div class="warning" id="phoneTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.phone}</div>
			</td>
		</tr>
		<tr>
			<td>
			<img src="../images/tip.gif"> 
			費率等級:<s:select name="rateLevel" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" headerKey="0" id="rateLevel"/>
				<c:choose>
					<c:when test="${empty errors.rateLevel}">
						<c:choose>
							<c:when test="${empty rateLevel}">
								<img id="rateLevelImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="rateLevelImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="rateLevelImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
			<img src="../images/tip.gif"> 
			賠款紀錄等級:<s:select name="indemnityLevel" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19'}" headerKey="0" id="indemnityLevel"/>
				<c:choose>
					<c:when test="${empty errors.indemnityLevel}">
						<c:choose>
							<c:when test="${empty indemnityLevel}">
								<img id="indemnityLevelImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="indemnityLevelImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="indemnityLevelImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="rateLevelTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.rateLevel}</div>
			</td>
			<td>
				<div class="warning" id="indemnityLevelTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.indemnityLevel}</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<img src="../images/tip.gif"> 
			戶籍地址:<s:textfield name="permanentAddr" id="permanentAddr" size="50" />
				<c:choose>
					<c:when test="${empty errors.permanentAddr}">
						<c:choose>
							<c:when test="${empty permanentAddr}">
								<img id="permanentAddrImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="permanentAddrImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="permanentAddrImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td >
				<div class="warning" id="permanentAddrTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.permanentAddr}</div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">	 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;聯絡地址:<s:textfield name="currentAddr" id="currentAddr" size="50" />
			</td>
		</tr>

		<tr>
			<td><br></td>
		</tr>
		<tr class="uiltIn">
			<td>
				<button type="submit" id="submit" class="btn btn-primary">
					確定<img class="hide" src="../images/wait.gif" id="waitImg"><br>
				</button>
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/setting/customer"/>'">取消</button>
			</td>
		</tr>
	</s:form>	
</table>
<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>