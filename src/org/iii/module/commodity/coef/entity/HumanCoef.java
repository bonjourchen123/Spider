package org.iii.module.commodity.coef.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;

/**
 * @author ant
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="HUMAN_COEF")
public class HumanCoef extends GenericEntity {
	@Column(name="gender")
	private String gender;
	
	@Column(name="from_Age")
	private Integer fromAge;
	
	@Column(name="to_Age")
	private Integer toAge;
	
	@Column(name="coef")
	private Double coef;

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

	public Double getCoef() {
		return coef;
	}

	public void setCoef(Double coef) {
		this.coef = coef;
	}
	
}
