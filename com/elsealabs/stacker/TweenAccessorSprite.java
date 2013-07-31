package com.elsealabs.stacker;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class TweenAccessorSprite implements TweenAccessor<Sprite> {
	
	public static final int ALPHA = 0;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		
		if (tweenType == ALPHA) {
			returnValues[0] = target.getColor().a;
			return 1;
		} else {
			return -1;
		}
		
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		
		if (tweenType == ALPHA) {
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
		}
		
	}

}