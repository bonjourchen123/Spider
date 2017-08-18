package org.iii.module.commodity.rate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;

/**
 * @author Tony
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RATE_21")
public class Rate21 extends GenericEntity {
	@Column(name = "cartype_ID")
	private Long cartypeID;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "from_Age")
	private Integer fromAge;
	
	@Column(name = "to_Age")
	private Integer toAge;
	
	@Column(name = "tonnage_Min")
	private Double tonnageMin;
	
	@Column(name = "tonnage_Max")
	private Double tonnageMax;
	
	@Column(name = "seats_Min")
	private Integer seatsMin;
	
	@Column(name = "seats_Max")
	private Integer seatsMax;
	
	@Column(name = "rate")
	private String rate;

	@Transient
	private String cartypeLocalName;
	
	@Transient
	private String rateLevel1;
	
	@Transient
	private String rateLevel2;
	
	@Transient
	private String rateLevel3;
	
	@Transient
	private String rateLevel4;

	@Transient
	private String rateLevel5;

	@Transient
	private String rateLevel6;

	@Transient
	private String rateLevel7;

	@Transient
	private String rateLevel8;

	@Transient
	private String rateLevel9;

	@Transient
	private String rateLevel10;
	
	public Long getCartypeID() {
		return cartypeID;
	}

	public void setCartypeID(Long cartypeID) {
		this.cartypeID = cartypeID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFromAge() {
		return fromAge;
	}

	public void setFromAge(Integer fromAge) {
		this.fromAge = fromAge;
	}

	public Integer getToAge() {
		return toAge;
	}

	public void setToAge(Integer toAge) {
		this.toAge = toAge;
	}

	public Double getTonnageMin() {
		return tonnageMin;
	}

	public void setTonnageMin(Double tonnageMin) {
		this.tonnageMin = tonnageMin;
	}

	public Double getTonnageMax() {
		return tonnageMax;
	}

	public void setTonnageMax(Double tonnageMax) {
		this.tonnageMax = tonnageMax;
	}

	public Integer getSeatsMin() {
		return seatsMin;
	}

	public void setSeatsMin(Integer seatsMin) {
		this.seatsMin = seatsMin;
	}

	public Integer getSeatsMax() {
		return seatsMax;
	}

	public void setSeatsMax(Integer seatsMax) {
		this.seatsMax = seatsMax;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCartypeLocalName() {
		return cartypeLocalName;
	}

	public void setCartypeLocalName(String cartypeLocalName) {
		this.cartypeLocalName = cartypeLocalName;
	}

	public String getRateLevel1() {
		return rateLevel1;
	}

	public void setRateLevel1(String rateLevel1) {
		this.rateLevel1 = rateLevel1;
	}

	public String getRateLevel2() {
		return rateLevel2;
	}

	public void setRateLevel2(String rateLevel2) {
		this.rateLevel2 = rateLevel2;
	}

	public String getRateLevel3() {
		return rateLevel3;
	}

	public void setRateLevel3(String rateLevel3) {
		this.rateLevel3 = rateLevel3;
	}

	public String getRateLevel4() {
		return rateLevel4;
	}

	public void setRateLevel4(String rateLevel4) {
		this.rateLevel4 = rateLevel4;
	}

	public String getRateLevel5() {
		return rateLevel5;
	}

	public void setRateLevel5(String rateLevel5) {
		this.rateLevel5 = rateLevel5;
	}

	public String getRateLevel6() {
		return rateLevel6;
	}

	public void setRateLevel6(String rateLevel6) {
		this.rateLevel6 = rateLevel6;
	}

	public String getRateLevel7() {
		return rateLevel7;
	}

	public void setRateLevel7(String rateLevel7) {
		this.rateLevel7 = rateLevel7;
	}

	public String getRateLevel8() {
		return rateLevel8;
	}

	public void setRateLevel8(String rateLevel8) {
		this.rateLevel8 = rateLevel8;
	}

	public String getRateLevel9() {
		return rateLevel9;
	}

	public void setRateLevel9(String rateLevel9) {
		this.rateLevel9 = rateLevel9;
	}

	public String getRateLevel10() {
		return rateLevel10;
	}

	public void setRateLevel10(String rateLevel10) {
		this.rateLevel10 = rateLevel10;
	}

}
