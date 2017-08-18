/**
 * 
 */
package org.iii.core.security.service.impl;


import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.iii.core.security.dao.SecRoleDao;
import org.iii.core.security.dao.SecUserDao;
import org.iii.core.security.dao.UserRoleDao;
import org.iii.core.security.entity.SecUser;
import org.iii.core.security.entity.UserRole;
import org.iii.core.security.service.SecUserService;
import org.iii.module.setting.office.dao.OfficeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shock
 *
 */
@Service
public class SecUserServiceImpl implements SecUserService{
	@Autowired
	private SecUserDao secUserDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private SecRoleDao secRoleDao;
	@Autowired
	private OfficeDao officeDao;
	
	@Override
	public Boolean validateLogin(String account,String password){
			SecUser user = secUserDao.findByAcount(account);
			if(user !=null){
				String pwd = StringUtils.trimToEmpty(password);
				String checkPwd = DigestUtils.md5Hex(pwd);
				return (checkPwd.equals(user.getPassword()));
			}else{
				return false;
			}
		
	}
	
	@Override
	public SecUser getUserInfo(String account) {
		SecUser user=secUserDao.findByAcount(account);
		try {
			user.setOfficeLocalName(officeDao.findByPk(user.getOfficeID()).getLocalName());
		} catch (Exception e) {

		}
		return user;
	}
	
	@Override
	public Boolean changePassword(SecUser user,String newPassword){
		try {
			user.initUpdate(user);
			user.setPassword(DigestUtils.md5Hex(newPassword));
			secUserDao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean resetPassword(SecUser loginUser,SecUser user){
		try {
			user.initUpdate(loginUser);	
			int z;
		    StringBuilder newPassword = new StringBuilder();
		    int i;
		    for (i = 0; i < 8; i++) {
		    	z=(int) ((Math.random()*7)%3);
		    	if(z==1){
		    		newPassword.append((int) ((Math.random()*10)));
		    	}else if(z==2){ //大寫英文
		    		newPassword.append((char) (((Math.random() * 26) + 65)));
		    	}else{//小寫英文
		    		newPassword.append(((char) ((Math.random() * 26) + 97)));
		    	}
		    }
			user.setPassword(DigestUtils.md5Hex(newPassword.toString()));
			
			//寄信
			System.setProperty( "mail.mime.charset", "big5");
			String host = "smtp.gmail.com";  
	        int port = 587;  
	        final String username = "spider.veh.pol@gmail.com";  
	        final String password = "spidervehpol";  
	  
	        Properties props = new Properties();  
	        props.put("mail.smtp.host", host);  
	        props.put("mail.smtp.auth", "true");  
	        props.put("mail.smtp.starttls.enable", "true");  
	        props.put("mail.smtp.port", port);  
	          
	        Session session = Session.getInstance(props,new Authenticator(){  
	              protected PasswordAuthentication getPasswordAuthentication() {  
	                  return new PasswordAuthentication(username, password);  
	              }} );  
	        
	        Message message = new MimeMessage(session);  
	        message.setFrom(new InternetAddress(username));  
	        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(user.getEmail())); 
	        message.setSubject("Spider車險投保系統-密碼通知信");  
	        message.setText(user.getName()+" 先生/小姐您好!\n\n"+"    您的新密碼為:"+newPassword+"\n\n    提醒您登入後請盡快變更您的密碼!\n\n    感謝您的使用，祝您萬事如意!\n\nSpider車險投保系統敬上");  
	  
	        Transport transport = session.getTransport("smtp");  
	        transport.connect(host, port, username, password); 
	        Transport.send(message); 
	        
			secUserDao.update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean createAccount(SecUser loginUser,String account,String name,String phone,String email,String officeId,String[] rolesCode){
		SecUser user=new SecUser();
		user.initInsert(loginUser);
		user.setAccount(account);
		int z;
	    StringBuilder newPassword = new StringBuilder();
	    int i;
	    for (i = 0; i < 8; i++) {
	    	z=(int) ((Math.random()*7)%3);
	    	if(z==1){
	    		newPassword.append((int) ((Math.random()*10)));
	    	}else if(z==2){ //大寫英文
	    		newPassword.append((char) (((Math.random() * 26) + 65)));
	    	}else{//小寫英文
	    		newPassword.append(((char) ((Math.random() * 26) + 97)));
	    	}
	    }
		user.setPassword(DigestUtils.md5Hex(newPassword.toString()));
		user.setName(name);
		
		System.setProperty( "mail.mime.charset", "big5");
		String host = "smtp.gmail.com";  
        int port = 587;  
        final String username = "spider.veh.pol@gmail.com";  
        final String password = "spidervehpol";  
        Properties props = new Properties();  
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.starttls.enable", "true");  
        props.put("mail.smtp.port", port);     
        Session session = Session.getInstance(props,new Authenticator(){  
              protected PasswordAuthentication getPasswordAuthentication() {  
                  return new PasswordAuthentication(username, password);  
              }} );  
        Message message = new MimeMessage(session);  
        try {
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email)); 
	        message.setSubject("Spider車險投保系統-密碼通知信");  
	        message.setText(user.getName()+" 先生/小姐您好!\n\n"+"    您的密碼為:"+newPassword+"\n\n    提醒您登入後請盡快變更您的密碼!\n\n    感謝您的使用，祝您萬事如意!\n\nSpider車險投保系統敬上");
	        Transport transport = session.getTransport("smtp");  
	        transport.connect(host, port, username, password); 
	        Transport.send(message);
        } catch (Exception e) {
			return false;
		}
		user.setPhone(phone);
		user.setEmail(email);
		user.setOfficeID(Long.valueOf(officeId));
		try {
			secUserDao.save(user);
			for(String roleCode:rolesCode){
				UserRole userRole=new UserRole();
				userRole.initInsert(loginUser);
				userRole.setUserID(user.getId());
				userRole.setRoleID(secRoleDao.findByCode(roleCode).getId());
				userRoleDao.save(userRole);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public List<SecUser> queryByLike(String account,String name,Long officeID){
		return secUserDao.findByLike(account,name,officeID);
	}
	
	@Override
	public SecUser queryByID(Long SecUserId){
		try {
			return secUserDao.findByPk(SecUserId);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean updateAccount(SecUser loginUser,String account,String name,String phone,String email,String officeId,String[] rolesCode){
		SecUser user=secUserDao.findByAcount(account);
		user.initUpdate(loginUser);
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		user.setOfficeID(Long.valueOf(officeId));
		try {
			secUserDao.update(user);
			List<UserRole> oldRoles=userRoleDao.findByUserID(user.getId());
			for(UserRole role:oldRoles){
				userRoleDao.delete(role);
			}
			for(String roleCode:rolesCode){
				UserRole userRole=new UserRole();
				userRole.initInsert(loginUser);
				userRole.setUserID(user.getId());
				userRole.setRoleID(secRoleDao.findByCode(roleCode).getId());
				userRoleDao.save(userRole);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean deleteAccount(Long Id){
		try {
			secUserDao.deleteByPk(Id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
