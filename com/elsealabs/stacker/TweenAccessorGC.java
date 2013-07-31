package com.elsealabs.stacker;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenAccessorGC implements TweenAccessor<GridCoordinate> {
	
	public static final int Y = 0;

	@Override
	public int getValues(GridCoordinate target, int tweenType, float[] returnValues) {
		
		if (tweenType == Y) {
			returnValues[0] = target.Y;
			return 1;
		} else {
			return -1;
		}
		
	}

	@Override
	public void setValues(GridCoordinate target, int tweenType, float[] newValues) {
		
		if (tweenType == Y) {
			target.Y = (int) newValues[0];
		}
		
	}

}