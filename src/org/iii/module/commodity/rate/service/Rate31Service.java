package org.iii.module.commodity.rate.service;

import java.util.List;

import org.iii.module.commodity.rate.entity.Rate31;

/**
 * @author kuso
 *
 */
public interface Rate31Service {
	public List<Rate31> queryRate31(Long cartypeID,Integer caseMoney);

	public Rate31 queryRate(Long cartypeID,Integer caseMoney);
}