package com.vimisky.dms.controller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CORSFilter extends OncePerRequestFilter{

	private static String acao;
	private static String acah;
	private static String acam;
	private static String acma;
	private static String acac;
	private static String aceh;
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		// TODO Auto-generated method stub
		super.initFilterBean();
		FilterConfig filterConfig = getFilterConfig();
		if(acao == null)
			acao = filterConfig.getInitParameter("responseAccessControlAllowOrigin");
		if(acah == null)
			acah = filterConfig.getInitParameter("responseAccessControlAllowHeaders");
		if(acam == null)
			acam = filterConfig.getInitParameter("responseAccessControlAllowMethods");
		if(acma == null)
			acma = filterConfig.getInitParameter("responseAccessControlMaxAge");
		if(acac == null)
			acac = filterConfig.getInitParameter("responseAccessControlAllowCredentials");
		if(aceh == null)
			aceh = filterConfig.getInitParameter("responseAccessControlExposeHeaders");
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Origin", acao);
            response.addHeader("Access-Control-Allow-Methods", acam);
//            response.addHeader("Access-Control-Allow-Headers", acah);
            response.addHeader("Access-Control-Max-Age", acma);//30 min
//            response.addHeader("Access-Control-Allow-Credentials",acac);
//            response.addHeader("Access-Control-Expose-Headers",aceh);
        }
        filterChain.doFilter(request, response);
	}


}
