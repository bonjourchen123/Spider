package org.iii.module.commodity.rate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.rate.entity.Rate31;
import org.springframework.stereotype.Repository;

/**
 * @author ant
 *
 */
@Repository  //標註為DAO組件。@Component-->標為組件(不知歸哪類時), @Service-->標為服務組件, @Controller-->標為控制組件  (Spring用的)
public class Rate31Dao extends GenericDao<Rate31, Long>{
	//一個DAO請對一個資料表做事，比較不會亂或方法重複到
	public Rate31 findRate(Long cartypeID,Integer caseMoney){
		Criteria crit=getSession().createCriteria(Rate31.class);
		crit.add(Restrictions.eq("cartypeID", cartypeID));
		crit.add(Restrictions.eq("caseMoney", caseMoney));
		Rate31 rate31 =(Rate31)crit.uniqueResult();
		return rate31;	
	}
	
	@SuppressWarnings("unchecked") //阻止未檢查的轉換時的警告，例如當使用集合時沒有用泛型(Generics) 來指定集合保存的類型。
	public List<Rate31> findRates(Long cartypeID,Integer caseMoney){
		Criteria crit=getSession().createCriteria(Rate31.class);
		if(cartypeID!=0){
			crit.add(Restrictions.eq("cartypeID", cartypeID));
		}
		if(caseMoney!=0){
			crit.add(Restrictions.eq("caseMoney", caseMoney));
		}
		List<Rate31> rate31s =crit.list();
		return rate31s;	
	}
}
