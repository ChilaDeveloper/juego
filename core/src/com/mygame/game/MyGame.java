package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Animation animation;
	int with;
	int heigth;
	Boolean dibujar;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		animation = new Animation(1,img);
		with = 0;
		heigth = 0;
		dibujar = true;

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(img, with,  heigth);

		if(with >= Gdx.app.getGraphics().getWidth()-img.getWidth()){
			dibujar = false;
		}

		if(with <= 0){
			dibujar = true;
		}

		if(dibujar){
			with = with + 10;
		}else{
			with = with - 10;
		}


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
