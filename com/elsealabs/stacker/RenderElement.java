package com.elsealabs.stacker;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class RenderElement {
	
	/*
	 *  THIS CLASS IS BEING WORKED ON
	 */
	
	public static final int MOVE_SELF = 0;
	public static final int MOVE_TIME = 1;
	
	private int MOVE_TYPE;
	
	private RenderManager RENDER_MANAGER;
	
	public RenderElement(RenderManager renderManager, int moveType) {
		RENDER_MANAGER = renderManager;
		MOVE_TYPE = moveType;
	}
	
	public abstract void instructions();
	
	public void render() {
		
		if (MOVE_TYPE == RenderElement.MOVE_SELF) {
			instructions();
		} else if (MOVE_TYPE == RenderElement.MOVE_TIME) {
			
		}
	}
	
	private void renderSprite(Sprite sprite) {
		sprite.draw(RENDER_MANAGER.getBatch());
	}
	
	public void setMoveType(int moveType) {
		MOVE_TYPE = moveType;
	}
	
	public int getMoveType() {
		return MOVE_TYPE;
	}
	
}