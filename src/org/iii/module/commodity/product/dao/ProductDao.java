package org.iii.module.commodity.product.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.product.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * @author bonjour
 *
 */
@Repository
public class ProductDao extends GenericDao<Product,Long>  {

	public Product findByCode(String code){
		Criteria crit = getSession().createCriteria(Product.class);
		crit.add(Restrictions.eq("code", code));
		Product roduct = (Product) crit.uniqueResult();
		return roduct;
	}
	
}
