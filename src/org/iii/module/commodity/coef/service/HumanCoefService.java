package org.iii.module.commodity.coef.service;

import org.iii.module.commodity.coef.entity.HumanCoef;

/**
 * @author kuso
 *
 */
public interface HumanCoefService {
	public HumanCoef queryHumanCoef(String gender,Integer age);
}