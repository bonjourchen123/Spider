package org.iii.module.commodity.coef.service.impl;

import org.iii.module.commodity.coef.dao.HumanCoefDao;
import org.iii.module.commodity.coef.entity.HumanCoef;
import org.iii.module.commodity.coef.service.HumanCoefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuso
 *
 */
@Service
public class HumanCoefServiceImpl implements HumanCoefService {
	@Autowired
	private HumanCoefDao humanCoefDao;
	
	@Override
	public HumanCoef queryHumanCoef(String gender,Integer age){
		return humanCoefDao.findByGenderAndAge(gender, age);
	}
}
