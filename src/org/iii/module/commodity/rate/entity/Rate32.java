package org.iii.module.commodity.rate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;

/**
 * @author ant
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="Rate_32")
public class Rate32 extends GenericEntity{
	
	@Column(name = "cartype_ID")
	private Long cartypeID;
	
	@Column(name = "case_Money")
	private Integer caseMoney;
	
	@Column(name = "rate")
	private Long rate;

	@Transient
	private String cartypeLocalName;
	
	public Long getCartypeID() {
		return cartypeID;
	}

	public void setCartypeID(Long cartypeID) {
		this.cartypeID = cartypeID;
	}

	public Integer getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(Integer caseMoney) {
		this.caseMoney = caseMoney;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	public String getCartypeLocalName() {
		return cartypeLocalName;
	}

	public void setCartypeLocalName(String cartypeLocalName) {
		this.cartypeLocalName = cartypeLocalName;
	}
}
