package org.iii.core.security.web;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.iii.core.action.GenericAction;
import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.core.security.service.SecUserService;
import org.iii.core.security.service.UserRoleService;
import org.iii.module.setting.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

/**
 * @author bonjour
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountAction extends GenericAction<SecUser>{
	//修改密碼
	private String password;
	private String newPassword;
	private String checkPassword;
	
	//部門資料
	private List<LabelValueModel> offices = Lists.newArrayList();
	
	//新建帳號+查詢帳號
	private String account;
	private String name;
	private String phone;
	private String email;
	private String[] rolesCode;
	private String officeID;
	
	//帳號是否存在
	private SecUser secUser;
	
	//編輯帳號
	private Long id;
	
	@Autowired
	private SecUserService secUserService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private UserRoleService userRoleService;
		
	//驗證修改密碼
	public void validateChangePassword() {
		this.password=password.trim();
		this.newPassword=newPassword.trim();
		this.checkPassword=checkPassword.trim();
		if(password==null || password.length()==0){
			this.addFieldError("password", "請輸入密碼!");
		}
		if(newPassword==null || newPassword.length()==0){
			this.addFieldError("newPassword", "請輸入新密碼!");
		}else{
			if(!newPassword.matches("^.*(?!.*\\s)(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,}).*$")){
				this.addFieldError("newPassword", "密碼強度不足!");
			}
		}
		if(checkPassword==null || checkPassword.length()==0){
			this.addFieldError("checkPassword", "請輸入新密碼!");
		}else{
			if(!checkPassword.equals(newPassword)){
				this.addFieldError("checkPassword", "請重新確認新密碼!");
			}
		}
	}
	
	//修改密碼
	public String changePassword(){
		SecUser user=getLoginUser();
		if(user.getPassword().equals(DigestUtils.md5Hex(password))){
			if(secUserService.changePassword(user, newPassword)){
				this.addFieldError("result","已成功修改密碼!");
				return SUCCESS;
			}else{
				this.addFieldError("result","修改密碼失敗!");
				return INPUT;
			}
		}else{
			this.addFieldError("result","密碼錯誤!");
			return INPUT;
		}
	}
	
	//抓出部門資料
	public String findOffices(){
		offices = officeService.getAllOffice();
		return SUCCESS;
	}
	
	//AJAX用 確認帳號是否存在
	public String checkAccountEmpty(){
		secUser=secUserService.getUserInfo(account);
		return SUCCESS;
	}
	
	//驗證新建帳號
	public void validateCreateAccount() {
		offices = officeService.getAllOffice();
		this.account=account.trim();
		this.name=name.trim();
		this.phone=phone.trim();
		this.email=email.trim();
		if(account==null || account.length()==0){
			this.addFieldError("account", "請輸入帳號!");
		}else{
			if(secUserService.getUserInfo(account)!=null){
				this.addFieldError("account", "帳號已存在，請更改!");
			}
		}
		if(name==null || name.length()==0){
			this.addFieldError("name", "請輸入姓名!");
		}
		if(email==null || email.length()==0){
			this.addFieldError("email", "請輸入電子信箱!");
		}else{
			if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")){
				this.addFieldError("email", "電子信箱格式錯誤!");
			}
		}
		if(officeID.equals("0")){
			this.addFieldError("officeID", "請選擇部門!");
		}
	}
	
	//新建帳號(亂數產生密碼寄至信箱)
	public String createAccount(){
		if(secUserService.createAccount(getLoginUser(), account, name, phone, email, officeID, rolesCode)){
			this.addFieldError("result","已成功新建帳號!");
			return SUCCESS;
		}else{
			this.addFieldError("result","新建帳號失敗!");
			return INPUT;
		}
	}
	
	//驗證查詢帳號
	public void validateQueryAccount() {
		this.account=account.trim();
		this.name=name.trim();
	}
	
	//查詢帳號
	public String queryAccount(){
		List<SecUser> users=secUserService.queryByLike(account,name,Long.valueOf(officeID));
		for(SecUser user:users){
			user.setRoleCodes(userRoleService.getRoleCodes(user));
		}
		jqGrid(users);
		return SUCCESS;
	}
	
	//進入編輯帳號畫面
	public String update(){
		offices = officeService.getAllOffice();
		SecUser secUser=secUserService.queryByID(id);
		List<String> rolesCodes=userRoleService.getRoleCodes(secUser);
		rolesCode=new String[rolesCodes.size()];
		int i=0;
		for(String roles:rolesCodes){
			rolesCode[i]=roles;
			i=i+1;
		}
		account=secUser.getAccount();
		name=secUser.getName();
		phone=secUser.getPhone();
		email=secUser.getEmail();
		officeID=secUser.getOfficeID().toString();
		return SUCCESS;
	}
	
	//驗證編輯帳號
	public void validateUpdateAccount() {
		offices = officeService.getAllOffice();
		this.name=name.trim();
		this.phone=phone.trim();
		this.email=email.trim();
		if(name==null || name.length()==0){
			this.addFieldError("name", "請輸入姓名!");
		}
		if(email==null || email.length()==0){
			this.addFieldError("email", "請輸入電子信箱!");
		}else{
			if(!email.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")){
				this.addFieldError("email", "電子信箱格式錯誤!");
			}
		}
		if(officeID.equals("0")){
			this.addFieldError("officeID", "請選擇部門!");
		}
	}
	
	//編輯帳號
	public String updateAccount(){
		if(secUserService.updateAccount(getLoginUser(), account, name, phone, email, officeID, rolesCode)){
			this.addFieldError("result","已成功修改帳號!");
			return SUCCESS;
		}else{
			this.addFieldError("result","修改帳號失敗!");
			return INPUT;
		}
	}
	
	//重設密碼(亂數產生密碼寄至信箱)
	public String resetPassword(){
		SecUser secUser=secUserService.queryByID(id);
		if(secUserService.resetPassword(getLoginUser(),secUser)){
			this.addFieldError("result","已成功重置密碼!");
		}else{
			this.addFieldError("result","重置密碼失敗!");
		}
		return SUCCESS;
	}
	
	//刪除帳號
	public String delete(){
		if(secUserService.deleteAccount(id)){
			this.addFieldError("result","已成功刪除帳號!");
		}else{
			this.addFieldError("result","刪除帳號失敗!");
		}
		return SUCCESS;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getRolesCode() {
		return rolesCode;
	}

	public void setRolesCode(String[] rolesCode) {
		this.rolesCode = rolesCode;
	}

	public String getOfficeID() {
		return officeID;
	}

	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}

	public List<LabelValueModel> getOffices() {
		return offices;
	}

	public void setOffices(List<LabelValueModel> offices) {
		this.offices = offices;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}