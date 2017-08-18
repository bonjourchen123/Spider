package org.iii.core.security.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.core.security.entity.UserRole;
import org.springframework.stereotype.Repository;

/**
 * @author Kuso
 *
 */
@Repository
public class UserRoleDao extends GenericDao<UserRole,Long>{
	
	@SuppressWarnings("unchecked")
	public List<UserRole> findByUserID(Long userID){
		Criteria crit = getSession().createCriteria(UserRole.class);
		crit.add(Restrictions.eq("userID", userID));
		List<UserRole> userRoles =crit.list();
		return userRoles;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRole> findByRoleID(Long roleID){
		Criteria crit = getSession().createCriteria(UserRole.class);
		crit.add(Restrictions.eq("roleID", roleID));
		List<UserRole> userRoles =crit.list();
		return userRoles;
	}
}
