package com.elsealabs.stacker;

public class GridSquare {
	
	private int POSITION;
	private int LIT_VALUE;
	
	public GridSquare(int xposition) {
		POSITION = xposition;
	}
	
	public int getPosition() {
		return POSITION;
	}
	
	public int getLitValue() {
		return LIT_VALUE;
	}
	
	public void setLitValue(int value) {
		LIT_VALUE = value;
	}

}