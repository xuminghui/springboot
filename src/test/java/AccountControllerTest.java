
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import afm.Caching;
import afm.controllers.AccountController;
import afm.entities.Account;

import com.google.gson.Gson;
@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot的配置信息(Caching中的注解就是配置信息)
@SpringApplicationConfiguration(classes = Caching.class)
//tell Spring that a WebApplicationContext should be loaded for the test
@WebAppConfiguration
//集成测试，非单元测试，需要完整的项目启动
@IntegrationTest("server.port:0")
public class AccountControllerTest {
	private MockMvc mvc;
	@Autowired
	private AccountController controller;
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getAccount() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/accounts/id/1").accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk());
	//	MvcResult result=actions.andReturn();
			//	actions.andExpect(content().string(equalTo("Hello world")));
	}
	@Test
	public void testGetAccount() throws Exception {
		ResultActions actions = mvc.perform(MockMvcRequestBuilders
				.get("/accounts/id/1"));
		actions.andExpect(status().isOk());
//		actions.andExpect(content().string(equalTo("Hello world")));
	}
	@Test
	public void testInsertAccount() throws Exception {
		Account account=new Account();
		account.setId(1);
		account.setFirstName("firstName");
		account.setLastName("lastName");
		Gson gson = new Gson();        
		String json = gson.toJson(account);
		ResultActions actions = mvc.perform(MockMvcRequestBuilders
				.post("/accounts/add").accept(MediaType.APPLICATION_JSON)
				.content(json.getBytes()));
		actions.andExpect(status().isOk());
//		actions.andExpect(content().string(equalTo("Hello world")));
	}
}