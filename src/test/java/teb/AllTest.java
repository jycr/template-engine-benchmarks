package teb;

import org.junit.Test;

public class AllTest {
	@Test
	public void testStringBuilder() throws Exception {
		new StringBuilderBenchmark().test();
	}

	@Test
	public void testFreeMarker() throws Exception {
		new FreemarkerBenchmark().test();
	}

	@Test
	public void testVelocity() throws Exception {
		new VelocityBenchmark().test();
	}

	@Test
	public void testBeetl() throws Exception {
		new BeetlBenchmark().test();
	}

	@Test
	public void testRythm() throws Exception {
		new RythmBenchmark().test();
	}

	@Test
	public void testJamon() throws Exception {
		new JamonBenchmark().test();
	}

	@Test
	public void testHttl() throws Exception {
		new HttlBenchmark().test();
	}

	@Test
	public void testJMTE() throws Exception {
		new JmteBenchmark().test();
	}

	@Test
	public void testMustacheBenchmark() throws Exception {
		new MustacheBenchmark().test();
	}

	@Test
	public void testJMustacheBenchmark() throws Exception {
		new JMustacheBenchmark().test();
	}

	@Test
	public void testHandlebarsBenchmark() throws Exception {
		new HandlebarsBenchmark().test();
	}

	@Test
	public void testThymeleaf() throws Exception {
		new ThymeleafBenchmark().test();
	}
}