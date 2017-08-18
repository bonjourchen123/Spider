<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- ================================================================================================ --%>

<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("localName").addEventListener("blur", checkLocalName,false);
	}
	
	function wait(){
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
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

<div class="title">編輯廠牌</div>
<table class="myTable">
	<s:form method="post" namespace="/car" action="updateBrand" theme="simple">
		<tr>
			<td>
				代碼:<s:textfield name="code" id="code" readonly="true" cssClass="noUse" size="12px"/><%--鎖死不讓使用--%>
			</td>
			<td>	
				<img src="../images/tip.gif"> 
					名稱:<s:textfield name="localName" id="localName" size="15px"/>&nbsp;	
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
			<td></td>
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
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/car/brand"/>'">取消</button>
			</td>
		</tr>
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>