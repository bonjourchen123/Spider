<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("name").addEventListener("blur",checkName,false);
		document.getElementById("email").addEventListener("blur",checkEmail,false);
		document.getElementById("officeID").addEventListener("blur",checkOfficeID,false);
	}
	
	function wait(){
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
	
	function checkEmail(){
		var email=this.value;
		var r=/^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
		var emailImg=document.getElementById("emailImg");
		var emailTip=document.getElementById("emailTip");
		if(email.length==0 || email==null){
			emailImg.setAttribute("src","../images/no.png");
			emailImg.removeAttribute("class");
			emailTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入電子信箱!]";
		}else{
			if(r.test(email)){
				emailImg.setAttribute("src","../images/yes.png");
				emailImg.removeAttribute("class");
				emailTip.innerHTML="";
			}else{
				emailImg.setAttribute("src","../images/no.png");
				emailImg.removeAttribute("class");
				emailTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[電子信箱格式錯誤!]";
			}
		}
	}
	
	function checkOfficeID(){
		var officeID=document.getElementById("officeID").value;
		var officeIDImg=document.getElementById("officeIDImg");
		var officeIDTip=document.getElementById("officeIDTip");
		if(officeID==0){
			officeIDImg.setAttribute("src","../images/no.png");
			officeIDImg.removeAttribute("class");
			officeIDTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇部門!]";
		}else{
			officeIDImg.setAttribute("src","../images/yes.png");
			officeIDImg.removeAttribute("class");
			officeIDTip.innerHTML="";
		}
	}
</script>

<style>	
	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 25%;}
 	
 	.myTable td {
		width: 250px;}
		
	.myTable img {
		height: 18px;
		padding-bottom: 3px;}
	
	.title {
		width: 100px;
		position: relative;
		top: 10px;
		left: 49%;}
</style>

<div class="title">編輯帳號</div>
<table class="myTable">
	<s:form method="post" namespace="/account" action="updateAccount" theme="simple">
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帳號:<s:textfield name="account" id="account" readonly="true" cssClass="noUse"/>
			</td>
			<td>
				<img src="../images/tip.gif">
				姓名:<s:textfield name="name" id="name" size="15px"/>
				<c:choose>
					<c:when test="${empty errors.name}">
						<img id="nameImg" src="../images/yes.png">
					</c:when>
					<c:otherwise>
						<img id="nameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning"></div>
			</td>
			<td>
				<div class="warning" id="nameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.name}</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;電話:<s:textfield name="phone" id="phone"/>
			</td>
			<td>
				<img src="../images/tip.gif">
				部門:<s:select name="officeID" list="offices" listKey="value" listValue="label" headerKey="0" headerValue="請選擇" id="officeID"/>
				<c:choose>
					<c:when test="${empty errors.officeID}">
						<img id="officeIDImg" src="../images/yes.png">
					</c:when>
					<c:otherwise>
						<img id="officeIDImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="warning" id="officeIDTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.officeID}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				電子信箱:<s:textfield name="email" id="email" size="47px"/>
				<c:choose>
					<c:when test="${empty errors.email}">
						<img id="emailImg" src="../images/yes.png">							
					</c:when>
					<c:otherwise>
						<img id="emailImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="warning" id="emailTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.email}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;權限:<s:checkboxlist name="rolesCode" list="#{'MIS':'系統管理人員','AC':'行政辦事人員','VS':'審核人員','PP':'產品規劃','LD':'主管'}" id="rolesCode"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="warning"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="textCenter">
				<button type="submit" id="submit" class="btn btn-primary">
					確認<img class="hide" src="../images/wait.gif" id="waitImg">
				</button>
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/account/query"/>'">取消</button>
			</td>
		</tr>
	</s:form>
	<tr>
		<td colspan="2" class="textCenter">
			<h1 class="warning" id="result">${errors.result[0]}</h1>
		</td>
	</tr>
</table>