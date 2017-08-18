package org.iii.module.setting.office.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.office.dao.OfficeDao;
import org.iii.module.setting.office.entity.Office;
import org.iii.module.setting.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author bonjour
 *
 */
@Service
public class OfficeServiceImpl implements OfficeService {
	@Autowired
	private OfficeDao officeDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllOffice(){
		List<LabelValueModel> list = Lists.newArrayList();
		List<Office> offices=officeDao.findAll();
		list = LabelValueModel.asList(offices, new Function<Office,LabelValueModel>(){
			@Override
			public LabelValueModel apply(Office office) {
				return LabelValueModel.getLabelValueModel(office.getLocalName(), office.getId().toString());
			}
		});
		return list;
	}

	@Override
	public List<Office> queryByLike(String code, String localName, String engName) {
		return officeDao.findByLike(code, localName, engName);
	}

	//新增
	@Override
	public boolean createOffice(SecUser loginUser, String code,	String localName, String engName) {
		Office office = new Office();
		office.initInsert(loginUser);
		office.setCode(code);
		office.setLocalName(localName);
		office.setEngName(engName);
		
		try {
			officeDao.save(office);
			return true;
			} catch(Exception e){
			return false;
			}
		
	}
	
	//修改
	@Override
	public boolean updateOffice(SecUser loginUser, String code,	String localName, String engName) {
		Office office = officeDao.findByCode(code);
		office.initUpdate(loginUser);
		office.setLocalName(localName);
		office.setEngName(engName);
		
		try {
			officeDao.update(office);
			return true;
			} catch(Exception e){
			return false;
			}
		
	}

	//id值
	@Override
	public Office getOfficeId(Long id) {
		Office list = null;
		try {
			list = officeDao.findByPk(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//刪除
	@Override
	public boolean deleteOffice(Long id){
		try {
			officeDao.deleteByPk(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Office getOfficeCode(String code) {
		Office office = officeDao.findByCode(code);
		return office;
	}

	@Override
	public Office getOfficeLocalName(String localName) {
		Office office = officeDao.findByLocalName(localName);
		return office;
	}

	@Override
	public Office getOfficeEngName(String engName) {
		Office office = officeDao.findByEngName(engName);
		return office;
	}

	
}
