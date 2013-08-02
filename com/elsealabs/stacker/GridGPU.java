package com.elsealabs.stacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

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
	private ArrayList<ArrayList<GridCoordinate>> DYNAMIC_COORD_ARRAYS;
	
	private ArrayList<AnimationFalling> A_FALLING;
	
	/*
	 *  INITIALIZATION AND CONSTRUCTOR
	 */
	
	public GridGPU(SpriteBatch batch, GridManager gm) {
		BATCH = batch;
		GRID_MANAGER = gm;
		COORDS = new ArrayList<GridCoordinate>();
		DYNAMIC_COORD_ARRAYS = new ArrayList<ArrayList<GridCoordinate>>();
		A_FALLING = new ArrayList<AnimationFalling>();
		loadSprites();
	}
	
	public void loadSprites() {
		S_SQUARE_BLUE = new Sprite(new Texture("textures/SQUARE_BLUE_WHOLE.png"));
	}
	
	/*
	 *  RENDERING
	 */
	
	public void render(float delta) {
		
		for (GridCoordinate gc : COORDS) {
			BATCH.draw(S_SQUARE_BLUE, 98 + (30 * gc.X), 163 + (30 * gc.Y));
		}
		
		for (ArrayList<GridCoordinate> gca: DYNAMIC_COORD_ARRAYS) {
			for (GridCoordinate gc : gca) {
				BATCH.draw(S_SQUARE_BLUE, 98 + (30 * gc.X), 163 + (30 * gc.Y));
			}
		}
		
		for (AnimationFalling af : A_FALLING) {
			for (GridCoordinate gc : af.getFallingCoords()) {
				
				S_SQUARE_BLUE.setBounds(98 + (30 * gc.X), 163 + (30 * gc.Y),
						S_SQUARE_BLUE.getHeight(), S_SQUARE_BLUE.getWidth());
				S_SQUARE_BLUE.setColor(
					S_SQUARE_BLUE.getColor().r,
					S_SQUARE_BLUE.getColor().g,
					S_SQUARE_BLUE.getColor().b,
					af.getAlpha()
				);
				S_SQUARE_BLUE.draw(BATCH);
				
			}
		}
		
	}
	
	/*
	 *  BLOCK FALLING ANIMATION
	 */
	
	/*
	 *  COORDINATE MANIPULATION
	 */
	
	public void addDynamicCoordArray(ArrayList<GridCoordinate> gca) {
		DYNAMIC_COORD_ARRAYS.add(gca);
	}
	
	public void addFallingAnimation(AnimationFalling af) {
		A_FALLING.add(af);
	}
	
	public void clearDynamicCoords() {
		DYNAMIC_COORD_ARRAYS.clear();
	}
	
	public void addCoord(GridCoordinate gc) {
		COORDS.add(gc);
	}
	
	public void clearCoords() {
		COORDS.clear();
	}

}