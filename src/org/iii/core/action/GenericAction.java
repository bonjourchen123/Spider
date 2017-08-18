/**
 * 
 */
package org.iii.core.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.iii.core.entity.GenericEntity;
import org.iii.core.model.GridModel;
import org.iii.core.security.entity.SecUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;

/**
 * @author Shock
 *
 */
@SuppressWarnings("serial")
public abstract class GenericAction<T extends GenericEntity> extends ActionSupport implements ValidationAware {
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public GenericAction() {
		try {
			entity = getGenericClass().newInstance();
		} catch (Exception e) {
			log.warn("faild to instance the entity because {}", e.getMessage());
		}
	}
	
	//response丟出json
	protected void jsonOut(Object obj){
		try {
			ObjectMapper mapper = new ObjectMapper();
			JodaModule module = new JodaModule();
			mapper.registerModule(module);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String jsonString = mapper.writeValueAsString(obj);
			HttpServletResponse response = getResponse();
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setHeader("Pragma", "No-cache");
			response.setContentLength(jsonString.getBytes("UTF-8").length);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(jsonString);
			response.getWriter().flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//設定分頁資訊
	protected void jqGrid(List<T> list){
		GridModel<T> grid = new GridModel<T>();
		grid.setRows(list);
		long total = BigDecimal.valueOf(list.size()).divide(BigDecimal.valueOf(10), 0, RoundingMode.CEILING).longValue();
		grid.setTotal(total);
		grid.setRecords(list.size());
		jsonOut(grid);
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() throws InstantiationException, IllegalAccessException {
		Type type = this.getClass();
		while (type instanceof ParameterizedType || (GenericAction.class.isAssignableFrom((Class<T>) type))) {
			if (type instanceof ParameterizedType) {
				return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
			}
			type = ((Class<T>) type).getGenericSuperclass();
		}
		throw new InstantiationException(this.getClass() + " doesn't indicate the generic type.");
	}
	
	/**
	 * 取得登入者
	 * @return
	 */
	protected SecUser getLoginUser() {
		return (SecUser) getSession().get(LOGIN);
	}
	
	/**
	 * Form entity
	 */
	private T entity;
	
	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	protected Map<String, Object> getSession() {
		return (Map<String, Object>) ActionContext.getContext().getSession();
	}

	protected HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	protected HttpSession getHttpSession(boolean create) {
		return getRequest().getSession(create);
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	@Override
	public void validate() {
		Map<String, List<String>> map = getFieldErrors();
		log.debug("validation fields={}", map);
		if (!map.isEmpty()) {
			for (String key : map.keySet()) {
				for (String msg : map.get(key)) {
					addActionError(msg);
				}
			}
		}
	}
}
