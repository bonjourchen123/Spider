package org.iii.module.commodity.product.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.commodity.product.entity.ProductInsitem;
import org.springframework.stereotype.Repository;

/**
 * @author Hansen
 *
 */
@Repository
public class ProductInsitemDao extends GenericDao<ProductInsitem,Long> {
	
	@SuppressWarnings("unchecked")
	public List<ProductInsitem> FindByProductID(Long productID){
		Criteria crit=getSession().createCriteria(ProductInsitem.class);
		crit.add(Restrictions.eq("productID", productID));
		List<ProductInsitem> pi =crit.list();
		return pi;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProductInsitem> FindByInsitemID(Long insitemID){
		Criteria crit=getSession().createCriteria(ProductInsitem.class);
		crit.add(Restrictions.eq("insitemID", insitemID));
		List<ProductInsitem>  pi =crit.list();
		return  pi;
	}
}
