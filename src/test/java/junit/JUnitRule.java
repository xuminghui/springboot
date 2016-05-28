package junit;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

public class JUnitRule {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public TestLogger logger = new TestLogger();//自定义的rule
	@Rule
	public ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			System.out.println("before");
		};

		@Override
		protected void after() {
			// 释放资源
			System.out.println("after");
		};
	};

	@ClassRule
	public static ExternalResource resourceForClassRule = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			System.out.println("classRuleBefore");
		};

		@Override
		protected void after() {
			System.out.println("classRuleAfter");
		};
	};
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void testTemporary() throws IOException {
		File file = folder.newFile("test");

	}

	@Test
	public void testExternalResource() {

	}

	@Test
	public void testErrorCollector() {
		try {
			throw new Exception();
		} catch (Exception e) {
			collector.addError(e);
		}
	}

	

	@Test
	public void checkOutMyLogger() {
		final Logger log = logger.getLogger();
		log.warning("Your test is showing!");
	}
}
