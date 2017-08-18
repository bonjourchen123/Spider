<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- ================================================================================================ --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- ================================================================================================ --%>
<script>
	window.addEventListener("load",init,false);
	
	function init(){
		document.getElementById("submit").addEventListener("click",wait,false);
		document.getElementById("code").addEventListener("blur", checkCode,false);
		document.getElementById("localName").addEventListener("blur", checkLocalName,false);
		document.getElementById("cc").addEventListener("blur", checkCC,false);
	}
	
	function wait(){
		document.getElementById("submitTip").removeAttribute("class");
		document.getElementById("waitImg").removeAttribute("class");
	}
	
	function checkCode() {
		var code =this.value;
		var r=/(\s)+/;
		var brandCode=document.getElementById("brandCode").value;//廠牌代碼
		var seriesCode=document.getElementById("seriesCode").value;//車系代碼
     	var oldCode=document.getElementById("oldCode").value;
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
				if(oldCode==code){
					codeImg.setAttribute("src","../images/yes.png");
					codeImg.removeAttribute("class");
					codeTip.innerHTML="";
				}else{
					checkCodeImg.removeAttribute("class");
					$.ajax({
						type:'GET',
						contentType:'application/json; charset=utf-8',		
						url:"queryModelAjax?code="+brandCode+seriesCode+code,
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
	
	function checkCC() {
		var cc =this.value;
		var ccImg=document.getElementById("ccImg");
		var ccTip=document.getElementById("ccTip");
		var r=/(\D)+/;
		if(cc==null||cc.length==0){
			ccImg.setAttribute("src","../images/no.png");
			ccImg.removeAttribute("class");
			ccTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入CC數!]";
		}else{
			if(r.test(cc)){
				ccImg.setAttribute("src","../images/no.png");
				ccImg.removeAttribute("class");
				ccTip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入整數!]";
			}else{
				ccImg.setAttribute("src","../images/yes.png");
				ccImg.removeAttribute("class");
	 			ccTip.innerHTML="";
			}
		}
	}
	
</script>	

<style>

.myTable {
		position: relative;
		top: 20px;
		left: 27%;
}

.myTable tr {
		height: 20px;
}

.myTable td {
		width: 250px;
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
		left:74%;
}

.title { 
 		width: 100px; 
 		position: relative; 
		top: 10px; 
		left: 49%; 
}

</style>


<div class="title" >編輯車型</div>
<table class="myTable" >
	<s:form method="post" namespace="/car" action=" updateCarModel" theme="simple">
		<s:textfield name="id" id="id" readonly="true" cssClass="hide"/>
		<s:textfield name="oldCode" id="oldCode" cssClass="hide" value="${code}"/>
		<tr>
			<td>
				廠牌代碼:<s:textfield name="brandCode" id="brandCode" size="8px"  readonly="true" cssClass="noUse"/>
			</td>
			<td>
				廠牌名稱:<s:textfield name="brand" id="brand" readonly="true" cssClass="noUse"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>		
		</tr>

		<tr>
			<td>
				車系代碼:<s:textfield name="seriesCode" id="seriesCode" size="8px" readonly="true" cssClass="noUse"/>
			</td>
			<td>
				車系名稱:<s:textfield name="carSeries" id="carSeries"  readonly="true" cssClass="noUse"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>		
		</tr>
		
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif"> 
				代碼:<s:textfield name="brandId" id="brandId" size="2px" readonly="true" cssClass="noUse"/>
				<s:textfield name="seriesId" id="seriesId"   size="2px" readonly="true" cssClass="noUse"/>	
				<s:textfield name="code" id="code"  size="12px"/>
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
		</tr>
		<tr>
			<td>
				<div class="warning" id="codeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.code}</div>
			</td>
		</tr>	
		
		<tr>
			<td>
				<img src="../images/tip.gif"> 
				名稱:<s:textfield name="localName" id="localName"/>
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
			
			<c:choose>
				<c:when test="${empty cc && empty  errors.cc}">
					<td id="ccTd" class="hide">
				</c:when>
				<c:otherwise>
					<td id="ccTd">
				</c:otherwise>
			</c:choose>	
				<img src="../images/tip.gif">
				CC數:<s:textfield name="cc" id="cc" size="10px" />
				<c:choose>
					<c:when test="${empty errors.cc}">
						<c:choose>
							<c:when test="${empty cc}">
								<img id="ccImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="ccImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="ccImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>	
		<tr>
			<td>
				<div class="warning" id="localNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.localName}</div>	
			</td>
			<td>
				<div class="warning" id="ccTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.cc}</div>	
			</td>
		</tr>	
			
		<tr class="button">
			<td><br>	
				<button type="submit" id="submit" class="btn btn-primary">
					確認<span id="submitTip" class="hide">中</span> <img class="hide"
						src="../images/wait.gif" id="waitImg">
				</button> 
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/car/model"/>'">取消</button>
			</td>
		</tr>
	</s:form>
</table><br><br>
		<h1 class="warning" id="result">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.result[0]}</h1>