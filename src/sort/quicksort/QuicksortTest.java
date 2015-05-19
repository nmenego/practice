package sort.quicksort;

import org.junit.Test;

public class QuicksortTest {

	@Test
	public void test() {
		String[] args = new String[] {"3", "7", "8", "5", "2", "1", "9", "5", "4"};
		Quicksort.main(args);
	}


	@Test
	public void test2() {
		String[] args = new String[] {"1", "9", "5", "4", "2", "3"};
		Quicksort.main(args);
	}

	@Test
	public void test3() {
		String[] args = new String[] {"1", "2", "3", "4"};
		Quicksort.main(args);
	}

	@Test
	public void test4() {
		String[] args = new String[] {"1", "-1", "1", "0"};
		Quicksort.main(args);
	}

	@Test
	public void test5() {
		String[] args = new String[] {"1", "1", "1", "1"};
		Quicksort.main(args);
	}
}
