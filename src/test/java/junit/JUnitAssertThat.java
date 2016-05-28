package junit;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitAssertThat {
	@Test
	
	public void testAssertArrayEquals() {
		byte[] expected = "trial".getBytes();
		byte[] actual = "trial".getBytes();
		assertArrayEquals("failure - byte arrays not same", expected, actual);
	}

	@Test
	
	public void testAssertEquals() {
		assertEquals("failure - strings are not equal", "text", "text");
	}

	@Test
	public void testAssertFalse() {
		assertFalse("failure - should be false", false);
	}

	@Test
	public void testAssertNotNull() {
		assertNotNull("should not be null", new Object());
	}

	@Test
	public void testAssertNotSame() {
		assertNotSame("should not be same Object", new Object(), new Object());
	}

	@Test
	public void testAssertNull() {
		assertNull("should be null", null);
	}

	@Test
	public void testAssertSame() {
		Integer aNumber = Integer.valueOf(768);
		assertSame("should be same", aNumber, aNumber);
	}

	// JUnit Matchers assertThat
	@Test
	public void testAssertThatBothContainsString() {
		assertThat("albumen", both(containsString("a")).and(containsString("b")));
	}

	@Test
	public void testAssertThatHasItems() {
		assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
	}

	@Test
	public void testAssertThatEveryItemContainsString() {
		assertThat(Arrays.asList(new String[] { "fua", "baa", "net" }), hasItem(containsString("n")));
		assertThat(Arrays.asList(new String[] { "fun", "ban", "aen" }), everyItem(containsString("n")));
		assertThat(Arrays.asList(new String[] { "fua", "baa", "aet" }), hasItem("aet"));

	}

	// Core Hamcrest Matchers with assertThat
	@Test
	public void testAssertThatHamcrestCoreMatchers() {
		assertThat("good", allOf(equalTo("good"), startsWith("good"), endsWith("good")));
		assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
		assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
		assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
		assertThat(new Object(), not(sameInstance(new Object())));
		assertThat("myValue", allOf(startsWith("my"), containsString("Val")));
		assertThat(new Object(), any(Object.class));
		// assertThat("good", CoreMatchers.both);
		// both and
		assertThat("fab", both(containsString("a")).and(containsString("b")));
		// either or
		assertThat("fab", either(containsString("a")).or(containsString("a")));
		assertThat("fab", instanceOf(String.class));
		/* CoreMatchers.; */
		assertThat(null, CoreMatchers.nullValue());
		assertThat("1111", CoreMatchers.notNullValue(String.class));
	}

	@Test
	public void testAssertTrue() {
		assertTrue("failure - should be true", true);
	}
	@Test
	public void testMatches(){
		User temp=new User(null,"111");
		User user=new User(temp,"222");
		assertThat(user,Matchers.hasProperty("userName",Matchers.equalTo("222")));
		assertThat(user,Matchers.hasProperty("user",Matchers.hasProperty("userName",Matchers.equalTo("111"))));
		assertThat(50,Matchers.greaterThan(20));
		assertThat(30,Matchers.lessThan(40));
		assertThat(new Integer[1],Matchers.arrayWithSize(1));
		assertThat(new Integer[]{1,2,3}, Matchers.is(Matchers.array(equalTo(1), equalTo(2), equalTo(3))));
		assertThat(new Integer[]{1,2,3}, Matchers.not(Matchers.array(equalTo(2), equalTo(2), equalTo(3))));
		assertThat(new String[]{"foo", "bar"}, Matchers.arrayContainingInAnyOrder("bar", "foo"));
		assertThat(new String[]{"foo", "bar"},Matchers.arrayContaining("foo","bar"));
		assertThat(new String[]{"a","b"},Matchers.not(Matchers.emptyArray()));
		assertThat(null,Matchers.isEmptyOrNullString());
		assertThat(null,not(Matchers.notNullValue()));
		
	}
	@Test
	public void testAssumeThat1(){
		Assume.assumeFalse(true);
		System.out.println("不会显示这句话");
		
	}
	@Test
	public void testAssumeThat2(){
		Assume.assumeFalse(false);
		System.out.println("显示这句话");
		
	}
	@Ignore("this is ignored")
	@Test
	public void testAssumeThat3(){
		Assume.assumeThat(File.separatorChar, Matchers.is('/'));
		
	}
	
	@Test
	@Category(FastTests.class)
	public void fastTest(){
		System.out.println("fast tests");
	}
	@Test
	@Category(SlowTests.class)
	public void slowTest(){
		System.out.println("slow tests");
	}
	
}
