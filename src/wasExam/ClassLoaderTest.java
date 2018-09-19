package wasExam;

public class ClassLoaderTest {

	public static void main(String[] args) throws Exception{
		Class clazz = Class.forName("javaexam.miniwas.HelloServlet");
		Object obj = clazz.newInstance();
		System.out.println(obj.getClass().getName());

	}

}
