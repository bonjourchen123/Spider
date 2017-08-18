package org.iii.module.setting.car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Tony
 * @collaborator Kuso
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CARTYPE")
public class Cartype extends GenericCodeNameEntity {
	@Column(name="purpose")
	private String purpose;
	
	@Column(name="type")
	private String type;
	
	@Column(name="cc_Min")
	private Integer ccMin;
	
	@Column(name="cc_Max")
	private Integer ccMax;

	@Transient
	private String cartypeLocalName;
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCcMin() {
		return ccMin;
	}

	public void setCcMin(Integer ccMin) {
		this.ccMin = ccMin;
	}

	public Integer getCcMax() {
		return ccMax;
	}

	public void setCcMax(Integer ccMax) {
		this.ccMax = ccMax;
	}

	public String getCartypeLocalName() {
		return cartypeLocalName;
	}

	public void setCartypeLocalName(String cartypeLocalName) {
		this.cartypeLocalName = cartypeLocalName;
	}
	
}