public class Test {
	static int counter = 0;

	public static void main(String[] args) {

		heapTestOne();
		heapTestTwo();

	}

	public static void heapTestOne() {
		// Heap example 1
		System.out.println("*****Heap Test One*****\n");
		MinMaxDHeap<String> heapOne = new MinMaxDHeap<String>(3);

		System.out.println("Inserting Values");
		heapOne.insert("A", 2);
		heapOne.insert("B", 8);
		heapOne.insert("C", 7);
		heapOne.insert("D", 6);
		heapOne.insert("E", 10);
		heapOne.insert("F", 1);
		heapOne.insert("G", 13);
		heapOne.insert("H", 33);
		heapOne.insert("I", 19);
		heapOne.insert("J", 80);
		heapOne.insert("K", 20);
		heapOne.insert("L", 15);
		heapOne.insert("M", 26);
		heapOne.insert("N", 3);
		heapOne.insert("O", 4);
		heapOne.insert("P", 17);
		heapOne.insert("Q", 29);
		heapOne.insert("R", 64);
		heapOne.insert("S", 12);
		heapOne.insert("T", 0);

		if (heapOne.toString().equals("T,R,J,M,N,A,F,C,I,H,D,L,K,B,O,G,P,Q,S,E")) { // expected output from my code.
			System.out.println("Pass Test 1");
		} else {
			System.out.println("Fail Test 1");
			counter--;

		}
		System.out.println("-------------------");

		System.out.println("Peeking Min " + heapOne.peekMin());
		System.out.println(heapOne.toString());
		System.out.println("Deleting Min " + heapOne.deleteMin());
		System.out.println(heapOne.toString());

		if (heapOne.toString().equals("F,R,J,M,N,A,E,C,I,H,D,L,K,B,O,G,P,Q,S")) { // expected output
			System.out.println("Pass Test 2");
		} else {
			System.out.println("Fail Test 2");
			counter--;

		}
		System.out.println("-------------------");
		String maxDelete = "";
		String maxPeek = "";
		String minPeek = "";
		String minDelete = "";
		while (heapOne.peekMax() != null) { // peekMax and peekMin should return null when the heap is empty (also the
											// delete functions)
											
			minPeek += heapOne.peekMin();
			minDelete += heapOne.deleteMin();
			//maxPeek += heapOne.peekMax();
			//maxDelete += heapOne.deleteMax();
			System.out.println(minPeek);
			//System.out.println(maxPeek);

		}
		// System.out.println(heapOne.deleteMax());
		// System.out.println(heapOne.toString());
		// System.out.println(heapOne.deleteMax());
		// System.out.println(heapOne.toString());


		if (minPeek.equals("FANODCBESG")) {
			System.out.println("Pass Test 3");
		} else {
			System.out.println("Fail Test 3");
			counter--;

		}

		if (minDelete.equals("FANODCBESG")) {
			System.out.println("Pass Test 4");
		} else {
			System.out.println("Fail Test 4");
			counter--;

		}

		if (maxPeek.equals("JRHQMKIPLnull")) {
			System.out.println("Pass Test 5");
		} else {
			System.out.println("Fail Test 5");
			counter--;

		}

		if (maxDelete.equals("JRHQMKIPLnull")) {
			System.out.println("Pass Test 6");
		} else {
			System.out.println("Fail Test 6");
			counter--;

		}

		System.out.println("----------------------");
		System.out.println("Clearing");
		heapOne.clear();
		if (heapOne.toString().equals("")) { // toString should now be empty
			System.out.println("Pass Test 7");
		} else {
			System.out.println("Fail Test 7");
			counter--;

		}

	}

	public static void heapTestTwo() {
		// Heap Example Given
		MinMaxDHeap<Integer> heapTwo = new MinMaxDHeap<Integer>(2);
		System.out.println("\n\n****** Heap Test Two *****");
		System.out.println("Inserting");
		heapTwo.insert(6, 6);
		heapTwo.insert(81, 81);
		heapTwo.insert(87, 87);
		heapTwo.insert(14, 14);
		heapTwo.insert(17, 17);
		heapTwo.insert(12, 12);
		heapTwo.insert(28, 28);
		heapTwo.insert(71, 71);
		heapTwo.insert(25, 25);
		heapTwo.insert(80, 80);
		heapTwo.insert(20, 20);
		heapTwo.insert(52, 52);
		heapTwo.insert(78, 78);
		heapTwo.insert(31, 31);
		heapTwo.insert(42, 42);
		heapTwo.insert(31, 31);
		heapTwo.insert(59, 59);
		heapTwo.insert(16, 16);
		heapTwo.insert(24, 24);
		heapTwo.insert(79, 79);
		heapTwo.insert(63, 63);
		heapTwo.insert(18, 18);
		heapTwo.insert(19, 19);
		heapTwo.insert(32, 32);
		heapTwo.insert(13, 13);
		heapTwo.insert(15, 15);
		heapTwo.insert(48, 48);

		System.out.println(heapTwo.toString());

		if (heapTwo.toString()
				.equals("6,81,87,14,17,12,28,71,25,80,20,52,78,31,42,31,59,16,24,79,63,18,19,32,13,15,48")) {
			System.out.println("Pass Test 8");
		} else {
			System.out.println("Fail Test 8");
			counter--;
		}

		if (heapTwo.peekMin().equals(6) && heapTwo.peekMax().equals(87) && heapTwo.toString()
				.equals("6,81,87,14,17,12,28,71,25,80,20,52,78,31,42,31,59,16,24,79,63,18,19,32,13,15,48")) { // peeking
																												// and
																												// checking
																												// if
																												// string
																												// remains
																												// unchanged
			System.out.println("Pass Test 9");
		} else {
			System.out.println("Fail Test 9");
			counter--;

		}

		System.out.println("---------------------");
		System.out.println("Changing D Test:");
		heapTwo.changeD(1);
		System.out.println("> D changed to 1");
		if (heapTwo.toString()
				.equals("6,87,12,81,13,80,14,79,15,78,16,71,17,63,18,59,19,52,20,48,24,42,25,32,28,31,31")) {
			System.out.println("Pass Test 10");
		} else {
			System.out.println("Fail Test 10");
			counter--;

		}

		heapTwo.changeD(2);
		System.out.println("> D changed to 2");
		if (heapTwo.toString()
				.equals("6,87,80,15,13,12,14,79,81,78,42,71,31,63,18,59,19,52,20,48,24,16,25,32,28,17,31")) {
			System.out.println("Pass Test 11");
		} else {
			System.out.println("Fail Test 11");
			counter--;

		}

		heapTwo.changeD(3);
		System.out.println("> D changed to 3");
		if (heapTwo.toString()
				.equals("6,87,81,71,13,12,14,25,17,78,42,15,31,63,18,59,19,52,20,48,24,16,79,32,28,80,31")) {
			System.out.println("Pass Test 12");
		} else {
			System.out.println("Fail Test 12");
			counter--;

		}

		heapTwo.changeD(4);
		System.out.println("> D changed to 4");
		if (heapTwo.toString()
				.equals("6,87,81,71,52,12,14,25,17,78,42,15,31,63,18,59,19,13,20,48,24,16,79,32,28,80,31")) {
			System.out.println("Pass Test 13");
		} else {
			System.out.println("Fail Test 13");
			counter--;

		}

		heapTwo.changeD(5);
		System.out.println("> D changed to 5");
		if (heapTwo.toString()
				.equals("6,87,81,71,80,31,14,25,17,78,42,15,31,63,18,59,19,13,20,48,24,16,79,32,28,52,12")) {
			System.out.println("Pass Test 14");
		} else {
			System.out.println("Fail Test 14");
			counter--;

		}

		heapTwo.changeD(6);
		System.out.println("> D changed to 6");
		if (heapTwo.toString()
				.equals("6,87,81,79,80,31,14,25,17,78,42,15,31,63,18,59,19,13,20,48,24,16,71,32,28,52,12")) {
			System.out.println("Pass Test 15");
		} else {
			System.out.println("Fail Test 15");
			counter--;

		}

		heapTwo.changeD(2);
		System.out.println("> D changed back to 2");
		if (heapTwo.toString()
				.equals("6,87,81,13,16,12,14,59,79,78,80,32,52,63,18,25,19,20,17,48,24,71,42,15,28,31,31")) {
			System.out.println("Pass Test 16");
		} else {
			System.out.println("Fail Test 16");
			counter--;

		}

		System.out.println("---------------------");

		constructTest(heapTwo); // move on to construct heap test from this heap

	}

	public static void constructTest(MinMaxDHeap heap) {

		System.out.println("\n\n********** Testing Construct Using Array**********");

		Node<Integer> nodeArr[] = new Node[20];

		Node<Integer> node1 = new Node<Integer>(6, 6);
		Node<Integer> node2 = new Node<Integer>(20, 20);
		Node<Integer> node3 = new Node<Integer>(0, 0);
		Node<Integer> node4 = new Node<Integer>(15, 15);
		Node<Integer> node5 = new Node<Integer>(25, 25);
		Node<Integer> node6 = new Node<Integer>(1, 1);
		Node<Integer> node7 = new Node<Integer>(2, 2);
		Node<Integer> node8 = new Node<Integer>(28, 28);
		Node<Integer> node9 = new Node<Integer>(35, 35);
		Node<Integer> node10 = new Node<Integer>(4, 4);

		nodeArr[0] = node1;
		nodeArr[1] = node2;
		nodeArr[2] = node3;
		nodeArr[3] = node4;
		nodeArr[4] = node5;
		nodeArr[5] = node6;
		nodeArr[6] = node7;
		nodeArr[7] = node8;
		nodeArr[8] = node9;
		nodeArr[9] = node10;

		heap.construct(nodeArr);

		if (heap.toString().equals("0,35,6,15,4,1,2,28,20,25")) { // expected output
			System.out.println("Pass Test 17");
		} else {
			System.out.println("Fail Test 17");
			counter--;

		}

		System.out.println("--------------\nChange D to 3:");
		heap.changeD(3);
		if (heap.toString().equals("0,35,28,15,4,1,2,6,20,25")) {
			System.out.println("Pass Test 18");
		} else {
			System.out.println("Fail Test 18");
			counter--;

		}

		System.out.println("--------------\nClearing:");
		heap.clear();
		System.out.println("Heap Cleared");
		if (heap.peekMax() == null && heap.peekMin() == null && heap.deleteMin() == null && heap.deleteMax() == null
				&& heap.toString().equals("")) { // assuming that these functions were implemented to return null if
													// empty
			System.out.println("Pass Test 19");
		} else {
			System.out.println("Fail Test 19");
			counter--;

		}
		System.out.println("\n");
		System.out.println("TOTAL SCORE : " + (19 + counter) + "/19");
		System.out.println("*********** END OF TEST **************");

	}
}
