package org.iii.core.security.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.core.security.entity.SecUser;
import org.springframework.stereotype.Repository;

/**
 * @author Shock
 *
 */
@Repository
public class SecUserDao extends GenericDao<SecUser,Long> {
	
	public SecUser findByAcount(String account){
		Criteria crit = getSession().createCriteria(SecUser.class);
		crit.add(Restrictions.eq("account", account));
		SecUser user = (SecUser) crit.uniqueResult();
		return user;
	}
	
	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<SecUser> findByLike(String account,String name,Long officeID){
		Criteria crit=getSession().createCriteria(SecUser.class);
		if(account!=null && account.length()!=0){
			crit.add(Restrictions.like("account", "%"+account+"%"));
		}
		if(name!=null && name.length()!=0){
			crit.add(Restrictions.like("name", "%"+name+"%"));
		}
		crit.add(Restrictions.eq("officeID", officeID));
		List<SecUser> secUsers =crit.list();
		return secUsers;	
	}
}
