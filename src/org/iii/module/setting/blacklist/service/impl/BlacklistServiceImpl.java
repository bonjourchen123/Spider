package org.iii.module.setting.blacklist.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.blacklist.dao.BlacklistDao;
import org.iii.module.setting.blacklist.entity.Blacklist;
import org.iii.module.setting.blacklist.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Hansen
 * 
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {
	@Autowired
	private BlacklistDao blacklistDao;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllBlacklist() {
		List<LabelValueModel> list = Lists.newArrayList();
		List<Blacklist> blacklists = blacklistDao.findAll();
		list = LabelValueModel.asList(blacklists,
		new Function<Blacklist, LabelValueModel>() {	
		  @Override
			public LabelValueModel apply(Blacklist blacklist) {
				return LabelValueModel.getLabelValueModel(blacklist.getNumber(), blacklist.getId().toString());// 不懂意思
					}
				});
				return list;
	}
	//查詢
	@Override
	public List<Blacklist> queryByLike(String number, String type, String reason) {
		return blacklistDao.findByLike(number, type, reason);
	}

	//新增
	@Override
	public Boolean createBlacklist(SecUser loginUser,String type, String number, String reason) {
		Blacklist blacklist=new Blacklist();
		blacklist.initInsert(loginUser);
		blacklist.setType(type);
		blacklist.setNumber(number);
		blacklist.setReason(reason);
			try {
				blacklistDao.save(blacklist);
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	//用數字抓資料
	@Override
	public Blacklist getBlacklistNumber(String number) {
		Blacklist list=blacklistDao.findByNumber(number);
		return list;
		
	}
	//修改
	public Boolean updateBlacklist(SecUser loginUser,String type, String number, String reason ,Long id) {
		Blacklist blacklist=null;
		try {
			blacklist = blacklistDao.findByPk(id);//先抓id值,後去用id抓到讓改吧
		} catch (Exception e1) {
			return false;
		}
		blacklist.initUpdate(loginUser);
		System.out.print(type);
		blacklist.setType(type);
		blacklist.setNumber(number);
		System.out.print(number);
		blacklist.setReason(reason);
		System.out.print(reason);
			try {
				blacklistDao.update(blacklist);
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	//刪除
	@Override
	public Boolean deleteBlacklist(Long Id) {
		try {
			blacklistDao.deleteByPk(Id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//抓id去做Ajax的驗證機制
	@Override
	public Blacklist getBlacklistId(Long id) {
		Blacklist list = null;
		try {
			list = blacklistDao.findByPk(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
}
