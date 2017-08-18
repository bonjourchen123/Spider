package org.iii.module.setting.car.entity;

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
@Table(name = "BRAND")
public class Brand extends GenericCodeNameEntity {
	@Transient
	private String brandLocalName;

	public String getBrandLocalName() {
		return brandLocalName;
	}

	public void setBrandLocalName(String brandLocalName) {
		this.brandLocalName = brandLocalName;
	}
}
