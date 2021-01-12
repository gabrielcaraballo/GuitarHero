import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

class Display extends JPanel implements Runnable, KeyListener {
	// Screen stuff
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = 500;// (int) screenSize.getWidth();
	private static final int HEIGHT = 500;// (int) screenSize.getHeight();
	private static final String KEYS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

	// Game stuff
	ArrayList<Note> fallingNotes;
	private static final int FINISH_LINE = (int) (HEIGHT * .5);
	private static final int FINISH_LINE_HEIGHT = 20;

	public Display() throws FileNotFoundException, InterruptedException {
		fallingNotes = new ArrayList<Note>();
		Scanner input = new Scanner(new File("song.txt"));

		Thread thread = new Thread() {
			public void run() {
				while (input.hasNext()) {
					String notesIn = input.next();
					for (int i = 0; i < notesIn.length(); i++) {
						char name = notesIn.charAt(i);
						fallingNotes.add(new Note(name, getStartingPosition(name)));
					}
					try {
						Thread.sleep(1000); // Time between each line of notes falls
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();

		setFocusable(true);
		addKeyListener(this);
		new Thread(this).start();
	}

	// Returns the starting position of the note, scaled for screen size
	private double getStartingPosition(char name) {
		return (double) KEYS.indexOf(name) / KEYS.length() * WIDTH;
	}

	public void paint(Graphics window) {
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, WIDTH, HEIGHT);

		// Draw the finish bar
		window.setColor(Color.green);
		window.fillRect(0, FINISH_LINE, WIDTH, FINISH_LINE_HEIGHT);

		// Falling note logic goes here
		for (int i = fallingNotes.size() - 1; i > -1; i--) {
			Note n = fallingNotes.get(i);
			n.paint(window);

			// A note is marked wrong once it passes the finish line
			if (n.getY() > FINISH_LINE + FINISH_LINE_HEIGHT && !n.getState()) {
				n.setState(false);
			}

			// Once the note goes out-of-bounds, remove it from the list of notes
			if (n.getY() > HEIGHT) {
				fallingNotes.remove(n);
			}
		}
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(10);
				repaint();
			}
		} catch (Exception e) {
		}
	}

	// Check if a key in the green box was pressed
	public void onKeyPressed(KeyEvent e) {
		char pressed = e.getKeyChar();

		// Debug thing
		if (pressed == '=') {
			fallingNotes.add(new Note('e', getStartingPosition('e')));
		}

		// Check if the note is in the green box
		for (Note n : fallingNotes) {
			if (n.getName() == pressed) {
				if (n.getY() > FINISH_LINE && n.getY() < FINISH_LINE + FINISH_LINE_HEIGHT) {
					n.setState(true);
				} else {
					// TODO: Count the nearest match wrong (too early)
				}
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		onKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
