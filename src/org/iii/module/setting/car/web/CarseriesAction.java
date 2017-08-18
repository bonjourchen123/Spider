package org.iii.module.setting.car.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.entity.Carseries;
import org.iii.module.setting.car.service.BrandService;
import org.iii.module.setting.car.service.CarseriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author ShinChen
 * 
 * 
 */
@SuppressWarnings("serial")
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarseriesAction extends GenericAction<Carseries> {
	// 查詢車型
	private String code;
	private String brandCode;
	private String localName;
	private String type;
	private String brandName;
	//編輯
	private Long id;
	//Ajax查詢
	private Brand brand;
	private Carseries carseries;
	@Autowired
	private CarseriesService carseriesService;
	@Autowired
	private BrandService brandService;
	
	//查詢驗證
	public void validateQueryCarseries(){
		this.code=code.trim();
		this.brandCode=brandCode.trim();
		this.localName=localName.trim();

	}
	
	//查詢
	public String queryCarseries() {
		List<Carseries> carseriess=carseriesService.queryByLike(code, brandCode, localName, type);
//		if(!carseriess.isEmpty()){
//			getRequest().setAttribute("carseriess",carseriess);
//		}else{
//			this.addFieldError("result","查無資料!");
//		}
		jqGrid(carseriess);
		return SUCCESS;
	}
	
	//新增驗證
	public void validateCreateCarseries(){
		this.code=code.trim();
		this.brandCode=brandCode.trim();
		this.localName=localName.trim();
		
		//代碼
		if(code == null || code.length() == 0){
			this.addFieldError("code", "請輸入代碼!");
		}
		//廠牌編號
		if(brandCode == null || brandCode.length() == 0){
			this.addFieldError("brandCode", "請輸入廠牌編號!");
		}
		else {
			if(brandService.getBrandCode(brandCode) == null){
					this.addFieldError("brandCode", "廠牌編號不存在,請更改!");
				}
			else if(carseriesService.getCarseries(code, brandCode) != null){
					this.addFieldError("code", "代碼已存在,請更改!");
				}
			}
		
		//名稱
		if(localName == null || localName.length() == 0){
			this.addFieldError("localName", "請輸入名稱!");
		}
		//車型
		if(type.equals("0")){
			this.addFieldError("type", "請選擇車型!");
		}
	}
	
	//驗證AJAX查車型
		public void validateQueryBrandAjax(){
			this.brandCode=brandCode.trim();
		}
	
	//AJAX;
	public String queryBrandAjax(){
		brand=carseriesService.getBrand(brandCode);
		return SUCCESS;
		
	}
	
	//AJAX用 確認車系是否存在
	public String checkCarseriesEmpty(){
		carseries=carseriesService.getCarseries(code,brandCode);
		return SUCCESS;
	}
	
	
	//新增
	public String createCarseries(){
		if(carseriesService.createCarseries(getLoginUser(), code, brandCode, localName, type)){
			this.addFieldError("result","已成功新建車系!");
			return SUCCESS;
		}else{
			this.addFieldError("result", "新建車系失敗!");
			return INPUT;
		}
	}

	//進入編輯客戶畫面
		public String editorCarseries(){
			Carseries carseries=carseriesService.getCarseries(id);
			code=carseries.getCode();
			Brand brand=brandService.getBrandId(carseries.getBrandID());
			brandCode=brand.getCode();
			brandName=brand.getLocalName();
			localName=carseries.getLocalName();
			type=carseries.getType();
			return SUCCESS;
		}
		
	//編輯驗證
		public void validateUpdateCarseries(){
			this.localName=localName.trim();
			
			//名稱
			if(localName == null || localName.length() == 0){
				this.addFieldError("localName", "請輸入名稱!");
			}
			
			//車型
			if(type.equals("0")){
				this.addFieldError("type", "請選擇車型!");
			}
		}
		
	//編輯
	public String updateCarseries(){
		if(carseriesService.updateCarseries(getLoginUser(), code, brandCode, localName, type)){
			this.addFieldError("result","已成功編輯車系!");
			return SUCCESS;
		}else{
			this.addFieldError("result","編輯車系失敗!");
			return INPUT;
		}
		
	}
	
	//刪除
	public String deleteCarseries(){
		if(carseriesService.deleteCarseries(id)){
			this.addFieldError("result","已成功刪除!");
		}else{
			this.addFieldError("result","刪除失敗!");
		}
		return SUCCESS;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Carseries getCarseries() {
		return carseries;
	}

	public void setCarseries(Carseries carseries) {
		this.carseries = carseries;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
	
}
