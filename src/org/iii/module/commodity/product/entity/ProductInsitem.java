package org.iii.module.commodity.product.entity;

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
@Table(name="PRODUCT_INSITEM")
public class ProductInsitem extends GenericEntity {

	@Column(name="product_ID")
	private Long productID;
	@Column(name="insitem_ID")
	private Long insitemID;
	
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public Long getInsitemID() {
		return insitemID;
	}
	public void setInsitemID(Long insitemID) {
		this.insitemID = insitemID;
	}
		
}