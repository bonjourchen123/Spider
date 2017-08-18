package org.iii.module.commodity.rate.service.impl;

import java.util.List;

import org.iii.module.commodity.rate.dao.Rate32Dao;
import org.iii.module.commodity.rate.entity.Rate32;
import org.iii.module.commodity.rate.service.Rate32Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kuso
 *
 */
@Service
public class Rate32ServiceImpl implements Rate32Service {
	@Autowired
	private Rate32Dao rate32Dao;
	
	@Override
	public List<Rate32> queryRate32(Long cartypeID,Integer caseMoney){
		return rate32Dao.findRates(cartypeID,caseMoney);
	}
	
	@Override
	public Rate32 queryRate(Long cartypeID,Integer caseMoney){
		return rate32Dao.findRate(cartypeID,caseMoney);
	}
}