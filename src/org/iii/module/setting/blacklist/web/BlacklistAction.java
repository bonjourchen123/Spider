package org.iii.module.setting.blacklist.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.blacklist.entity.Blacklist;
import org.iii.module.setting.blacklist.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Hansen
 * 
 */
@SuppressWarnings("serial")
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BlacklistAction extends GenericAction<Blacklist> {

	// 查詢黑名單+新增黑名單
	private String type;
	private String number;
	private String reason;

	//帳號是否存在
	//private Boolean numberEmpty;
	private Blacklist blacklist;
	
	//編輯帳號
	private Long id;
	private String oldNumber;
	
	@Autowired
	private BlacklistService blacklistService;

	// 查詢黑名單
	public String queryBlacklist() {
		List<Blacklist> blacklists = blacklistService.queryByLike(number, type,reason);
		jqGrid(blacklists);
		return SUCCESS; 
	}
	
	//驗證查詢黑名單
	public void validateQueryBlacklist() {
		this.number=number.trim();//查詢時把多打的空白自動刪除來查詢
	}
	
	//AJAX用 確認黑名單是否存在
//	public String checkNumberEmpty(){
//		if(blacklistService.getBlacklistNumber(number)==null){
//			numberEmpty=true;
//		}else{
//			numberEmpty=false;
//		}
//		return SUCCESS;
//	}
	public String checkBlacklistEmpty(){
		blacklist=blacklistService.getBlacklistNumber(number);
		return SUCCESS;
	}
		
	// 驗證新增黑名單
	public void validateCreateBlacklist() {
		this.number=number.trim();
		this.reason=reason.trim();
		if(number==null || number.length()==0){
			this.addFieldError("number", "請輸入號碼!");
		}else{
			if(blacklistService.getBlacklistNumber(number)!=null){
				this.addFieldError("number", "號碼已存在，請更改!");
			}
		}
	}
	
	// 新增黑名單
	public String createBlacklist() {
		if(blacklistService.createBlacklist(getLoginUser(),type, number, reason)){
			this.addFieldError("result","已成功新建黑名單!");
			return SUCCESS;
		}else{
			this.addFieldError("result","新建失敗!");
		}
		return INPUT;
	}
	
	//進入編輯黑名單畫面
		public String editorBlacklist(){
			Blacklist blacklist=blacklistService.getBlacklistId(id);
			type=blacklist.getType().toString();
			number=blacklist.getNumber();
			reason=blacklist.getReason();
			return SUCCESS;
		}
	
	//驗證編輯黑名單
	public void validateUpdateBlacklist() {
		this.number=number.trim();
		this.reason=reason.trim();
		if(number==null || number.length()==0){
			this.addFieldError("number", "請輸入號碼!");
		}else{
			if(!number.equals(oldNumber)){
				if(blacklistService.getBlacklistNumber(number)!=null){
					this.addFieldError("number", "號碼已存在，請更改!");
				}
			}
		}
	}	
		
	//編輯黑名單
	public String updateBlacklist(){
		if(blacklistService.updateBlacklist(getLoginUser(),type, number, reason ,id)){
			this.addFieldError("result","已成功修改黑名單!");
			return SUCCESS;
		}else{
			this.addFieldError("result","修改黑名單失敗!");
			return INPUT;
		}
	}
	//刪除黑名單
	public String deleteBlacklist(){
		if(blacklistService.deleteBlacklist(id)){
			this.addFieldError("result","已成功刪除黑名單!");
		}else{
			this.addFieldError("result","刪除黑名單失敗!");
		}
		return SUCCESS;
	}
	
	
	public Blacklist getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(Blacklist blacklist) {
		this.blacklist = blacklist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Boolean getNumberEmpty() {
//		return numberEmpty;
//	}
//
//	public void setNumberEmpty(Boolean numberEmpty) {
//		this.numberEmpty = numberEmpty;
//	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}
}
