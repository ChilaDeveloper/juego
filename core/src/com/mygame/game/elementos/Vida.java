package com.mygame.game.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygame.game.utiles.Recursos;

public class Vida {

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int x,y;


    private Texture imagen;
    private TextureRegion frameActual;

    public Vida(int x, int y) {
        this.x = x;
        this.y = y;
        //cargar la imagen
        imagen = new Texture(Gdx.files.internal(Recursos.vida));

    }


    public void render(final SpriteBatch batch) {

        batch.draw(imagen,x,y);
    }
}
