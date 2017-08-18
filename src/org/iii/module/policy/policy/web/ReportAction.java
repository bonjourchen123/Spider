package org.iii.module.policy.policy.web;

import java.util.List;

import org.iii.core.action.GenericAction;
import org.iii.core.model.LabelValueModel;
import org.iii.core.model.ReportModel;
import org.iii.module.policy.policy.entity.Policy;
import org.iii.module.policy.policy.service.ReportService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;

/**
 * @author bonjour
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportAction extends GenericAction<Policy>{
	//查詢管理報表
	private Long officeID;
	private Long role;
	private String startDate;
	private String endDate;
	
	private List<ReportModel> superviseData;
	
	//進入業績報表-抓出日期
	private List<LabelValueModel> years = Lists.newArrayList();
	
	//查詢業績報表
	private String year;
	
	private ReportModel policyData;
	
	@Autowired
	private ReportService reportService;
	
	public void validateQuerySupervise(){
		this.startDate=startDate.trim();
		this.endDate=endDate.trim();
	}
	
	public String querySupervise(){
		superviseData=reportService.querySuperviseData(officeID,startDate,endDate,role);
		return SUCCESS;
	}
	
	public String findYears(){
		int nowYear=LocalDateTime.now().getYear();
		for(int i=0;i<10;i++){
			LabelValueModel year=new LabelValueModel();
			year.setLabel((nowYear-i)+"年");
			year.setValue(String.valueOf(nowYear-i));
			years.add(year);
		}
		return SUCCESS;
	}

	public String queryPolicy(){
		policyData=reportService.queryPolicyData(year);
		return SUCCESS;
	}
	
	public List<LabelValueModel> getYears() {
		return years;
	}

	public void setYears(List<LabelValueModel> years) {
		this.years = years;
	}

	public Long getOfficeID() {
		return officeID;
	}

	public void setOfficeID(Long officeID) {
		this.officeID = officeID;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<ReportModel> getSuperviseData() {
		return superviseData;
	}

	public void setSuperviseData(List<ReportModel> superviseData) {
		this.superviseData = superviseData;
	}

	public ReportModel getPolicyData() {
		return policyData;
	}

	public void setPolicyData(ReportModel policyData) {
		this.policyData = policyData;
	}
}
