package org.iii.core.security.service;

import java.util.List;
import org.iii.core.security.entity.SecUser;

/**
 * @author kuso
 *
 */
public interface UserRoleService {
	public List<String> getRoleCodes(SecUser user);
}
