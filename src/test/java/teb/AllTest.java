package teb;

import org.junit.BeforeClass;
import org.junit.Test;

public class AllTest {
	@BeforeClass
	public static void initSystemEnv() {
		System.setProperty("out", "os");
		System.setProperty("wtimes", "1");
		System.setProperty("ntimes", "1");
		System.setProperty("buf", "false");
	}

	@Test
	public void testStringBuilder() throws Exception {
		new StringBuilder().run();
	}

	@Test
	public void testFreeMarker() throws Exception {
		new FreeMarker().run();
	}

	@Test
	public void testVelocity() throws Exception {
		new Velocity().run();
	}

	@Test
	public void testBeetl() throws Exception {
		new Beetl().run();
	}

	@Test
	public void testRythm() throws Exception {
		new Rythm().run();
	}

	@Test
	public void testJamon() throws Exception {
		new Jamon().run();
	}

	@Test
	public void testHttl() throws Exception {
		new Httl().run();
	}

	@Test
	public void testJMTE() throws Exception {
		new JMTE().run();
	}

	@Test
	public void testMustacheBenchmark() throws Exception {
		new MustacheBenchmark().run();
	}

	@Test
	public void testJMustacheBenchmark() throws Exception {
		new JMustacheBenchmark().run();
	}

	@Test
	public void testHandlebarsBenchmark() throws Exception {
		new HandlebarsBenchmark().run();
	}

	@Test
	public void testThymeleaf() throws Exception {
		new Thymeleaf().run();
	}
}