package org.iii.module.policy.policy.service.impl;

import java.util.List;

import org.iii.core.security.dao.SecUserDao;
import org.iii.core.security.entity.SecUser;
import org.iii.module.commodity.product.dao.ProductDao;
import org.iii.module.policy.customer.dao.CustomerDao;
import org.iii.module.policy.customer.entity.Customer;
import org.iii.module.policy.policy.dao.PolicyDao;
import org.iii.module.policy.policy.dao.PolicyItemDao;
import org.iii.module.policy.policy.dao.PolicyReqDao;
import org.iii.module.policy.policy.entity.Policy;
import org.iii.module.policy.policy.entity.PolicyItem;
import org.iii.module.policy.policy.entity.PolicyReq;
import org.iii.module.policy.policy.service.PolicyService;
import org.iii.module.setting.car.dao.CarmodelDao;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * @author bonjour
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PolicyDao policyDao;
	@Autowired
	private SecUserDao secUserDao;
	@Autowired
	private CarmodelDao carmodelDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private PolicyItemDao policyItemDao;
	@Autowired
	private PolicyReqDao policyReqDao;
	
	@Override
	public List<Policy> queryByLike(String code,String carNo,String applicantName,String insurantName,String officeID){
		Long[] applicantID;
		if(applicantName!=null && applicantName.length()!=0){
			List<Customer> applicantCustomer=customerDao.findByLike(applicantName, null,Long.valueOf(officeID));
			if(applicantCustomer.isEmpty()){
				return Lists.newArrayList();
			}
			applicantID=new Long[applicantCustomer.size()];
			int i=0;
			for(Customer customer:applicantCustomer){
				applicantID[i]=customer.getId();
				i++;
			}
		}else{
			applicantID=null;
		}
		Long[] insurantID;
		if(insurantName!=null && insurantName.length()!=0){
			List<Customer> insurantCustomer=customerDao.findByLike(insurantName, null,Long.valueOf(officeID));
			if(insurantCustomer.isEmpty()){
				return Lists.newArrayList();
			}
			insurantID=new Long[insurantCustomer.size()];
			int j=0;
			for(Customer customer:insurantCustomer){
				insurantID[j]=customer.getId();
				j++;
			}
		}else{
			insurantID=null;
		}
		List<Policy> policys= policyDao.findByLike(code, carNo, applicantID, insurantID, Long.valueOf(officeID));
		for(Policy policy:policys){
			try {
				policy.setUnderwriterUser(secUserDao.findByPk(policy.getUnderwriterUserID()));
				policy.setApplicantCustomer(customerDao.findByPk(policy.getApplicantCustomerID()));
				policy.setInsurantCustomer(customerDao.findByPk(policy.getInsurantCustomerID()));
				policy.setCarmodelLocalName(carmodelDao.findByPk(policy.getCarmodelID()).getLocalName());
				policy.setProductCode(productDao.findByPk(policy.getProductID()).getCode());
				if(policy.getAssessorUserID()!=null){
					policy.setAssessorUser(secUserDao.findByPk(policy.getAssessorUserID()));
				}
				policy.setPolicyItems(policyItemDao.findByPolicyID(policy.getId()));
				StringBuilder policyItemsString=new StringBuilder();
				for(PolicyItem policyItem:policy.getPolicyItems()){
					if(policyItem.getInsitemID()==1){
						policyItemsString.append("強制險&nbsp;&nbsp;");
					}else if(policyItem.getInsitemID()==2){
						policyItemsString.append("第三傷害責任險(每人/每事故保險金額:"+policyItem.getPersonMoney()+"/"+policyItem.getCaseMoney()+"萬元)&nbsp;&nbsp;");
					}else if(policyItem.getInsitemID()==3){
						policyItemsString.append("第三責任財損險(每事故保險金額:"+policyItem.getCaseMoney()+"萬元)&nbsp;&nbsp;");
					}
				}
				policy.setPolicyItemsString(policyItemsString.toString());
			} catch (Exception e) {
				
			}
		}
		return policys;
	}
	
	@Override
	public List<Policy> queryVerifyByLike(String code,String carNo,String applicantName,String insurantName,String officeID){
		Long[] applicantID;
		if(applicantName!=null && applicantName.length()!=0){
			List<Customer> applicantCustomer=customerDao.findByLike(applicantName, null,Long.valueOf(officeID));
			if(applicantCustomer.isEmpty()){
				return Lists.newArrayList();
			}
			applicantID=new Long[applicantCustomer.size()];
			int i=0;
			for(Customer customer:applicantCustomer){
				applicantID[i]=customer.getId();
				i++;
			}
		}else{
			applicantID=null;
		}
		Long[] insurantID;
		if(insurantName!=null && insurantName.length()!=0){
			List<Customer> insurantCustomer=customerDao.findByLike(insurantName, null,Long.valueOf(officeID));
			if(insurantCustomer.isEmpty()){
				return Lists.newArrayList();
			}
			insurantID=new Long[insurantCustomer.size()];
			int j=0;
			for(Customer customer:insurantCustomer){
				insurantID[j]=customer.getId();
				j++;
			}
		}else{
			insurantID=null;
		}
		List<Policy> policys= policyDao.findVerifyByLike(code, carNo, applicantID, insurantID, Long.valueOf(officeID));
		for(Policy policy:policys){
			try {
				policy.setUnderwriterUser(secUserDao.findByPk(policy.getUnderwriterUserID()));
				policy.setApplicantCustomer(customerDao.findByPk(policy.getApplicantCustomerID()));
				policy.setInsurantCustomer(customerDao.findByPk(policy.getInsurantCustomerID()));
				policy.setCarmodelLocalName(carmodelDao.findByPk(policy.getCarmodelID()).getLocalName());
				policy.setProductCode(productDao.findByPk(policy.getProductID()).getCode());
				if(policy.getAssessorUserID()!=null){
					policy.setAssessorUser(secUserDao.findByPk(policy.getAssessorUserID()));
				}
				policy.setPolicyItems(policyItemDao.findByPolicyID(policy.getId()));
				StringBuilder policyItemsString=new StringBuilder();
				for(PolicyItem policyItem:policy.getPolicyItems()){
					if(policyItem.getInsitemID()==1){
						policyItemsString.append("強制險&nbsp;&nbsp;");
					}else if(policyItem.getInsitemID()==2){
						policyItemsString.append("第三傷害責任險(每人/每事故保險金額:"+policyItem.getPersonMoney()+"/"+policyItem.getCaseMoney()+"萬元)&nbsp;&nbsp;&nbsp;&nbsp;");
					}else if(policyItem.getInsitemID()==3){
						policyItemsString.append("第三責任財損險(每事故保險金額:"+policyItem.getCaseMoney()+"萬元)&nbsp;&nbsp;&nbsp;&nbsp;");
					}
				}
				policy.setPolicyItemsString(policyItemsString.toString());
			} catch (Exception e) {
				
			}
		}
		return policys;
	}
	
	@Override
	public Policy queryPolicy(Long ID){
		try {
			Policy policy=policyDao.findByPk(ID);
			policy.setPolicyItems(policyItemDao.findByPolicyID(ID));
			return policy;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void verifyPolicy(SecUser loginUser,Long policyID){
		try {
			Policy policy=policyDao.findByPk(policyID);
			policy.initUpdate(loginUser);
			policy.setAssessorUserID(loginUser.getId());
			policy.setAssessorDate(LocalDateTime.now());
			policyDao.update(policy);
		} catch (Exception e) {

		}
	}
	
	@Override
	public void deletePolicy(Long policyID){
		try {
			Policy policy=policyDao.findByPk(policyID);
			List<PolicyItem> policyItems=policyItemDao.findByPolicyID(policyID);
			for(PolicyItem policyItem:policyItems){
				policyItemDao.delete(policyItem);
			}
			policyDao.delete(policy);
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public Policy createPolicy(SecUser loginUser,Policy policy){
		policy.initInsert(loginUser);
		long productID=policy.getProductID();
		PolicyReq policyReq;
		if(productID==1){
			policyReq=policyReqDao.findBytype("強制險");
		}else{
			policyReq=policyReqDao.findBytype("任意險");
		}
		policyReq.getLastPolicyNo();
		LocalDateTime now=LocalDateTime.now();
		String year=String.valueOf(now.getYear());
		year=year.substring(2,4);
		String month;
		if(now.getMonthOfYear()<10){
			month="0"+now.getMonthOfYear();
		}else{
			month=String.valueOf(now.getMonthOfYear());
		}
		long policyNo=policyReq.getLastPolicyNo()+1;
		String no;
		String code;
		if(productID==1){
			if(policyNo<10){
				no="000"+policyNo;
			}else if(policyNo<100){
				no="00"+policyNo;
			}else if(policyNo<1000){
				no="0"+policyNo;
			}else{
				no=policyNo+"";
			}
			code=99+year+month+"iiii"+no;
		}else{
			if(policyNo<10){
				no="0000"+policyNo;
			}else if(policyNo<100){
				no="000"+policyNo;
			}else if(policyNo<1000){
				no="00"+policyNo;
			}else if(policyNo<10000){
				no="0"+policyNo;
			}else{
				no=policyNo+"";
			}
			code=99+year+month+"iii"+no;
		}
		policy.setCode(code);
		policyReq.initUpdate(loginUser);
		policyReq.setLastPolicyNo(Long.valueOf(no));
		try {
			policyReqDao.update(policyReq);
			Policy newPolicy=policyDao.save(policy);
			return newPolicy;
		} catch (Exception e) {
			createPolicy(loginUser,policy);
			return null;
		}
	}
	
	@Override
	public Boolean createPolicyItem(SecUser loginUser,PolicyItem policyItem){
		policyItem.initInsert(loginUser);
		try {
			policyItemDao.save(policyItem);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}