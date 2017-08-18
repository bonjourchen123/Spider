package org.iii.module.setting.office.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.office.entity.Office;
import org.iii.module.setting.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author ant
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OfficeAction extends GenericAction<Office>{
	//查詢部門+新建部門
	private String code;
	private String localName;
	private String engName;
	
	//編輯帳號
	private Long id;
	
	//查詢部門
	public String queryOffice(){
		List<Office> officeBeans=officeService.queryByLike(code, localName, engName);
//		if(!officeBeans.isEmpty()){
//			getRequest().setAttribute("officeBeans", officeBeans);
//		}else{
//			this.addFieldError("result","查無資料!");
//		}
		jqGrid(officeBeans);
		return SUCCESS; 
	}
	
	//驗證查詢部門
	public void validateQueryOffice() {
			this.code=code.trim();
			this.localName=localName.trim();
			this.engName=engName.trim();
	}
	
	//部門是否存在
	//private Boolean numberEmpty;
	private Office office;
	
	//確認部門代碼是否存在
	public String checkOfficeCodeEmpty(){
		office = officeService.getOfficeCode(code);
		return SUCCESS;
	}
	
	//確認部門中文名稱是否存在
	public String checkOfficeLocalNameEmpty(){
		office = officeService.getOfficeLocalName(localName);
		return SUCCESS;
	}	
		
	//確認部門英文名稱是否存在
	public String checkOfficeEngNameEmpty(){
		office = officeService.getOfficeEngName(engName);
		return SUCCESS;
	}	
		
	//驗證新建部門
	public void validateCreateOffice() {
		this.code=code.trim();
		this.localName=localName.trim();
		this.engName=engName.trim();
	
		//驗證代碼
		if(code == null || code.length() == 0){
			this.addFieldError("code", "請輸入代碼!");
		}
		else if(officeService.getOfficeCode(code)!=null){
			this.addFieldError("code", "代碼已存在,請更改!");
		}
		//驗證名稱
		if(localName == null || localName.length() == 0){
			this.addFieldError("localName", "請輸入名稱!");
		}
		else if(officeService.getOfficeLocalName(localName)!=null){
			this.addFieldError("localName", "部門名稱已存在,請更改!");
		}
	}
	
	//新建部門訊息
	public String createOffice() {
		if(officeService.createOffice(getLoginUser(), code, localName, engName)){
			this.addFieldError("result","已成功新建部門!");
			return SUCCESS;
		}else{
			this.addFieldError("result","新建部門失敗!");
			return INPUT;
		}
	}
	
	//進入編輯部門畫面
		public String editorOffice(){
			Office office = officeService.getOfficeId(id);
			code = office.getCode();
			localName = office.getLocalName();
			engName = office.getEngName();
			return SUCCESS;
		}
		
	//驗證編輯部門
	public void validateUpdateOffice() {
		this.code=code.trim();
		this.localName=localName.trim();
		this.engName=engName.trim();
		
		//驗證代碼
		if(code == null || code.length() == 0){
			this.addFieldError("code", "請輸入代碼!");
		}
//		else{
//			if(officeService.getOfficeCode(code)!=null){
//				this.addFieldError("code", "代碼已存在,請更改!");
//			}
//		}
		//驗證名稱
		if(localName == null || localName.length() == 0){
			this.addFieldError("localName", "請輸入名稱!");
		}
//		else{
//			if(officeService.getOfficeLocalName(localName)!=null){
//				this.addFieldError("localName", "部門名稱已存在,請更改!");
//			}
//		}
	}
	
	//編輯部門訊息
	public String updateOffice() {
		if(officeService.updateOffice(getLoginUser(), code, localName, engName)){
			this.addFieldError("result","已成功編輯部門!");
			return SUCCESS;
		}else{
			this.addFieldError("result","編輯部門失敗!");
			return INPUT;
		}
	}
	
	//刪除部門訊息
	public String deleteOffice(){
		if(officeService.deleteOffice(id)){
			this.addFieldError("result","已成功刪除部門!");
			return SUCCESS;
		}else{
			this.addFieldError("result","刪除部門失敗!");
			return INPUT;
		}
	}
	
	@Autowired
	private OfficeService officeService;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}
