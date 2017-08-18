package org.iii.module.policy.policy.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.iii.core.model.ReportModel;
import org.iii.core.security.dao.SecUserDao;
import org.iii.core.security.entity.SecUser;
import org.iii.module.policy.policy.dao.PolicyDao;
import org.iii.module.policy.policy.service.ReportService;
import org.iii.module.setting.office.dao.OfficeDao;
import org.iii.module.setting.office.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * @author kuso
 *
 */
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private PolicyDao policyDao;
	@Autowired
	private SecUserDao secUserDao;
	@Autowired
	private OfficeDao officeDao;
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<ReportModel> querySuperviseData(Long officeID,String startDate,String endDate,Long role){
		String sql;
		if(role==0){
			if(startDate!=null && startDate.length()!=0 && endDate!=null && endDate.length()!=0){
				sql="select underwriter_User_ID as 'underwriterUserID',count(*) as 'count' from policy where created_Date> '"+startDate+"' and created_Date< '"+endDate+"' and office_ID="+officeID+" group by underwriter_User_ID";
			}else if(startDate!=null && startDate.length()!=0){
				sql="select underwriter_User_ID as 'underwriterUserID',count(*) as 'count' from policy where created_Date> '"+startDate+"' and office_ID="+officeID+" group by underwriter_User_ID";
			}else if(endDate!=null && endDate.length()!=0){
				sql="select underwriter_User_ID as 'underwriterUserID',count(*) as 'count' from policy where created_Date< '"+endDate+"' and office_ID="+officeID+" group by underwriter_User_ID";
			}else{
				sql="select underwriter_User_ID as 'underwriterUserID',count(*) as 'count' from policy where office_ID="+officeID+" group by underwriter_User_ID";
			}
		}else if(role==1){
			if(startDate!=null && startDate.length()!=0 && endDate!=null && endDate.length()!=0){
				sql="select assessor_User_ID as 'assessorUserID',count(*) as 'count' from policy where created_Date> '"+startDate+"' and created_Date< '"+endDate+"' and office_ID="+officeID+" group by assessor_User_ID";
			}else if(startDate!=null && startDate.length()!=0){
				sql="select assessor_User_ID as 'assessorUserID',count(*) as 'count' from policy where created_Date> '"+startDate+"' and office_ID="+officeID+" group by assessor_User_ID";
			}else if(endDate!=null && endDate.length()!=0){
				sql="select assessor_User_ID as 'assessorUserID',count(*) as 'count' from policy where created_Date< '"+endDate+"' and office_ID="+officeID+" group by assessor_User_ID";
			}else{
				sql="select assessor_User_ID as 'assessorUserID',count(*) as 'count' from policy where office_ID="+officeID+" group by assessor_User_ID";
			}
		}else{
			return null;
		}
		try {
			List data=policyDao.createSQLQuery(sql);
			if(data.isEmpty()){
				return null;
			}
			List<ReportModel> superviseData=Lists.newArrayList();
			for(int i=0;i<data.size();i++){
				Object[] object = (Object[])data.get(i);
				ReportModel reportModel=new ReportModel();
				if(object[0]==null){
					reportModel.setLabel("未審核");
				}else{
					SecUser underwriterUser=secUserDao.findByPk(Long.valueOf(object[0].toString()));
					reportModel.setLabel(underwriterUser.getAccount()+":"+underwriterUser.getName());
				}
				reportModel.setCount((Integer)object[1]);
				superviseData.add(reportModel);
			} 
			return superviseData;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ReportModel queryPolicyData(String year){
		List<Office> offices=officeDao.findAll();
		ReportModel policyData=new ReportModel();
		List<String> officeNames=Lists.newArrayList();
		List<BigInteger> rate1s=Lists.newArrayList();
		List<BigInteger> rate2s=Lists.newArrayList();
		List<BigInteger> rate3s=Lists.newArrayList();
		List<BigInteger> rate4s=Lists.newArrayList();
		List<BigInteger> rate5s=Lists.newArrayList();
		List<BigInteger> rate6s=Lists.newArrayList();
		List<BigInteger> rate7s=Lists.newArrayList();
		List<BigInteger> rate8s=Lists.newArrayList();
		List<BigInteger> rate9s=Lists.newArrayList();
		List<BigInteger> rate10s=Lists.newArrayList();
		List<BigInteger> rate11s=Lists.newArrayList();
		List<BigInteger> rate12s=Lists.newArrayList();
		for(Office office:offices){
			officeNames.add(office.getLocalName());
			String sql1="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"01________' and office_ID="+office.getId()+" group by office_ID";
			String sql2="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"02________' and office_ID="+office.getId()+" group by office_ID";
			String sql3="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"03________' and office_ID="+office.getId()+" group by office_ID";
			String sql4="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"04________' and office_ID="+office.getId()+" group by office_ID";
			String sql5="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"05________' and office_ID="+office.getId()+" group by office_ID";
			String sql6="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"06________' and office_ID="+office.getId()+" group by office_ID";
			String sql7="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"07________' and office_ID="+office.getId()+" group by office_ID";
			String sql8="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"08________' and office_ID="+office.getId()+" group by office_ID";
			String sql9="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"09________' and office_ID="+office.getId()+" group by office_ID";
			String sql10="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"10________' and office_ID="+office.getId()+" group by office_ID";
			String sql11="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"11________' and office_ID="+office.getId()+" group by office_ID";
			String sql12="select sum(total_Rate) as 'sum' from policy where code like '99"+year.substring(2,4)+"12________' and office_ID="+office.getId()+" group by office_ID";	
			try {
				BigInteger rate1=(BigInteger) policyDao.createSQLQueryUnique(sql1);
				if(rate1==null){
					rate1s.add(BigInteger.ZERO);
				}else{
					rate1s.add(rate1);
				}
				BigInteger rate2=(BigInteger) policyDao.createSQLQueryUnique(sql2);
				if(rate2==null){
					rate2s.add(BigInteger.ZERO);
				}else{
					rate2s.add(rate2);
				}
				BigInteger rate3=(BigInteger) policyDao.createSQLQueryUnique(sql3);
				if(rate3==null){
					rate3s.add(BigInteger.ZERO);
				}else{
					rate3s.add(rate3);
				}
				BigInteger rate4=(BigInteger) policyDao.createSQLQueryUnique(sql4);
				if(rate4==null){
					rate4s.add(BigInteger.ZERO);
				}else{
					rate4s.add(rate4);
				}
				BigInteger rate5=(BigInteger) policyDao.createSQLQueryUnique(sql5);
				if(rate5==null){
					rate5s.add(BigInteger.ZERO);
				}else{
					rate5s.add(rate5);
				}
				BigInteger rate6=(BigInteger) policyDao.createSQLQueryUnique(sql6);
				if(rate6==null){
					rate6s.add(BigInteger.ZERO);
				}else{
					rate6s.add(rate6);
				}
				BigInteger rate7=(BigInteger) policyDao.createSQLQueryUnique(sql7);
				if(rate7==null){
					rate7s.add(BigInteger.ZERO);
				}else{
					rate7s.add(rate7);
				}
				BigInteger rate8=(BigInteger) policyDao.createSQLQueryUnique(sql8);
				if(rate8==null){
					rate8s.add(BigInteger.ZERO);
				}else{
					rate8s.add(rate8);
				}
				BigInteger rate9=(BigInteger) policyDao.createSQLQueryUnique(sql9);
				if(rate9==null){
					rate9s.add(BigInteger.ZERO);
				}else{
					rate9s.add(rate9);
				}
				BigInteger rate10=(BigInteger) policyDao.createSQLQueryUnique(sql10);
				if(rate10==null){
					rate10s.add(BigInteger.ZERO);
				}else{
					rate10s.add(rate10);
				}
				BigInteger rate11=(BigInteger) policyDao.createSQLQueryUnique(sql11);
				if(rate11==null){
					rate11s.add(BigInteger.ZERO);
				}else{
					rate11s.add(rate11);
				}
				BigInteger rate12=(BigInteger) policyDao.createSQLQueryUnique(sql12);
				if(rate12==null){
					rate12s.add(BigInteger.ZERO);
				}else{
					rate12s.add(rate12);
				}
			} catch (Exception e) {
				return null;
			}
		}
		policyData.setOffices(officeNames);
		policyData.setRate1(rate1s);
		policyData.setRate2(rate2s);
		policyData.setRate3(rate3s);
		policyData.setRate4(rate4s);
		policyData.setRate5(rate5s);
		policyData.setRate6(rate6s);
		policyData.setRate7(rate7s);
		policyData.setRate8(rate8s);
		policyData.setRate9(rate9s);
		policyData.setRate10(rate10s);
		policyData.setRate11(rate11s);
		policyData.setRate12(rate12s);
		return policyData;
	}
}
