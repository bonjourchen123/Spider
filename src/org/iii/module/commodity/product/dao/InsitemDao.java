package org.iii.module.commodity.product.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.product.entity.Insitem;
import org.springframework.stereotype.Repository;

/**
 * @author Tony
 * @collaborator bonjour
 *
 */
@Repository
public class InsitemDao extends GenericDao<Insitem, Long> {
	
	public Insitem findByCode(String code){
		Criteria crit = getSession().createCriteria(Insitem.class);
		crit.add(Restrictions.eq("code", code));
		Insitem insitem = (Insitem) crit.uniqueResult();
		return insitem;
	}
}
