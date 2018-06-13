package testSpring.bean;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		XmlBeanFactory bf = new XmlBeanFactory(new ClassPathResource("/CloudNote/src/test/resources/xml/test.xml"));
		MyTestBean  bean = (MyTestBean)bf.getBean("testBean");
		String str = bean.getStr();
		System.out.println(str);
	}
}
