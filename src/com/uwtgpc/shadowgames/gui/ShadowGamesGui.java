package com.uwtgpc.shadowgames.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShadowGamesGui extends JFrame {
	public ShadowGamesGui()
	{
		add(new JLabel("Hello, World!"));
		setSize(400,400);
	}
	public void start()
	{
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
