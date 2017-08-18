package org.iii.module.setting.car.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.car.entity.Brand;

/**
 * @author Hansen
 * 
 */
public interface BrandService {
	
	public List<LabelValueModel> getAllBrand();

	public List<Brand> queryByLike(String code, String localName,String engName,String descript);

	public Boolean createBrand(SecUser loginUser,String code,String localName,String engName,String descript);
	
	public Boolean updateBrand(SecUser loginUser,String code,String localName,String engName,String descript);
	
	public Boolean deleteBrand(Long Id);
	
	public Brand getBrandCode(String Code);
	
	public Brand getBrandId(Long Id);

	public Brand getBrandName(String brandCode);
	
}