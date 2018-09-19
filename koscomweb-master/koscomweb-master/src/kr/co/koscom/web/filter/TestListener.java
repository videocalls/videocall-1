package kr.co.koscom.web.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class TestListener
 *
 */
@WebListener
public class TestListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TestListener() {
       System.out.println("생성");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        System.out.println("contextDestroyed 실행");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	 System.out.println("contextInitialized 실행");
    }
	
}
