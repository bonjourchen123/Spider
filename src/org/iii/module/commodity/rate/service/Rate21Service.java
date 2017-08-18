package org.iii.module.commodity.rate.service;

import java.util.List;

import org.iii.module.commodity.rate.entity.Rate21;

/**
 * @author bonjour
 *
 */
public interface Rate21Service {
	public List<Rate21> queryRate21(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats);

	public Rate21 queryRate(Long cartypeID,String gender,Integer age,Double tonnage,Integer seats);
}