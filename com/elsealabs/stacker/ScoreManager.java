package com.elsealabs.stacker;

import java.util.ArrayList;

public class ScoreManager {
	
	private ArrayList<Integer> SCORES;
	private int SCORE;
	private float ROW_SCORE;
	
	public ScoreManager() {
		SCORES = new ArrayList<Integer>();
	}
	
	public void nextSession() {
		System.out.println("SCORE: " + SCORE);
		SCORES.add(SCORE);
		SCORE = 0;
	}
	
	public void scoreRow(int rowHeight, int selectionLength, int iterations, int difficulty) {
		
		ROW_SCORE += (rowHeight * (difficulty * 2)) * (selectionLength) +
		(selectionLength * rowHeight);
		
		if (rowHeight == 5) ROW_SCORE += 100;
		if (rowHeight == 10) ROW_SCORE += 250;
		if (rowHeight == 15) ROW_SCORE += 500;
		if (iterations > 30) ROW_SCORE -= iterations * 1.1f;
		
		Math.round(ROW_SCORE);
		
		System.out.println("+" + ROW_SCORE);
		
		SCORE += ROW_SCORE;
		ROW_SCORE = 0;
	}
	
}