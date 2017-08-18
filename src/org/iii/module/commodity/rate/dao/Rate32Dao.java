package org.iii.module.commodity.rate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.rate.entity.Rate32;
import org.springframework.stereotype.Repository;

/**
 * @author ant
 *
 */
@Repository
public class Rate32Dao extends GenericDao<Rate32, Long>{
	
	public Rate32 findRate(Long cartypeID,Integer caseMoney){
		Criteria crit=getSession().createCriteria(Rate32.class);
		crit.add(Restrictions.eq("cartypeID", cartypeID));
		crit.add(Restrictions.eq("caseMoney", caseMoney));
		Rate32 rate32 =(Rate32)crit.uniqueResult();
		return rate32;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Rate32> findRates(Long cartypeID,Integer caseMoney){
		Criteria crit=getSession().createCriteria(Rate32.class);
		if(cartypeID!=0){
			crit.add(Restrictions.eq("cartypeID", cartypeID));
		}
		if(caseMoney!=0){
			crit.add(Restrictions.eq("caseMoney", caseMoney));
		}
		List<Rate32> rate32s =crit.list();
		return rate32s;
	}
	
}
