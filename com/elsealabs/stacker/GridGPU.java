package com.elsealabs.stacker;

import java.util.ArrayList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GridGPU {
	
	/*
	 *  VARIABLES
	 */
	
	private SpriteBatch BATCH;
	private GridManager GRID_MANAGER;
	private Sprite S_SQUARE_BLUE;
	
	private ArrayList<GridCoordinate> COORDS;
	private ArrayList<GridCoordinate> DYNAMIC_COORDS;
	
	/*
	 *  INITIALIZATION AND CONSTRUCTOR
	 */
	
	public GridGPU(SpriteBatch batch, GridManager gm) {
		BATCH = batch;
		GRID_MANAGER = gm;
		COORDS = new ArrayList<GridCoordinate>();
		DYNAMIC_COORDS = new ArrayList<GridCoordinate>();
		loadSprites();
		
		Tween.registerAccessor(GridCoordinate.class, new TweenAccessorGC());
	}
	
	public void loadSprites() {
		S_SQUARE_BLUE = new Sprite(new Texture("textures/SQUARE_BLUE_WHOLE.png"));
	}
	
	/*
	 *  RENDERING
	 */
	
	public void render() {
		for (GridCoordinate gc : COORDS) {
			BATCH.draw(S_SQUARE_BLUE, 98 + (30 * gc.X), 163 + (30 * gc.Y));
		}
		for (GridCoordinate gc : DYNAMIC_COORDS) {
			BATCH.draw(S_SQUARE_BLUE, 98 + (30 * gc.X), 163 + (30 * gc.Y));
		}
	}
	
	/*
	 *  ANIMATIONS
	 */
	
	private GridCoordinate fallingGC;
	
	public void animateFalling(GridCoordinate gc) {
		// removed code because it's bad, will fix later
	}
	
	/*
	 *  COORDINATE MANIPULATION
	 */
	
	public void addDynamicCoord(GridCoordinate gc) {
		DYNAMIC_COORDS.add(gc);
	}
	
	public void clearDynamicCoords() {
		DYNAMIC_COORDS.clear();
	}
	
	public void addCoord(GridCoordinate gc) {
		COORDS.add(gc);
	}
	
	public void clearCoords() {
		COORDS.clear();
	}

}