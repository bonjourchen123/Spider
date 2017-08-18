package org.iii.module.policy.policy.service;

import java.util.List;

import org.iii.core.model.ReportModel;

/**
 * @author bonjour
 *
 */
public interface ReportService {
	public List<ReportModel> querySuperviseData(Long officeID,String startDate,String endDate,Long role);

	public ReportModel queryPolicyData(String year);
}