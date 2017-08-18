package org.iii.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
// J2EE 會被序列化  ,會把黃色的Warning 給消除掉 
@MappedSuperclass
public abstract class GenericCodeNameEntity extends GenericEntity {

	/** 代碼. */
	@Column(name="code")
	private String code;

	/** 當地語言名稱. */
	@Column(name="local_Name")
	private String localName;

	/** 英文名稱. */
	@Column(name="eng_Name")
	private String engName;

	/** 描述. */
	@Column(name="descript")
	private String descript;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
