package com.uwtgpc.shadowgames.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShadowGamesGUI extends JFrame
{
	public ShadowGamesGUI()
	{
		add(new JLabel("Hello World!"));
	}
	public void start()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
