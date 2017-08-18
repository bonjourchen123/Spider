package org.iii.module.commodity.rate.service.impl;

import java.util.List;

import org.iii.module.commodity.rate.dao.Rate31Dao;
import org.iii.module.commodity.rate.entity.Rate31;
import org.iii.module.commodity.rate.service.Rate31Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bonjour
 *
 */
@Service
public class Rate31ServiceImpl implements Rate31Service {
	@Autowired
	private Rate31Dao rate31Dao;
	
	@Override
	public List<Rate31> queryRate31(Long cartypeID,Integer caseMoney) {
		return rate31Dao.findRates(cartypeID,caseMoney);
	}
	
	@Override
	public Rate31 queryRate(Long cartypeID,Integer caseMoney) {
		return rate31Dao.findRate(cartypeID,caseMoney);
	}
}