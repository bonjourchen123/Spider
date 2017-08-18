package org.iii.module.setting.car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Tony
 *
 * @collaborator ShinChen
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="CARSERIES")
public class Carseries extends GenericCodeNameEntity {
	@Column(name="brand_ID")
	private Long brandID;

	@Column(name="type")
	private String type;
	
	@Transient
	private Brand brand;
	
	@Transient
	private String carseriesLocalName;
	
	public Long getBrandID() {
		return brandID;
	}

	public void setBrandID(Long brandID) {
		this.brandID = brandID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCarseriesLocalName() {
		return carseriesLocalName;
	}

	public void setCarseriesLocalName(String carseriesLocalName) {
		this.carseriesLocalName = carseriesLocalName;
	}

	
	
}
