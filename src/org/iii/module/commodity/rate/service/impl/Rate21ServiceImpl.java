package org.iii.module.commodity.rate.service.impl;

import java.util.List;

import org.iii.module.commodity.rate.dao.Rate21Dao;
import org.iii.module.commodity.rate.entity.Rate21;
import org.iii.module.commodity.rate.service.Rate21Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuso
 *
 */
@Service
public class Rate21ServiceImpl implements Rate21Service {
	@Autowired
	private Rate21Dao rate21Dao;
	
	@Override
	public List<Rate21> queryRate21(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats) {
		return rate21Dao.findRates(cartypeID, gender, age, tonnage, seats);
	}
	
	@Override
	public Rate21 queryRate(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats) {
		return rate21Dao.findRate(cartypeID,gender,age,tonnage,seats);
	}
}