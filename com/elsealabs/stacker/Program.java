package com.elsealabs.stacker;

import com.badlogic.gdx.Game;

public class Program extends GameObject {
	
	public ScreenGame SCREEN_GAME;
	
	@Override
	public void create() {
		
		System.out.print("\nElsea Laboratories 2013\nGaming Division\n\n" +
		"[Powered By]\n- LibGDX Framework\n- Universal Tween Engine\n- Java 7\n\n"+
		"[Art]\n- Music by Bossfight\n- Art by Elsea Design Division\n\n[Preparing Game]\n\n");
		
		SCREEN_GAME = new ScreenGame(this, "SCREEN_GAME");
		this.addScreen(SCREEN_GAME);
		this.setScreen("SCREEN_GAME");
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
