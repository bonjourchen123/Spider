package org.iii.module.commodity.rate.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.core.model.LabelValueModel;
import org.iii.module.commodity.rate.entity.Rate21;
import org.iii.module.commodity.rate.entity.Rate31;
import org.iii.module.commodity.rate.entity.Rate32;
import org.iii.module.commodity.rate.service.Rate21Service;
import org.iii.module.commodity.rate.service.Rate31Service;
import org.iii.module.commodity.rate.service.Rate32Service;
import org.iii.module.setting.car.service.CartypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author bonjour
 *
 */
@SuppressWarnings({ "serial", "rawtypes" })
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RateAction extends GenericAction{
	//車種資料
	private List<LabelValueModel> cartypes;
	
	//查詢21保費
	private String cartypeID;
	private String gender;
	private String age;
	private String tonnage;
	private String seats;
	
	//查詢31保費+查詢32保費
	private String caseMoney;
	
	@Autowired
	private CartypeService cartypeService;
	@Autowired
	private Rate21Service rate21Service;
	@Autowired
	private Rate31Service rate31Service;
	@Autowired
	private Rate32Service rate32Service;
	
	//抓出車種資料
	public String findCartypes(){
		cartypes = cartypeService.getAllCarType();
		return SUCCESS;
	}

	//查詢21保費
	@SuppressWarnings("unchecked")
	public String queryRate21(){
		cartypes = cartypeService.getAllCarType();
		List<Rate21> rate21s=rate21Service.queryRate21(Long.valueOf(cartypeID),gender,Integer.valueOf(age),Double.valueOf(tonnage),Integer.valueOf(seats));
		for(Rate21 rate21:rate21s){
			for(LabelValueModel cartype:cartypes){
				if(rate21.getCartypeID().toString().equals(cartype.getValue())){
					rate21.setCartypeLocalName(cartype.getLabel());
					break;
				}
			}
			String[] rates=rate21.getRate().split(",");
			rate21.setRateLevel1(rates[0]);
			rate21.setRateLevel2(rates[1]);
			rate21.setRateLevel3(rates[2]);
			rate21.setRateLevel4(rates[3]);
			rate21.setRateLevel5(rates[4]);
			rate21.setRateLevel6(rates[5]);
			rate21.setRateLevel7(rates[6]);
			rate21.setRateLevel8(rates[7]);
			rate21.setRateLevel9(rates[8]);
			rate21.setRateLevel10(rates[9]);
		}
		jqGrid(rate21s);
		return SUCCESS;
	}
	
	//查詢31保費
	@SuppressWarnings("unchecked")
	public String queryRate31(){
		cartypes = cartypeService.getAllCarType();
		List<Rate31> rate31s=rate31Service.queryRate31(Long.valueOf(cartypeID), Integer.valueOf(caseMoney));
		for(Rate31 rate31:rate31s){
			for(LabelValueModel cartype:cartypes){
				if(rate31.getCartypeID().toString().equals(cartype.getValue())){
					rate31.setCartypeLocalName(cartype.getLabel());
					break;
				}
			}
		}
		jqGrid(rate31s);
		return SUCCESS;
	}
	
	//查詢32保費
	@SuppressWarnings("unchecked")
	public String queryRate32(){
		cartypes = cartypeService.getAllCarType();
		List<Rate32> rate32s=rate32Service.queryRate32(Long.valueOf(cartypeID), Integer.valueOf(caseMoney));
		for(Rate32 rate32:rate32s){
			for(LabelValueModel cartype:cartypes){
				if(rate32.getCartypeID().toString().equals(cartype.getValue())){
					rate32.setCartypeLocalName(cartype.getLabel());
					break;
				}
			}
		}
		jqGrid(rate32s);
		return SUCCESS;
	}
	
	public List<LabelValueModel> getCartypes() {
		return cartypes;
	}

	public void setCartypes(List<LabelValueModel> cartypes) {
		this.cartypes = cartypes;
	}

	public String getCartypeID() {
		return cartypeID;
	}

	public void setCartypeID(String cartypeID) {
		this.cartypeID = cartypeID;
	}

	public String getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(String caseMoney) {
		this.caseMoney = caseMoney;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTonnage() {
		return tonnage;
	}

	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}
}
