package org.iii.core.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kuso
 *
 */
public class AcRoleFilter implements Filter{
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		req.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		if(session.getAttribute("AC")==null){
			response.sendRedirect(request.getContextPath()+"/error/noCompetence");
		}else{
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {

	}
}
