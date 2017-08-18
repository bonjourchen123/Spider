package org.iii.module.setting.car.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.dao.BrandDao;
import org.iii.module.setting.car.dao.CarmodelDao;
import org.iii.module.setting.car.dao.CarseriesDao;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.entity.Carmodel;
import org.iii.module.setting.car.entity.Carseries;
import org.iii.module.setting.car.service.CarmodelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author ant
 * @collaborator Hansen
 */
@Service
public class CarmodelServiceImpl implements CarmodelService{
	@Autowired
	private CarmodelDao carmodelDao;
	
	@Autowired
	private CarseriesDao carseriesDao;
	
	@Autowired
	private BrandDao brandDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllModel() {
		List<LabelValueModel> list = Lists.newArrayList();
		List<Carmodel> carmodels=carmodelDao.findAll();
		list = LabelValueModel.asList(carmodels, new Function<Carmodel,LabelValueModel>(){
			@Override
			public LabelValueModel apply(Carmodel carmodel) {
				return LabelValueModel.getLabelValueModel(carmodel.getLocalName(), carmodel.getId().toString());
			}
		});
		return list;
	}

	//查詢車系及廠牌名稱(英文代碼也可查)，因為是一次分頁查全部太慢才這樣寫
	@Override
	public List<Carmodel> queryByLike(String code, String brandCode, String seriesCode, String localName) {
		List<Carmodel> carmodels= carmodelDao.findByLike(code, brandCode, seriesCode, localName);
		List<Brand> brands=brandDao.findAll();
		for(Carmodel carmodel:carmodels){
			try {
				carmodel.setCarseriesLocalName(carseriesDao.findByPk(carmodel.getCarseriesID()).getLocalName());	
				for(Brand brand:brands){
					if(brand.getCode().equalsIgnoreCase(carmodel.getCode().substring(0, 2))){
						carmodel.setBrandLocalName(brand.getLocalName());
						break;
					}
				}
			} catch (Exception e) {

			}
		}
		return carmodels;
	}
	
	@Override
	public Carmodel queryByCode(String code){
		Carmodel carmodel=carmodelDao.findByCode(code);
		try {
			carmodel.setCarmodelLocalName(carmodel.getLocalName());
			Carseries carseries = carseriesDao.findByPk(carmodel.getCarseriesID());
			carmodel.setCarseriesLocalName(carseries.getLocalName());
			carmodel.setBrandLocalName(brandDao.findByPk(carseries.getBrandID()).getLocalName());
		} catch (Exception e) {
			return null;
		}
		return carmodel;
	}
	
	@Override
	public Carmodel queryByID(Long ID){
		try {
			return carmodelDao.findByPk(ID);
		} catch (Exception e) {
			return null;
		}
	}
	//新增
	@Override
	public Boolean createCarModel(SecUser loginUser, String code,String brandCode, String seriesCode, String localName, Integer cc) {
		Carmodel carmodel=new Carmodel();
		carmodel.initInsert(loginUser);
		carmodel.setCode(brandCode+seriesCode+code);
		carmodel.setLocalName(localName);
		carmodel.setCc(cc);
		carmodel.setCarseriesID(carseriesDao.findByCodeAndBrandID(seriesCode, brandDao.findByCode(brandCode).getId()).getId());
		try {
			carmodelDao.save(carmodel);
			return true;
		} catch (Exception e) {
			return false;
			}
	}
	
	//修改
	@Override
	public Boolean updateCarModel(SecUser loginUser,Long id ,String code,String brandCode, String seriesCode, String localName, Integer cc) {
		try {
			Carmodel carmodel = carmodelDao.findByPk(id);
			carmodel.initUpdate(loginUser);
			carmodel.setCode(brandCode+seriesCode+code);
			carmodel.setLocalName(localName);
			carmodel.setCc(cc);
			carmodelDao.update(carmodel);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
	
	
	//刪除
	@Override
	public Boolean deleteCarModel(Long Id) {
		try {
			carmodelDao.deleteByPk(Id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//抓id值
	@Override
	public Carmodel getCarModelId(Long Id) {
		Carmodel list = null;
		try {
			list = carmodelDao.findByPk(Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}


	

	

