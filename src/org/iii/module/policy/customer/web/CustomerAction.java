package org.iii.module.policy.customer.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.policy.customer.entity.Customer;
import org.iii.module.policy.customer.service.CustomerService;
import org.iii.module.setting.office.service.OfficeService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Shin Chen
 * 
 */
@SuppressWarnings("serial")
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerAction extends GenericAction<Customer> {

	private Boolean customerEmpty;

	//新增&查詢
	private String pin;
	private String name;
	private String gender;
	private LocalDateTime birthday;
	private String phone;
	private String permanentAddr;
	private String currentAddr;
	private String officeID;
	private String rateLevel;
	private String indemnityLevel;
	//編輯&刪除
	private Long id;
	
	//AJAX抓客戶資料
	private String[] customerString;
	
	@Autowired
	private OfficeService officeService;

	@Autowired
	private CustomerService customerService;
	
	//驗證查詢
	public void validateQueryCustomer() {
//		if(getLoginUser().getOfficeID()!=Long.valueOf(officeID)){
//			this.addFieldError("result","無法查詢其他部門!");
//		}
		this.pin=pin.trim();
		this.name=name.trim();
	}

	// 查詢客戶資料
	public String queryCustomer() {
		List<Customer> customers = customerService.queryByLike(name, pin,Long.valueOf(officeID));
//		if (!customers.isEmpty()) {
//			getRequest().setAttribute("customers", customers);
//		} else {
//			this.addFieldError("result", "查無資料");
//		}
		jqGrid(customers);
		return SUCCESS;
	}
	
	//AJAX 確認身分證字號是否存在
	public String checkPinEmpty(){
		if(customerService.getCustomer(pin, Long.valueOf(officeID))==null){
			customerEmpty=true;
		}else{
			customerEmpty=false;
		}
		return SUCCESS;
	}
	
	
	//新增驗證
	public void validateCreateCustomer() {
		this.name=name.trim();
		this.pin=pin.trim();
		this.phone=phone.trim();
		this.permanentAddr=permanentAddr.trim();
		this.currentAddr=currentAddr.trim();
		
		//名子
		if(name==null || name.length()==0){
			this.addFieldError("name", "請輸入姓名!");
		}
		
		//身分證字號
		if(pin==null || pin.length()==0){
			this.addFieldError("pin", "請輸入身分證字號!");
		}else{
			if(customerService.getCustomer(pin, Long.valueOf(officeID))!=null){
				this.addFieldError("pin", "身分證字號已存在，請更改!");
			}
			
		}
		
		//生日
		if(birthday==null || birthday.toString().length()==0){
			this.addFieldError("birthday", "請輸入出生年月日!");
		}
		
		//電話
		if(phone==null || phone.length()==0){
			this.addFieldError("phone", "請輸入電話!");
		}
		
		//戶籍地址
		if(permanentAddr==null || permanentAddr.length()==0){
			this.addFieldError("permanentAddr", "請輸入戶籍地址!");
		}
		
	}
	
	//新增
	public String createCustomer(){
		if(customerService.createCustomer(getLoginUser(), name,officeID, pin, gender, birthday, phone, permanentAddr, currentAddr)){
			this.addFieldError("result","己成功新建客戶資料!");
			return SUCCESS;
		}
			this.addFieldError("result", "新建客戶資料失敗!");
			return INPUT;
	}
	
	//進入編輯客戶畫面
	public String editorCustomer(){
		Customer customer=customerService.getCustomer(id);
		name=customer.getName();
		pin=customer.getPin();
		gender=customer.getGender();
		phone=customer.getPhone();
		birthday=customer.getBirthday();
		permanentAddr=customer.getPermanentAddr();
		currentAddr=customer.getCurrentAddr();
		rateLevel=customer.getRateLevel().toString();
		indemnityLevel=customer.getIndemnityLevel().toString();
		return SUCCESS;
	}
	
	//編輯驗證
	public void validateRenewCustomer(){
		this.name=name.trim();
		this.phone=phone.trim();
		this.permanentAddr=permanentAddr.trim();
		this.currentAddr=currentAddr.trim();
		
		//名子
		if(name==null || name.length()==0){
			this.addFieldError("name", "請輸入姓名!");
		}
		
		//生日
		if(birthday==null || birthday.toString().length()==0){
			this.addFieldError("birthday", "請輸入出生年月日!");
		}
		
		//電話
		if(phone==null || phone.length()==0){
			this.addFieldError("phone", "請輸入電話!");
		}
		
		//戶籍地址
		if(permanentAddr==null || permanentAddr.length()==0){
			this.addFieldError("permanentAddr", "請輸入戶籍地址!");
		}
	}
	
	//編輯
	public String renewCustomer(){
		if(customerService.renewCustomer(getLoginUser(), name, officeID, pin, gender, birthday, phone, permanentAddr, currentAddr, rateLevel, indemnityLevel)){
			this.addFieldError("result","己成功編輯客戶資料!");
			return SUCCESS;
		}
			this.addFieldError("result", "編輯客戶資料失敗!");
			return INPUT;
	}
	
	//刪除
		public String deleteCustomer(){
			if(customerService.deleteCustomer(id)){
				this.addFieldError("result","已成功刪除客戶資料!");
			}else{
				this.addFieldError("result","刪除客戶資料失敗!");
			}
			return SUCCESS;
		}
	
	//驗證AJAX抓客戶資料
	public void validateQueryCustomerAjax(){
		this.pin=pin.trim();
	}
	
	//AJAX抓客戶資料
	public String queryCustomerAjax(){
		Customer customer=customerService.getCustomer(pin, Long.valueOf(officeID));	
		if(customer!=null){
			customerString=new String[6];
			customerString[0]=customer.getName();
			customerString[1]=customer.getGender();
			String month=String.valueOf(customer.getBirthday().getMonthOfYear());
			if(customer.getBirthday().getMonthOfYear()<10){
				month="0"+month;
			}
			String dath=String.valueOf(customer.getBirthday().getDayOfMonth());
			if(customer.getBirthday().getDayOfMonth()<10){
				dath="0"+dath;
			}
			customerString[2]=customer.getBirthday().getYear()+"-"+month+"-"+dath;
			customerString[3]=customer.getPhone();
			customerString[4]=customer.getPermanentAddr();
			customerString[5]=customer.getCurrentAddr();
		}
		return SUCCESS;
	}
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPermanentAddr() {
		return permanentAddr;
	}

	public void setPermanentAddr(String permanentAddr) {
		this.permanentAddr = permanentAddr;
	}

	public String getCurrentAddr() {
		return currentAddr;
	}

	public void setCurrentAddr(String currentAddr) {
		this.currentAddr = currentAddr;
	}


	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public String[] getCustomerString() {
		return customerString;
	}

	public void setCustomerString(String[] customerString) {
		this.customerString = customerString;
	}

	public Boolean getCustomerEmpty() {
		return customerEmpty;
	}

	public void setCustomerEmpty(Boolean customerEmpty) {
		this.customerEmpty = customerEmpty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRateLevel() {
		return rateLevel;
	}

	public void setRateLevel(String rateLevel) {
		this.rateLevel = rateLevel;
	}

	public String getIndemnityLevel() {
		return indemnityLevel;
	}

	public void setIndemnityLevel(String indemnityLevel) {
		this.indemnityLevel = indemnityLevel;
	}

	
}