import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class Panel extends JPanel {
	public static JLabel level, moves, status, valueDis;
	public static JButton add2, subtract5, multiply3, reverse, restart,
		next;
	public static JPanel displayField;
	
	public Panel() {
		this.setSize(300, 400);
		this.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.BOTH;
		level = new JLabel();
		moves = new JLabel();
		status = new JLabel();
		valueDis = new JLabel();
		valueDis.setFont(new Font(valueDis.getName(), Font.BOLD, 32));
		valueDis.setHorizontalAlignment(SwingConstants.RIGHT);
		updateDisplay();
		cs.gridy = 0;
		cs.weighty = 0.4;
		this.add(createDisplay(), cs);
		cs.gridy = 1;
		cs.weighty = 1;
		this.add(createButtonPanel(), cs);
	}
	public JPanel createDisplay() {
		JPanel displayBar = new JPanel();
		displayBar.setBackground(Color.YELLOW);
		displayBar.setLayout(new GridLayout(1,3));
		level.setHorizontalAlignment(SwingConstants.CENTER);
		status.setHorizontalAlignment(SwingConstants.CENTER);
		moves.setHorizontalAlignment(SwingConstants.CENTER);
		displayBar.add(level);
		displayBar.add(status);
		displayBar.add(moves);
		
		displayField = new JPanel();
		displayField.setBackground(Color.WHITE);
		SpringLayout layout = new SpringLayout();
		displayField.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, valueDis, 12, SpringLayout.NORTH, displayField);
		layout.putConstraint(SpringLayout.EAST, valueDis, -10, SpringLayout.EAST, displayField);
		displayField.add(valueDis);
		displayField.setBorder(BorderFactory.createEtchedBorder());
		displayField.setPreferredSize(new Dimension(300,10));
		
		JPanel display = new JPanel();
		display.setLayout(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.BOTH;
		cs.gridy = 0;
		cs.gridx = 0;
		cs.weighty = 0.08;
		display.add(displayBar, cs);
		cs.gridy = 1;
		cs.gridx = 0;
		cs.weighty = 0.3;
		display.add(displayField, cs);
		
		return display;
	}
	public JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		GridLayout layout = new GridLayout(3,2);
		buttonPanel.setLayout(layout);
		add2 = new JButton("+2");
		add2.addActionListener((ActionEvent e) -> {
			if(Main.moves != 0) {
				Main.value += 2;
				Main.moves--;
				if(Main.moves == 0 || Main.target == Main.value)
					Main.outOfMovesAction();
			}
			else {
				Main.outOfMovesAction();
			}
			updateDisplay();
		});
		subtract5 = new JButton("-5");
		subtract5.addActionListener((ActionEvent e) -> {
			if(Main.moves != 0) {
				Main.value -= 5;
				Main.moves--;
				if(Main.moves == 0 || Main.target == Main.value)
					Main.outOfMovesAction();
			}
			else {
				Main.outOfMovesAction();
			}
			updateDisplay();
		});
		multiply3 = new JButton("x3");
		multiply3.addActionListener((ActionEvent e) -> {
			if(Main.moves != 0) {
				Main.value *= 3;
				Main.moves--;
				if(Main.moves == 0 || Main.target == Main.value)
					Main.outOfMovesAction();
			}
			else {
				Main.outOfMovesAction();
			}
			updateDisplay();
		});
		reverse = new JButton("flip");
		reverse.addActionListener((ActionEvent e) -> {
			if(Main.moves != 0) {
				String numb = Integer.toString(Main.value);
				String newNum = "";
				for(int i = numb.length() - 1; i > 0; i++) {
					newNum += numb.substring(i-1, i);
				}
				Main.value = Integer.parseInt(newNum);
				Main.moves--;
				if(Main.moves == 0 || Main.target == Main.value)
					Main.outOfMovesAction();
			}
			else {
				Main.outOfMovesAction();
			}
			updateDisplay();
		});
		restart = new JButton("restart");
		restart.addActionListener((ActionEvent e) -> {
			Main.resetLevel();
			displayField.setBackground(Color.WHITE);
		});
		next = new JButton("next level");
		next.addActionListener((ActionEvent e) -> {
			Main.goToNextLevel();
		});
		buttonPanel.add(add2);
		buttonPanel.add(subtract5);
		buttonPanel.add(multiply3);
		buttonPanel.add(reverse);
		buttonPanel.add(restart);
		buttonPanel.add(next);
		return buttonPanel;
	}
	public static void resetButtons() {
		add2.setEnabled(true);
		subtract5.setEnabled(true);
		multiply3.setEnabled(true);
		reverse.setEnabled(true);
		restart.setEnabled(true);
		next.setVisible(false);
	}
	public static void disableButtons() {
		add2.setEnabled(false);
		subtract5.setEnabled(false);
		multiply3.setEnabled(false);
		reverse.setEnabled(false);
		next.setVisible(true);
	}
	public void updateDisplay() {
		valueDis.setText(Integer.toString(Main.value));
		moves.setText("Moves: " + Main.moves);
		status.setText("Target: " + Integer.toString(Main.target));
		level.setText("Level " + Main.level);
	}
}
