package org.iii.module.setting.car.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.car.entity.Carseries;
import org.springframework.stereotype.Repository;

/**
 * @author Tony
 * @collaborator Kuso,ShinChen
 * 
 */
@Repository
public class CarseriesDao extends GenericDao<Carseries, Long> {
	
	public Carseries findByCodeAndBrandID(String code, Long brandID) {
		Criteria crit = getSession().createCriteria(Carseries.class);
		crit.add(Restrictions.eq("brandID", brandID))
			.add(Restrictions.eq("code", code));
		Carseries carseriess = (Carseries) crit.uniqueResult();
		return carseriess;
	}
	
	@SuppressWarnings("unchecked")
	public List<Carseries> findAll() {
		Criteria crit = getSession().createCriteria(Carseries.class);
		List<Carseries> carseriess=crit.list();
		return carseriess;
	}
	
	public Carseries findByCode(String code){
		Criteria crit = getSession().createCriteria(Carseries.class);
		crit.add(Restrictions.eq("code",code));
		Carseries carseriess = (Carseries) crit.uniqueResult();
		return carseriess;
	}
	
	
	// 模糊查詢
	@SuppressWarnings("unchecked")
	public List<Carseries> findByLike(String code, Long[] brandID,
			String localName, String type) {
		Criteria crit = getSession().createCriteria(Carseries.class);
		if (code != null && code.length() != 0) {
			crit.add(Restrictions.like("code", "%" + code + "%"));
		}
		if (brandID != null && brandID.toString().length() != 0) {
			crit.add(Restrictions.in("brandID", brandID));
		}
		if (localName != null && localName.length() != 0) {
			crit.add(Restrictions.like("localName", "%" + localName + "%"));
		}
		if(!("0".equals(type))){
			crit.add(Restrictions.or(
						Restrictions.eq("type", type),
						Restrictions.isNull("type")));
		}
		List<Carseries> carseries = crit.list();
		return carseries;
	}

	
}
