package org.iii.module.commodity.rate.service;

import java.util.List;

import org.iii.module.commodity.rate.entity.Rate32;

/**
 * @author kuso
 *
 */
public interface Rate32Service {
	public List<Rate32> queryRate32(Long cartypeID,Integer caseMoney);

	public Rate32 queryRate(Long cartypeID,Integer caseMoney);
}