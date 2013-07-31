package com.elsealabs.stacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.badlogic.gdx.Gdx;

public class KeyListener {
	
	private int KEY_TYPE;
	private int TIME;
	private Timer TIMER;
	private ActionListener ACTION;
	private boolean BLOCKED;
	
	public KeyListener(int keyType, int timeout) {
		KEY_TYPE = keyType;
		TIME = timeout;
		BLOCKED = false;
		initTimer();
	}
	
	public void initTimer() {
		
		ACTION = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLOCKED = false;
				TIMER.stop();
			}
		};
		TIMER = new Timer(TIME, ACTION);
		TIMER.start();
	}
	
	public boolean isKeyPressed() {
		
		if (Gdx.input.isKeyPressed(KEY_TYPE) && BLOCKED == false) {
			BLOCKED = true;
			TIMER.start();
			return true;
		}
		else return false;
		
	}

}