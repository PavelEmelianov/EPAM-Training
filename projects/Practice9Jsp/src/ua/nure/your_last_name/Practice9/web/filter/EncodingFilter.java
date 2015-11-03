package ua.nure.your_last_name.Practice9.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String encoding;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;		
		if (httpRequest.getCharacterEncoding() == null) {
			httpRequest.setCharacterEncoding(encoding);
		}
		
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		httpResponse.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}

	public void destroy() {
		// no op
	}

}
