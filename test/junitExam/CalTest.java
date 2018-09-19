package junitExam;

import junit.framework.TestCase;

public class CalTest extends TestCase {
	Calculator cal = null;
	public void testAdd() {
		System.out.println("test add 실행");
		int result = cal.add(3, 4);
		assertEquals(7, result);
		assertEquals(10, cal.add(5, 5));
	}
	public void testMinus() {
		System.out.println("test minus 실행");
		assertEquals(3, cal.minus(7, 4));
	}
	
	@Override
	protected void setUp() throws Exception {
		System.out.println("setup 실행");
		cal = new Calculator();
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("tearDown 실행");
		cal = null;
	}
	
}
