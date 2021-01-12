import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

class Runner extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = 500;// (int) screenSize.getWidth();
	private int height = 500;// (int) screenSize.getHeight();

	public Runner() {
		super("Gabriel Caraballo");

		setSize(width, height);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		try {
			getContentPane().add(new Display());
		} catch (Exception e) {

		}

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		Runner run = new Runner();
	}
}
