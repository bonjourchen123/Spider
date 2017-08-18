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
@Table(name ="POLICY_REQ")
public class PolicyReq extends GenericEntity {
	@Column(name="type")
	private String type;
	
	@Column(name="last_Policy_No")
	private Long lastPolicyNo;



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getLastPolicyNo() {
		return lastPolicyNo;
	}

	public void setLastPolicyNo(Long lastPolicyNo) {
		this.lastPolicyNo = lastPolicyNo;
	}
	
}
