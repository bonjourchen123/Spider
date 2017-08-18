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
@Table(name ="INDEMNITY_COEF")
public class IndemnityCoef extends GenericEntity {
	@Column(name="level")
	private Integer level;
	
	@Column(name="coef")
	private Double coef;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getCoef() {
		return coef;
	}

	public void setCoef(Double coef) {
		this.coef = coef;
	}
	
}
