import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/******************************************************************************
 * Name: Shane Johnson
 * 
 * Description:
 *
 * This is a template file for RingBuffer.java. It lists the constructors and
 * methods you need, along with descriptions of what they're supposed to do.
 * 
 * Note: it won't compile until you fill in the constructors and methods (or at
 * least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class RingBuffer {
	// YOUR INSTANCE VARIABLES HERE
	BlockingQueue<Double> buffer;
	int cap;

	// creates an empty ring buffer with the specified capacity
	public RingBuffer(int capacity) {
		buffer = new ArrayBlockingQueue(capacity);
		cap = capacity;
	}

	// return the capacity of this ring buffer
	public int capacity() {
		return cap;
	}

	// return number of items currently in this ring buffer
	public int size() {
		return buffer.size();
	}

	// is this ring buffer empty (size equals zero)?
	public boolean isEmpty() {
		return buffer.size() == 0;
	}

	// is this ring buffer full (size equals capacity)?
	public boolean isFull() {
		return buffer.size() == cap;
	}

	// adds item x to the end of this ring buffer
	public void enqueue(double x) {
		if (buffer.size() == cap)
			throw new IllegalStateException();
		buffer.add(x);
	}

	// deletes and returns the item at the front of this ring buffer
	public double dequeue() {
		if (buffer.size() == 0)
			throw new NoSuchElementException();
		return buffer.poll();
	}

	// returns the item at the front of this ring buffer
	public double peek() {
		if (buffer.size() == 0)
			throw new NoSuchElementException();
		return buffer.peek();
	}

}
