package org.iii.module.commodity.coef.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.coef.entity.IndemnityCoef;
import org.springframework.stereotype.Repository;

/**
 * @author ant
 *
 */
@Repository
public class IndemnityCoefDao extends GenericDao<IndemnityCoef, Long> {
	
	public IndemnityCoef findByLevel(Integer level) {
		Criteria crit = getSession().createCriteria(IndemnityCoef.class);
		crit.add(Restrictions.eq("level", level));
		IndemnityCoef coef = (IndemnityCoef) crit.uniqueResult();
		return coef;
	}
}
