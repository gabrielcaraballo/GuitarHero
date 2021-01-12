
/******************************************************************************
 * Name: Shane Johnson
 *
 * Description:
 * 
 * This is a template file for GuitarString.java. It lists the constructors and
 * methods you need, along with descriptions of what they're supposed to do.
 * 
 * Note: it won't compile until you fill in the constructors and methods (or at
 * least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {
	// YOUR INSTANCE VARIABLES HERE
	RingBuffer buff;
	int count;

	// creates a guitar string of the specified frequency,
	// using sampling rate of 44,100
	public GuitarString(double frequency) {
		int size = (int) Math.round(44100 / frequency);
		buff = new RingBuffer(size);
		int s = buff.capacity();
		while (s > 0) {
			buff.enqueue(0);
			s--;
		}
	}

	// creates a guitar string whose size and initial values are given by
	// the specified array
	public GuitarString(double[] init) {
		buff = new RingBuffer(init.length);
		for (double x : init)
			buff.enqueue(x);
		count = 0;
	}

	// returns the number of samples in the ring buffer
	public int length() {
		return buff.size();
	}

	// plucks the guitar string (by replacing the buffer with white noise)
	public void pluck() {
		for (int i = 0; i < buff.size(); i++) {
			double d = (Math.random() - .5);
			buff.dequeue();
			buff.enqueue(d);
		}
	}

	// advances the Karplus-Strong simulation one time step
	public void tic() {
		double initial = buff.peek();
		buff.dequeue();
		double second = buff.peek();
		double average = ((initial + second) / 2) * .994;
		buff.enqueue(average);
		count++;
	}

	// returns the current sample
	public double sample() {
		return buff.peek();
	}

	// returns number of tics
	public int time() {
		return count;
	}

	public String toString() {
		return buff.toString();
	}

	// tests and calls every constructor and instance method in this class
	public static void main(String[] args) {
		GuitarString one = new GuitarString(3);
		double[] ray = { 0.0, 1.0, 2.0 };
		GuitarString two = new GuitarString(ray);

	}

}
