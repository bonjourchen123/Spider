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
		document.getElementById("same").addEventListener("click",same,false);
		document.getElementById("applicantPin").addEventListener("blur",checkApplicantPin,false);
		document.getElementById("applicantName").addEventListener("blur",checkApplicantName,false);
		document.getElementById("applicantBirthday").addEventListener("blur",checkApplicantBirthday,false);
		document.getElementById("applicantPermanentAddr").addEventListener("blur",checkApplicantPermanentAddr,false);
		document.getElementById("insurantPin").addEventListener("blur",checkInsurantPin,false);
		document.getElementById("insurantName").addEventListener("blur",checkInsurantName,false);
		document.getElementById("insurantBirthday").addEventListener("blur",checkInsurantBirthday,false);
		document.getElementById("insurantPermanentAddr").addEventListener("blur",checkInsurantPermanentAddr,false);
		document.getElementById("underwriterAccount").addEventListener("blur",checkUnderwriterAccount,false);
		document.getElementById("productID").addEventListener("blur",checkProductID,false);
		document.getElementById("startDate").addEventListener("blur",checkStartDate,false);
		document.getElementById("carNo").addEventListener("blur",checkCarNo,false);
		document.getElementById("carmodelCode").addEventListener("blur",checkCarmodelCode,false);
		document.getElementById("purpose").addEventListener("blur",checkPurpose,false);
		document.getElementById("tonnage").addEventListener("blur",checkTonnage,false);
		document.getElementById("seats").addEventListener("blur",checkSeats,false);
		document.getElementById("caseMoney31").addEventListener("blur",checkCaseMoney31,false);
		document.getElementById("caseMoney32").addEventListener("blur",checkCaseMoney32,false);
		$("#applicantBirthday").datepicker({
			onSelect: checkApplicantBirthday,
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-100:-18",
			defaultDate : (new Date().getFullYear()-20)+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
		}).datepicker('option', $.datepicker.regional['zh-TW']);
		$("#insurantBirthday").datepicker({
			onSelect: checkInsurantBirthday,
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-100:-18",
			defaultDate : (new Date().getFullYear()-20)+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate()
		}).datepicker('option', $.datepicker.regional['zh-TW']);
		$("#startDate").datepicker({
			onSelect: checkStartDate,
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "+0:+10"
		}).datepicker('option', $.datepicker.regional['zh-TW']);
		$("#productionDate").datepicker({
			onSelect: checkInsurantBirthday,
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-20:+0",
		}).datepicker('option', $.datepicker.regional['zh-TW']);
	}
	
	function wait(){
		document.getElementById("waitImg").removeAttribute("class");
	}
	
	function reset(){
		document.getElementById("applicantPin").value="";
		document.getElementById("applicantPinTip").innerHTML="";
		document.getElementById("applicantPinImg").setAttribute("class","hide");
		document.getElementById("applicantName").value="";
		document.getElementById("applicantNameTip").innerHTML="";
		document.getElementById("applicantNameImg").setAttribute("class","hide");
		document.getElementById("applicantBirthday").value="";
		document.getElementById("applicantBirthdayTip").innerHTML="";
		document.getElementById("applicantBirthdayImg").setAttribute("class","hide");
		document.getElementById("applicantPermanentAddr").value="";
		document.getElementById("applicantPermanentAddrTip").innerHTML="";
		document.getElementById("applicantPermanentAddrImg").setAttribute("class","hide");
		document.getElementById("insurantPin").value="";
		document.getElementById("insurantPinTip").innerHTML="";
		document.getElementById("insurantPinImg").setAttribute("class","hide");
		document.getElementById("insurantName").value="";
		document.getElementById("insurantNameTip").innerHTML="";
		document.getElementById("insurantNameImg").setAttribute("class","hide");
		document.getElementById("insurantBirthday").value="";
		document.getElementById("insurantBirthdayTip").innerHTML="";
		document.getElementById("insurantBirthdayImg").setAttribute("class","hide");
		document.getElementById("insurantPermanentAddr").value="";
		document.getElementById("insurantPermanentAddrTip").innerHTML="";
		document.getElementById("insurantPermanentAddrImg").setAttribute("class","hide");
		document.getElementById("underwriterAccount").value="";
		document.getElementById("underwriterAccountTip").innerHTML="";
		document.getElementById("underwriterAccountImg").setAttribute("class","hide");
		document.getElementById("productID").value=0;
		document.getElementById("productIDTip").innerHTML="";
		document.getElementById("productIDImg").setAttribute("class","hide");
		document.getElementById("startDate").value="";
		document.getElementById("startDateTip").innerHTML="";
		document.getElementById("startDateImg").setAttribute("class","hide");
		document.getElementById("carNo").value="";
		document.getElementById("carNoTip").innerHTML="";
		document.getElementById("carNoImg").setAttribute("class","hide");
		document.getElementById("carmodelCode").value="";
		document.getElementById("carmodelCodeTip").innerHTML="";
		document.getElementById("carmodelCodeImg").setAttribute("class","hide");
		document.getElementById("purpose").value=0;
		document.getElementById("purposeTip").innerHTML="";
		document.getElementById("purposeImg").setAttribute("class","hide");
		document.getElementById("tonnage").value="";
		document.getElementById("tonnageTip").innerHTML="";
		document.getElementById("tonnageImg").setAttribute("class","hide");
		document.getElementById("seats").value="";
		document.getElementById("seatsTip").innerHTML="";
		document.getElementById("seatsImg").setAttribute("class","hide");
		document.getElementById("table31").setAttribute("class","hide");
		document.getElementById("caseMoney31").value=0;
		document.getElementById("caseMoney31Tip").innerHTML="";
		document.getElementById("caseMoney31Img").setAttribute("class","hide");
		document.getElementById("table32").setAttribute("class","hide");
		document.getElementById("caseMoney32").value=0;
		document.getElementById("caseMoney32Tip").innerHTML="";
		document.getElementById("caseMoney32Img").setAttribute("class","hide");
		document.getElementById("applicantPhone").value="";
		document.getElementById("applicantCurrentAddr").value="";
		document.getElementById("insurantPhone").value="";
		document.getElementById("insurantCurrentAddr").value="";
		document.getElementById("productionDate").value="";
		document.getElementById("carmodelName").value="";
		document.getElementById("brand").value="";
		document.getElementById("carseries").value="";
		document.getElementById("cartypeLocalName").value="";
	}
	
	function same(){
		document.getElementById("insurantPin").value=document.getElementById("applicantPin").value;
		document.getElementById("insurantName").value=document.getElementById("applicantName").value;
 		document.getElementById("insurantGender男").checked=document.getElementById("applicantGender男").checked;
 		document.getElementById("insurantGender女").checked=document.getElementById("applicantGender女").checked;
		document.getElementById("insurantBirthday").value=document.getElementById("applicantBirthday").value;
		document.getElementById("insurantPhone").value=document.getElementById("applicantPhone").value;
		document.getElementById("insurantPermanentAddr").value=document.getElementById("applicantPermanentAddr").value;
		document.getElementById("insurantCurrentAddr").value=document.getElementById("applicantCurrentAddr").value;
		document.getElementById("insurantPinImg").setAttribute("class","hide");
		document.getElementById("insurantPinTip").innerHTML="";
		document.getElementById("insurantNameImg").setAttribute("class","hide");
		document.getElementById("insurantNameTip").innerHTML="";
		document.getElementById("insurantBirthdayImg").setAttribute("class","hide");
		document.getElementById("insurantBirthdayTip").innerHTML="";
		document.getElementById("insurantPermanentAddrImg").setAttribute("class","hide");
		document.getElementById("insurantPermanentAddrTip").innerHTML="";
	}
	
	function checkApplicantPin(){
		var value=this.value;
		var officeID=document.getElementById("officeID").value;
		var r=/[a-zA-Z]\d{9}/;
		var img=document.getElementById("applicantPinImg");
		var tip=document.getElementById("applicantPinTip");
		var checkImg=document.getElementById("checkApplicantPinImg");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入身分證字號!]";
		}else{
			if(!r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[身分證字號格式錯誤!]";
			}else{
				checkImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"../setting/queryCustomerAjax?officeID="+officeID+"&pin="+value,
					success: function(data) {
						img.setAttribute("src","../images/yes.png");
						img.removeAttribute("class");
						tip.innerHTML="";
						checkImg.setAttribute("class","hide");
						if(data!=null){
							document.getElementById("applicantName").value=data[0];
							document.getElementById("applicantNameImg").setAttribute("src","../images/yes.png");
							document.getElementById("applicantNameImg").removeAttribute("class");
							document.getElementById("applicantNameTip").innerHTML="";
							document.getElementById("applicantGender"+data[1]).checked=true;
							document.getElementById("applicantBirthday").value=data[2];
							document.getElementById("applicantBirthdayImg").setAttribute("src","../images/yes.png");
							document.getElementById("applicantBirthdayImg").removeAttribute("class");
							document.getElementById("applicantBirthdayTip").innerHTML="";
							document.getElementById("applicantPhone").value=data[3];
							document.getElementById("applicantPermanentAddr").value=data[4];
							document.getElementById("applicantPermanentAddrImg").setAttribute("src","../images/yes.png");
							document.getElementById("applicantPermanentAddrImg").removeAttribute("class");
							document.getElementById("applicantPermanentAddrTip").innerHTML="";
							document.getElementById("applicantCurrentAddr").value=data[5];
						}
					}
				});
			}
		}
	}
	
	function checkApplicantName(){
		var value=this.value;
		var r=/(\s)+/;
		var img=document.getElementById("applicantNameImg");
		var tip=document.getElementById("applicantNameTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入姓名!]";
		}else{
			if(r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkApplicantBirthday(){
		var value=this.value;
		var img=document.getElementById("applicantBirthdayImg");
		var tip=document.getElementById("applicantBirthdayTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇出生日期!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
		}
	}
	
	function checkApplicantPermanentAddr(){
		var value=this.value;
		var r=/(\s)+/;
		var img=document.getElementById("applicantPermanentAddrImg");
		var tip=document.getElementById("applicantPermanentAddrTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入戶籍地址!]";
		}else{
			if(r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkInsurantPin(){
		var value=this.value;
		var officeID=document.getElementById("officeID").value;
		var r=/[a-zA-Z]\d{9}/;
		var img=document.getElementById("insurantPinImg");
		var tip=document.getElementById("insurantPinTip");
		var checkImg=document.getElementById("checkInsurantPinImg");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入身分證字號!]";
		}else{
			if(!r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[身分證字號格式錯誤!]";
			}else{
				checkImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"../setting/queryCustomerAjax?officeID="+officeID+"&pin="+value,
					success: function(data) {
						img.setAttribute("src","../images/yes.png");
						img.removeAttribute("class");
						tip.innerHTML="";
						checkImg.setAttribute("class","hide");
						if(data!=null){
							document.getElementById("insurantName").value=data[0];
							document.getElementById("insurantNameImg").setAttribute("src","../images/yes.png");
							document.getElementById("insurantNameImg").removeAttribute("class");
							document.getElementById("insurantNameTip").innerHTML="";
							document.getElementById("insurantGender"+data[1]).checked=true;
							document.getElementById("insurantBirthday").value=data[2];
							document.getElementById("insurantBirthdayImg").setAttribute("src","../images/yes.png");
							document.getElementById("insurantBirthdayImg").removeAttribute("class");
							document.getElementById("insurantBirthdayTip").innerHTML="";
							document.getElementById("insurantPhone").value=data[3];
							document.getElementById("insurantPermanentAddr").value=data[4];
							document.getElementById("insurantPermanentAddrImg").setAttribute("src","../images/yes.png");
							document.getElementById("insurantPermanentAddrImg").removeAttribute("class");
							document.getElementById("insurantPermanentAddrTip").innerHTML="";
							document.getElementById("insurantCurrentAddr").value=data[5];
						}
					}
				});
			}
		}
	}
	
	function checkInsurantName(){
		var value=this.value;
		var r=/(\s)+/;
		var img=document.getElementById("insurantNameImg");
		var tip=document.getElementById("insurantNameTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入姓名!]";
		}else{
			if(r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkInsurantBirthday(){
		var value=this.value;
		var img=document.getElementById("insurantBirthdayImg");
		var tip=document.getElementById("insurantBirthdayTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇出生日期!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
		}
	}
	
	function checkInsurantPermanentAddr(){
		var value=this.value;
		var r=/(\s)+/;
		var img=document.getElementById("insurantPermanentAddrImg");
		var tip=document.getElementById("insurantPermanentAddrTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入戶籍地址!]";
		}else{
			if(r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請勿使用空白字元!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkUnderwriterAccount(){
		var value=this.value;
		var img=document.getElementById("underwriterAccountImg");
		var tip=document.getElementById("underwriterAccountTip");
		var checkImg=document.getElementById("checkUnderwriterAccountImg");
		if(value.length==0 || value==null){
			img.setAttribute("class","hide");
			tip.innerHTML="";
			document.getElementById("underwriterName").value="${session.login.name}";
		}else{
			checkImg.removeAttribute("class");
			$.ajax({
				type:'GET',
				contentType:'application/json; charset=utf-8',		
				url:"../account/checkAccountEmpty?account="+value,
				success: function(data) {
					if(data==null){
						img.setAttribute("src","../images/no.png");
						img.removeAttribute("class");
						tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[帳號不存在，請更改!]";
						checkImg.setAttribute("class","hide");
						document.getElementById("underwriterName").value="";
					}else{
						img.setAttribute("src","../images/yes.png");
						img.removeAttribute("class");
						tip.innerHTML="";
						checkImg.setAttribute("class","hide");
						document.getElementById("underwriterName").value=data["name"];
					}
				}
			});
		}
	}
	
	function checkProductID(){
		var value=this.value;
		var img=document.getElementById("productIDImg");
		var tip=document.getElementById("productIDTip");
		var table31=document.getElementById("table31");
		var table32=document.getElementById("table32");
		table31.setAttribute("class","hide");
		table32.setAttribute("class","hide");
		if(value==0){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇投保產品!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
			if(value==1){
				table31.setAttribute("class","hide");
				table32.setAttribute("class","hide");
			}
			if(value==2 || value==4 || value==6 || value==7){
				table31.setAttribute("class","myTable");
			}
			if(value==3 || value==5 || value==6 || value==7){
				table32.setAttribute("class","myTable");
			}
		}
	}
	
	function checkStartDate(){
		var value=this.value;
		var img=document.getElementById("startDateImg");
		var tip=document.getElementById("startDateTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇承保日期!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
		}
	}
	
	function checkCarNo(){
		var value=this.value;
		var r=/^[0-9a-zA-Z-]+$/;
		var img=document.getElementById("carNoImg");
		var tip=document.getElementById("carNoTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入牌照號碼!]";
		}else{
			if(!r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[牌照號碼格式錯誤!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkCarmodelCode(){
		var value=this.value;
		var img=document.getElementById("carmodelCodeImg");
		var tip=document.getElementById("carmodelCodeTip");
		var checkImg=document.getElementById("checkCarmodelCodeImg");
		document.getElementById("cartypeLocalName").value="";
		document.getElementById("purpose").value=0;
		document.getElementById("tableSeats").setAttribute("class","hide");
		document.getElementById("tableTonnage").setAttribute("class","hide");
		document.getElementById("purposeImg").setAttribute("class","hide");
		document.getElementById("purposeTip").innerHTML="";
		document.getElementById("cartypeLocalName").value="";
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入車型代碼!]";
			document.getElementById("carmodelName").value="";
			document.getElementById("brand").value="";
			document.getElementById("carseries").value="";
		}else{
			checkImg.removeAttribute("class");
			$.ajax({
				type:'GET',
				contentType:'application/json; charset=utf-8',		
				url:"../car/queryModelAjax?code="+value,
				success: function(data) {
					if(data==null){
						img.setAttribute("src","../images/no.png");
						img.removeAttribute("class");
 						tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[代碼不存在，請更改!]";
 						checkImg.setAttribute("class","hide");
 						document.getElementById("carmodelName").value="";
 						document.getElementById("brand").value="";
 						document.getElementById("carseries").value="";
					}else{
						//alert(JSON.stringify(data));
						img.setAttribute("src","../images/yes.png");
 						img.removeAttribute("class");
 						tip.innerHTML="";
 						checkImg.setAttribute("class","hide");
 						document.getElementById("carmodelName").value=data["carmodelLocalName"];
 						document.getElementById("brand").value=data["brandLocalName"];
 						document.getElementById("carseries").value=data["carseriesLocalName"];
					}
				}
			});
		}
	}
	
	function checkPurpose(){
		var value=this.value;
		var carmodelCode=document.getElementById("carmodelCode").value;
		var img=document.getElementById("purposeImg");
		var tip=document.getElementById("purposeTip");
		var checkImg=document.getElementById("checkPurposeImg");
		var tableSeats=document.getElementById("tableSeats");
		var tableTonnage=document.getElementById("tableTonnage");
		tableSeats.setAttribute("class","hide");
		tableTonnage.setAttribute("class","hide");
		if(carmodelCode.length==0 || carmodelCode==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[未輸入車型代碼!]";
			document.getElementById("cartypeLocalName").value="";
		}else{
			if(value==0){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇用途!]";
				document.getElementById("cartypeLocalName").value="";
			}else{
				checkImg.removeAttribute("class");
				$.ajax({
					type:'GET',
					contentType:'application/json; charset=utf-8',		
					url:"../car/queryTypeAjax?carmodelCode="+carmodelCode+"&purpose="+value,
					success: function(data) {
						if(data==null){
							img.setAttribute("src","../images/no.png");
							img.removeAttribute("class");
							tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[車種不存在，請更改用途!]";
							checkImg.setAttribute("class","hide");
							document.getElementById("cartypeLocalName").value="";
						}else{
	 						img.setAttribute("src","../images/yes.png");
	 						img.removeAttribute("class");
	 						tip.innerHTML="";
	 						checkImg.setAttribute("class","hide");
	 						var cartypeLocalName=data["cartypeLocalName"];
	 						document.getElementById("cartypeLocalName").value=cartypeLocalName;
							if(cartypeLocalName=="自用大貨車" || cartypeLocalName=="營業大貨車" || cartypeLocalName=="公司行號自用大貨車"){
								tableTonnage.setAttribute("class","myTable");
							}
							if(cartypeLocalName=="自用大客車" || cartypeLocalName=="營業大客車" || cartypeLocalName=="長期租賃大客車"){
								tableSeats.setAttribute("class","myTable");
							}
						}
					}
				});
			}
		}
	}
	
	function checkTonnage(){
		var value=this.value;
		var r=/^[0-9.]+$/;
		var img=document.getElementById("tonnageImg");
		var tip=document.getElementById("tonnageTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入載重數!]";
		}else{
			if(!r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入數字!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkSeats(){
		var value=this.value;
		var r=/^[0-9]+$/;
		var img=document.getElementById("seatsImg");
		var tip=document.getElementById("seatsTip");
		if(value.length==0 || value==null){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入座位數!]";
		}else{
			if(!r.test(value)){
				img.setAttribute("src","../images/no.png");
				img.removeAttribute("class");
				tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請輸入整數!]";
			}else{
				img.setAttribute("src","../images/yes.png");
				img.removeAttribute("class");
				tip.innerHTML="";
			}
		}
	}
	
	function checkCaseMoney31(){
		var value=this.value;
		var img=document.getElementById("caseMoney31Img");
		var tip=document.getElementById("caseMoney31Tip");
		if(value==0){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇保險金額!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
		}
	}
	
	function checkCaseMoney32(){
		var value=this.value;
		var img=document.getElementById("caseMoney32Img");
		var tip=document.getElementById("caseMoney32Tip");
		if(value==0){
			img.setAttribute("src","../images/no.png");
			img.removeAttribute("class");
			tip.innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[請選擇保險金額!]";
		}else{
			img.setAttribute("src","../images/yes.png");
			img.removeAttribute("class");
			tip.innerHTML="";
		}
	}
</script>

<style>		
	.bottomBody {
		height: 1250px}

	.myTable {
 		position: relative;
 		left: 22%;}
 			
	.myTable img {
		height: 18px;
		padding-bottom: 3px;}
		 		
	.myTable td {
		width: 320px;}
		 		 		
 	.myDiv {
 		width: 200px;
 		position: relative;
 		left: 48%;}
 		
 	.title {
 		width: 100px;
 		position: relative;
		top: 10px;
		left: 49%;}
 		
	.title2 {
		width: 100px;
		margin-bottom: 10px;
		font: 0.5cm 'News Cycle', "Microsoft YaHei", Arial;
		letter-spacing: 1px;
		position: relative;
		left: 20%;
		color: #228B22;}
</style>

<div class="title">新建保單</div>
<s:form method="post" namespace="/policy" action="checkPolicy" theme="simple">
	<s:textfield name="officeID" id="officeID" value="${session.login.officeID}" readonly="true" cssClass="hide"/>
	<div class="title2">投保人</div>
	<table class="myTable">
		<tr>
			<td>
				<img src="../images/tip.gif">
				身分證字號:<s:textfield name="applicantPin" id="applicantPin"/>
				<c:choose>
					<c:when test="${empty errors.applicantPin}">
						<c:choose>
							<c:when test="${empty applicantPin}">
								<img id="applicantPinImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="applicantPinImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="applicantPinImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkApplicantPinImg">
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="applicantPinTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.applicantPin}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				姓名:<s:textfield name="applicantName" id="applicantName" size="15px"/>
				<c:choose>
					<c:when test="${empty errors.applicantName}">
						<c:choose>
							<c:when test="${empty applicantName}">
								<img id="applicantNameImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="applicantNameImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="applicantNameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<img src="../images/tip.gif">
				性別:<s:radio name="applicantGender" id="applicantGender" list="#{'男':'男','女':'女'}" value="'男'" />
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="applicantNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.applicantName}</div>
			</td>
			<td>
				<div class="warning" id="applicantGenderTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.applicantGender}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				出生日期:<s:textfield name="applicantBirthday" id="applicantBirthday" />
				<c:choose>
					<c:when test="${empty errors.applicantBirthday}">
						<c:choose>
							<c:when test="${empty applicantBirthday}">
								<img id="applicantBirthdayImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="applicantBirthdayImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="applicantBirthdayImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;聯絡電話:<s:textfield name="applicantPhone" id="applicantPhone"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="applicantBirthdayTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.applicantBirthday}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				戶籍地址:<s:textfield name="applicantPermanentAddr" id="applicantPermanentAddr" size="50px"/>
				<c:choose>
					<c:when test="${empty errors.applicantPermanentAddr}">
						<c:choose>
							<c:when test="${empty applicantPermanentAddr}">
								<img id="applicantPermanentAddrImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="applicantPermanentAddrImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="applicantPermanentAddrImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="applicantPermanentAddrTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.applicantPermanentAddr}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;聯絡地址:<s:textfield name="applicantCurrentAddr" id="applicantCurrentAddr" size="50px"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning"></div>
			</td>
		</tr>
	</table>
	<div class="title2">被保人</div>
	<table class="myTable">
		<tr>
			<td>
				<img src="../images/tip.gif">
				身分證字號:<s:textfield name="insurantPin" id="insurantPin"/>
				<c:choose>
					<c:when test="${empty errors.insurantPin}">
						<c:choose>
							<c:when test="${empty insurantPin}">
								<img id="insurantPinImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="insurantPinImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="insurantPinImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkInsurantPinImg">
			</td>
			<td class="textCenter">
				<input type="button" value="同投保人" id="same" class="btn btn-success">
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="insurantPinTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.insurantPin}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				姓名:<s:textfield name="insurantName" id="insurantName" size="15px"/>
				<c:choose>
					<c:when test="${empty errors.insurantName}">
						<c:choose>
							<c:when test="${empty insurantName}">
								<img id="insurantNameImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="insurantNameImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="insurantNameImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<img src="../images/tip.gif">
				性別:<s:radio name="insurantGender" id="insurantGender" list="#{'男':'男','女':'女'}"  value="'男'"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="insurantNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.insurantName}</div>
			</td>
			<td>
				<div class="warning" id="insurantGenderTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.insurantGender}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				出生日期:<s:textfield name="insurantBirthday" id="insurantBirthday"/>
				<c:choose>
					<c:when test="${empty errors.insurantBirthday}">
						<c:choose>
							<c:when test="${empty insurantBirthday}">
								<img id="insurantBirthdayImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="insurantBirthdayImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="insurantBirthdayImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;聯絡電話:<s:textfield name="insurantPhone" id="insurantPhone"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="insurantBirthdayTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.insurantBirthday}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				戶籍地址:<s:textfield name="insurantPermanentAddr" id="insurantPermanentAddr" size="50px"/>
				<c:choose>
					<c:when test="${empty errors.insurantPermanentAddr}">
						<c:choose>
							<c:when test="${empty insurantPermanentAddr}">
								<img id="insurantPermanentAddrImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="insurantPermanentAddrImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="insurantPermanentAddrImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="insurantPermanentAddrTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.insurantPermanentAddr}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;聯絡地址:<s:textfield name="insurantCurrentAddr" id="insurantCurrentAddr" size="50px"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning"></div>
			</td>
		</tr>
	</table>
	<div class="title2">保單</div>
	<table class="myTable">
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;經辦人帳號:<s:textfield name="underwriterAccount" id="underwriterAccount"/>
				<c:choose>
					<c:when test="${empty errors.underwriterAccount}">
						<c:choose>
							<c:when test="${empty underwriterAccount}">
								<img id="underwriterAccountImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="underwriterAccountImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="underwriterAccountImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkUnderwriterAccountImg">
			</td>
			<td>
				<c:choose>
					<c:when test="${empty errors.underwriterAccount}">
						<c:choose>
							<c:when test="${empty underwriterName}">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;經辦人姓名:<s:textfield id="underwriterName" name="underwriterName" size="18px" readonly="true" cssClass="noUse" value="${session.login.name }"/>
							</c:when>
							<c:otherwise>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;經辦人姓名:<s:textfield id="underwriterName" name="underwriterName" size="18px" readonly="true" cssClass="noUse"/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;經辦人姓名:<s:textfield id="underwriterName" name="underwriterName" size="18px" readonly="true" cssClass="noUse"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="underwriterAccountTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.underwriterAccount}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				投保產品:<s:select name="productID" list="#{'1':'21','2':'31','3':'32','4':'2131','5':'2132','6':'3132','7':'213132'}" headerKey="0" headerValue="請選擇" id="productID"/>
				<c:choose>
					<c:when test="${empty errors.productID}">
						<c:choose>
							<c:when test="${empty productID}">
								<img id="productIDImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="productIDImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="productIDImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<img src="../images/tip.gif">
				承保日期:<s:textfield name="startDate" id="startDate"/>
				<c:choose>
					<c:when test="${empty errors.startDate}">
						<c:choose>
							<c:when test="${empty startDate}">
								<img id="startDateImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="startDateImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="startDateImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="productIDTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.productID}</div>
			</td>
			<td>
				<div class="warning" id="startDateTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.startDate}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				牌照號碼:<s:textfield name="carNo" id="carNo" size="15px"/>
				<c:choose>
					<c:when test="${empty errors.carNo}">
						<c:choose>
							<c:when test="${empty carNo}">
								<img id="carNoImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="carNoImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="carNoImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出廠日期:<s:textfield name="productionDate" id="productionDate"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="carNoTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.carNo}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				車型代碼:<s:textfield name="carmodelCode" id="carmodelCode" size="15px"/>
				<c:choose>
					<c:when test="${empty errors.carmodelCode}">
						<c:choose>
							<c:when test="${empty carmodelCode}">
								<img id="carmodelCodeImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="carmodelCodeImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="carmodelCodeImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkCarmodelCodeImg">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="warning" id="carmodelCodeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.carmodelCode}</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;車型名稱:<s:textfield id="carmodelName" name="carmodelName" readonly="true" cssClass="noUse" size="50px"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="warning" id="carmodelNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.carmodelName}</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;廠牌:<s:textfield id="brand" name="brand" readonly="true" cssClass="noUse"/>
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;車系:<s:textfield id="carseries" name="carseries" readonly="true" cssClass="noUse"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="carmodelCodeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.brand}</div>
			</td>
			<td>
				<div class="warning" id="carmodelNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.carseries}</div>
			</td>
		</tr>
		<tr>
			<td>
				<img src="../images/tip.gif">
				用途:<s:select name="purpose" list="#{'自用':'自用','營業用':'營業用','營業用一般':'營業用一般','自用一般':'自用一般','租賃用':'租賃用','長期租賃':'長期租賃','個人計程車':'個人計程車','公司行號自用':'公司行號自用','自用櫃貨':'自用櫃貨','營業用櫃貨':'營業用櫃貨'}" headerKey="0" headerValue="請選擇" id="purpose"/>
				<c:choose>
					<c:when test="${empty errors.purpose}">
						<c:choose>
							<c:when test="${empty purpose}">
								<img id="purposeImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="purposeImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="purposeImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<img class="hide" src="../images/ajaxWait.gif" id="checkPurposeImg">
			</td>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;車種:<s:textfield id="cartypeLocalName" name="cartypeLocalName" readonly="true" cssClass="noUse"/>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="purposeTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.purpose}</div>
			</td>
			<td>
				<div class="warning" id="cartypeLocalNameTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.cartypeLocalName}</div>
			</td>
		</tr>
	</table>
	<table class="hide" id="tableTonnage">
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				載重數:<s:textfield name="tonnage" id="tonnage" size="5px"/>噸
				<c:choose>
					<c:when test="${empty errors.tonnage}">
						<c:choose>
							<c:when test="${empty tonnage}">
								<img id="tonnageImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="tonnageImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="tonnageImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="tonnageTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.tonnage}</div>
			</td>
			<td></td>
		</tr>
	</table>
	<table  class="hide" id="tableSeats">
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				座位數:<s:textfield name="seats" id="seats" size="5px"/>人
				<c:choose>
					<c:when test="${empty errors.seats}">
						<c:choose>
							<c:when test="${empty seats}">
								<img id="seatsImg" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="seatsImg" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="seatsImg" src="../images/no.png">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning" id="seatsTip">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors.seats}</div>
			</td>
			<td></td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${empty productID}">
			<table class="hide" id="table31">
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${productID==2 || productID==4 || productID==6 || productID==7}">
					<table class="myTable" id="table31">
				</c:when>
				<c:otherwise>
					<table class="hide" id="table31">
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				第三傷害責任險(31)&nbsp;&nbsp;&nbsp;每人/每事故保險金額:<s:select name="caseMoney31" list="#{'60':'30萬/60萬','70':'35萬/70萬','80':'40萬/80萬','90':'45萬/90萬','100':'50萬/100萬','110':'55萬/110萬','120':'60萬/120萬','130':'65萬/130萬','140':'70萬/140萬','150':'75萬/150萬','160':'80萬/160萬','170':'85萬/170萬','180':'90萬/180萬','190':'95萬/190萬','200':'100萬/200萬','210':'105萬/210萬','220':'110萬/220萬','230':'115萬/230萬','240':'120萬/240萬','250':'125萬/250萬','260':'130萬/260萬','270':'135萬/270萬','280':'140萬/280萬','290':'145萬/290萬','300':'150萬/300萬','310':'155萬/310萬','320':'160萬/320萬','330':'165萬/330萬','340':'170萬/340萬','350':'175萬/350萬','360':'180萬/360萬','370':'185萬/370萬','380':'190萬/380萬','390':'195萬/390萬','400':'200萬/400萬','500':'250萬/500萬','600':'300萬/600萬','700':'350萬/700萬','800':'400萬/800萬','900':'450萬/900萬','1000':'500萬/1000萬','1100':'550萬/1100萬','1200':'600萬/1200萬','1300':'650萬/1300萬','1400':'700萬/1400萬','1500':'750萬/1500萬','1600':'800萬/1600萬','1700':'850萬/1700萬','1800':'900萬/1800萬','1900':'950萬/1900萬','2000':'1000萬/2000萬'}" 
					headerKey="0" headerValue="請選擇" id="caseMoney31"/>
				<c:choose>
					<c:when test="${empty errors.caseMoney31}">
						<c:choose>
							<c:when test="${caseMoney31==0}">
								<img id="caseMoney31Img" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="caseMoney31Img" class="hide" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="caseMoney31Img" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<span class="warning" id="caseMoney31Tip">${errors.caseMoney31}</span>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<div class="warning"></div>
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${empty productID}">
			<table class="hide" id="table32">
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${productID==3 || productID==5 || productID==6 || productID==7}">
					<table class="myTable" id="table32">
				</c:when>
				<c:otherwise>
					<table class="hide" id="table32">
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
		<tr>
			<td colspan="2">
				<img src="../images/tip.gif">
				第三責任財損險(32)&nbsp;&nbsp;&nbsp;每事故保險金額:<s:select name="caseMoney32" list="#{'5':'5萬','6':'6萬','8':'8萬','10':'10萬','15':'15萬','20':'20萬','30':'30萬','40':'40萬','50':'50萬','60':'60萬','70':'70萬','80':'80萬','90':'90萬','100':'100萬','150':'150萬','200':'200萬','250':'250萬','300':'300萬','350':'350萬','400':'400萬','450':'450萬','500':'500萬'}" 
					headerKey="0" headerValue="請選擇" id="caseMoney32"/>
				<c:choose>
					<c:when test="${empty errors.caseMoney32}">
						<c:choose>
							<c:when test="${caseMoney32==0}">
								<img id="caseMoney32Img" class="hide" src="../images/no.png">
							</c:when>
							<c:otherwise>
								<img id="caseMoney32Img" class="hide" src="../images/yes.png">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<img id="caseMoney32Img" src="../images/no.png">
					</c:otherwise>
				</c:choose>
				<span class="warning" id="caseMoney32Tip">${errors.caseMoney32}</span>
			</td>
		</tr>
		<tr>
			<td>
				<div class="warning"></div>
			</td>
			<td>
			</td>
		</tr>
	</table>
	<div class="myDiv">
		<button type="submit" id="submit" class="btn btn-primary">
			確認<img class="hide" src="../images/wait.gif" id="waitImg">
		</button>
		<input type="button" value="清除" id="reset" class="btn btn-info">
	</div>
</s:form>