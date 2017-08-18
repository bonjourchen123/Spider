package org.iii.module.setting.car.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.entity.Brand;
import org.iii.module.setting.car.entity.Carseries;

public interface CarseriesService {
	public List<LabelValueModel> getAllCarseries();

	public List<Carseries> queryByLike(String code, String brandCode, String localName, String type);

	public Boolean createCarseries(SecUser LoginUser,String code, String brandCode, String localName, String type);
	
	public Boolean updateCarseries(SecUser LoginUser,String code, String brandCode, String localName, String type);
	
	public Carseries getCarseries(Long id);
	
	public Boolean deleteCarseries(Long ID);
	
	public Brand getBrand(String brandCode);
	
	public Carseries getCarseries(String code,String brandCode);
}