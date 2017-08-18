<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("reset").addEventListener("click",reset,false);
		document.getElementById("password").addEventListener("blur",oldPassword,false);
		document.getElementById("newPassword").addEventListener("blur",checkPassword,false);
		document.getElementById("checkPassword").addEventListener("blur",doubleCheck,false);
	}
	
	function wait(){
		document.getElementById("waitImg").removeAttribute("class");
	}
	
	function reset(){
		document.getElementById("password").value="";
		document.getElementById("passwordTip").innerHTML="";
		document.getElementById("passwordImg").setAttribute("class","hide");
		document.getElementById("newPassword").value="";
		document.getElementById("newPasswordTip").innerHTML="";
		document.getElementById("newPasswordImg").setAttribute("class","hide");
		document.getElementById("checkPassword").value="";
		document.getElementById("checkPasswordTip").innerHTML="";
		document.getElementById("checkPasswordImg").setAttribute("class","hide");
		document.getElementById("result").innerHTML="";
	}
	
	function oldPassword(){
		var password=this.value;
		var passwordImg=document.getElementById("passwordImg");
		var passwordTip=document.getElementById("passwordTip");
		if(password.length==0 || password==null){
			passwordImg.setAttribute("src","../images/no.png");
			passwordImg.removeAttribute("class");
			passwordTip.innerHTML="[請輸入密碼!]";
		}else{
			passwordImg.setAttribute("src","../images/yes.png");
			passwordImg.removeAttribute("class");
			passwordTip.innerHTML="";
		}
	}
	
	function checkPassword(){
		var newPassword=this.value;
		var r=/(?!.*\s)(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,})/;
		var newPasswordImg=document.getElementById("newPasswordImg");
		var newPasswordTip=document.getElementById("newPasswordTip");
		if(newPassword.length==0 || newPassword==null){
			newPasswordImg.setAttribute("src","../images/no.png");
			newPasswordImg.removeAttribute("class");
			newPasswordTip.innerHTML="[請輸入新密碼!]";
		}else{
			if(r.test(newPassword)){
				newPasswordImg.setAttribute("src","../images/yes.png");
				newPasswordImg.removeAttribute("class");
				newPasswordTip.innerHTML="";
			}else{
				newPasswordImg.setAttribute("src","../images/no.png");
				newPasswordImg.removeAttribute("class");
				newPasswordTip.innerHTML="[密碼強度不足]";
			}
		}
	}
	
	function doubleCheck(){
		var newPasswordImg=document.getElementById("newPasswordImg");
		if(newPasswordImg.getAttribute("src")!="../images/no.png"){
			var checkPassword=this.value;
			var checkPasswordImg=document.getElementById("checkPasswordImg");
			var checkPasswordTip=document.getElementById("checkPasswordTip");
			if(checkPassword.length==0 || checkPassword==null){
				checkPasswordImg.setAttribute("src","../images/no.png");
				checkPasswordImg.removeAttribute("class");
				checkPasswordTip.innerHTML="[請輸入新密碼!]";
			}else{
				var newPassword=document.getElementById("newPassword").value;
				if(newPassword==checkPassword){
					checkPasswordImg.setAttribute("src","../images/yes.png");
					checkPasswordImg.removeAttribute("class");
					checkPasswordTip.innerHTML="";
				}else{
					checkPasswordImg.setAttribute("src","../images/no.png");
					checkPasswordImg.removeAttribute("class");
					checkPasswordTip.innerHTML="[請重新確認新密碼!]";
				}
			}
		}
	}
</script>

<style>	
 	.myTable {
 		position: relative; 
 		top: 20px; 
 		left: 27%;} 
 		
 	.myTable td {
		width: 180px;}
		
	.myTable img {
		height: 18px;
		padding-bottom: 3px; }
		
	.title {
		width: 100px;
		position: relative;
		top: 10px;
		left: 49%;}
</style>

<div class="title">修改密碼</div>
<table class="myTable">
	<s:form method="post" namespace="/account" action="changePassword" theme="simple">
		<tr>
			<td class="textRight">
				<img src="../images/tip.gif">原密碼:
			</td>
			<td>
				<s:password name="password" id="password"/>
				<img id="passwordImg" class="hide" src="../images/no.png">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="warning" id="passwordTip">${errors.password}</div>
			</td>
		</tr>
		<tr>
			<td class="textRight">
				<img src="../images/tip.gif">新密碼:
			</td>
			<td>
				<s:password name="newPassword" id="newPassword"/>
				<img id="newPasswordImg" class="hide" src="../images/no.png">
			</td>
		</tr>
		<tr>
			<td colspan="2" class="textRight">
				<div class="tip">[至少6個字且包含英文、數字、特殊字元(!@#$%^&amp;*)]</div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="warning" id="newPasswordTip" >${errors.newPassword}</div>
			</td>
		</tr>
		<tr>
			<td class="textRight">
				<img src="../images/tip.gif">確認新密碼:
			</td>
			<td>	
				<s:password name="checkPassword" id="checkPassword"/>
				<img id="checkPasswordImg" class="hide" src="../images/no.png">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="warning" id="checkPasswordTip">${errors.checkPassword}</div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" id="submit" class="btn btn-primary">
					確認<img class="hide" src="../images/wait.gif" id="waitImg">
				</button>
				<input type="button" value="清除" id="reset" class="btn btn-info">
			</td>
		</tr>
	</s:form>
	<tr>
		<td></td>
		<td>
			<h1 class="warning" id="result">${errors.result[0]}</h1>
		</td>
	</tr>
</table>