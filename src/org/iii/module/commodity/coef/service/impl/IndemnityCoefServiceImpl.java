package org.iii.module.commodity.coef.service.impl;

import org.iii.module.commodity.coef.dao.IndemnityCoefDao;
import org.iii.module.commodity.coef.entity.IndemnityCoef;
import org.iii.module.commodity.coef.service.IndemnityCoefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuso
 *
 */
@Service
public class IndemnityCoefServiceImpl implements IndemnityCoefService {
	@Autowired
	private IndemnityCoefDao indemnityCoefDao;
	
	@Override
	public IndemnityCoef queryIndemnityCoef(Integer level){
		return indemnityCoefDao.findByLevel(level);
	}
}
