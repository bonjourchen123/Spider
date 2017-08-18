<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("account").addEventListener("blur",checkAccount,false);
		document.getElementById("password").addEventListener("blur",checkPassword,false);
		document.getElementById("submit").addEventListener("click",login,false);
	}
	
	function checkAccount(){
		var account=this.value;
		if(account.length==0 || account==null){
			document.getElementById("accountTip").innerHTML="[請輸入帳號!]";
		}else{
			document.getElementById("accountTip").innerHTML="";
		}
	}
	
	function checkPassword(){
		var password=this.value;
		if(password.length==0 || password==null){
			document.getElementById("passwordTip").innerHTML="[請輸入密碼!]";
		}else{
			document.getElementById("passwordTip").innerHTML="";
		}
	}
	
	function login(){
		document.getElementById("loginTip").removeAttribute("class");
		document.getElementById("loginImg").removeAttribute("class");
	}
</script>

<style>
	.container {
		width: 300px;
		padding: 20px;}
	
	.control-group {
		letter-spacing: 1px;}
		
	.btn{
		letter-spacing: 1px;
		margin-bottom: 10px;}
	
	#loginImg {
		width: 30px;
		padding-bottom: 2px;
		margin: 3px;}
</style>

<center>
<div class="bottomBody">
	<br><br><br>
	<div class="container well">
		<div class="title">登入系統</div>
		<s:form id="indexForm" method="post" namespace="/auth" action="login" cssClass="form-horizontal" theme="simple">
			<div class="control-group">
				帳號：<s:textfield name="account" id="account"/>
			</div>
			<div class="warning" id="accountTip">${errors.account}</div>
			<div class="control-group">
				密碼：<s:password name="password" id="password"/>
			</div>
			<div class="warning" id="passwordTip">${errors.password}</div>
			<button type="submit" class="btn btn-success" id="submit">
				登入<span id="loginTip" class="hide">中</span>
				<img id="loginImg" class="hide" src="../images/loginWait.gif">
			</button>
			<div class="tip">[忘記密碼請洽系統管理人員]</div>
		</s:form>
	</div>
	<h1 class="warning">${errors.result[0]}</h1>
<%-- 	<s:actionerror theme="simple" cssClass="alert alert-danger"/> --%>
<%-- 	<s:actionmessage theme="simple" cssClass="alert alert-danger"/>	 --%>
</div>
</center>