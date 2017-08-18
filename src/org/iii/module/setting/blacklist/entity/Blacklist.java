package org.iii.module.setting.blacklist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;

/**
 * @author Hansen
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BLACKLIST")
public class Blacklist extends GenericEntity {

	@Column(name = "number")
	private String number;
	@Column(name = "type")
	private String type;
	@Column(name = "reason")
	private String reason;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}