package junit;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)//继承自SUIT
@Suite.SuiteClasses({
	JUnitAssertThat.class
})
@IncludeCategory(SlowTests.class)
@ExcludeCategory(FastTests.class)
public class FeatureTestSuite {
  // the class remains empty,
  // used only as a holder for the above annotations
}

interface SlowTests{};
interface FastTests{};