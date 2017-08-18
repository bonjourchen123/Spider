package org.iii.module.setting.car.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.module.setting.car.entity.Cartype;

/**
 * @author kuso
 *
 */
public interface CartypeService {
	public List<LabelValueModel> getAllCarType();
	
	public List<Cartype> queryByLike(String code,String localName,String purpose,String type,Integer cc);

	public Cartype queryType(String carmodelCode,String purpose);
}