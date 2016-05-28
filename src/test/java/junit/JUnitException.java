package junit;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.mockito.Matchers;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitException {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected=Exception.class)
	public void testException() throws Exception {
		throw new Exception();
	}
	
	@Test
	public void testExceptionWithRule() throws Exception{
		thrown.expect(Exception.class);
		thrown.expectMessage("exception");//包含即可，不需要完全相等
		thrown.expectMessage(Matchers.contains("excep"));
		throw new Exception("exception12313123");
	}


}
