package org.iii.core.auth.web;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.iii.core.action.GenericAction;
import org.iii.core.security.entity.SecUser;
import org.iii.core.security.service.SecUserService;
import org.iii.core.security.service.UserRoleService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author Shock
 *
 */
//@SuppressWarnings作用：告訴編譯器忽略指定的警告，不用在編譯完成後出現警告信息。 
//如果編譯器出現這樣的警告信息：The serializable class WmailCalendar does not declare a static final serialVersionUID field of type long 
//使用這個註釋將警告信息去掉。 
@SuppressWarnings("serial")
//The basic purpose of the @Controller annotation is to act as a stereotype for the annotated class, indicating its role. 
// The dispatcher will scan such annotated classes for mapped methods, detecting @RequestMapping annotations (see the next section).
@Controller
//所有Action都該設定Scope為SCOPE_PROTOTYPE 讓裡面所有要使用的物件都new一個新的
//預設為尋找已經建立的物件注入使用 沒找到才建新的
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginAction extends GenericAction<SecUser> {
	private String account;
	
	private String password;
	
	@Autowired
	private SecUserService secUserService;
	@Autowired
	private UserRoleService userRoleService;

	//驗證登入
	public void validateLogin() {
		this.account=account.trim();
		this.password=password.trim();
		if(account==null || account.length()==0){
			this.addFieldError("account", "請輸入帳號!");
		}
		if(password==null || password.length()==0){
			this.addFieldError("password", "請輸入密碼!");
		}
	}
	
	//登入
	public String login(){
		if(secUserService.validateLogin(account, password)){
			SecUser user = secUserService.getUserInfo(account);
			getSession().put(LOGIN, user);
			List<String> roleCodes=userRoleService.getRoleCodes(user);
			for(String roleCode:roleCodes){
				getSession().put(roleCode, roleCode);
			}
			return SUCCESS;
		}else{
			SecUser user=secUserService.getUserInfo(account);
			if(user!=null){
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
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(user.getEmail())); 
					message.setSubject("Spider車險投保系統-登入失敗通知");  
					LocalDateTime now=LocalDateTime.now();
					message.setText(user.getName()+" 先生/小姐您好!\n\n    提醒您在時間:"+now.getYear()+"年"+now.getMonthOfYear()+"月"+now.getDayOfMonth()+"日"+now.getHourOfDay()+":"+now.getMinuteOfHour()+":"+now.getSecondOfMinute()+"有人嘗試登入您的帳號\n\n    如非本人使用請盡速更換您的密碼\n\n    感謝您的使用，祝您萬事如意!\n\nSpider車險投保系統敬上");  
		  
					Transport transport = session.getTransport("smtp");  
					transport.connect(host, port, username, password); 
					Transport.send(message);
		        } catch (Exception e) {
					
				}
			}
			this.addFieldError("result","帳號密碼錯誤!");
			return INPUT;
		}
	}
	
	//首頁
	public String index(){
		if(getLoginUser()==null){
			return INPUT;
		}else{
			return SUCCESS;
		}
	}
	
	//登出
	public String logout(){
		getSession().clear();
		return INPUT;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}