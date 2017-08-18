package org.iii.core.security.service;

import java.util.List;

import org.iii.core.security.entity.SecUser;

/**
 * @author Shock
 * 
 */
public interface SecUserService {
	public Boolean validateLogin(String account,String password);
	
	public SecUser getUserInfo(String account);
	
	public Boolean changePassword(SecUser user,String newPassword);

	public Boolean resetPassword(SecUser loginUser,SecUser user);
	
	public Boolean createAccount(SecUser loginUser,String account,String name,String phone,String email,String officeId,String[] rolesCode);

	public List<SecUser> queryByLike(String account,String name,Long officeID);

	public SecUser queryByID(Long SecUserId);
	
	public Boolean updateAccount(SecUser loginUser,String account,String name,String phone,String email,String officeId,String[] rolesCode);

	public Boolean deleteAccount(Long Id);
}
