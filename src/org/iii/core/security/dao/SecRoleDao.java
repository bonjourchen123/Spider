package org.iii.core.security.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.core.security.entity.SecRole;
import org.springframework.stereotype.Repository;

/**
 * @author Kuso
 *
 */
@Repository
public class SecRoleDao extends GenericDao<SecRole,Long>  {

	public SecRole findByCode(String code){
		Criteria crit = getSession().createCriteria(SecRole.class);
		crit.add(Restrictions.eq("code", code));
		SecRole role = (SecRole) crit.uniqueResult();
		return role;
	}
	
}
