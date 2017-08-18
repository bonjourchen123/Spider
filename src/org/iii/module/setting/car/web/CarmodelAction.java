package org.iii.module.setting.car.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.entity.Carmodel;
import org.iii.module.setting.car.entity.Carseries;
import org.iii.module.setting.car.service.BrandService;
import org.iii.module.setting.car.service.CarmodelService;
import org.iii.module.setting.car.service.CarseriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author ant
 * @collaborator Hansen
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarmodelAction extends GenericAction<Carmodel>{
	//查詢車型+新增車型
	private String code;
	private String brandCode;
	private String seriesCode;
	private String localName;
	private Long carseriesID;
	private Integer cc;
	private String brandId;
	private String seriesId;
	private String carSeries;
	private String brand;
	
	//AJAX查詢車型
	private Carmodel carmodel;
	
	//編輯帳號
	private Long id;
	private String oldCode;
	
	@Autowired
	private CarmodelService carmodelService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CarseriesService carseriesService;
	
	
	//查詢車型
	public String queryModel(){
		List<Carmodel> modelBeans=carmodelService.queryByLike(code, brandCode, seriesCode, localName);
		if(!modelBeans.isEmpty()){
			getRequest().setAttribute("modelBeans", modelBeans);
		}else{
			this.addFieldError("result","查無資料!");
		}
		jqGrid(modelBeans);
		return SUCCESS; 
	}

	//驗證查詢帳號(去欄位空白)
	public void validateQueryModel() {
		this.brandCode=brandCode.trim();
		this.seriesCode=seriesCode.trim();
		this.code=code.trim();
		this.localName=localName.trim();
	}
	
	//驗證AJAX查車型
	public void validateQueryModelAjax(){
		this.code=code.trim();
	}
	
	//AJAX查車型
	public String queryModelAjax(){
		carmodel=carmodelService.queryByCode(code);
		return SUCCESS;
	}
	//驗證新增車型
	public void validateCreateCarModel(){
		this.brandCode=brandCode.trim();
		this.seriesCode=seriesCode.trim();
		this.code=code.trim();
		this.localName=localName.trim();
		
		if(brandCode==null || brandCode.length()==0){
			this.addFieldError("brandCode", "請輸入廠牌代碼!");
			this.addFieldError("seriesCode", "請輸入廠牌代碼!");
			this.addFieldError("code", "請輸入廠牌代碼!");
		}else{
			if(brandService.getBrandCode(brandCode) ==null){
				this.addFieldError("brandCode", "廠牌代碼不存在，請更改!");
				this.addFieldError("seriesCode", "廠牌代碼不存在，請更改!");
				this.addFieldError("code", "廠牌代碼不存在，請更改!");
			}else{
				if(seriesCode==null || seriesCode.length()==0){
					this.addFieldError("seriesCode", "請輸入車系代碼!");
					this.addFieldError("code", "請輸入車系代碼!");
				}else{
					Carseries carseries=carseriesService.getCarseries(seriesCode, brandCode);
					if(carseries==null){
						this.addFieldError("seriesCode", "車系代碼不存在，請更改!");		
						this.addFieldError("code", "車系代碼不存在，請更改!");
					}else{
						if(carseries.getType().equals("機車")){
							if(cc==null){
								this.addFieldError("cc", "請輸入CC數!");
							}
						}
						if(code==null || code.length()==0){
							this.addFieldError("code", "請輸入代碼!");
						}else{
							if(carmodelService.queryByCode(brandCode+seriesCode+code)!=null){
								this.addFieldError("code", "代碼已存在，請更改!");
							}
						}
					}
				}
			}
		}
		if(localName==null || localName.length()==0){
			this.addFieldError("localName", "請輸入名稱!");
		}
		
	}
	
	
	//新增車型
	public String  createCarModel() {
		if(carmodelService.createCarModel(getLoginUser(), code, brandCode, seriesCode, localName, cc)){
			this.addFieldError("result","已成功新建車型!");
			return SUCCESS;
		}else{
			this.addFieldError("result","新建失敗!");
			return INPUT;
		}
	}
	
	
	//進入編輯車型畫面
	public String editorCarModel(){
		//Carseries carseries=carseriesService.getCarseries(id);
		Carmodel carmodel=carmodelService.getCarModelId(id);
		code=carmodel.getCode().substring(4, carmodel.getCode().length());
		Carseries carseriess=carseriesService.getCarseries(carmodel.getCarseriesID());
		Brand brands=brandService.getBrandId(carseriess.getBrandID());
		brandCode=brands.getCode();
		brand=brands.getLocalName();
		seriesCode=carseriess.getCode();
		carSeries=carseriess.getLocalName();
		brandId=brands.getCode();
		seriesId=carseriess.getCode();
		localName=carmodel.getLocalName();
		cc=carmodel.getCc();
		return SUCCESS;
	}
	
	//驗證編輯車型
	public void validateUpdateCarModel(){
		this.code=code.trim();
		this.localName=localName.trim();
		if(code==null || code.length()==0){
			this.addFieldError("code", "請輸入代碼!");
		}else{
			if(!code.equals(oldCode)){
				if(carmodelService.queryByCode(brandCode+seriesCode+code)!=null){
					this.addFieldError("code", "代碼已存在，請更改!");
				}
			}
		}
		if(localName==null || localName.length()==0){
			this.addFieldError("localName", "請輸入名稱!");	
		}
		if(carseriesService.getCarseries(seriesCode, brandCode).getType().equals("機車")){
			if(cc==null){
				this.addFieldError("cc", "請輸入CC數!");
			}
		}
	}
		
	//編輯車型
	public String updateCarModel(){
		if(carmodelService.updateCarModel(getLoginUser(),id, code, brandCode, seriesCode, localName, cc)){
			this.addFieldError("result","已成功修改車型!");
			return SUCCESS;
		}else{
			this.addFieldError("result","修改車型失敗!");
			return INPUT;
		}
	}
	
	//刪除車型
	public String deleteCarModel(){
		if(carmodelService.deleteCarModel(id)){
			this.addFieldError("result","已成功刪除車型!");
		}else{
			this.addFieldError("result","刪除車型失敗!");
		}
		return SUCCESS;
	}
		
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getSeriesCode() {
		return seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
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

	public Long getCarseriesID() {
		return carseriesID;
	}

	public void setCarseriesID(Long carseriesID) {
		this.carseriesID = carseriesID;
	}

	public Carmodel getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(Carmodel carmodel) {
		this.carmodel = carmodel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public String getCarSeries() {
		return carSeries;
	}

	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}
	
	
}