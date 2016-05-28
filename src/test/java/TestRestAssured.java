import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith; 
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import afm.Caching;
@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot的配置信息(Caching中的注解就是配置信息)
@SpringApplicationConfiguration(classes = Caching.class)
//tell Spring that a WebApplicationContext should be loaded for the test
@WebAppConfiguration
//集成测试，非单元测试，需要完整的项目启动
@IntegrationTest("server.port:0")
public class TestRestAssured {
	@Before
	public void setup(){
		//RestAssured.port = 8080;
	}
	@Test
	public void test(){
		//expect().statusCode(200).get("/accounts/all");
	}
}
