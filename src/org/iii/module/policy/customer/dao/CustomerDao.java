package org.iii.module.policy.customer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.policy.customer.entity.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author Shin Chen
 * 
 */
@Repository
public class CustomerDao extends GenericDao<Customer, Long> {

	public Customer findByPinAndOfficeID(String pin,Long officeID) {
		Criteria crit = getSession().createCriteria(Customer.class);
		crit.add(Restrictions.eq("pin", pin))
			.add(Restrictions.eq("officeID", officeID));
		Customer customer = (Customer) crit.uniqueResult();
		return customer;
	}

	//模糊查詢
	@SuppressWarnings("unchecked")
	public List<Customer> findByLike(String name, String pin, Long officeID) {
		Criteria crit = getSession().createCriteria(Customer.class);
		if (name != null && name.length() != 0) {
			crit.add(Restrictions.like("name", "%" + name + "%"));
		}
		if (pin != null && pin.length() != 0) {
			crit.add(Restrictions.like("pin", "%" + pin + "%"));
		}
		crit.add(Restrictions.eq("officeID", officeID));
		List<Customer> customers = crit.list();
		return customers;
	}
}
