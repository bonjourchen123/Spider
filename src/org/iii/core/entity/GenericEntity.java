/**
 * 
 */
package org.iii.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.iii.core.enums.SystemStatus;
import org.iii.core.security.entity.SecUser;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shock
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class GenericEntity implements Serializable{
	/** The log. */
	@Transient
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	/** The id. */
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name="ID", unique=true, nullable=false, insertable=true, updatable=false, precision=20, scale=0)
	private Long id;

	/** The created by. */
	@Column(name="created_By",updatable = false)
	private Long createdBy;

	/** The created date. */
	@Column(name="created_Date",updatable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdDate;

	/** The last modified by. */
	@Column(name="last_Modified_By")
	private Long lastModifiedBy;

	/** The last modified date. */
	@Column(name="last_Modified_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime lastModifiedDate;

	/** The status. */
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private SystemStatus status;

	/** The version no. */
	@Column(name="version_No")
	private Long versionNo;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public SystemStatus getStatus() {
		return status;
	}

	public void setStatus(SystemStatus status) {
		this.status = status;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * check entity is new or not.
	 * 
	 * @return true, if is new
	 */
	//確認entity是不是新的(只有新的才會沒pk)
	public boolean isNew() {
		return id == null;
	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status when insert <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 */
	//創造時沒設狀態則預設為有效
	public void initInsert(SecUser user) {
		if (status == null)
			initInsert(user, SystemStatus.Y);
		else if (status != null)
			initInsert(user, status);
	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status when insert <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 * @param sysStatus
	 *            the sys status
	 * @param bpmId
	 *            the bpm id
	 */
	//設定創造者以及狀態
	public void initInsert(SecUser user, SystemStatus sysStatus) {
		this.setVersionNo(1L);
		this.setStatus(sysStatus);
		this.setCreatedBy(user.getId());
		this.setCreatedDate(LocalDateTime.now());
		this.setLastModifiedBy(getCreatedBy());
		this.setLastModifiedDate(getCreatedDate());
	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status when update <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 */
	//修改時沒設狀態預設為原本的狀態
	public void initUpdate(SecUser user) {
		initUpdate(user, status);
	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status when update <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 * @param sysStatus
	 *            the sys status
	 */
	//initUpdate設定修改者和修改時間以及狀態
	public void initUpdate(SecUser user, SystemStatus sysStatus) {
		this.setStatus(sysStatus);
		this.setVersionNo(versionNo == null ? 1 : versionNo + 1);
		this.setLastModifiedBy(user.getId());
		this.setLastModifiedDate(LocalDateTime.now());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
