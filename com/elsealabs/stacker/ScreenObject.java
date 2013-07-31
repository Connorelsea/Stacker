package com.elsealabs.stacker;

import com.badlogic.gdx.Screen;

public abstract class ScreenObject implements Screen {
	
	private GameObject GAME;
	private String NAME;
	
	public ScreenObject(GameObject game, String name) {
		GAME = game;
		NAME = name;
	}
	
	public GameObject getGame() {
		return GAME;
	}
	
	public void setScreen(ScreenObject screen) {
		GAME.addScreen(screen);
		GAME.setScreen(screen.getName());
	}
	
	public String getName() {
		return NAME;
	}
	
}