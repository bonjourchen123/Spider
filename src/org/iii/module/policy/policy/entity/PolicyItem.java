package org.iii.module.policy.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;

/**
 * @author Shin Chen
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="POLICY_ITEM")
public class PolicyItem extends GenericEntity {
	
	@Column(name = "policy_ID")
	private Long policyID;
	
	@Column(name = "insitem_ID")
	private Long insitemID;
	
	@Column(name = "person_Money")
	private Long personMoney;
	
	@Column(name = "case_Money")
	private Long caseMoney;
	
	@Column(name = "rate")
	private Long rate;

	public Long getPolicyID() {
		return policyID;
	}

	public void setPolicyID(Long policyID) {
		this.policyID = policyID;
	}

	public Long getInsitemID() {
		return insitemID;
	}

	public void setInsitemID(Long insitemID) {
		this.insitemID = insitemID;
	}

	public Long getPersonMoney() {
		return personMoney;
	}

	public void setPersonMoney(Long personMoney) {
		this.personMoney = personMoney;
	}

	public Long getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(Long caseMoney) {
		this.caseMoney = caseMoney;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

}
