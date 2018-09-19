package kr.co.koscom.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/ttt")
public class TestFilter1 implements Filter {

   
    public TestFilter1() {
        System.out.println("TestFilter1 생성!!");
    }


	public void destroy() {
		System.out.println("destroy 호출!!");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("서블릿 요청 전 처리!! ");
		chain.doFilter(request, response);
		System.out.println("서블릿 요청 후 처리!! ");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init 호출!!");
	}

}
