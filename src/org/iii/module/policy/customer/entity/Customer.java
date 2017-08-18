package org.iii.module.policy.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.iii.core.entity.GenericEntity;
import org.joda.time.LocalDateTime;

/**
 * @author Shin Chen
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CUSTOMER")
public class Customer extends GenericEntity {

	@Column(name = "pin")
	private String pin;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birthday")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime birthday;

	@Column(name = "phone")
	private String phone;

	@Column(name = "permanent_Addr")
	private String permanentAddr;

	@Column(name = "current_Addr")
	private String currentAddr;

	@Column(name = "rate_Level")
	private Integer rateLevel;

	@Column(name = "indemnity_Level")
	private Integer indemnityLevel;

	@Column(name = "office_ID")
	private Long officeID;

	
	@Transient
	private String officeLocalName;
	
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPermanentAddr() {
		return permanentAddr;
	}

	public void setPermanentAddr(String permanentAddr) {
		this.permanentAddr = permanentAddr;
	}

	public String getCurrentAddr() {
		return currentAddr;
	}

	public void setCurrentAddr(String currentAddr) {
		this.currentAddr = currentAddr;
	}

	public Integer getRateLevel() {
		return rateLevel;
	}

	public void setRateLevel(Integer rateLevel) {
		this.rateLevel = rateLevel;
	}

	public Integer getIndemnityLevel() {
		return indemnityLevel;
	}

	public void setIndemnityLevel(Integer indemnityLevel) {
		this.indemnityLevel = indemnityLevel;
	}

	public Long getOfficeID() {
		return officeID;
	}

	public void setOfficeID(Long officeID) {
		this.officeID = officeID;
	}

	public String getOfficeLocalName() {
		return officeLocalName;
	}

	public void setOfficeLocalName(String officeLocalName) {
		this.officeLocalName = officeLocalName;
	}

	
}
