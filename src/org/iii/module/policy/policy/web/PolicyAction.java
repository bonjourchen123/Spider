package org.iii.module.policy.policy.web;


import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.core.security.entity.SecUser;
import org.iii.core.security.service.SecUserService;
import org.iii.module.commodity.coef.entity.HumanCoef;
import org.iii.module.commodity.coef.entity.IndemnityCoef;
import org.iii.module.commodity.coef.service.HumanCoefService;
import org.iii.module.commodity.coef.service.IndemnityCoefService;
import org.iii.module.commodity.rate.service.Rate21Service;
import org.iii.module.commodity.rate.service.Rate31Service;
import org.iii.module.commodity.rate.service.Rate32Service;
import org.iii.module.policy.customer.entity.Customer;
import org.iii.module.policy.customer.service.CustomerService;
import org.iii.module.policy.policy.entity.Policy;
import org.iii.module.policy.policy.entity.PolicyItem;
import org.iii.module.policy.policy.service.PolicyService;
import org.iii.module.setting.blacklist.entity.Blacklist;
import org.iii.module.setting.blacklist.service.BlacklistService;
import org.iii.module.setting.car.entity.Carmodel;
import org.iii.module.setting.car.entity.Cartype;
import org.iii.module.setting.car.service.CarmodelService;
import org.iii.module.setting.car.service.CartypeService;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author kuso
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PolicyAction extends GenericAction<Policy>{
	//查詢保單
	private String code;
	private String carNo;
	private String applicantName;
	private String insurantName;
	private String officeID;
	
	//確認新增保單
	private String applicantPin;
	private String applicantGender;
	private LocalDateTime applicantBirthday;
	private String applicantPhone;
	private String applicantPermanentAddr;
	private String applicantCurrentAddr;
	private String insurantPin;
	private String insurantGender;
	private LocalDateTime insurantBirthday;
	private String insurantPhone;
	private String insurantPermanentAddr;
	private String insurantCurrentAddr;
	private String underwriterAccount;
	private String underwriterName;
	private Long productID;
	private LocalDateTime startDate;
	private LocalDateTime productionDate;
	private String carmodelCode;
	private String carmodelName;
	private String brand;
	private String carseries;
	private String purpose;
	private String cartypeLocalName;
	private Double tonnage;
	private Integer seats;
	private String caseMoney31;
	private String caseMoney32;
	private String productCode;
	private SecUser underwriter;
	private Carmodel carmodel;
	private Cartype cartype;
	private LocalDateTime endDate;
	private Long rate21;
	private Long rate31;
	private Long rate32;
	private Long totalRate;
	
	//新增保單
	private Long underwriterID;
	private Long carmodelID;
	private String submit;
	private String assessorName;
	private LocalDateTime assessorDate;
	
	//顯示保單
	private Long id;
	
	//顯示未審核保單
	private String reason;
	
	@Autowired
	private PolicyService policyService;
	@Autowired
	private SecUserService secUserService;
	@Autowired
	private CarmodelService carmodelService;
	@Autowired
	private CartypeService cartypeService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private Rate21Service rate21Service;
	@Autowired
	private Rate31Service rate31Service;
	@Autowired
	private Rate32Service rate32Service;
	@Autowired
	private HumanCoefService humanCoefService;
	@Autowired
	private IndemnityCoefService indemnityCoefService;
	@Autowired
	private BlacklistService blacklistService;
	
	//驗證確認新增保單
	public void validateCheckPolicy(){
		this.applicantPin=applicantPin.trim();
		this.applicantName=applicantName.trim();
		this.applicantPhone=applicantPhone.trim();
		this.applicantPermanentAddr=applicantPermanentAddr.trim();
		this.applicantCurrentAddr=applicantCurrentAddr.trim();
		this.insurantPin=insurantPin.trim();
		this.insurantName=insurantName.trim();
		this.insurantPhone=insurantPhone.trim();
		this.insurantPermanentAddr=insurantPermanentAddr.trim();
		this.insurantCurrentAddr=insurantCurrentAddr.trim();
		this.underwriterAccount=underwriterAccount.trim();
		this.carNo=carNo.trim();
		this.carmodelCode=carmodelCode.trim();
		if(applicantPin==null || applicantPin.length()==0){
			this.addFieldError("applicantPin", "請輸入身分證字號!");
		}
		if(applicantName==null || applicantName.length()==0){
			this.addFieldError("applicantName", "請輸入姓名!");
		}
		if(applicantBirthday==null){
			this.addFieldError("applicantBirthday", "請選擇出生日期!");
		}
		if(applicantPermanentAddr==null || applicantPermanentAddr.length()==0){
			this.addFieldError("applicantPermanentAddr", "請輸入戶籍地址!");
		}
		if(insurantPin==null || insurantPin.length()==0){
			this.addFieldError("insurantPin", "請輸入身分證字號!");
		}
		if(insurantName==null || insurantName.length()==0){
			this.addFieldError("insurantName", "請輸入姓名!");
		}
		if(insurantBirthday==null){
			this.addFieldError("insurantBirthday", "請選擇出生日期!");
		}
		if(insurantPermanentAddr==null || insurantPermanentAddr.length()==0){
			this.addFieldError("insurantPermanentAddr", "請輸入戶籍地址!");
		}
		if(underwriterAccount==null || underwriterAccount.length()==0){
			underwriter=getLoginUser();
		}else{
			underwriter=secUserService.getUserInfo(underwriterAccount);
			if(underwriter==null){
				this.addFieldError("underwriterAccount", "帳號不存在，請更改!");
			}
		}
		if(productID==0){
			this.addFieldError("productID", "請選擇投保產品!");
		}else{
			if(productID==2 || productID==4 || productID==6 || productID==7){
				if("0".equals(caseMoney31)){
					this.addFieldError("caseMoney31", "請選擇保險金額!");
				}
			}
			if(productID==3 || productID==5 || productID==6 || productID==7){
				if("0".equals(caseMoney32)){
					this.addFieldError("caseMoney32", "請選擇保險金額!");
				}
			}
		}
		if(startDate==null){
			this.addFieldError("startDate", "請選擇承保日期!");
		}
		if(carNo==null || carNo.length()==0){
			this.addFieldError("carNo", "請輸入牌照號碼!");
		}
		if(carmodelCode==null || carmodelCode.length()==0){
			this.addFieldError("carmodelCode", "請輸入車型代碼!");
		}else{
			carmodel=carmodelService.queryByCode(carmodelCode);
			if(carmodel==null){
				this.addFieldError("carmodelCode", "代碼不存在，請更改!");
			}
		}
		if("0".equals(purpose)){
			this.addFieldError("purpose", "請選擇用途!");
		}else{
			cartype=cartypeService.queryType(carmodelCode, purpose);
			if(cartype==null){
				this.addFieldError("purpose", "車種不存在，請更改用途!");
			}
		}
		//重複投保要做檢查嗎...
	}
	
	//確認新增保單
	public String checkPolicy(){
		endDate=startDate.plusYears(1);
		Customer insurant=customerService.getCustomer(insurantPin, Long.valueOf(officeID));
		int age=Math.abs(Period.fieldDifference(startDate, insurantBirthday).getYears());
		//21
		if(productID==1 || productID==4 || productID==5 || productID==7){
			String rates=rate21Service.queryRate(cartype.getId(), insurantGender, age, tonnage, seats).getRate();
			if(insurant==null){
				rate21=Long.valueOf(rates.split(",")[4]);
			}else{
				rate21=Long.valueOf(rates.split(",")[insurant.getRateLevel()]);
			}
		}
		//係數
		HumanCoef humanCoef=humanCoefService.queryHumanCoef(insurantGender, age);
		IndemnityCoef indemnityCoef;
		if(insurant==null){
			indemnityCoef=indemnityCoefService.queryIndemnityCoef(4);
		}else{
			indemnityCoef=indemnityCoefService.queryIndemnityCoef(insurant.getIndemnityLevel());
		}
		Double coef;
		if(purpose.indexOf("自用")!=-1){
			coef=humanCoef.getCoef()+indemnityCoef.getCoef();
		}else{
			coef=1+indemnityCoef.getCoef();
		}
		//31
		if(productID==2 || productID==4 || productID==6 || productID==7){			
			rate31=rate31Service.queryRate(cartype.getId(), Integer.valueOf(caseMoney31)).getRate();
			rate31=Math.round(rate31*coef);
		}
		//32
		if(productID==3 || productID==5 || productID==6 || productID==7){
			rate32=rate32Service.queryRate(cartype.getId(), Integer.valueOf(caseMoney32)).getRate();
			rate32=Math.round(rate32*coef);
		}
		totalRate=0l;
		if(rate21!=null){
			totalRate=totalRate+rate21;
		}
		if(rate31!=null){
			totalRate=totalRate+rate31;
		}
		if(rate32!=null){
			totalRate=totalRate+rate32;
		}
		return SUCCESS;
	}
	
	//驗證新增保單
	public void validateCreatePolicy(){
		if(submit.equals("back")){
			this.addFieldError("back","");
		}
	}
	
	//新增保單
	public String createPolicy(){
		Customer applicant=customerService.getCustomer(applicantPin,Long.valueOf(officeID));
		if(applicant==null){
			customerService.createCustomer(getLoginUser(), applicantName, officeID, applicantPin, applicantGender, applicantBirthday, applicantPhone, applicantPermanentAddr, applicantCurrentAddr);
			applicant=customerService.getCustomer(applicantPin,Long.valueOf(officeID));
		}else{
			applicant.setName(applicantName);
			applicant.setGender(applicantGender);
			applicant.setBirthday(applicantBirthday);
			applicant.setPhone(applicantPhone);
			applicant.setPermanentAddr(applicantPermanentAddr);
			applicant.setCurrentAddr(applicantCurrentAddr);
			customerService.updateCustomer(getLoginUser(), applicant);
		}
		Customer insurant=customerService.getCustomer(insurantPin,Long.valueOf(officeID));
		if(insurant==null){
			customerService.createCustomer(getLoginUser(), insurantName, officeID, insurantPin, insurantGender, insurantBirthday, insurantPhone, insurantPermanentAddr, insurantCurrentAddr);
			insurant=customerService.getCustomer(insurantPin,Long.valueOf(officeID));
		}else{
			insurant.setName(insurantName);
			insurant.setGender(insurantGender);
			insurant.setBirthday(insurantBirthday);
			insurant.setPhone(insurantPhone);
			insurant.setPermanentAddr(insurantPermanentAddr);
			insurant.setCurrentAddr(insurantCurrentAddr);
			customerService.updateCustomer(getLoginUser(), insurant);
		}
		Policy policy=new Policy();
		policy.setUnderwriterUserID(underwriterID);
		policy.setApplicantCustomerID(applicant.getId());
		policy.setInsurantCustomerID(insurant.getId());
		policy.setCarNo(carNo);
		policy.setCarmodelID(carmodelID);
		policy.setProductionDate(productionDate);
		policy.setTonnage(tonnage);
		policy.setSeats(seats);
		policy.setProductID(productID);
		policy.setStartDate(startDate);
		policy.setEndDate(endDate);
		policy.setTotalRate(totalRate);
		Blacklist blacklistPin=blacklistService.getBlacklistNumber(insurantPin);
		Blacklist blacklistCarNo=blacklistService.getBlacklistNumber(carNo);
		if(blacklistPin==null && blacklistCarNo==null){
			policy.setAssessorUserID(underwriterID);
			policy.setAssessorDate(LocalDateTime.now());
		}
		policy.setOfficeID(Long.valueOf(officeID));
		policy.setPurpose(purpose);
		Policy newPolicy=policyService.createPolicy(getLoginUser(), policy);
		if(newPolicy!=null){
			//21
			if(newPolicy.getProductID()==1 || newPolicy.getProductID()==4 || newPolicy.getProductID()==5 || newPolicy.getProductID()==7){
				PolicyItem policyItem=new PolicyItem();
				policyItem.setPolicyID(newPolicy.getId());
				policyItem.setInsitemID(1l);
				policyItem.setRate(rate21);
				policyService.createPolicyItem(getLoginUser(), policyItem);
			}
			//31
			if(newPolicy.getProductID()==2 || newPolicy.getProductID()==4 || newPolicy.getProductID()==6 || newPolicy.getProductID()==7){
				PolicyItem policyItem=new PolicyItem();
				policyItem.setPolicyID(newPolicy.getId());
				policyItem.setInsitemID(2l);
				policyItem.setCaseMoney(Long.valueOf(caseMoney31));
				policyItem.setPersonMoney(policyItem.getCaseMoney()/2);
				policyItem.setRate(rate31);
				policyService.createPolicyItem(getLoginUser(), policyItem);
			}
			//32
			if(newPolicy.getProductID()==3 || newPolicy.getProductID()==5 || newPolicy.getProductID()==6 || newPolicy.getProductID()==7){
				PolicyItem policyItem=new PolicyItem();
				policyItem.setPolicyID(newPolicy.getId());
				policyItem.setInsitemID(3l);
				policyItem.setCaseMoney(Long.valueOf(caseMoney32));
				policyItem.setRate(rate32);
				policyService.createPolicyItem(getLoginUser(), policyItem);
			}
			this.addFieldError("result","已成功新建保單");
		}else{
			this.addFieldError("result","新建保單失敗");
		}
		return SUCCESS;		
	}
	
	//驗證查詢保單
	public void validateQueryPolicy() {
		this.code=code.trim();
		this.carNo=carNo.trim();
		this.applicantName=applicantName.trim();
		this.insurantName=insurantName.trim();
	}
	
	//查詢保單
	public String queryPolicy(){
		List<Policy> policys=policyService.queryByLike(code, carNo, applicantName, insurantName, officeID);
		jqGrid(policys);
		return SUCCESS;
	}

	//顯示保單
	public String showPolicy(){
		Policy policy=policyService.queryPolicy(id);
		code=policy.getCode();
		Customer applicantCustomer=customerService.queryCustomer(policy.getApplicantCustomerID());
		applicantName=applicantCustomer.getName();
		applicantPin=applicantCustomer.getPin();
		applicantGender=applicantCustomer.getGender();
		applicantBirthday=applicantCustomer.getBirthday();
		applicantPhone=applicantCustomer.getPhone();
		applicantPermanentAddr=applicantCustomer.getPermanentAddr();
		applicantCurrentAddr=applicantCustomer.getCurrentAddr();
		Customer insurantCustomer=customerService.queryCustomer(policy.getInsurantCustomerID());
		insurantName=insurantCustomer.getName();
		insurantPin=insurantCustomer.getPin();
		insurantGender=insurantCustomer.getGender();
		insurantBirthday=insurantCustomer.getBirthday();
		insurantPhone=insurantCustomer.getPhone();
		insurantPermanentAddr=insurantCustomer.getPermanentAddr();
		insurantCurrentAddr=insurantCustomer.getCurrentAddr();
		underwriterName=secUserService.queryByID(policy.getUnderwriterUserID()).getName();
		productID=policy.getProductID();
		startDate=policy.getStartDate();
		endDate=policy.getEndDate();
		carNo=policy.getCarNo();
		productionDate=policy.getProductionDate();
		Carmodel carmodel=carmodelService.queryByCode(carmodelService.queryByID(policy.getCarmodelID()).getCode());			
		carmodelCode=carmodel.getCode();
		carmodelName=carmodel.getLocalName();
		brand=carmodel.getBrandLocalName();
		carseries=carmodel.getCarseriesLocalName();
		purpose=policy.getPurpose();
		cartypeLocalName=cartypeService.queryType(carmodelCode, purpose).getLocalName();
		tonnage=policy.getTonnage();
		seats=policy.getSeats();
		List<PolicyItem> policyItems=policy.getPolicyItems();
		for(PolicyItem policyItem:policyItems){
			if(policyItem.getInsitemID()==1){
				rate21=policyItem.getRate();
			}else if(policyItem.getInsitemID()==2){
				rate31=policyItem.getRate();
				caseMoney31=policyItem.getCaseMoney().toString();
			}else if(policyItem.getInsitemID()==3){
				rate32=policyItem.getRate();
				caseMoney32=policyItem.getCaseMoney().toString();
			}
		}
		totalRate=policy.getTotalRate();
		if(policy.getAssessorUserID()!=null){
			assessorName=secUserService.queryByID(policy.getAssessorUserID()).getName();
			assessorDate=policy.getAssessorDate();
		}
		this.addFieldError("show","");
		return SUCCESS;
	}
	
	//驗證查詢未審核保單
	public void validateQueryVerify() {
		this.code=code.trim();
		this.carNo=carNo.trim();
		this.applicantName=applicantName.trim();
		this.insurantName=insurantName.trim();
	}
	
	//查詢未審核保單
	public String queryVerify(){
		List<Policy> policys=policyService.queryVerifyByLike(code, carNo, applicantName, insurantName, officeID);
		jqGrid(policys);
		return SUCCESS;
	}
	
	//顯示未審核保單
	public String showVerify(){
		Policy policy=policyService.queryPolicy(id);
		code=policy.getCode();
		Customer applicantCustomer=customerService.queryCustomer(policy.getApplicantCustomerID());
		applicantName=applicantCustomer.getName();
		applicantPin=applicantCustomer.getPin();
		applicantGender=applicantCustomer.getGender();
		applicantBirthday=applicantCustomer.getBirthday();
		applicantPhone=applicantCustomer.getPhone();
		applicantPermanentAddr=applicantCustomer.getPermanentAddr();
		applicantCurrentAddr=applicantCustomer.getCurrentAddr();
		Customer insurantCustomer=customerService.queryCustomer(policy.getInsurantCustomerID());
		insurantName=insurantCustomer.getName();
		insurantPin=insurantCustomer.getPin();
		insurantGender=insurantCustomer.getGender();
		insurantBirthday=insurantCustomer.getBirthday();
		insurantPhone=insurantCustomer.getPhone();
		insurantPermanentAddr=insurantCustomer.getPermanentAddr();
		insurantCurrentAddr=insurantCustomer.getCurrentAddr();
		underwriterName=secUserService.queryByID(policy.getUnderwriterUserID()).getName();
		productID=policy.getProductID();
		startDate=policy.getStartDate();
		endDate=policy.getEndDate();
		carNo=policy.getCarNo();
		productionDate=policy.getProductionDate();
		Carmodel carmodel=carmodelService.queryByCode(carmodelService.queryByID(policy.getCarmodelID()).getCode());			
		carmodelCode=carmodel.getCode();
		carmodelName=carmodel.getLocalName();
		brand=carmodel.getBrandLocalName();
		carseries=carmodel.getCarseriesLocalName();
		purpose=policy.getPurpose();
		cartypeLocalName=cartypeService.queryType(carmodelCode, purpose).getLocalName();
		tonnage=policy.getTonnage();
		seats=policy.getSeats();
		List<PolicyItem> policyItems=policy.getPolicyItems();
		for(PolicyItem policyItem:policyItems){
			if(policyItem.getInsitemID()==1){
				rate21=policyItem.getRate();
			}else if(policyItem.getInsitemID()==2){
				rate31=policyItem.getRate();
				caseMoney31=policyItem.getCaseMoney().toString();
			}else if(policyItem.getInsitemID()==3){
				rate32=policyItem.getRate();
				caseMoney32=policyItem.getCaseMoney().toString();
			}
		}
		totalRate=policy.getTotalRate();
		if(policy.getAssessorUserID()!=null){
			assessorName=secUserService.queryByID(policy.getAssessorUserID()).getName();
			assessorDate=policy.getAssessorDate();
		}
		Blacklist blacklist=blacklistService.getBlacklistNumber(insurantPin);
		reason="";
		if(blacklist!=null){
			reason=reason+"&nbsp;&nbsp;&nbsp;&nbsp;"+blacklist.getType()+":"+blacklist.getReason()+"<br>";
		}
		blacklist=blacklistService.getBlacklistNumber(carNo);
		if(blacklist!=null){
			reason=reason+"&nbsp;&nbsp;&nbsp;&nbsp;"+blacklist.getType()+":"+blacklist.getReason()+"<br>";
		}
		this.addFieldError("verify","");
		return SUCCESS;
	}
	
	//審核保單
	public String verifyPolicy(){
		if(submit.equals("approves")){
			policyService.verifyPolicy(getLoginUser(), id);
		}else{
			policyService.deletePolicy(id);
		}
		return SUCCESS;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getInsurantName() {
		return insurantName;
	}

	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}

	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public String getApplicantPin() {
		return applicantPin;
	}

	public void setApplicantPin(String applicantPin) {
		this.applicantPin = applicantPin;
	}

	public String getApplicantGender() {
		return applicantGender;
	}

	public void setApplicantGender(String applicantGender) {
		this.applicantGender = applicantGender;
	}

	public String getApplicantPhone() {
		return applicantPhone;
	}

	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getApplicantPermanentAddr() {
		return applicantPermanentAddr;
	}

	public void setApplicantPermanentAddr(String applicantPermanentAddr) {
		this.applicantPermanentAddr = applicantPermanentAddr;
	}

	public String getApplicantCurrentAddr() {
		return applicantCurrentAddr;
	}

	public void setApplicantCurrentAddr(String applicantCurrentAddr) {
		this.applicantCurrentAddr = applicantCurrentAddr;
	}

	public String getInsurantPin() {
		return insurantPin;
	}

	public void setInsurantPin(String insurantPin) {
		this.insurantPin = insurantPin;
	}

	public String getInsurantGender() {
		return insurantGender;
	}

	public void setInsurantGender(String insurantGender) {
		this.insurantGender = insurantGender;
	}

	public String getInsurantPhone() {
		return insurantPhone;
	}

	public void setInsurantPhone(String insurantPhone) {
		this.insurantPhone = insurantPhone;
	}

	public String getInsurantPermanentAddr() {
		return insurantPermanentAddr;
	}

	public void setInsurantPermanentAddr(String insurantPermanentAddr) {
		this.insurantPermanentAddr = insurantPermanentAddr;
	}

	public String getInsurantCurrentAddr() {
		return insurantCurrentAddr;
	}

	public void setInsurantCurrentAddr(String insurantCurrentAddr) {
		this.insurantCurrentAddr = insurantCurrentAddr;
	}

	public String getUnderwriterAccount() {
		return underwriterAccount;
	}

	public void setUnderwriterAccount(String underwriterAccount) {
		this.underwriterAccount = underwriterAccount;
	}

	public SecUser getUnderwriter() {
		return underwriter;
	}

	public void setUnderwriter(SecUser underwriter) {
		this.underwriter = underwriter;
	}

	public String getCarmodelCode() {
		return carmodelCode;
	}

	public void setCarmodelCode(String carmodelCode) {
		this.carmodelCode = carmodelCode;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCaseMoney31() {
		return caseMoney31;
	}

	public void setCaseMoney31(String caseMoney31) {
		this.caseMoney31 = caseMoney31;
	}

	public String getCaseMoney32() {
		return caseMoney32;
	}

	public void setCaseMoney32(String caseMoney32) {
		this.caseMoney32 = caseMoney32;
	}
	
	public Carmodel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(Carmodel carmodel) {
		this.carmodel = carmodel;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public LocalDateTime getApplicantBirthday() {
		return applicantBirthday;
	}

	public void setApplicantBirthday(LocalDateTime applicantBirthday) {
		this.applicantBirthday = applicantBirthday;
	}

	public LocalDateTime getInsurantBirthday() {
		return insurantBirthday;
	}

	public void setInsurantBirthday(LocalDateTime insurantBirthday) {
		this.insurantBirthday = insurantBirthday;
	}
	
	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDateTime productionDate) {
		this.productionDate = productionDate;
	}

	
	
	public String getUnderwriterName() {
		return underwriterName;
	}

	public void setUnderwriterName(String underwriterName) {
		this.underwriterName = underwriterName;
	}

	public String getCarmodelName() {
		return carmodelName;
	}

	public void setCarmodelName(String carmodelName) {
		this.carmodelName = carmodelName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCarseries() {
		return carseries;
	}

	public void setCarseries(String carseries) {
		this.carseries = carseries;
	}

	public String getCartypeLocalName() {
		return cartypeLocalName;
	}

	public void setCartypeLocalName(String cartypeLocalName) {
		this.cartypeLocalName = cartypeLocalName;
	}

	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public Cartype getCartype() {
		return cartype;
	}

	public void setCartype(Cartype cartype) {
		this.cartype = cartype;
	}

	public Long getRate21() {
		return rate21;
	}

	public void setRate21(Long rate21) {
		this.rate21 = rate21;
	}

	public Long getRate31() {
		return rate31;
	}

	public void setRate31(Long rate31) {
		this.rate31 = rate31;
	}

	public Long getRate32() {
		return rate32;
	}

	public void setRate32(Long rate32) {
		this.rate32 = rate32;
	}

	public Long getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(Long totalRate) {
		this.totalRate = totalRate;
	}

	public Long getCarmodelID() {
		return carmodelID;
	}

	public void setCarmodelID(Long carmodelID) {
		this.carmodelID = carmodelID;
	}

	public Long getUnderwriterID() {
		return underwriterID;
	}

	public void setUnderwriterID(Long underwriterID) {
		this.underwriterID = underwriterID;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssessorName() {
		return assessorName;
	}

	public void setAssessorName(String assessorName) {
		this.assessorName = assessorName;
	}

	public LocalDateTime getAssessorDate() {
		return assessorDate;
	}

	public void setAssessorDate(LocalDateTime assessorDate) {
		this.assessorDate = assessorDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}