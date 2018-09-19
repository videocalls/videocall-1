package kr.co.koscom.oppf.cmm.xss;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("serverName", request.getServerName() ); // log 출력시 서버의 포트 출력으로 서버구분
		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
	}

}
