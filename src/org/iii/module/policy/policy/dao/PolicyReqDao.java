package org.iii.module.policy.policy.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.policy.policy.entity.PolicyReq;
import org.springframework.stereotype.Repository;

/**
 * @author Shin Chen
 *
 */
@Repository
public class PolicyReqDao extends GenericDao<PolicyReq, Long>{
	public PolicyReq findBytype(String type){
		Criteria crit=getSession().createCriteria(PolicyReq.class);
		crit.add(Restrictions.eq("type", type));
		PolicyReq req=(PolicyReq)crit.uniqueResult();
		return req;
	}
}
