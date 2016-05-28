package mockito;


import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class TestMockito {
	@InjectMocks //注入属性引用，来源是此Test的其他dao
	private MockitoService injectService;
	@Mock
	private MockitoService service;
	@Mock
	private Dao dao;
	@Mock
	private List<String> list;
	
	//必须结合verify使用，来捕获执行方法时候的参数，和期望值进行比较；
	//但是多次的Mock调用后，arg取得是最后一次的值
	@Captor 
	private ArgumentCaptor argCaptor; 
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testMockitoBase(){
		when(service.service()).thenReturn("fakeValue");
		System.out.println(service.service());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testMockitoException(){
		when(service.service()).thenThrow(new IllegalArgumentException("a"));
		System.out.println(service.service());
	}
	@Test
	public void testMockitoArgs(){
		when(service.serviceWithArgs(Matchers.anyString())).thenReturn("fakeValue");
		System.out.println(service.serviceWithArgs(Matchers.anyString()));
	}
	//Mock JDK或者第三方类
	@Test
	public void testMockitoExist(){
		when(list.get(0)).thenReturn("123");
		System.out.println(list.get(0));
		verify(list,times(1)).get(0);//验证该方法被调用几次
		verify(list,atLeast(1)).get(0);//验证该方法被调用几次
		verify(list,atLeastOnce()).get(0);//验证该方法被调用几次
		verify(list,only()).get(0);//验证该方法被调用几次
		verify(list,timeout(1000)).get(0);//并发情况下使用
	}
	@Test
	public void testDoAnswer(){
		Mockito.doAnswer(new Answer(){@Override
		public Object answer(InvocationOnMock invocation) throws Throwable {
			String arg= (String)invocation.getArguments()[0];
			System.out.println(arg);
			return null;
		}}).when(service).doService("123");
	}
	//确保方法调用不会返回NULL指针异常
	// RETURNS_DEEP_STUBS 这个标识Mock外层对象，内部的对象也会被设计成mock
	@Test
	public void testWithReturn(){
		List list=Mockito.mock(List.class,Mockito.RETURNS_SMART_NULLS);
		List listNull=Mockito.mock(List.class,Mockito.RETURNS_DEFAULTS);
		Assert.assertThat(listNull.get(0),org.hamcrest.Matchers.nullValue());
		System.out.println(list);
	}
	@Test
	public void testInjectMocks(){
		System.out.println(dao);
		System.out.println(injectService.getDao());
	}
	
	@Test
	public void whenNotUseCaptorAnnotation_thenCorrect() {
	  List<String> mockList = Mockito.mock(List.class);
	  ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);

	  mockList.add("one");
	  mockList.add("two");
	  Mockito.verify(mockList,Mockito.times(2)).add(arg.capture());

	  Assert.assertEquals("one", arg.getValue());
	}
	
	
	
	@Test  
	//legacy code
	public void spyTest2() {  
	      
	    List list = new LinkedList();  
	    List spy = Mockito.spy(list);  
	    
	    //optionally, you can stub out some methods:  
	    when(spy.size()).thenReturn(100);  
	    
	    //using the spy calls real methods  
	    spy.add("one");  
	    spy.add("two");  
	    
	    //prints "one" - the first element of a list  
	    System.out.println(spy.get(0));  
	    
	    //size() method was stubbed - 100 is printed  
	    System.out.println(spy.size());  
	    
	    //optionally, you can verify  
	    verify(spy).add("one");  
	    verify(spy).add("two");   
	}  
}
