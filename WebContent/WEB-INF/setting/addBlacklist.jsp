<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load", init, false);

	function init() {
		document.getElementById("submit").addEventListener("click", wait, false);
		document.getElementById("reset").addEventListener("click", reset, false);
		document.getElementById("number").addEventListener("blur", checkNumber,false);
		

	}

	function wait() {
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
	}

	function reset() {
		document.getElementById("numberImg").setAttribute("class","hide");
		document.getElementById("numberTip").innerHTML = "";
		document.getElementById("number").value = "";
		document.getElementById("reason").value = "";
		document.getElementById("type").value = "車牌";
		document.getElementById("result").innerHTML="";
		
	}

	function checkNumber() {
		var number =this.value;
		var r=/(\s)+/;//設定空白符號
		var numberImg=document.getElementById("numberImg");
		var numberTip=document.getElementById("numberTip");
		if(number.length==0 || name==null){
			numberImg.setAttribute("src","../images/no.png");
			numberImg.removeAttribute("class");
			numberTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入號碼!]";
		}else{
			if(r.test(number)){
				numberImg.setAttribute("src","../images/no.png");
				numberImg.removeAttribute("class");
				numberTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				checkNumberImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"checkBlacklistEmpty?number="+number,
					success: function(data) {
						if(data==null){
							numberImg.setAttribute("src","../images/yes.png");
							numberImg.removeAttribute("class");
							numberTip.innerHTML="";
							checkNumberImg.setAttribute("class","hide");
						}else{
							numberImg.setAttribute("src","../images/no.png");
							numberImg.removeAttribute("class");
							numberTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[號碼已存在，請更改!]";
							checkNumberImg.setAttribute("class","hide");
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
		left: 29%;
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

.button {
		position:relative; 
		float:left;
		left:67%;
}
.title {
		width: 125px;
		position: relative;
		top: 10px;
		left: 46%;
}	
			
</style>

<div class="title">新建黑名單</div>
<table class="myTable" >
	<s:form method="post" namespace="/setting" action="createBlacklist" theme="simple">
		<tr>
			<td>
			<img src="../images/tip.gif"> 
				類型:<s:select name="type" list="#{'車牌':'車牌','身分證字號':'身分證字號'}" headerKey="0" id="type" />&nbsp; 
			</td>
			<td>
			<img src="../images/tip.gif"> 
				號碼:<s:textfield name="number" id="number" size="15"/>&nbsp;
			<c:choose>
					<c:when test="${empty errors.number}">
						<c:choose>
							<c:when test="${empty number}">
								<img id="numberImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="numberImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="numberImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkNumberImg">
			</td>
		<tr>
			<td></td>
			<td>
				<div class="warning" id="numberTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.number} <%--字串陣列消除字就加上->[0],讓它去抓第一個--%></div>
			</td>
		</tr>
		
		<tr>	
			<td colspan="2">
				事由:<br><s:textarea name="reason" id="reason"/><br>
			</td>
		</tr>
		<tr class="button">
			<td>
			    <button type="submit" id="submit" class="btn btn-primary">
					確認<span id="submitTip" class="hide">中</span> <img class="hide"
						src="../images/wait.gif" id="waitImg">
				</button> 	
				<input type="button" value="清除" id="reset" class="btn btn-info">
		    <!--<input type ="button" onclick="history.back()" value="回到上一頁"> -->
			</td>
		</tr>
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>

<%--<s:form method="post" namespace="/account" action="update" theme="simple">
		<button type="submit" value="update" name="submit" class="btn btn-warning">
			編輯
		</button>
		<button type="submit" value="resetPassword" name="submit" class="btn btn-danger" id="resetPassword">
			重置密碼<img class="hide" src="../images/wait.gif" id="waitResetPasswordImg">
		</button>
		<button type="submit" value="delete" name="submit" class="btn btn-danger" id="delete">
			刪除<img class="hide" src="../images/wait.gif" id="waitDeleteImg">
		</button>
	</s:form> --%>