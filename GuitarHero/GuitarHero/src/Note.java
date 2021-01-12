import java.awt.Color;
import java.awt.Graphics;

public class Note {
	private double x, y;
	private char name;
	private Color color;
	private boolean state; // Null = untouched, true = correct, false = incorrect

	public Note(char name, double x) {
		this.x = x;
		y = 10;
		this.name = name;
		color = Color.WHITE;
	}

	public void paint(Graphics window) {
		window.setColor(color);
		y = y + 0.5;
		window.drawString("" + name, (int) Math.round(x), (int) Math.round(y));
	}

	public void setState(boolean state) {
		this.state = state;
		if (state == false) {
			color = Color.RED;
		} else
			color = Color.GREEN;
	}

	public boolean getState() {
		return state;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public char getName() {
		return name;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String toString() {
		return name + " " + x + " " + y;
	}
}