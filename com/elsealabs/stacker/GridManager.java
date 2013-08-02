package com.elsealabs.stacker;

import java.util.ArrayList;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GridManager {
	
	/*
	 *  VARIABLES
	 */
	
	private ArrayList<GridRow> ROWS;
	private int ROW_COUNT;
	private int NEXT_LENGTH;
	
	private SpriteBatch BATCH;
	private GridGPU GRID_GPU;
	private TweenManager TWEEN_MANAGER;
	private ScreenGame SCREEN_GAME;
	private ScoreManager SCORE_MANAGER;
	
	private int DIFFICULTY;
	public static final int EASY = 11;
	public static final int NORMAL = 12;
	public static final int HARD = 13;
	public static final int EXTREME = 14;
	
	/*
	 *  INITIATION AND CONSTRUCTOR
	 */
	
	public GridManager(SpriteBatch batch, ScreenGame sg, TweenManager tm) {
		
		BATCH = batch;
		SCREEN_GAME = sg;
		TWEEN_MANAGER = tm;
		
		initVariables();
		initRendering();
	}
	
	public void initVariables() {
		DIFFICULTY = GridManager.NORMAL;
		SCORE_MANAGER = new ScoreManager();
		ROWS = new ArrayList<GridRow>();
	}
	
	/*
	 *  TIME HANDLING, PAUSING, RESUMING, ETC
	 */
	
	public void start() {
		
		System.out.println("New Game {");
		System.out.println("	INFO:");
		if (DIFFICULTY == EASY) System.out.println("	Difficulty: Easy");
		if (DIFFICULTY == NORMAL) System.out.println("	Difficulty: Normal");
		if (DIFFICULTY == HARD) System.out.println("	Difficulty: Hard");
		if (DIFFICULTY == EXTREME) System.out.println("	Difficulty: Extreme");
		System.out.println("	Time Multiplier: " + DIFFICULTY);
		System.out.println("}");
		
		createNextRow();
	}
	
	public void pause() {
		getCurrentRow().pause();
	}
	
	public void resume() {
		getCurrentRow().resume();
	}
	
	public boolean isPaused() {
		return getCurrentRow().isPaused();
	}
	
	/*
	 *  GAME END HANDLING
	 */
	
	public void exit() {
		
	}
	
	/*
	 *  CLEARING AND RESTART HANDLING
	 */
	
	public void reset() {
		SCORE_MANAGER.nextSession();
		clearAll();
		start();
	}
	
	public void clearAll() {
		ROWS.clear();
		ROW_COUNT = 0;
		NEXT_LENGTH = 0;
	}
	
	/*
	 *  STACK AND ROW HANDLING
	 */
	
	public void createNextRow() {
		
		if (ROW_COUNT < 15) {
			
			if (ROWS.size() == 0) {
				
				ROWS.add(new GridRow(this, 200, 3));
				ROW_COUNT++;
				getCurrentRow().start();
			} else {
				
				SCORE_MANAGER.scoreRow(
					ROWS.size(),
					ROWS.get(ROWS.size() - 1).getNextLength(),
					ROWS.get(ROWS.size() - 1).getIterations(),
					DIFFICULTY
				);
				
				NEXT_LENGTH = ROWS.get(ROWS.size() - 1).getNextLength();
				ROWS.add(new GridRow(this, 200 - (DIFFICULTY * ROW_COUNT), NEXT_LENGTH));
				ROW_COUNT++;
				getCurrentRow().start();
			}
		} else {
			
			SCORE_MANAGER.scoreRow(
					ROWS.size(),
					ROWS.get(ROWS.size() - 1).getNextLength(),
					ROWS.get(ROWS.size() - 1).getIterations(),
					DIFFICULTY
				);
			
			doWin();
			
		}
		
	}
	
	public void doStack() {
		
		if (isPaused() == false) {
			
			if (getCurrentRow().doStack() == true) {
				SCREEN_GAME.flashLights(SCREEN_GAME.LIGHT_GREEN, 2);
				createNextRow();
			} else {
				SCREEN_GAME.flashLights(SCREEN_GAME.LIGHT_RED, 2);
				reset();
			}
		}
		
	}
	
	/*
	 *  GAME WINNING
	 */
	
	public void doWin() {
		System.out.println("[Won Game]");
		reset();
	}
	
	/*
	 *  RENDERING
	 */
	
	public void initRendering() {
		GRID_GPU = new GridGPU(BATCH, this);
	}
	
	public void render(float delta) {
		GRID_GPU.clearCoords();
		int c = -1;
		int a[] = new int[10];
		
		for (GridRow gr : ROWS) {
			c++;
			for (int i = 0; i != 10; i++) {
				if (gr.getSquares().get(i).getLitValue() == 1)
					GRID_GPU.addCoord(new GridCoordinate(gr.getSquares().get(i).getPosition() + 1, c));
			}
		}
		
		GRID_GPU.render(delta);
	}
	
	/*
	 *  GETTERS AND SETTERS
	 */
	
	public GridRow getCurrentRow() {
		return ROWS.get(ROWS.size() - 1);
	}
	
	public ArrayList<GridRow> getRows() {
		return ROWS;
	}
	
	public int getRowCount() {
		return ROW_COUNT;
	}
	
	public GridGPU getGPU() {
		return GRID_GPU;
	}
	
	public TweenManager getTweenManager() {
		if (TWEEN_MANAGER == null) System.out.println("null");
		return TWEEN_MANAGER;
	}
	
	public void setDifficulty(int difficulty) {
		DIFFICULTY = difficulty;
	}

}