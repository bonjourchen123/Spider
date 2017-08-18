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
		document.getElementById("localName").addEventListener("blur", checkLocalName, false);
		document.getElementById("type").addEventListener("blur", checkType,	false);
	}

	function wait() {
		document.getElementById("waitImg").removeAttribute("class");
	}

	function checkLocalName() {
		var localName = this.value;
		var r = /(\s)+/;
		var localNameImg = document.getElementById("localNameImg");
		var localNameTip = document.getElementById("localNameTip");
		if (localName.length == 0 || localName == null) {
			localNameImg.setAttribute("src", "../images/no.png");
			localNameImg.removeAttribute("class");
			localNameTip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入名稱!]";
		} else {
			if (r.test(localName)) {
				localNameImg.setAttribute("src", "../images/no.png");
				localNameImg.removeAttribute("class");
				localNameTip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			} else {
				localNameImg.setAttribute("src", "../images/yes.png");
				localNameImg.removeAttribute("class");
				localNameTip.innerHTML = "";
			}
		}
	}

	function checkType() {
		var type = document.getElementById("type").value;
		var typeImg = document.getElementById("typeImg");
		var typeTip = document.getElementById("typeTip");
		if (type == 0) {
			typeImg.setAttribute("src", "../images/no.png");
			typeImg.removeAttribute("class");
			typeTip.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇車型!]";
		} else {
			typeImg.setAttribute("src", "../images/yes.png");
			typeImg.removeAttribute("class");
			typeTip.innerHTML = "";
		}
	}
</script>

<style>
.myTable {
	position: relative;
	top: 20px;
	left: 25%;
}

.myTable td {
	width: 260px;
}

.myTable img {
	height: 18px;
	padding-bottom: 3px;
}

.uiltIn {
	position: relative;
	float: left;
	left: 70%;
}

.title {
	width: 125px;
	position: relative;
	top: 10px;
	left: 47%;
}

</style>

<div class="title">編輯車系</div>
<table class="myTable">
	<s:form method="post" namespace="/car" action="updateCarseries"
		theme="simple">

		<tr>
			<td>
				<img src="../images/tip.gif"> 廠牌代碼:<s:textfield name="brandCode" id="brandCode" size="10px" readonly="true" cssClass="noUse"/>
				<c:choose>
					<c:when test="${empty errors.brandCode}">
						<c:choose>
							<c:when test="${empty brandCode}">
								<img id="brandCodeImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="brandCodeImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="brandCodeImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkBrandCodeImg">
			</td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;廠牌名稱:<s:textfield
					name="brandName" id="brand" readonly="true" cssClass="noUse"
					size="10px" />
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="brandCodeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.brandCode}</div>
			</td>
			<td>
				<div class="warning" id="brandTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.brand}</div>
			</td>
		</tr>
		<tr>
			<td>
			<img src="../images/tip.gif"> 代碼:<s:textfield	name="code" id="code" size="10px" readonly="true" cssClass="noUse"/>
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
			<td>&nbsp;&nbsp;&nbsp;<img src="../images/tip.gif"> 名稱:<s:textfield name="localName" id="localName" />
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
				<div class="warning" id="codeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.code}</div>
			</td>
			<td>
				<div class="warning" id="localNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.localName}</div>
			</td>

		</tr>
		<tr>
			<td><img src="../images/tip.gif"> 類型:<s:select name="type"
					list="#{'機車':'機車','小客車':'小客車','小貨車':'小貨車','大客車':'大客車','大貨車':'大貨車','小型特種車':'小型特種車','大型特種車':'大型特種車','曳引車':'曳引車','客貨兩用車':'客貨兩用車'}"
					headerKey="0" headerValue="所有類型" id="type" /> <c:choose>
					<c:when test="${empty errors.type}">
						<c:choose>
							<c:when test="${empty type}">
								<img id="typeImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="typeImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="typeImg" src="../images/no.png">
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="typeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.type}</div>
			</td>
		</tr>
		<tr class="uiltIn">
			<td>
				<button type="submit" id="submit" class="btn btn-primary">
					送出<img class="hide" src="../images/wait.gif" id="waitImg"><br>
				</button>
				<button type="button" class="btn btn-info" onclick="location.href='<c:url value="/car/series"/>'">取消</button>
			</td>
		</tr>
	</s:form>
	
</table>