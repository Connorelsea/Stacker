package com.elsealabs.stacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class GridRow {
	
	/*
	 * VARIABLES
	 */
	
	private GridManager GRID_MANAGER;
	private ArrayList<GridSquare> SQUARES;
	private int SPEED;
	
	private int SEL_LENGTH;
	private int[] SELS;
	
	private boolean SEL_MOVE_RIGHT;
	
	private Timer TIMER;
	private ActionListener TIMER_ACTION;
	private int ITERATIONS;
	
	private int NEXT_LENGTH;
	
	/*
	 *  INITIATION AND CONSTRUCTOR
	 */
	
	public GridRow(GridManager gridManager, int speed, int selectionLength) {
		
		GRID_MANAGER = gridManager;
		SPEED = speed;
		SQUARES = new ArrayList<GridSquare>();
		SEL_LENGTH = selectionLength;
		
		genSquares();
		initSelection();
		updateSquareLighting();
		genTimer();
	}
	
	public void genSquares() {
		for (int i = -1; i != 10; i++) SQUARES.add(new GridSquare(i));
	}
	
	public void initSelection() {
		SEL_MOVE_RIGHT = true;
		SELS = new int[3];
		SELS[0] = 0;
		SELS[1] = 1;
		SELS[2] = 2;
	}
	
	/*
	 *  TIME MANAGEMENT
	 */
	
	public void start() {
		TIMER.start();
	}
	
	public void genTimer() {
		
		TIMER_ACTION = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move();
				ITERATIONS++;
			}
		};
		TIMER = new Timer(SPEED, TIMER_ACTION);
	}
	
	/*
	 *  STACK HANDLING
	 */
	
	public boolean doStack() {
		
		TIMER.stop();
		return determineBlockMatching();
		
	}
	
	public boolean determineBlockMatching() {
		
		if (GRID_MANAGER.getRowCount() > 1) {
			
			NEXT_LENGTH = 0;
			
			GridRow prev = GRID_MANAGER.getRows().get(GRID_MANAGER.getRows().size() - 2);
			int[] a_prev = new int[10];
			int[] a_this = new int[10];
			
			for (int i = 0; i != 10; i++) {
				a_prev[i] = prev.getSquares().get(i).getLitValue();
				a_this[i] = this.getSquares().get(i).getLitValue();
			}
			
			/*
			 *  DO THE MATH TO SEE IF THE BLOCKS ONTOP OF DA' OTHER BLOCKS MATCH EACH OTHER.
			 */
			
			for (int i = 0; i !=10; i++) {
				
				if (a_this[i] == 1 && a_prev[i] == 1) {
					NEXT_LENGTH++;
					lightSquare(i, true);
				}
				if (a_this[i] == 1 && a_prev[i] == 0) {
					lightSquare(i, false);
					GRID_MANAGER.getGPU().animateFalling(new GridCoordinate(i, GRID_MANAGER.getRowCount() - 1));
				}

			}
			
			this.SEL_LENGTH = NEXT_LENGTH;
			if (NEXT_LENGTH > 0) return true; else return false;
		}
		
		NEXT_LENGTH = 3;
		return true;
	}
	
	/*
	 *  SELECTION MOVEMENT
	 */
	
	public void move() {
		
		if (SEL_MOVE_RIGHT == true) {
			
			if (SELS[SEL_LENGTH - 1] == 9) {
				SEL_MOVE_RIGHT = false;
			} else {
				SELS[0] += 1;
				SELS[1] += 1;
				SELS[2] += 1;
				updateSquareLighting();
			}
		}
		
		if (SEL_MOVE_RIGHT == false) {
			
			if (SELS[0] == 0) {
				SEL_MOVE_RIGHT = true;
				move();
			} else {
				SELS[0] -= 1;
				SELS[1] -= 1;
				SELS[2] -= 1;
				updateSquareLighting();
			}
		}
		
	}
	
	/*
	 * SQUARE HANDLING
	 */
	
	public void lightSquare(int xpos, boolean to) {
		if (to == true) SQUARES.get(xpos).setLitValue(1); 
		else SQUARES.get(xpos).setLitValue(0);
	}
	
	public void updateSquareLighting() {
		for (GridSquare s : SQUARES) s.setLitValue(0);
		for (int i = 0; i != SEL_LENGTH; i++) lightSquare(SELS[i], true);
	}
	
	/*
	 *  GETTERS AND SETTERS
	 */
	
	public ArrayList<GridSquare> getSquares() {
		return SQUARES;
	}
	
	public int getSpeed() {
		return SPEED;
	}
	
	public int getSelectionLength() {
		return SEL_LENGTH;
	}
	
	public int[] getSelectionPositions() {
		return SELS;
	}
	
	public int getIterations() {
		return ITERATIONS;
	}
	
	public int getNextLength() {
		return NEXT_LENGTH;
	}

}