package org.iii.module.commodity.rate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;

/**
 * @author ant
 *
 */
@SuppressWarnings("serial")  //有這行就不用定義序列化 EX: private static final long serialVersionUID = 1L;
@Entity
@Table(name ="Rate_31")
public class Rate31 extends GenericEntity{
	//定義資料庫的欄位名稱對應的屬性名稱   (給hibernate看的)
	//一個資料表請對應到一個entity，這樣才不會亂掉
	@Column(name = "cartype_ID")
	private Long cartypeID;
	
	@Column(name = "person_Money")
	private Integer personMoney;
	
	@Column(name = "case_Money")
	private Integer caseMoney;
	
	@Column(name = "rate")
	private Long rate;

	@Transient
	private String cartypeLocalName;
	
	public Long getCartypeID() {
		return cartypeID;
	}

	public void setCartypeID(Long cartypeID) {
		this.cartypeID = cartypeID;
	}

	public Integer getPersonMoney() {
		return personMoney;
	}

	public void setPersonMoney(Integer personMoney) {
		this.personMoney = personMoney;
	}

	public Integer getCaseMoney() {
		return caseMoney;
	}

	public void setCaseMoney(Integer caseMoney) {
		this.caseMoney = caseMoney;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}
	
	public String getCartypeLocalName() {
		return cartypeLocalName;
	}

	public void setCartypeLocalName(String cartypeLocalName) {
		this.cartypeLocalName = cartypeLocalName;
	}
}
