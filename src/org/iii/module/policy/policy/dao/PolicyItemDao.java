package org.iii.module.policy.policy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.policy.policy.entity.PolicyItem;
import org.springframework.stereotype.Repository;

/**
 * @author Shin Chen
 *
 */
@Repository
public class PolicyItemDao extends GenericDao<PolicyItem, Long>{
	
	@SuppressWarnings("unchecked")
	public List<PolicyItem> findByPolicyID(Long policyID){
		Criteria crit=getSession().createCriteria(PolicyItem.class);
		crit.add(Restrictions.eq("policyID",policyID ));
		List<PolicyItem> policyitems =crit.list();
		return policyitems;
	}
}
