package com.mygame.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.game.utiles.Recursos;
import com.mygame.game.utiles.Render;

public class GameOver implements Screen {

    private Texture imagen;

    int ancho,alto;
    @Override
    public void show() {

        imagen = new Texture(Gdx.files.internal(Recursos.gameOver));

    }

    @Override
    public void render(float delta) {

        Render.limpiarPantallaBlack();

        Render.batch.begin();

        ancho = Gdx.app.getGraphics().getWidth();
        alto = Gdx.app.getGraphics().getHeight();

        Render.batch.draw(imagen,0,alto/2);

        Render.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
