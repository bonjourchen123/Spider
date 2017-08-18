package org.iii.module.commodity.rate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.rate.entity.Rate21;
import org.springframework.stereotype.Repository;

/**
 * @author bonjour
 * 
 *
 */
@Repository
public class Rate21Dao extends GenericDao<Rate21, Long>{
	
	public Rate21 findRate(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats){
		Criteria crit = getSession().createCriteria(Rate21.class);
		crit.add(Restrictions.eq("cartypeID", cartypeID));
		crit.add(Restrictions.or(
					Restrictions.eq("gender",gender),
					Restrictions.isNull("gender")));
		crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("fromAge",age),
									 Restrictions.ge("toAge",age)),
					Restrictions.and(Restrictions.isNull("fromAge"),
									 Restrictions.isNull("toAge"))));
		crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("tonnageMin",tonnage),
									 Restrictions.ge("tonnageMax",tonnage)),
					Restrictions.and(Restrictions.isNull("tonnageMin"),
									 Restrictions.isNull("tonnageMax"))));
		crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("seatsMin",seats),
									 Restrictions.ge("seatsMax",seats)),
					Restrictions.and(Restrictions.isNull("seatsMin"),
									 Restrictions.isNull("seatsMax"))));
		Rate21 rate21 = (Rate21) crit.uniqueResult();
		return rate21;
	}
	
	@SuppressWarnings("unchecked")
	public List<Rate21> findRates(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats){
		Criteria crit = getSession().createCriteria(Rate21.class);
		if(cartypeID!=0){
			crit.add(Restrictions.eq("cartypeID", cartypeID));
		}
		if(!("0".equals(gender))){
			crit.add(Restrictions.or(
					Restrictions.eq("gender",gender),
					Restrictions.isNull("gender")));
		}
		if(age!=0){
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("fromAge",age),
									 Restrictions.ge("toAge",age)),
					Restrictions.and(Restrictions.isNull("fromAge"),
									 Restrictions.isNull("toAge"))));
		}
		if(tonnage!=0){
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("tonnageMin",tonnage),
									 Restrictions.ge("tonnageMax",tonnage)),
					Restrictions.and(Restrictions.isNull("tonnageMin"),
									 Restrictions.isNull("tonnageMax"))));	
		}
		if(seats!=0){
			crit.add(Restrictions.or(
					Restrictions.and(Restrictions.lt("seatsMin",seats),
									 Restrictions.ge("seatsMax",seats)),
					Restrictions.and(Restrictions.isNull("seatsMin"),
									 Restrictions.isNull("seatsMax"))));
		}
		List<Rate21> rate21s =crit.list();
		return rate21s;
	}
}
