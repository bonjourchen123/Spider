package org.iii.module.setting.car.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.entity.Carmodel;

/**
 * @author ant
 * @collaborator Hansen
 */
public interface CarmodelService {
	public List<LabelValueModel> getAllModel();
	
	public List<Carmodel> queryByLike(String code, String brandCode, String seriesCode, String localName);
	
	public Carmodel queryByCode(String code);
	
	public Carmodel queryByID(Long ID);
	
	public Boolean createCarModel(SecUser loginUser,String code,String brandCode,String seriesCode,String localName,Integer cc );
	
	public Boolean updateCarModel(SecUser loginUser,Long id ,String code,String brandCode,String seriesCode,String localName,Integer cc );
	
	public Boolean deleteCarModel(Long Id);
	
	public Carmodel getCarModelId(Long Id);
}