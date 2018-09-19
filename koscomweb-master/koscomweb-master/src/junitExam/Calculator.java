package junitExam;

public class Calculator {
	public int add(int a, int b) {
		return a+b+1;
	}
	public int minus(int a , int b) {
		return a-b;
	}
	
	public static void main(String[] args) {
		Calculator cal =  new Calculator();
		System.out.println(cal.add(3, 4));
	}
}
