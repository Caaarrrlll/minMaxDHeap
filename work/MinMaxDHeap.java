/* Complete this class to implement a fully functional min-max d-heap. Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your task,
BUT you are not allowed to remove or change the names or properties of any of the features you were given.

Importing Java's built in data structures is incorrect, this task is to teach you how to implement your own DHeap.
*/
@SuppressWarnings({"unchecked"})
public class MinMaxDHeap<T>
{
	public MinMaxDHeap(int d)
	{
		/* Parameter d specifies the order of your min-max heap. If d = 2, you should construct a binary heap, 
		   if d = 3, you should construct a ternary heap, etc. You may implement this constructor to suit your 
		   needs, or you may add additional constructors. This is the constructor which will be used for marking. */ 

		   this.d = d;
		   this.size = 0;
		   this.arr = new Node[1];
		   this.arr[0] = null;
	}
	
	/* Insertion */
	public void insert(T data, int key)
	{
		/* Insert a Node object according to its key (priority).
			 The Node object has to be initialised with the given data/key values.
		   Refer to the assignment spec for insertion algorithm details. */

		Node newNode = new Node(data, key);
		if(size <= 0){
			this.arr[0] = newNode;
			this.size++;
		}
		else{
			if(size == this.arr.length)
				resize();

			this.arr[size] = newNode;
			bubbleUp(size);
			this.size++;
		}
	}
	
	/* Read-only access */
	public T peekMin()
	{
		/* Return the data of the min priority Node. Min-max heap should not be modified by this function. */
		if(size >= 1)
			return (T)this.arr[0].getData();
		else{
			return null;
		}
	}
	
	public T peekMax()
	{
		/* Return the data of the max priority Node. Min-max heap should not be modified by this function. */
		if(size <= 1){
			return null;
		}
		else{
			Node temp = this.arr[1];

			for(int i = 2; i <= d && i < size; i++){
				if(this.arr[i].getKey() > temp.getKey()){
					temp = this.arr[i];
				}
			}

			return (T)temp.getData();
		}
	}
	
	public String toString()
	{
		/* Return a breadth-first traversal representation of the Min-Max d-heap by constructing 
		   a comma-separated string of the data stored in the heap. To construct the string,
       iterate over the heap, and append each Node object by invoking the toString() method.
       NB: The output format should contain no spaces and/or new line characters. 
       Individual nodes must be comma-separated. Eg., if alphabetical characters A, B, and C 
       were stored in the min-max heap in this order, you should return the string "A,B,C" 
       */
		String result = "";
		if(size >= 1){
			for(int i = 0; i < size - 1; i++){
				result += this.arr[i].getData();
				result += ",";
			}
			result += this.arr[size - 1].getData();
		}

		return result;
	}
	
	
	/* Deletion */
	public T deleteMin()
	{
		/* Remove the Node with the min priority, and return its data. 
			 Min-max heap has to be restructured accordingly: see spec for details. */
			 if(size > 0){
				T temp = (T)this.arr[0].getData();
				this.arr[0] = this.arr[size - 1];
				this.arr[size - 1] = null;
				this.size--;
				trickleDown(0);
				return temp;
			 }
			 else{
				return null;
			 }
			 
	}
	
	public T deleteMax()
	{
		/* Remove the Node with the max priority, and return its data. 
			 Min-max heap has to be restructured accordingly: see spec for details. */
		if(size <= 1){
			return null;
		}
		else{
			T temp;
			int max = 1;

			for(int i = 1; i <= d && i < size; i++){
				if(this.arr[i].getKey() > this.arr[max].getKey()){
					max = i;
				}
			}

			temp = (T)this.arr[max].getData();
			this.arr[max] = this.arr[size - 1];
			this.arr[size - 1] = null;
			this.size--;
			trickleDown(max);
			return temp;
		}
	}
	
	/* Construction */
	public void construct(Node[] arr)
	{
		/* Given an array of Node objects in arbitrary order, construct a min-max heap by 
	   applying Floyd's algorithm modified for min-max d-heaps. */
	   this.clear();
	   for(int i = 0; i < arr.length && arr[i] != null; i++){
		   this.insert((T)arr[i].getData(), arr[i].getKey());
	   }
	}
	
	public void changeD(int newD)
	{
		/* Given a new order d, restructure the current min-max d-heap such that it is a d-heap with d = newD. */
		Node [] temp = new Node[size];

		for(int i = 0; i < size; i++){
			temp[i] = this.arr[i];
		}

		this.d = newD;
		this.clear();
		
		construct(temp);
	}
		
	/* Clearing */
	public void clear()
	{
		/* Clear the min-max heap by removing all nodes. */
		this.arr = new Node[1];
		this.arr[0] = null;
		this.size = 0;
	}
	
	private int d; // The d-order of the min-max d-heap
	private Node[] arr; // array of nodes
	private int size; // size of array

	//helpers
	//resize the array as more elements get added
	public void resize(){
		Node newArr[] = new Node[this.arr.length * 2];

		for(int i = 0; i < size; i++)
			if(this.arr[i] != null){
				newArr[i] = this.arr[i];
			}

		this.arr = newArr;
	}

	public int getNthChildIndex(int parentIndex, int n){ return (this.d * parentIndex + n); }
	public int getParentIndex(int childIndex){ return (int)((childIndex - 1)/this.d); }
	public int getGrandParentIndex(int childIndex){ return getParentIndex(getParentIndex(childIndex)); }

	public boolean hasNthChild(int index, int n){ return getNthChildIndex(index, n) < size; }
	public boolean hasParent(int index){ return getParentIndex(index) >= 0; }
	public boolean hasGrandparent(int index){ return getParentIndex(index) > 0 && getGrandParentIndex(index) >= 0; }
	public boolean hasGrandChild(int index){ return getNthChildIndex(getNthChildIndex(index, 1), 1) < size; }

	public void swap(int indexOne, int indexTwo){
		Node temp = this.arr[indexOne];
		this.arr[indexOne] = this.arr[indexTwo];
		this.arr[indexTwo] = temp;
	}

	public boolean determineMax(int index){
        int border = 0, prev = 0, counter = 1;
        boolean max = false;

        while(true){
            if((index <= border) && (index >= prev)){
                return max;
            }
            max = !max;
            prev = border + 1;
            counter = counter * d;
            border = border + counter;
        }
    }

	//base bubble up
	public void bubbleUp(int i){
		if(determineMax(i) == false){
			if((i-1)/this.d >= 0 && this.arr[i].getKey() > this.arr[(i - 1)/this.d].getKey()){
				swap(i, (i - 1)/this.d);
				bubbleUpMax((i - 1)/this.d);
			}
			else{
				bubbleUpMin(i);
			}
		}
		else{
			if((i-1)/this.d >= 0 && this.arr[i].getKey() < this.arr[(i - 1)/this.d].getKey()){
				swap(i, (i - 1)/this.d);
				bubbleUpMin((i - 1)/this.d);
			}
			else{
				bubbleUpMax(i);
			}
		}
	}

	public void bubbleUpMin(int i){
		if(hasGrandparent(i)){
			if(this.arr[i].getKey() < this.arr[getGrandParentIndex(i)].getKey()){
				swap(i, getGrandParentIndex(i));
				bubbleUpMin(getGrandParentIndex(i));
			}
		}
	}

	public void bubbleUpMax(int i){
		if(hasGrandparent(i)){
			if(this.arr[i].getKey() > this.arr[getGrandParentIndex(i)].getKey()){
				swap(i, getGrandParentIndex(i));
				bubbleUpMax(getGrandParentIndex(i));
			}
		}
	}

	public void trickleDown(int i){
		if(determineMax(i) == false){
			trickleDownMin(i);
		}
		else{
			trickleDownMax(i);
		}
	}

	public void trickleDownMin(int i){
		if(i >= size) return;
		if(hasNthChild(i, 1)){
			int firstChild = getNthChildIndex(i, 1);
			int smallest = getNthChildIndex(i, 1);

			for(int children = firstChild; children <= i * d + d && children < size; children++){
				for(int grandchildren = getNthChildIndex(children, 1); grandchildren <= children * d + d && grandchildren < size; grandchildren++){
					if(this.arr[grandchildren].getKey() < this.arr[smallest].getKey()){
						smallest = grandchildren;
					}
				}

				if(this.arr[children].getKey() < this.arr[smallest].getKey()){
					smallest = children;
				}
			}

			if(getGrandParentIndex(smallest) == i){
				if(this.arr[smallest].getKey() < this.arr[i].getKey()){
					swap(i, smallest);
					if(this.arr[smallest].getKey() > this.arr[getParentIndex(smallest)].getKey()){
						swap(smallest, getParentIndex(smallest));
					}
					trickleDownMin(smallest);
				}
			}
			else{
				if(this.arr[smallest].getKey() < this.arr[i].getKey()){
					swap(i, smallest);
				}
			}
		}
	}

	public void trickleDownMax(int i){
		if(i >= size) return;
		if(hasNthChild(i, 1)){
			int firstChild = getNthChildIndex(i, 1);
			int biggest = getNthChildIndex(i, 1);

			for(int children = firstChild; children <= i * d + d && children < size; children++){
				for(int grandchildren = getNthChildIndex(children, 1); grandchildren <= children * d + d && grandchildren < size; grandchildren++){
					if(this.arr[grandchildren].getKey() > this.arr[biggest].getKey()){
						biggest = grandchildren;
					}
				}

				if(this.arr[children].getKey() > this.arr[biggest].getKey()){
					biggest = children;
				}
			}

			if(getGrandParentIndex(biggest) == i){
				if(this.arr[biggest].getKey() > this.arr[i].getKey()){
					swap(i, biggest);
					if(this.arr[biggest].getKey() < this.arr[getParentIndex(biggest)].getKey()){
						swap(biggest, getParentIndex(biggest));
					}
					trickleDownMax(biggest);
				}
			}
			else{
				if(this.arr[biggest].getKey() > this.arr[i].getKey()){
					swap(i, biggest);
				}
			}
		}
	}
}