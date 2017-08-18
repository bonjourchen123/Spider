<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("reset").addEventListener("click",reset,false);
		document.getElementById("code").addEventListener("blur", checkCode,false);
		document.getElementById("localName").addEventListener("blur", checkLocalName,false);
		
	}
	
	function wait(){
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
	}
	
	function reset(){
		document.getElementById("codeImg").setAttribute("class","hide");
		document.getElementById("codeTip").innerHTML = "";
		document.getElementById("code").value="";
		document.getElementById("localNameImg").setAttribute("class","hide");
		document.getElementById("localNameTip").innerHTML = "";
		document.getElementById("localName").value="";
		document.getElementById("engName").value="";
 		document.getElementById("descript").value="";
 		document.getElementById("result").innerHTML="";
	}
	
	function checkCode() {
		var code =this.value;
		var r=/(\s)+/;
		var codeImg=document.getElementById("codeImg");
		var codeTip=document.getElementById("codeTip");
		var checkCodeImg=document.getElementById("checkCodeImg");
		if(code.length==0 || code==null){
			codeImg.setAttribute("src","../images/no.png");
			codeImg.removeAttribute("class");
			codeTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入代碼!]";
		}else{
			if(r.test(code)){
				codeImg.setAttribute("src","../images/no.png");
				codeImg.removeAttribute("class");
				codeTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				checkCodeImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"checkBrandEmpty?code="+code,
					success: function(data) {
						if(data==null){
							codeImg.setAttribute("src","../images/yes.png");
							codeImg.removeAttribute("class");
							codeTip.innerHTML="";
							checkCodeImg.setAttribute("class","hide");
						}else{
							codeImg.setAttribute("src","../images/no.png");
							codeImg.removeAttribute("class");
							codeTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[代碼已存在，請更改!]";
							checkCodeImg.setAttribute("class","hide");
						}
					}
				});
			}
		}
	}
	
	function checkLocalName() {
		var localName =this.value;
		var localNameImg=document.getElementById("localNameImg");
		var localNameTip=document.getElementById("localNameTip");
		if(localName.length==0 || localName==null){
			localNameImg.setAttribute("src","../images/no.png");
			localNameImg.removeAttribute("class");
			localNameTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入名稱!]";
		}else{
			localNameImg.setAttribute("src","../images/yes.png");
			localNameImg.removeAttribute("class");
			localNameTip.innerHTML="";
			}
		}

</script>

<style>
.myTable {
		position: relative;
		top: 20px;
		left: 29%;
}

.myTable tr {
		height: 20px;
}

.myTable td {
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

.button {
		position:relative; 
		float:left;
		left:67%;
}

.title { 
 		width: 100px; 
 		position: relative; 
		top: 10px; 
		left: 49%; 
}		 
</style>

<div class="title">新建廠牌</div>
<table class="myTable">
	<s:form method="post" namespace="/car" action=" createBrand" theme="simple">
		<tr>
			<td>
				<img src="../images/tip.gif"> 
					代碼:<s:textfield name="code" id="code" size="12px"/>		
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
					名稱:<s:textfield name="localName" id="localName" size="15px"/>
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
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="codeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.code}</div><%--錯誤提示--%>
			</td>
			<td>
				<div class="warning" id="localNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.localName}</div>
			</td>
		</tr>
		<tr>
			<td>
				英文名稱:<s:textfield name="engName" id="engName"/>&nbsp;&nbsp;&nbsp;
			</td>
		</tr>	
		<tr>	
			<td colspan="2">	
				描述:<br><s:textarea name="descript" id="descript"/>		
			</td>
		</tr>
		<tr class="button">
			<td><br>	
				<button type="submit" id="submit" class="btn btn-primary">
					確認<span id="submitTip" class="hide">中</span> <img class="hide"
						src="../images/wait.gif" id="waitImg">
				</button> 
				<input type="button" value="清除" id="reset" class="btn btn-info">
			</td>
		</tr>
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>