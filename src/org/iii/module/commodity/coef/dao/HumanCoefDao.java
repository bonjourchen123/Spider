package org.iii.module.commodity.coef.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.coef.entity.HumanCoef;
import org.springframework.stereotype.Repository;

/**
 * @author ant
 *
 */
@Repository
public class HumanCoefDao extends GenericDao<HumanCoef, Long>{
	
	public HumanCoef findByGenderAndAge(String gender,Integer age){
		Criteria crit=getSession().createCriteria(HumanCoef.class);
		crit.add(Restrictions.eq("gender", gender))
			.add(Restrictions.le("fromAge", age))
			.add(Restrictions.ge("toAge", age));
		//lt小於 le小於等於 ge大於等於 gt大於
		HumanCoef humanCoef =(HumanCoef)crit.uniqueResult();
		return humanCoef;
	}
}
