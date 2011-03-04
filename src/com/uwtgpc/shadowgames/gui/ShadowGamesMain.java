package com.uwtgpc.shadowgames.gui;

import com.golden.gamedev.GameLoader;

import java.awt.*;

public class ShadowGamesMain{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameLoader gl = new GameLoader();
        gl.setup(new ShadowGamesGui(), new Dimension(640,480), false);
        gl.start();
	}

}
