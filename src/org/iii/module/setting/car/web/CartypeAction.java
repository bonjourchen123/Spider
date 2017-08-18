package org.iii.module.setting.car.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.module.setting.car.entity.Cartype;
import org.iii.module.setting.car.service.CartypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author bonjour
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CartypeAction extends GenericAction<Cartype>{
	//查詢車種
	private String code;
	private String localName;
	private String type;
	private String purpose;
	private String cc;
	
	//AJAX查詢車型
	private String carmodelCode;
	private Cartype cartype;
	
	@Autowired
	private CartypeService cartypeService;
	
	public void validateQueryType() {
		this.code=code.trim();
		this.localName=localName.trim();
	}
	
	//查詢車種
	public String queryType(){
		List<Cartype> cartypes=cartypeService.queryByLike(code, localName, purpose, type,Integer.valueOf(cc));
		jqGrid(cartypes);
		return SUCCESS; 
	}
		
	//AJAX查車種
	public String queryTypeAjax(){
		cartype=cartypeService.queryType(carmodelCode, purpose);
		return SUCCESS;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public Cartype getCartype() {
		return cartype;
	}

	public void setCartype(Cartype cartype) {
		this.cartype = cartype;
	}

	public String getCarmodelCode() {
		return carmodelCode;
	}

	public void setCarmodelCode(String carmodelCode) {
		this.carmodelCode = carmodelCode;
	}
}
