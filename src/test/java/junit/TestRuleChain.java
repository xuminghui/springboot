package junit;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.RuleChain;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;



public class TestRuleChain {
	@Rule
	public TestRule chain = RuleChain.outerRule(new TemporaryFolder()).around(ExpectedException.none())
			.around(new Timeout(20, TimeUnit.SECONDS));

	@Test
	public void example() {
		Assert.assertTrue(true);
	}
}
