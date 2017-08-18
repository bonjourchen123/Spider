package org.iii.module.setting.car.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.car.entity.Carmodel;
import org.springframework.stereotype.Repository;

/**
 * @author Hansen
 *
 */
@Repository
public class CarmodelDao extends GenericDao<Carmodel, Long> {
	
	public Carmodel findByCode(String code){
		Criteria crit = getSession().createCriteria(Carmodel.class);
		crit.add(Restrictions.eq("code", code));
		Carmodel carmodel = (Carmodel) crit.uniqueResult();
		return carmodel;
	}

	@SuppressWarnings("unchecked")
	public List<Carmodel> findAll() {
		Criteria crit = getSession().createCriteria(Carmodel.class);
		List<Carmodel> carmodels=crit.list();
		return carmodels;
	}

	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<Carmodel> findByLike(String code, String brandcode, String seriescode, String localName) {
		Criteria crit = getSession().createCriteria(Carmodel.class);
		if(code != null && code.length() != 0){
			crit.add(Restrictions.like("code", "%" + code + "%"));
		}
		if(brandcode != null && brandcode.length() < 2){
			Criterion rest1= Restrictions.like("code", "_" + brandcode + "%"); 
			Criterion rest2= Restrictions.like("code", brandcode + "%");
			crit.add(Restrictions.or(rest1, rest2));
		}
		if(brandcode != null && brandcode.length() == 2){
			crit.add(Restrictions.like("code", brandcode + "%"));
		}
		if(seriescode != null && seriescode.length() < 2){
			Criterion rest1= Restrictions.like("code", "___" + seriescode + "%"); 
			Criterion rest2= Restrictions.like("code", "__" + seriescode + "%");
			crit.add(Restrictions.or(rest1, rest2));
		}
		if(seriescode != null && seriescode.length() == 2){
			crit.add(Restrictions.like("code", "__" + seriescode + "%"));
		}
		if(localName != null && localName.length() != 0){
			crit.add(Restrictions.like("localName", "%" + localName + "%"));
		}
		List<Carmodel> carmodel= crit.list();
		return carmodel;
	}
}
