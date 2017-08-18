package org.iii.module.setting.car.service.impl;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.module.setting.car.dao.CarmodelDao;
import org.iii.module.setting.car.dao.CarseriesDao;
import org.iii.module.setting.car.dao.CartypeDao;
import org.iii.module.setting.car.entity.Carmodel;
import org.iii.module.setting.car.entity.Carseries;
import org.iii.module.setting.car.entity.Cartype;
import org.iii.module.setting.car.service.CartypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author kuso
 *
 */
@Service
public class CartypeServiceImpl implements CartypeService {
	@Autowired
	private CartypeDao cartypeDao;
	
	@Autowired
	private CarmodelDao carmodelDao;
	
	@Autowired
	private CarseriesDao carseriesDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabelValueModel> getAllCarType() {
		List<LabelValueModel> list = Lists.newArrayList();
		List<Cartype> cartypes=cartypeDao.findAll();
		list = LabelValueModel.asList(cartypes, new Function<Cartype,LabelValueModel>(){
			@Override
			public LabelValueModel apply(Cartype cartype) {
				return LabelValueModel.getLabelValueModel(cartype.getLocalName(), cartype.getId().toString());
			}
		});
		return list;
	}
	
	@Override
	public List<Cartype> queryByLike(String code,String localName,String purpose,String type,Integer cc){
		return cartypeDao.findByLike(code, localName, purpose, type, cc);
	}
	
	@Override
	public Cartype queryType(String carmodelCode,String purpose){
		Carmodel carmodel=carmodelDao.findByCode(carmodelCode);
		try {
			Carseries carseries = carseriesDao.findByPk(carmodel.getCarseriesID());
			Cartype cartype= cartypeDao.findCarType(purpose, carseries.getType(), carmodel.getCc());
			cartype.setCartypeLocalName(cartype.getLocalName());
			return cartype;
		} catch (Exception e) {
			return null;
		}
	}
}
