package org.iii.module.setting.car.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.car.entity.Brand;
import org.springframework.stereotype.Repository;

/**
 * @author Hansen
 * 
 */
@Repository
public class BrandDao extends GenericDao<Brand, Long> {

	public Brand findByCode(String code) {
		Criteria crit = getSession().createCriteria(Brand.class);
		crit.add(Restrictions.eq("code", code));
		Brand brand = (Brand) crit.uniqueResult();
		return brand;
	}
	
	@SuppressWarnings("unchecked")
	public List<Brand> findAll() {
		Criteria crit = getSession().createCriteria(Brand.class);
		List<Brand> brands = crit.list();
		return brands;
	}

	// 模糊查詢
	@SuppressWarnings("unchecked")
	public List<Brand> findByLike(String code, String localName,
			String engName,String descript) {
		Criteria crit = getSession().createCriteria(Brand.class);
		if (code != null && code.length() != 0) {
			crit.add(Restrictions.like("code", "%" + code + "%"));
		}
		if (localName != null && localName.length() != 0) {
			crit.add(Restrictions.like("localName", "%" + localName + "%"));
		}
		if (engName != null && engName.length() != 0) {
			crit.add(Restrictions.like("engName", "%" + engName + "%"));
		}

		List<Brand> brands = crit.list();
		return brands;
	}
}