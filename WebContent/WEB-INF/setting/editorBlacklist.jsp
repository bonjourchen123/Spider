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
		document.getElementById("number").addEventListener("blur", checkNumber,false);
	}

	function wait() {
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
	}


	function checkNumber() {
		var number =this.value;
		var r=/(\s)+/;
		var numberImg=document.getElementById("numberImg");
		var numberTip=document.getElementById("numberTip");
		var oldNumber=document.getElementById("oldNumber").value;
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
				if(oldNumber==number){
					numberImg.setAttribute("src","../images/yes.png");
					numberImg.removeAttribute("class");
					numberTip.innerHTML="";
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
		padding-bottom: 3px;}
.button {
		position:relative; 
		float:left;
		left:67%;
}
.title {
		width: 125px;
		position: relative;
		top: 10px;
		left: 46%;}	
			
</style>

<div class="title">編輯黑名單</div>
<table class="myTable" >
	<s:form method="post" namespace="/setting" action="updateBlacklist" theme="simple">
 		<s:textfield name="oldNumber" id="oldNumber" cssClass="hide" value="${number}"/><%--value="${number}"是name="number"這裡的name--%>
 		<s:textfield name="id" id="id" cssClass="hide" /><%--抓號碼的name改名叫id再把id值寫回程式裡去呼叫;cssClass="hide"是藏起來的意思 --%>
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
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/setting/blacklist"/>'">取消</button>
			</td>
		</tr>
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>
		