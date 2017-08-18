package org.iii.module.policy.customer.service;

import java.util.List;

import org.iii.core.security.entity.SecUser;
import org.iii.module.policy.customer.entity.Customer;
import org.joda.time.LocalDateTime;

/**
 * @author Shin Chen
 * 
 */
public interface CustomerService {
	public List<Customer> queryByLike(String name, String pin, Long officeID);
	
	public Customer getCustomer(String pin,Long officeID);
	
	public Boolean createCustomer(SecUser LoginUser,String name,String officeID,String pin,String gender,LocalDateTime birthday,String phone,String permanentAddr,String currentAddr);

	public Customer queryCustomer(Long id);
	
	public Boolean updateCustomer(SecUser LoginUser,Customer customer);
	
	public Boolean renewCustomer(SecUser LoginUser,String name,String officeID,String pin,String gender,LocalDateTime birthday,String phone,String permanentAddr,String currentAddr,String rateLevel,String indemnityLevel);
	
	public Customer getCustomer(Long id);
	
	public Boolean deleteCustomer(Long id);

}