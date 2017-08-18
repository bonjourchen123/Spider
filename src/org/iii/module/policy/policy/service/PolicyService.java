package org.iii.module.policy.policy.service;

import java.util.List;

import org.iii.core.security.entity.SecUser;
import org.iii.module.policy.policy.entity.Policy;
import org.iii.module.policy.policy.entity.PolicyItem;

/**
 * @author bonjour
 *
 */
public interface PolicyService {
	public List<Policy> queryByLike(String code,String carNo,String applicantName,String insurantName,String officeID);
	
	public List<Policy> queryVerifyByLike(String code,String carNo,String applicantName,String insurantName,String officeID);

	public Policy queryPolicy(Long ID);
	
	public void verifyPolicy(SecUser loginUser,Long policyID);

	public void deletePolicy(Long policyID);
	
	public Policy createPolicy(SecUser loginUser,Policy policy);

	public Boolean createPolicyItem(SecUser loginUser,PolicyItem policyItem);
}