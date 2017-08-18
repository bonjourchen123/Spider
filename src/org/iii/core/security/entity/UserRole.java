package org.iii.core.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;

/**
 * @author Kuso
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="USER_ROLE")
public class UserRole extends GenericEntity {
	@Column(name="user_ID")
	private Long userID;
	
	@Column(name="role_ID")
	private Long roleID;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

}
