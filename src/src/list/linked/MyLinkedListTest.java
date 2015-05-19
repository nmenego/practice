package src.list.linked;

import java.util.AbstractList;

import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void test() {
		AbstractList<String> list = new MyLinkedList<String>();
		list.add("h");
		list.add("e");
		list.add("l");
		list.add("o");
		list.add(",");
		list.add("w");
		list.add("r");
		list.add("l");
		list.add("d");
		list.add("!");

		System.out.println("list: " + list.toString() + ", size: "
				+ list.size());
		list.add(3, "L");
		list.add(7, "o");
		System.out.println("list: " + list.toString() + ", size: "
				+ list.size());

		System.out.println("[3]:" + list.get(3));
		System.out.println("[7]:" + list.get(7));

		list.remove(5);
		list.remove(10);
		System.out.println("list: " + list.toString() + ", size: "
				+ list.size());
	}

}
