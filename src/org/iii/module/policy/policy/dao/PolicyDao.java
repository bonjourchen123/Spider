package org.iii.module.policy.policy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.policy.policy.entity.Policy;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;

/**
 * @author bonjour
 *
 */
@Repository
public class PolicyDao  extends GenericDao<Policy, Long>{
	public Policy findByCode(String code){
		Criteria crit=getSession().createCriteria(Policy.class);
		crit.add(Restrictions.eq("code", code));
		Policy policy =(Policy)crit.uniqueResult();
		return policy;	
	}
	
	//查出還沒過期的保單
	@SuppressWarnings("unchecked")
	public List<Policy> findUnexpired(LocalDateTime date){
		Criteria crit=getSession().createCriteria(Policy.class);
		crit.add(Restrictions.ge("endDate", date));
		List<Policy> policys =crit.list();
		return policys;	
	}
		
	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<Policy> findByLike(String code,String carNo,Long[] applicantID,Long[] insurantID,Long officeID){
		Criteria crit=getSession().createCriteria(Policy.class);
		if(code!=null && code.length()!=0){
			crit.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(carNo!=null && carNo.length()!=0){
			crit.add(Restrictions.like("carNo", "%"+carNo+"%"));
		}
		if(applicantID!=null){
			crit.add(Restrictions.in("applicantCustomerID", applicantID));
		}
		if(insurantID!=null){
			crit.add(Restrictions.in("insurantCustomerID", insurantID));
		}
		crit.add(Restrictions.eq("officeID", officeID));
		List<Policy> policys =crit.list();
		return policys;
	}
	
	//模糊查詢未審核的保單
	@SuppressWarnings("unchecked")
	public List<Policy> findVerifyByLike(String code,String carNo,Long[] applicantID,Long[] insurantID,Long officeID){
		Criteria crit=getSession().createCriteria(Policy.class);
		if(code!=null && code.length()!=0){
			crit.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(carNo!=null && carNo.length()!=0){
			crit.add(Restrictions.like("carNo", "%"+carNo+"%"));
		}
		if(applicantID!=null){
			crit.add(Restrictions.in("applicantCustomerID", applicantID));
		}
		if(insurantID!=null){
			crit.add(Restrictions.in("insurantCustomerID", insurantID));
		}
		crit.add(Restrictions.eq("officeID", officeID));
		crit.add(Restrictions.isNull("assessorUserID"));
		List<Policy> policys =crit.list();
		return policys;
	}
}
