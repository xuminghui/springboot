package junit;

import org.junit.Test;
import org.junit.runner.JUnitCore;
@Person(age=123,name="123")
public class JUnitAnnotation {
	@Test
	public void testAnnotation(){
		Person person=JUnitAnnotation.class.getAnnotation(Person.class);
		System.out.println(person.age());
	}
}
