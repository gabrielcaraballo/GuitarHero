import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

class Display extends JPanel implements Runnable, KeyListener {
	// Screen stuff
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = (int) screenSize.getWidth();
	private static final int HEIGHT = (int) screenSize.getHeight();

	// Game stuff
	ArrayList<Note> fallingNotes;
	private static final int FINISH_HEIGHT = (int) (HEIGHT * .8);

	public Display() {
		fallingNotes = new ArrayList<Note>();
		fallingNotes.add(new Note('a', WIDTH / 2, 0));
		for (int i = 0; i < 10; i++) {
			fallingNotes.add(new Note('a', Math.random() * WIDTH, Math.random() * 50));
		}

		setFocusable(true);
		new Thread(this).start();
	}

	public void paint(Graphics window) {
		window.setColor(Color.BLACK);
		window.fillRect(0, 0, WIDTH, HEIGHT);

		// Draw the finish bar
		window.setColor(Color.green);
		window.fillRect(0, FINISH_HEIGHT, WIDTH, 10);

		for (int i = fallingNotes.size() - 1; i > -1; i--) {
			Note n = fallingNotes.get(i);
			n.paint(window);

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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
