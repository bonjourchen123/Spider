package org.iii.core.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iii.core.security.dao.SecRoleDao;
import org.iii.core.security.dao.UserRoleDao;
import org.iii.core.security.entity.SecUser;
import org.iii.core.security.entity.UserRole;
import org.iii.core.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuso
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private SecRoleDao secRoleDao;
	
	@Override
	public List<String> getRoleCodes(SecUser user) {
		List<UserRole> userRole=userRoleDao.findByUserID(user.getId());
		List<String> result=new ArrayList<String>();
		for(UserRole bean:userRole){
			try {
				result.add(secRoleDao.findByPk(bean.getRoleID()).getCode());
			} catch (Exception e) {
				
			}
		}
		return result;
	}

}
