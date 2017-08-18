package org.iii.module.setting.car.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.service.BrandService;
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
public class BrandAction extends GenericAction<Brand> {
	// 查詢廠牌+新增廠牌
	private String code;
	private String localName;
	private String engName;
	private String descript;
	
	//帳號是否存在
	private Brand brand;
	
	//編輯帳號
	private Long id;
	
	@Autowired
	private BrandService brandService;

	// 查詢廠牌
	public String queryBrand() {
		List<Brand> brands = brandService.queryByLike(code, localName, engName,descript);
		jqGrid(brands);
		return SUCCESS; 
	}
	
	//驗證查詢廠牌
	public void validateQueryBrand() {
		this.code=code.trim();//查詢時把多打的空白自動刪除來查詢
		this.localName=localName.trim();
		this.engName=engName.trim();
	}
		
	//AJAX用 確認廠牌是否存在
	public String checkBrandEmpty(){
		brand=brandService.getBrandCode(code);
		return SUCCESS;
	}
		
	//驗證新增廠牌
	public void validateCreateBrand() {
		this.code=code.trim();
		this.localName=localName.trim();
		if(code==null || code.length()==0){
			this.addFieldError("code", "請輸入代號!");
		}else{
			if(brandService.getBrandCode(code)!=null){
				this.addFieldError("code", "代號已存在，請更改!");
			}
		}if(localName==null || localName.length()==0){
			this.addFieldError(localName, "請輸入名稱!");
		}
	}	
		
	// 新增廠牌
	public String  createBrand() {
		if(brandService.createBrand(getLoginUser(), code, localName, engName, descript)){
			this.addFieldError("result","已成功新建廠牌!");
			return SUCCESS;
		}else{
			this.addFieldError("result","新建失敗!");
			return INPUT;
		}
	}
	
	//進入編輯廠牌畫面
	public String editorBrand(){
		Brand brand=brandService.getBrandId(id);
		code=brand.getCode();
		localName=brand.getLocalName();
		engName=brand.getEngName();
		descript=brand.getDescript();
		return SUCCESS;
	}
	
	//驗證編輯廠牌
	public void validateUpdateBrand() {
		this.code=code.trim();
		this.localName=localName.trim();
		this.engName=engName.trim();
		this.descript=descript.trim();
		if(localName==null || localName.length()==0){
			this.addFieldError(localName, "請輸入名稱!");
		}
	}
	
	//編輯廠牌
	public String updateBrand(){
		if(brandService.updateBrand(getLoginUser(), code, localName, engName, descript)){
			this.addFieldError("result","已成功修改廠牌!");
			return SUCCESS;
		}else{
			this.addFieldError("result","修改廠牌失敗!");
			return INPUT;
		}
	}
	
	//刪除廠牌
	public String deleteBrand(){
		if(brandService.deleteBrand(id)){
			this.addFieldError("result","已成功刪除廠牌!");
		}else{
			this.addFieldError("result","刪除廠牌失敗!");
		}
		return SUCCESS;
	}
	
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
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


	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
