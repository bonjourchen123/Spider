package org.iii.module.policy.customer.service.impl;

import java.util.List;

import org.iii.core.security.entity.SecUser;
import org.iii.module.policy.customer.dao.CustomerDao;
import org.iii.module.policy.customer.entity.Customer;
import org.iii.module.policy.customer.service.CustomerService;
import org.iii.module.setting.office.dao.OfficeDao;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shin Chen
 * 
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private OfficeDao officeDao;
	
	//查詢
	@Override
	public List<Customer> queryByLike(String name, String pin, Long officeID) {
		return customerDao.findByLike(name, pin, officeID);
	}
	
	@Override
	public Customer getCustomer(String pin,Long officeID) {
		Customer customer=customerDao.findByPinAndOfficeID(pin, officeID);
		try {
			customer.setOfficeLocalName(officeDao.findByPk(customer.getOfficeID()).getLocalName());
		} catch (Exception e) {

		}
		return customer;
	}
	
	
	//新增
	@Override
	public Boolean createCustomer(SecUser LoginUser, String name,String officeID, String pin,String gender, LocalDateTime birthday, String phone,String permanentAddr, String currentAddr) {
		Customer customer=new Customer();
		customer.initInsert(LoginUser);
		customer.setName(name);
		customer.setOfficeID(Long.valueOf(officeID));
		customer.setPin(pin);
		customer.setGender(gender);
		customer.setBirthday(birthday);
		customer.setPhone(phone);
		customer.setPermanentAddr(permanentAddr);
		customer.setCurrentAddr(currentAddr);
		customer.setRateLevel(4);
		customer.setIndemnityLevel(4);
		try {
			customerDao.save(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Customer queryCustomer(Long id){
		try {
			return customerDao.findByPk(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean updateCustomer(SecUser LoginUser,Customer customer){
		customer.initUpdate(LoginUser);
		try {
			customerDao.update(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//刪除
	@Override
	public Boolean deleteCustomer(Long id){
		try {
			customerDao.deleteByPk(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//編輯
	@Override
	public Boolean renewCustomer(SecUser LoginUser, String name,String officeID, String pin, String gender, LocalDateTime birthday, String phone, String permanentAddr, String currentAddr, String rateLevel, String indemnityLevel) {
			Customer customer=null;
		try {
			customer=customerDao.findByPinAndOfficeID(pin, LoginUser.getOfficeID());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		customer.initUpdate(LoginUser);
		customer.setName(name);
		customer.setGender(gender);
		customer.setBirthday(birthday);
		customer.setPhone(phone);
		customer.setPermanentAddr(permanentAddr);
		customer.setCurrentAddr(currentAddr);
		customer.setRateLevel(Integer.valueOf(rateLevel));
		customer.setIndemnityLevel(Integer.valueOf(indemnityLevel));
		try {
			customerDao.update(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Customer getCustomer(Long id) {
		Customer list=null;
		try {
			list=customerDao.findByPk(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
