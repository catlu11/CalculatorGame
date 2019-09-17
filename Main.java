import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	public static int value = 0;
	public static final int[] BASES = {1,1,1,1,1};
	public static final int[] TARGETS = {3,3,3,3,3};
	public static final int[] MOVES = {1,2,3,4,5};
	public static int level = 1;
	public static int target = TARGETS[level - 1];
	public static int moves = MOVES[level - 1];
//	public static enum state {LEVEL, RESTART, NEW_LEVEL};
//	public static state current = state.LEVEL;
	public static String message = "";
	
	public static Panel display = new Panel();
	
	public Main() {
		this.setSize(300,400);
		this.setVisible(true);
		this.setTitle("Calculator: Stage One");
		this.add(display);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		resetLevel();
		
	}
	public static boolean isCorrect() {
		if(TARGETS[level - 1] == value) {
			return true;
		}
		return false;
	}
	public static void goToNextLevel() {
		level++;
		resetLevel();
	}
	public static void resetLevel() {
		if(level > BASES.length) {
			display.disableButtons();
			display.next.setVisible(false);
			display.restart.setEnabled(false);
			display.moves.setVisible(false);
			display.status.setText("Out of levels.");
			display.level.setVisible(false);
		}
		else {
		moves = MOVES[level - 1];
		value = BASES[level - 1];
		target = TARGETS[level - 1];
//		message = "Good luck!";
		display.resetButtons();
		display.updateDisplay();
		}
	}
	public static void outOfMovesAction() {
		display.disableButtons();
		if(Main.value == Main.target) {
			display.next.setVisible(true);
//			message = "You pass!";
		}
		else {
			display.next.setVisible(false);
			display.displayField.setBackground(Color.RED);
//			message = "Oopsies.";
		}
		display.updateDisplay();
	}
	public static void main(String[] args) {
		Main main = new Main();
	}
}
