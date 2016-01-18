package GameEngine;

import javax.swing.JFrame;

public class WindowViewer {

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Pickin' Sticks Game");
		frame.setSize(GameRunner.WIDTH, GameRunner.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(new GameRunner());
		frame.setVisible(true);
	}

}
