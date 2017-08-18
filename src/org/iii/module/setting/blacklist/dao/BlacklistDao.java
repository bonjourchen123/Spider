package org.iii.module.setting.blacklist.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.module.setting.blacklist.entity.Blacklist;
import org.springframework.stereotype.Repository;

/**
 * @author Hansen
 * 
 */
@Repository
public class BlacklistDao extends GenericDao<Blacklist, Long> {

	public Blacklist findByNumber(String number) {
		Criteria crit = getSession().createCriteria(Blacklist.class);
		crit.add(Restrictions.eq("number", number));
		Blacklist blacklist = (Blacklist) crit.uniqueResult();
		return blacklist;
	}

	public Blacklist findByType(String type) {
		Criteria crit = getSession().createCriteria(Blacklist.class);
		crit.add(Restrictions.eq("type", type));
		Blacklist blacklist = (Blacklist) crit.uniqueResult();
		return blacklist;

	}

	public Blacklist findByReason(String reason) {
		Criteria crit = getSession().createCriteria(Blacklist.class);
		crit.add(Restrictions.eq("reason", reason));
		Blacklist blacklist = (Blacklist) crit.uniqueResult();
		return blacklist;
	}

	@SuppressWarnings("unchecked")
	public List<Blacklist> findAll() {
		Criteria crit = getSession().createCriteria(Blacklist.class);
		List<Blacklist> blacklists = crit.list();
		return blacklists;
	}

	// 模糊查詢
	@SuppressWarnings("unchecked")
	public List<Blacklist> findByLike(String number, String type, String reason) {
		Criteria crit = getSession().createCriteria(Blacklist.class);
		if (number != null && number.length() != 0) {
			crit.add(Restrictions.like("number", "%" + number + "%"));
		}
		if (reason != null && reason.length() != 0) {
			crit.add(Restrictions.like("reason", "%" + reason + "%"));

		}
		if (!("0".equals(type))) {
			crit.add(Restrictions.or(Restrictions.eq("type", type),
									 Restrictions.isNull("type")));
		}

		List<Blacklist> blacklists = crit.list();
		return blacklists;
	}
}
