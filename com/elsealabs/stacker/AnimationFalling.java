package com.elsealabs.stacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class AnimationFalling {
	
	private ArrayList<GridCoordinate> FALLING_COORDS;
	private Timer TIMER_FALLING;
	private ActionListener ACTION_FALLING;
	private int TO_FALL;
	private boolean FALLING = false;
	private GridGPU GPU;
	
	private float ALPHA = 1;
	
	public AnimationFalling(GridGPU gpu, int toFall) {
		GPU = gpu;
		TO_FALL = toFall;
		FALLING_COORDS = new ArrayList<GridCoordinate>();
	}
	
	public void addFallingCoord(GridCoordinate gc) {
		FALLING = true;
		FALLING_COORDS.add(gc);
	}
	
	public ArrayList<GridCoordinate> getFallingCoords() {
		return FALLING_COORDS;
	}
	
	public void animateFalling() {
		
		GPU.addFallingAnimation(this);
		
		ACTION_FALLING = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for (GridCoordinate gc : FALLING_COORDS) {
					gc.setY(gc.getY() - 1);
				}
				
				ALPHA = ALPHA / 2;
				
				TO_FALL -= 1;
				if (TO_FALL == 0) {
					TIMER_FALLING.stop();
					FALLING_COORDS.clear();
				}
				
			}
		};
		TIMER_FALLING = new Timer(150, ACTION_FALLING);
		TIMER_FALLING.start();
		
	}
	
	public float getAlpha() {
		return ALPHA;
	}
	
	public boolean isFalling() {
		return FALLING;
	}

}