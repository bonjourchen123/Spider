package org.iii.module.commodity.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.iii.core.entity.GenericCodeNameEntity;

/**
 * @author Tony
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="INSITEM")
public class Insitem extends GenericCodeNameEntity {
	@Column(name="type")
	private String type;

	@Column(name="corresponding_Table")
	private String correspondingTable;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCorrespondingTable() {
		return correspondingTable;
	}

	public void setCorrespondingTable(String correspondingTable) {
		this.correspondingTable = correspondingTable;
	}
	
}
