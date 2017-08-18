package org.iii.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.iii.core.util.StringCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class GenericDao<T extends Serializable,PK extends Long>{
	// transient modifier tell java to exclude modified field from serialization process  
	// Logger is an logging object to forwarding logging message to relative object like IO,....
	protected final transient Logger log = LoggerFactory.getLogger(getClass());
	 
	private Class<T> entityClass;

	/**
	 * 取得TableName
	 * @return
	 */
	// Table is annotation type to specify the annotated table .
    // get annotation : Returns this element's annotation for the specified type 
	// if such an annotation is present, else null. 
	protected String getTabelName() {
		 
		Table table = entityClass.getAnnotation(Table.class);
		Assert.notNull(table, entityClass + " must has " + Table.class + " annotation!");
		return table.name();
	}

	@Autowired
	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericDao() {
		this.entityClass = null;
		Class c = getClass();
		//Type : interface --> 
		//include raw types, parameterized types, array types, type variables and primitive types.
		// 
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}

	public Session getSession() {
		Session session = getSessionFactory().getCurrentSession();
		return session;		
	}

	public T save(T model) throws Exception {
		log.debug("insert model: " + StringCommonUtil.toString(model));
		try {
			Serializable sysId = getSession().save(model);
			BeanUtils.setProperty(model, "id", sysId);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
		return model;
	}

	public void delete(T model) throws Exception {
		log.debug("delete model: " + StringCommonUtil.toString(model));
		try {
			getSession().delete(model);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}

	public void deleteByPk(Long pk) throws Exception {
		T model = findByPk(pk);
		log.debug(model.toString());
		delete(model);
	}

	
	public void update(T model) throws Exception {
		log.debug("update model: " + StringCommonUtil.toString(model));
		try {
			getSession().update(model);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	public void saveOrUpdate(T model) throws Exception {
		log.debug("insert or update model: " + StringCommonUtil.toString(model));
		try {
			getSession().saveOrUpdate(model);
		} catch (Exception e) {

			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T findByPk(Long pk) throws Exception {
		/**
		 * use get() is better than load() when you can't find it, it will
		 * return NULL
		 */
		try {
			return (T) getSession().byId(entityClass).load(pk);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}




	@SuppressWarnings("unchecked")	
	public List<T> createSQLQueryOfT(String sql, Object[] values, org.hibernate.type.Type[] types) throws Exception {
		try {
			return getSession().createSQLQuery(sql).addEntity(entityClass).setParameters(values, types).list();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	
	public List<?> createSQLQuery(String sql, Object[] values, org.hibernate.type.Type[] types) throws Exception {
		try {
			return getSession().createSQLQuery(sql).setParameters(values, types).list();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	
	public Object createSQLQueryUnique(String sql, Object[] values, org.hibernate.type.Type[] types) throws Exception {
		try {
			return getSession().createSQLQuery(sql).setParameters(values, types).uniqueResult();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T createSQLQueryUniqueOfT(String sql, Object[] values, org.hibernate.type.Type[] types) throws Exception {
		try {
			return (T)getSession().createSQLQuery(sql).addEntity(entityClass).setParameters(values, types).uniqueResult();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")	
	public List<T> createSQLQueryOfT(String sql) throws Exception {
		try {
			return getSession().createSQLQuery(sql).addEntity(entityClass).list();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	@SuppressWarnings("unchecked")	
	public T createSQLQueryUniqueOfT(String sql) throws Exception {
		try {
			return (T) getSession().createSQLQuery(sql).addEntity(entityClass).uniqueResult();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	
	public List<?> createSQLQuery(String sql) throws Exception {
		try {
			return getSession().createSQLQuery(sql).list();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
	}

	public Object createSQLQueryUnique(String sql) throws Exception {
		try {
			return getSession().createSQLQuery(sql).uniqueResult();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}


}
