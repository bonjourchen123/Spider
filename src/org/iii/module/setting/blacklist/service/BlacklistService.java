package org.iii.module.setting.blacklist.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.blacklist.entity.Blacklist;

/**
 * @author Hansen
 * 
 */
public interface BlacklistService {

	public List<LabelValueModel> getAllBlacklist();

	public List<Blacklist> queryByLike(String number, String type, String reason);
	
	public Blacklist getBlacklistNumber(String number);
	
	public Blacklist getBlacklistId(Long Id);
	
	public Boolean createBlacklist(SecUser loginUser,String type, String number, String reason);

	public Boolean updateBlacklist(SecUser loginUser,String type, String number, String reason ,Long id);
	
	public Boolean deleteBlacklist(Long Id);
	
}
