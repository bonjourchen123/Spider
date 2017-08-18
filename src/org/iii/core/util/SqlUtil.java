package org.iii.core.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;

public class SqlUtil {

	private static final String br = "\r\n";
	private static final String tab = "\t";

	public static String toPrettySQL(StringBuffer sb) {
		return toPrettySQL(sb.toString());
	}

	public static String toPrettySQL(StringBuilder sb) {
		return toPrettySQL(sb.toString());
	}

	public static String toPrettySQL(String sql) {
		sql = StringUtils.replaceOnce(sql, "select ", "select" + br + tab);
		sql = StringUtils.replaceOnce(sql, "from", br + "from");
		sql = StringUtils.replace(sql, "join", br + "join");
		sql = StringUtils.replace(sql, " on ", br + tab + "on ");
		sql = StringUtils.replace(sql, "where", br + "where");
		return sql;
	}

	public static String toSql(Criteria criteria) {
		try {
			CriteriaImpl c = (CriteriaImpl) criteria;
			SessionImpl s = (SessionImpl) c.getSession();
			SessionFactoryImplementor factory = (SessionFactoryImplementor) s.getSessionFactory();
			String[] implementors = factory.getImplementors(c.getEntityOrClassName());
			CriteriaLoader loader = new CriteriaLoader((OuterJoinLoadable) factory.getEntityPersister(implementors[0]),
					factory, c, implementors[0], s.getLoadQueryInfluencers());
			Field f = OuterJoinLoader.class.getDeclaredField("sql");
			f.setAccessible(true);
			return (String) f.get(loader);
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}
}
