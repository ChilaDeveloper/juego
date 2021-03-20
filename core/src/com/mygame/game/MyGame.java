package com.mygame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.game.pantallas.PantallaJuego;
import com.mygame.game.utiles.Render;

import java.util.ArrayList;

import javax.swing.Renderer;

public class MyGame extends Game {
//	SpriteBatch batch;
//	Texture img;

//	ArrayList<objeto> listaObjetos;

	@Override
	public void create () {

		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaJuego());

	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {

		Render.batch.dispose();

	}
}
