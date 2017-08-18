package org.iii.module.setting.car.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.dao.BrandDao;
import org.iii.module.setting.car.dao.CarseriesDao;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.entity.Carseries;
import org.iii.module.setting.car.service.CarseriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author ShinChen
 * 
 * 
 */
@Service
public class CarseriesServiceImpl implements CarseriesService {

	@Autowired
	private CarseriesDao carseriesDao;
	@Autowired
	private BrandDao brandDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllCarseries() {
		List<LabelValueModel> list = Lists.newArrayList();
		List<Carseries> carseriess = carseriesDao.findAll();
		list = LabelValueModel.asList(carseriess,
				new Function<Carseries, LabelValueModel>() {

					@Override
					public LabelValueModel apply(Carseries carseries) {
						return LabelValueModel.getLabelValueModel(carseries
								.getLocalName(), carseries.getId().toString());
					}
				});
		return list;
	}
	//查詢
	@Override
	public List<Carseries> queryByLike(String code, String brandCode,
			String localName, String type) {
		Long[] brandID;
		if (brandCode != null && brandCode.length() != 0) {
			List<Brand> brand=brandDao.findByLike(brandCode, null, null,null);
			if(brand.isEmpty()){
				return Lists.newArrayList();
			}
			brandID=new Long[brand.size()];
			int i=0;
			for(Brand brands:brand){
				brandID[i]=brands.getId();
				i++;
			}
		}else{
			brandID=null;
		}
		List<Carseries> carseriess=carseriesDao.findByLike(code, brandID, localName, type);
		for(Carseries carseries:carseriess){
			try {
				carseries.setBrand(brandDao.findByPk(carseries.getBrandID()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			return carseriess;
	}
	
	//新增
	@Override
	public Boolean createCarseries(SecUser LoginUser,String code, String brandCode, String localName, String type){
		Carseries carseriess=new Carseries();
		carseriess.initInsert(LoginUser);
		carseriess.setCode(code);
		carseriess.setBrandID(brandDao.findByCode(brandCode).getId());
		carseriess.setLocalName(localName);
		carseriess.setType(type);
		try {
		carseriesDao.save(carseriess);
			return true;
		} catch (Exception e) {
			return false;
			}	
		}

	//刪除
		@Override
		public Boolean deleteCarseries(Long Id){
			try {
				carseriesDao.deleteByPk(Id);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		@Override
		public Brand getBrand(String brandCode) {
			Brand brand=brandDao.findByCode(brandCode);
			try {
				brand.setBrandLocalName(brand.getLocalName());
			} catch (Exception e) {
				return null;
			}
			return brand;
		}
		@Override
		public Carseries getCarseries(String code,String brandCode) {
			Carseries carseries=carseriesDao.findByCodeAndBrandID(code,brandDao.findByCode(brandCode).getId());
			if(carseries!=null){
				carseries.setCarseriesLocalName(carseries.getLocalName());
				return carseries;
			}else{
				return null;
			}
		}
		//編輯
		@Override
		public Boolean updateCarseries(SecUser LoginUser, String code, String brandCode, String localName, String type) {
				Carseries carseries=null;
			try {
				carseries=carseriesDao.findByCode(code);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			carseries.initUpdate(LoginUser);
			carseries.setLocalName(localName);
			carseries.setType(type);
			try {
				carseriesDao.update(carseries);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		
		@Override
		public Carseries getCarseries(Long id) {
			Carseries list=null;
			try {
				list=carseriesDao.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
}