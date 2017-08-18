package org.iii.module.setting.office.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.office.entity.Office;
import org.springframework.stereotype.Repository;

/**
 * @author Shin Chen
 *
 */
@Repository
public class OfficeDao extends GenericDao<Office, Long> {

	public Office findByCode(String code) {
		Criteria crit = getSession().createCriteria(Office.class);
		crit.add(Restrictions.eq("code", code));
		Office ofc = (Office) crit.uniqueResult();
		return ofc;
	}
	
	public Office findByLocalName(String localName) {
		Criteria crit = getSession().createCriteria(Office.class);
		crit.add(Restrictions.eq("localName", localName));
		Office ofc = (Office) crit.uniqueResult();
		return ofc;
	}
	
	public Office findByEngName(String engName) {
		Criteria crit = getSession().createCriteria(Office.class);
		crit.add(Restrictions.eq("engName", engName));
		Office ofc = (Office) crit.uniqueResult();
		return ofc;
	}
	
	@SuppressWarnings("unchecked")
	public List<Office> findAll(){
		Criteria crit = getSession().createCriteria(Office.class);
		List<Office> offices=crit.list();
		return offices;
	}

	
	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<Office> findByLike(String code, String localName, String engName) {
		Criteria crit = getSession().createCriteria(Office.class);
		if(code!=null && code.length()!=0){
			crit.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(localName!=null && localName.length()!=0){
			crit.add(Restrictions.like("localName", "%"+localName+"%"));
		}
		if(engName!=null && engName.length()!=0){
			crit.add(Restrictions.like("engName", "%"+engName+"%"));
		}
		List<Office> office= crit.list();
		return office;
	}
}
