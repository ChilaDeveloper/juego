package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	ArrayList<objeto> listaObjetos;
	
	@Override
	public void create () {

		listaObjetos = new ArrayList<>();
		listaObjetos.add(new objeto(0,0,10));
		listaObjetos.add(new objeto(400,0,20));
		listaObjetos.add(new objeto(800,0,5));


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		for (objeto objeto: listaObjetos
			 ) {
			objeto.render();
		}



	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}
}
