<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load", init, false);

	function init() {
		document.getElementById("submit").addEventListener("click", wait, false);
		document.getElementById("reset").addEventListener("click", reset, false);
		document.getElementById("code").addEventListener("blur", checkCode, false);
		document.getElementById("localName").addEventListener("blur", checkLocalName, false);
		document.getElementById("engName").addEventListener("blur", checkEngName, false);

	}

	function wait() {
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
	}

	function reset() {
		document.getElementById("code").value="";
		document.getElementById("codeTip").innerHTML="";
		document.getElementById("codeImg").setAttribute("class","hide");
		document.getElementById("localName").value="";
		document.getElementById("localNameTip").innerHTML="";
		document.getElementById("localNameImg").setAttribute("class","hide");
		document.getElementById("engName").value="";
		document.getElementById("engNameTip").innerHTML="";
		document.getElementById("engNameImg").setAttribute("class","hide");
		document.getElementById("result").innerHTML ="";
	}
	
	function checkCode(){
		var code = this.value;
		//var r1 = /^[a-zA-Z]+$/;   //字串中有英文字元
		var r2 = /^[\s]+$/;   //字串中有空白字元
		var img = document.getElementById("codeImg");
		var tip = document.getElementById("codeTip");
		if(code.length == 0 || code == null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入代碼!]";
		}else{
// 			if(!r1.test(code)){
// 				img.setAttribute("src","../images/no.png");
// 				img.removeAttribute("class");
// 				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入英文代碼!]";
// 			}else 
				if(r2.test(code)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				checkCodeImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"checkOfficeCodeEmpty?code="+code,
					success: function(data) {
						if(data==null){
							img.setAttribute("src","../images/yes.png");
							img.removeAttribute("class");
							tip.innerHTML="";
							checkCodeImg.setAttribute("class","hide");
						}else{
							img.setAttribute("src","../images/no.png");
							img.removeAttribute("class");
							tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[代碼已存在，請更改!]";
							checkCodeImg.setAttribute("class","hide");
						}
					}
				});
			}
		}
	}
	
	function checkLocalName(){
		var localName = this.value;
		var r1 = /^[0-9]+$/;   //字串中有數字
		var r2 = /^[\s]+$/;   //字串中有空白字元
		var r3 = /^[a-zA-Z]+$/;   //字串中有英文字元 
		var img = document.getElementById("localNameImg");
		var tip = document.getElementById("localNameTip");
		if(localName.length == 0 || localName == null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入名稱!]";
		}else{
			if(r1.test(localName)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用數字!]";
			}else if(r2.test(localName)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else if(r3.test(localName)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請使用中文字元!]";
			}else{
				checkLocalNameImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"checkOfficeLocalNameEmpty?localName="+localName,
					success: function(data) {
						if(data==null){
							img.setAttribute("src","../images/yes.png");
							img.removeAttribute("class");
							tip.innerHTML="";
							checkLocalNameImg.setAttribute("class","hide");
						}else{
							img.setAttribute("src","../images/no.png");
							img.removeAttribute("class");
							tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[部門名稱已存在，請更改!]";
							checkLocalNameImg.setAttribute("class","hide");
						}
					}
				});
			}
		}
	}
	
	function checkEngName(){
		var engName=this.value;
		var r = /^[0-9a-zA-Z\s]+$/;   //字串中有數字和英文和空白字元
		var img = document.getElementById("engNameImg");
		var tip = document.getElementById("engNameTip");
		if(engName.length == 0 || engName == null){
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
			checkEngNameImg.setAttribute("class","hide");
		}else{
			if(!r.test(engName)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請使用英文、數字或空白字元!]";
			}else{
				checkEngNameImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"checkOfficeEngNameEmpty?engName="+engName,
					success: function(data) {
						if(data==null ){
							img.setAttribute("src","../images/yes.png");
							img.removeAttribute("class");
							tip.innerHTML="";
							checkEngNameImg.setAttribute("class","hide");
						}else {
							img.setAttribute("src","../images/no.png");
							img.removeAttribute("class");
							tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[英文名稱已存在，請更改!]";
							checkEngNameImg.setAttribute("class","hide");
						}
					}
				});
			}
		}
	}
</script>

<style>
	.myTable {
			position: relative;
			top: 20px;
			left: 15%;
	}
	.button {
		position:relative; 
		float:left;
		left:112%;
	}
	.myTable tr {
			height: 20px;
	}
	
	.myTable td{
			width: 230px;
	}
	#result {
			width: 500px;
			position: relative;
			left: 230px;
	}
	
	.myTable img {
			height: 18px;
			padding-bottom: 3px;
}
</style>
<center>
<div class="title">&nbsp;&nbsp;&nbsp;新建部門</div>
</center>
<table class="myTable" >
	<s:form method="post" namespace="/setting" action="createOffice" theme="simple">
		<tr>
			<td>
			<img src="../images/tip.gif">
				代碼:<s:textfield name="code" id="code" size="15"/>&nbsp;
				<c:choose>
					<c:when test="${empty errors.code}">
						<c:choose>
							<c:when test="${empty code}">
								<img id="codeImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="codeImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="codeImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkCodeImg"> 
			</td>
			<td>
			<img src="../images/tip.gif"> 
				名稱:<s:textfield name="localName" id="localName" size="15"/>&nbsp;
				<c:choose>
					<c:when test="${empty errors.localName}">
						<c:choose>
							<c:when test="${empty localName}">
								<img id="localNameImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="localNameImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="localNameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkLocalNameImg">
			</td>
			<td>
				英文名稱:<s:textfield name="engName" id="engName" size="15"/>&nbsp;
				<c:choose>
					<c:when test="${empty errors.engName}">
						<c:choose>
							<c:when test="${empty engName}">
								<img id="engNameImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="engNameImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="engNameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkEngNameImg">
			</td>
		<tr>
			<td>
				<div class="warning" id="codeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.code}</div>
			</td>
			<td>
				<div class="warning" id="localNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.localName}</div>
			</td>
			<td>
				<div class="warning" id="engNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.engName}</div>
			</td>
		</tr>
		
		<tr class="button">
			<td>
			    <button type="submit" id="submit" class="btn btn-primary">
					確認<span id="submitTip" class="hide">中</span><img class="hide" src="../images/wait.gif" id="waitImg">
				</button> 	
				<input type="button" value="清除" id="reset" class="btn btn-info">
		    <!--<input type ="button" onclick="history.back()" value="回到上一頁"> -->
			</td>
		</tr>
		
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>