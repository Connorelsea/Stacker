package com.elsealabs.stacker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenGame extends ScreenObject {
	
	public ScreenGame(GameObject game, String name) {
		super(game, name);
	}

	private GridManager GRID_MANAGER;
	
	public SpriteBatch BATCH;
	private TweenManager TWEEN_MANAGER;
	
	private Sprite S_BG_OFF;
	private Sprite S_BG_GREY;
	private Sprite S_BG_BLUE;
	private Sprite S_BG_GREEN;
	private Sprite S_BG_RED;
	private Sprite S_MACHINE;
	private Sprite S_MACHINE_OFF;
	private Sprite S_GRID_FULL;
	private Sprite S_LOGO_STACKER_ON;
	private Sprite S_LOGO_STACKER_OFF;
	private Sprite S_LIGHTS_BLUE;
	private Sprite S_LIGHTS_GREEN;
	private Sprite S_LIGHTS_RED;
	private Sprite S_LIGHTS_WHITE;
	
	private KeyListener KEY_LISTENER_SPACE;
	private KeyListener KEY_LISTENER_ESCAPE;
	private KeyListener KEY_LISTENER_P;
	
	private int MODE;
	private int MODE_STEPS = 0;
		
	private final int MODE_OFF = 0;
	private final int MODE_ON = 1;
	
	private Timer TIMER_START;
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        TWEEN_MANAGER.update(delta);
        
        BATCH.begin();
        
        /*
         *  MODE == MODE_OFF
         */
        
        if (MODE == MODE_OFF) {
        	

        	TIMER_START = new Timer(3000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MODE = MODE_ON;
					TIMER_START.stop();
				}
        	});
        	TIMER_START.start();
        	
        	BATCH.draw(S_BG_OFF, 0, 0);
            BATCH.draw(S_MACHINE_OFF, 0, 0);
            BATCH.draw(S_GRID_FULL, 98, 163);
            BATCH.draw(S_LOGO_STACKER_OFF, 0, 0);
            
        }
        
        /*
         *  MODE == MODE_ON
         */
        
        if (MODE == MODE_ON) {
        	
        	if (MODE_STEPS == 0) {
        		
        		// LOGO FADE IN, THEN PULSE
        		
        		Tween.set(S_LOGO_STACKER_ON, TweenAccessorSprite.ALPHA)
    				.target(0)
    				.start(TWEEN_MANAGER);
        		Tween.to(S_LOGO_STACKER_ON, TweenAccessorSprite.ALPHA, 2)
    				.target(1)
    				.setCallback(new TweenCallback() {
						public void onEvent(int type, BaseTween<?> source) {
							if (type == TweenCallback.COMPLETE) {
								
								Tween.set(S_LOGO_STACKER_ON, TweenAccessorSprite.ALPHA)
		        					.target(.6f)
		        					.start(TWEEN_MANAGER);
								Tween.to(S_LOGO_STACKER_ON, TweenAccessorSprite.ALPHA, 1)
									.target(1)
									.repeatYoyo(Tween.INFINITY, 0)
		        					.start(TWEEN_MANAGER);
							}
						}
    				})
    				.start(TWEEN_MANAGER);
        		
        		// MACHINE ON FADE IN
        		
        		Tween.set(S_MACHINE, TweenAccessorSprite.ALPHA)
    				.target(0)
    				.start(TWEEN_MANAGER);
        		Tween.to(S_MACHINE, TweenAccessorSprite.ALPHA, 2)
    				.target(1)
    				.start(TWEEN_MANAGER);
        		
        		// LIGHTS PULSE
        		
        		Tween.set(S_LIGHTS_BLUE, TweenAccessorSprite.ALPHA)
        			.target(.3f)
        			.start(TWEEN_MANAGER);
        		Tween.to(S_LIGHTS_BLUE, TweenAccessorSprite.ALPHA, .5f)
					.target(1f)
					.repeatYoyo(Tween.INFINITY, 0)
					.start(TWEEN_MANAGER);
        		
        		// TURNING BACKGROUND LIGHT ON
        		
        		Timeline.createSequence()
        			.push(Tween.set(S_BG_GREY, TweenAccessorSprite.ALPHA)
        				.target(0)
        			)
        			.push(Tween.to(S_BG_GREY, TweenAccessorSprite.ALPHA, 1)
        				.target(1)
        			)
        			.delay(.5f)
        			.push(Tween.set(S_BG_BLUE, TweenAccessorSprite.ALPHA)
        				.target(0)
        			)
        			.push(Tween.to(S_BG_BLUE, TweenAccessorSprite.ALPHA, 3)
        				.target(1)
        				.repeatYoyo(2, 0)
        			)
        			.start(TWEEN_MANAGER);
        		
        		// DRAWING ELEMENTS
        		
        		BATCH.draw(S_BG_OFF, 0, 0);
                BATCH.draw(S_MACHINE_OFF, 0, 0);
                BATCH.draw(S_GRID_FULL, 98, 163);
                S_LOGO_STACKER_OFF.draw(BATCH);
                
                
                // PREPARING ELEMENTS FOR NEXT MODE
                
                S_LIGHTS_BLUE.setColor(S_LIGHTS_BLUE.getColor().r,
                		S_LIGHTS_BLUE.getColor().g,
                		S_LIGHTS_BLUE.getColor().b,
                		0);
                
                S_LIGHTS_GREEN.setColor(S_LIGHTS_GREEN.getColor().r,
                		S_LIGHTS_GREEN.getColor().g,
                		S_LIGHTS_GREEN.getColor().b,
                		0);
                
                S_LIGHTS_RED.setColor(S_LIGHTS_RED.getColor().r,
                		S_LIGHTS_RED.getColor().g,
                		S_LIGHTS_RED.getColor().b,
                		0);
                
                S_BG_BLUE.setColor(S_BG_BLUE.getColor().r,
                		S_BG_BLUE.getColor().g,
                		S_BG_BLUE.getColor().b,
                		0);
                
                S_BG_GREY.setColor(S_BG_GREY.getColor().r,
                		S_BG_GREY.getColor().g,
                		S_BG_GREY.getColor().b,
                		0);
                
                S_MACHINE.setColor(S_MACHINE.getColor().r,
                		S_MACHINE.getColor().g,
                		S_MACHINE.getColor().b,
                		0);
                
                S_LOGO_STACKER_ON.setColor(S_LOGO_STACKER_ON.getColor().r,
                		S_LOGO_STACKER_ON.getColor().g,
                		S_LOGO_STACKER_ON.getColor().b,
                		0);
        		
        		MODE_STEPS = 1;
        	}
        	
        	if (MODE_STEPS == 1) {
        		
        		// DRAWING ELEMENTS
        		
        		BATCH.draw(S_BG_OFF, 0, 0);
        		S_BG_GREY.draw(BATCH);
        		S_BG_BLUE.draw(BATCH);
                BATCH.draw(S_MACHINE_OFF, 0, 0);
                S_MACHINE.draw(BATCH);
                BATCH.draw(S_GRID_FULL, 98, 163);
                S_LOGO_STACKER_OFF.draw(BATCH);
                S_LOGO_STACKER_ON.draw(BATCH);
                S_LIGHTS_BLUE.draw(BATCH);
                
                GRID_MANAGER.setDifficulty(GridManager.HARD);
                GRID_MANAGER.start();
                
                MODE_STEPS = 2;
                
        	}
        	
        	if (MODE_STEPS == 2) {
        		
        		BATCH.draw(S_BG_OFF, 0, 0);
        		S_BG_GREY.draw(BATCH);
        		S_BG_BLUE.draw(BATCH);
                BATCH.draw(S_MACHINE_OFF, 0, 0);
                S_MACHINE.draw(BATCH);
                BATCH.draw(S_GRID_FULL, 98, 163);
                S_LOGO_STACKER_OFF.draw(BATCH);
                S_LOGO_STACKER_ON.draw(BATCH);
                
                S_LIGHTS_BLUE.draw(BATCH);
                S_LIGHTS_GREEN.draw(BATCH);
                S_LIGHTS_RED.draw(BATCH);
                
                GRID_MANAGER.render();
        	}
        	
        }
        
        // KEY HANDLING
        
        if (KEY_LISTENER_SPACE.isKeyPressed()) {
        	GRID_MANAGER.doStack();
        }
        
        if (KEY_LISTENER_ESCAPE.isKeyPressed()) {
        	
        }
        
        if (KEY_LISTENER_P.isKeyPressed()) {
        	if (GRID_MANAGER.isPaused()) GRID_MANAGER.resume();
        	else GRID_MANAGER.pause();
        }
        
        BATCH.end();
	}
	
	public static final int LIGHT_BLUE = 0;
	public static final int LIGHT_WHITE = 1;
	public static final int LIGHT_GREEN = 3;
	public static final int LIGHT_RED = 4;
	
	public void flashLights(int color, int amount) {
		
		Sprite toUse = null;
		if (color == LIGHT_GREEN) toUse = S_LIGHTS_GREEN;
		if (color == LIGHT_RED) toUse = S_LIGHTS_RED;
		
		Tween.set(toUse, TweenAccessorSprite.ALPHA)
			.target(0)
			.start(TWEEN_MANAGER);
		
		Tween.to(toUse, TweenAccessorSprite.ALPHA, .2f)
			.target(1)
			.repeatYoyo(amount + 1, 0)
			.start(TWEEN_MANAGER);
	}
	

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
		MODE = MODE_OFF;
		BATCH = new SpriteBatch();
		
		initSprites();
		initKeyListeners();
		initTweens();
		
		GRID_MANAGER = new GridManager(BATCH, this, TWEEN_MANAGER);
		
	}

	@Override
	public void hide() {
		System.out.println("[Hiding Game]");
	}

	@Override
	public void pause() {
		System.out.println("[Pausing Game]");
	}

	@Override
	public void resume() {
		System.out.println("[Resuming Game]");
	}

	@Override
	public void dispose() {
		
	}
	
	/*
	 *  INITIATION METHODS
	 */
	
	public void initSprites() {
		S_BG_OFF = new Sprite(new Texture("textures/BACKGROUND_OFF.png"));
		S_BG_GREY = new Sprite(new Texture("textures/BACKGROUND_GREY.png"));
		S_BG_BLUE = new Sprite(new Texture("textures/BACKGROUND_BLUE.png"));
		S_BG_GREEN = new Sprite(new Texture("textures/BACKGROUND_GREEN.png"));
		S_BG_RED = new Sprite(new Texture("textures/BACKGROUND_RED.png"));
		S_MACHINE = new Sprite(new Texture("textures/ARCADE_MACHINE.png"));
		S_MACHINE_OFF = new Sprite(new Texture("textures/ARCADE_MACHINE_OFF.png"));
		S_GRID_FULL = new Sprite(new Texture("textures/GRID_FULL.png"));
		S_LOGO_STACKER_ON = new Sprite(new Texture("textures/LOGO_STACKER_ON.png"));
		S_LOGO_STACKER_OFF = new Sprite(new Texture("textures/LOGO_STACKER_OFF.png"));
		S_LIGHTS_BLUE = new Sprite(new Texture("textures/LIGHTS_BLUE.png"));
		S_LIGHTS_GREEN = new Sprite(new Texture("textures/LIGHTS_GREEN.png"));
		S_LIGHTS_RED = new Sprite(new Texture("textures/LIGHTS_RED.png"));
		S_LIGHTS_WHITE = new Sprite(new Texture("textures/LIGHTS_WHITE.png"));
	}
	
	public void initKeyListeners() {
		KEY_LISTENER_SPACE = new KeyListener(Input.Keys.SPACE, 300);
		KEY_LISTENER_ESCAPE = new KeyListener(Input.Keys.ESCAPE, 300);
		KEY_LISTENER_P = new KeyListener(Input.Keys.P, 300);
	}
	
	public void initTweens() {
		TWEEN_MANAGER = new TweenManager();
		Tween.registerAccessor(Sprite.class, new TweenAccessorSprite());
	}

}