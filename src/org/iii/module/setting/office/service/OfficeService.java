package org.iii.module.setting.office.service;

import java.util.List;

import org.iii.core.model.LabelValueModel;
import org.iii.core.security.entity.SecUser;
import org.iii.module.setting.office.entity.Office;

/**
 * @author kuso
 *
 */
public interface OfficeService {
	public List<LabelValueModel> getAllOffice();
	
	public List<Office> queryByLike(String code, String localName, String engName);

	public Office getOfficeCode(String code);

	public Office getOfficeLocalName(String localName);

	public Office getOfficeEngName(String engName);

	public boolean createOffice(SecUser loginUser, String code,	String localName, String engName);

	public Office getOfficeId(Long id);

	boolean updateOffice(SecUser loginUser, String code, String localName,	String engName);

	boolean deleteOffice(Long id);
}