package org.iii.module.setting.car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Hansen
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="CARMODEL")
public class Carmodel extends GenericCodeNameEntity {
	
	/**車系編號*/
	@Column(name="carseries_ID")
	private Long carseriesID;
	
	@Column(name="cc")
	private Integer cc;
		
	@Transient
	private String carmodelLocalName;
	
	@Transient
	private String brandLocalName;
	
	@Transient
	private String carseriesLocalName;
	
	@Transient
	private String brandCode;
	
	@Transient
	private String seriesCode;
	
	public Long getCarseriesID() {
		return carseriesID;
	}

	public void setCarseriesID(Long carseriesID) {
		this.carseriesID = carseriesID;
	}

	public Integer getCc() {
		return cc;
	}

	public void setCc(Integer cc) {
		this.cc = cc;
	}

	public String getCarmodelLocalName() {
		return carmodelLocalName;
	}

	public void setCarmodelLocalName(String carmodelLocalName) {
		this.carmodelLocalName = carmodelLocalName;
	}

	public String getBrandLocalName() {
		return brandLocalName;
	}

	public void setBrandLocalName(String brandLocalName) {
		this.brandLocalName = brandLocalName;
	}

	public String getCarseriesLocalName() {
		return carseriesLocalName;
	}

	public void setCarseriesLocalName(String carseriesLocalName) {
		this.carseriesLocalName = carseriesLocalName;
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

}