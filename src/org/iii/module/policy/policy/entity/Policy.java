package org.iii.module.policy.policy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.iii.core.entity.GenericEntity;
import org.iii.core.security.entity.SecUser;
import org.iii.module.policy.customer.entity.Customer;
import org.joda.time.LocalDateTime;

/**
 * @author Kuso
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name ="POLICY")
public class Policy extends GenericEntity{
	@Column(name="code")
	private String code;
	
	@Column(name="underwriter_User_ID")
	private Long underwriterUserID;
	
	@Column(name="applicant_Customer_ID")
	private Long applicantCustomerID;
	
	@Column(name="insurant_Customer_ID")
	private Long insurantCustomerID;
	
	@Column(name="car_No")
	private String carNo;
	
	@Column(name="carmodel_ID")
	private Long carmodelID;
	
	@Column(name="production_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime productionDate;
	
	@Column(name="tonnage")
	private Double tonnage;
	
	@Column(name="seats")
	private Integer seats;
	
	@Column(name="product_ID")
	private Long productID;
	
	@Column(name="start_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startDate;
	
	@Column(name="end_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime endDate;
	
	@Column(name="total_Rate")
	private Long totalRate;
	
	@Column(name="assessor_User_ID")
	private Long assessorUserID;
	
	@Column(name="assessor_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime assessorDate;
	
	@Column(name="office_ID")
	private Long officeID;

	@Column(name="purpose")
	private String purpose;
	
	@Transient
	private SecUser underwriterUser;
	
	@Transient
	private Customer applicantCustomer;
	
	@Transient
	private Customer insurantCustomer;
	
	@Transient
	private String carmodelLocalName;
	
	@Transient
	private String productCode;
	
	@Transient
	private SecUser assessorUser;
	
	@Transient
	private List<PolicyItem> policyItems;
	
	@Transient
	private String policyItemsString;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getUnderwriterUserID() {
		return underwriterUserID;
	}

	public void setUnderwriterUserID(Long underwriterUserID) {
		this.underwriterUserID = underwriterUserID;
	}

	public Long getApplicantCustomerID() {
		return applicantCustomerID;
	}

	public void setApplicantCustomerID(Long applicantCustomerID) {
		this.applicantCustomerID = applicantCustomerID;
	}

	public Long getInsurantCustomerID() {
		return insurantCustomerID;
	}

	public void setInsurantCustomerID(Long insurantCustomerID) {
		this.insurantCustomerID = insurantCustomerID;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Long getCarmodelID() {
		return carmodelID;
	}

	public void setCarmodelID(Long carmodelID) {
		this.carmodelID = carmodelID;
	}

	public LocalDateTime getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDateTime productionDate) {
		this.productionDate = productionDate;
	}

	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Long getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(Long totalRate) {
		this.totalRate = totalRate;
	}

	public Long getAssessorUserID() {
		return assessorUserID;
	}

	public void setAssessorUserID(Long assessorUserID) {
		this.assessorUserID = assessorUserID;
	}

	public LocalDateTime getAssessorDate() {
		return assessorDate;
	}

	public void setAssessorDate(LocalDateTime assessorDate) {
		this.assessorDate = assessorDate;
	}

	public Long getOfficeID() {
		return officeID;
	}

	public void setOfficeID(Long officeID) {
		this.officeID = officeID;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public SecUser getUnderwriterUser() {
		return underwriterUser;
	}

	public void setUnderwriterUser(SecUser underwriterUser) {
		this.underwriterUser = underwriterUser;
	}

	public String getCarmodelLocalName() {
		return carmodelLocalName;
	}

	public void setCarmodelLocalName(String carmodelLocalName) {
		this.carmodelLocalName = carmodelLocalName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Customer getApplicantCustomer() {
		return applicantCustomer;
	}

	public void setApplicantCustomer(Customer applicantCustomer) {
		this.applicantCustomer = applicantCustomer;
	}

	public Customer getInsurantCustomer() {
		return insurantCustomer;
	}

	public void setInsurantCustomer(Customer insurantCustomer) {
		this.insurantCustomer = insurantCustomer;
	}

	public SecUser getAssessorUser() {
		return assessorUser;
	}

	public void setAssessorUser(SecUser assessorUser) {
		this.assessorUser = assessorUser;
	}

	public List<PolicyItem> getPolicyItems() {
		return policyItems;
	}

	public void setPolicyItems(List<PolicyItem> policyItems) {
		this.policyItems = policyItems;
	}

	public String getPolicyItemsString() {
		return policyItemsString;
	}

	public void setPolicyItemsString(String policyItemsString) {
		this.policyItemsString = policyItemsString;
	}	
}
