import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import afm.Caching;
import afm.controllers.AccountController;
import afm.entities.Account;
import afm.services.AccountService;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
// 指定SpringBoot的配置信息(Caching中的注解就是配置信息)
@SpringApplicationConfiguration(classes = Caching.class)
// tell Spring that a WebApplicationContext should be loaded for the test
@WebAppConfiguration
@FixMethodOrder(MethodSorters.DEFAULT)//执行顺序
public class TestSpringMvcMock {
	private MockMvc mockMvc;
	@Autowired
	private AccountController controller;
	@Autowired
	private AccountService accountService;
	final static int accountId=1;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	/**
	 * 测试add
	 * 1、请求值@RequestBody
	 * 2、响应值@ResponseBody
	 * 3、缓存之所以失效，是因为AOP不起作用，是在代理内部调用原来的API导致的
	 * @throws Exception
	 */
	@Test
	public void testAddAccount() throws Exception{
		int accountId=1;
		mockMvc.perform(
				post("/accounts/add")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(toJson(accountId)).accept(MediaType.APPLICATION_JSON))
				// 验证状态码
				.andExpect(status().isOk())
				// 验证响应内容
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(jsonPath("$.id").value(1)); // 输出MvcResult到控制台
	}
	@Test
	public void testGetAccount() throws Exception{
		int accountId=1;
		mockMvc.perform(
				get("/accounts/id/{id}",accountId).accept(MediaType.APPLICATION_JSON))
				// 验证状态码
				.andExpect(status().isOk())
				// 验证响应内容
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(jsonPath("$.id").value(1)); // 输出MvcResult到控制台
	}
	@Test
	public void testCache(){
		//accountCache.testAccount("id");
		accountService.getAccount(1);
	}
	@Test
	public void testSimple() throws Exception {
		int accountId=1;
		// 测试普通控制器// 执行请求
		mockMvc.perform(
				get("/accounts/all")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(toJson(accountId)).accept(MediaType.APPLICATION_JSON))
				// 验证状态码
				.andExpect(status().isOk())
				// 验证响应内容
				.andExpect(
						content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(print()).andExpect(jsonPath("$.id").value(1)); // 输出MvcResult到控制台
	}

	private String toJson(int accountId) {
		Account account = new Account();
		account.setId(accountId);
		account.setFirstName("firstName");
		account.setLastName("lastName");
		//account.setDate(new Date());
		account.setEmail("xuminghui@gmail.com");
		account.setLevel(0);
		account.setPoints(0);
		Gson gson = new Gson();
		return gson.toJson(account);
	}
}
