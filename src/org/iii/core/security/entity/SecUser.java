/**
 * 
 */
package org.iii.core.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;

/**
 * @author Shock
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="SEC_USER")
public class SecUser extends GenericEntity {
	@Column(name="account")
	private String account;
	@Column(name="password")
	private String password;
	@Column(name="name")
	private String name;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="office_ID")
	private Long officeID;
	
	@Transient
	private String officeLocalName;
	
	@Transient
	private List<String> roleCodes;
		
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}
	
}
