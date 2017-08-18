package org.iii.module.setting.car.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.car.entity.Cartype;
import org.springframework.stereotype.Repository;

/**
 * @author bonjour
 *
 */
@Repository
public class CartypeDao extends GenericDao<Cartype, Long>{
	
	public Cartype findByCode(String code){
		Criteria crit = getSession().createCriteria(Cartype.class);
		crit.add(Restrictions.eq("code", code));
		Cartype cartype = (Cartype) crit.uniqueResult();
		return cartype;
	}
	
	public Cartype findByLocalName(String localName){
		Criteria crit = getSession().createCriteria(Cartype.class);
		crit.add(Restrictions.eq("localName", localName));
		Cartype cartype = (Cartype) crit.uniqueResult();
		return cartype;
	}
	
	public Cartype findCarType(String purpose,String type,Integer cc){
		Criteria crit = getSession().createCriteria(Cartype.class);
		crit.add(Restrictions.or(Restrictions.eq("purpose", purpose),
								 Restrictions.isNull("purpose")))
			.add(Restrictions.or(Restrictions.eq("type", type),
								 Restrictions.isNull("type")))
			.add(Restrictions.or(
					Restrictions.and(Restrictions.le("ccMin",cc),
									 Restrictions.gt("ccMax",cc)),
					Restrictions.and(Restrictions.isNull("ccMin"),
									 Restrictions.isNull("ccMax"))));
		Cartype cartype = (Cartype) crit.uniqueResult();
		return cartype;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cartype> findAll(){
		Criteria crit = getSession().createCriteria(Cartype.class);
		List<Cartype> cartypes=crit.list();
		return cartypes;
	}
	
	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<Cartype> findByLike(String code,String localName,String purpose,String type,Integer cc){
		Criteria crit = getSession().createCriteria(Cartype.class);
		if(code!=null && code.length()!=0){
			crit.add(Restrictions.like("code", "%"+code+"%"));
		}
		if(localName!=null && localName.length()!=0){
			crit.add(Restrictions.like("localName", "%"+localName+"%"));
		}
		if(!("0".equals(purpose))){
			crit.add(Restrictions.eq("purpose", purpose));
		}
		if(!("0".equals(type))){
			crit.add(Restrictions.or(
						Restrictions.eq("type", type),
						Restrictions.isNull("type")));
		}
		if(cc!=0){
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.le("ccMin",cc),
									 Restrictions.gt("ccMax",cc)),
					Restrictions.and(Restrictions.isNull("ccMin"),
									 Restrictions.isNull("ccMax"))));
		}
		List<Cartype> cartypes = crit.list();
		return cartypes;
	}
}
