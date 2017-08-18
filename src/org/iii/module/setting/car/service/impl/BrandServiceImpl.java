package org.iii.module.setting.car.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.dao.BrandDao;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Hansen
 * 
 */
@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllBrand() {
		List<LabelValueModel> list = Lists.newArrayList();
		List<Brand> brands = brandDao.findAll();
		list = LabelValueModel.asList(brands,new Function<Brand, LabelValueModel>() {
		  @Override
			public LabelValueModel apply(Brand brand) {
				return LabelValueModel.getLabelValueModel(brand.getLocalName(), brand.getId().toString());// 不懂意思
				}
			});
	       return list;
	}
	//查詢
	@Override
	public List<Brand> queryByLike(String code, String localName,String engName,String descript) {
		return brandDao.findByLike(code, localName, engName,descript);
	}

	//新增
	@Override
	public Boolean createBrand(SecUser loginUser,String code, String localName, String engName,String descript) {
		Brand brand=new Brand();
		brand.initInsert(loginUser);
		brand.setCode(code);
		brand.setLocalName(localName);
		brand.setEngName(engName);
		brand.setDescript(descript);
			try {
				brandDao.save(brand);
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	@Override
	public Brand getBrandCode(String code) {
		Brand brand=brandDao.findByCode(code);
		if(brand!=null){
			brand.setBrandLocalName(brand.getLocalName());
			return brand;
		}else{
			return null;
		}
	}
	
	//修改
	@Override
	public Boolean updateBrand(SecUser loginUser,String code, String localName, String engName,String descript){
		Brand brand=brandDao.findByCode(code);
		brand.initUpdate(loginUser);
		brand.setLocalName(localName);
		brand.setEngName(engName);
		brand.setDescript(descript);
			try {
				brandDao.update(brand);
				return true;
			} catch (Exception e) {
				return false;
			}
	}

	//刪除
	@Override
	public Boolean deleteBrand(Long Id) {
		try {
			brandDao.deleteByPk(Id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//抓id值
	@Override
	public Brand getBrandId(Long id) {
		Brand list = null;
		try {
			list = brandDao.findByPk(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public Brand getBrandName(String brandCode) {
		Brand list = null;
		try {
			list = brandDao.findByCode(brandCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
