package com.mygame.game.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygame.game.utiles.Recursos;
import com.mygame.game.utiles.Render;

public class Nave {

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
    private Animation animation;
    private float tiempo;
    private TextureRegion[] regionsMovimiento;
    private Texture imagen;
    private TextureRegion frameActual;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
        //cargar la imagen
        imagen = new Texture(Gdx.files.internal(Recursos.nave));
//        System.out.println("Imagen: "+imagen.getWidth());
//        imagen = new Texture(Recursos.enemigo);
        TextureRegion [][] tmp = TextureRegion.split(imagen,
                imagen.getWidth()/4,imagen.getHeight());

        regionsMovimiento = new TextureRegion[4];
        for (int i = 0; i < 4; i++) regionsMovimiento[i] = tmp[0][i];
        animation = new Animation(1,regionsMovimiento);
        this.tiempo = 0f;

    }


    public void render(final SpriteBatch batch) {
        tiempo += Gdx.graphics.getDeltaTime(); //es el tiempo que paso desde el ultimo render

//        System.out.println(tiempo);
        frameActual = (TextureRegion) animation.getKeyFrame( tiempo, true);
        batch.draw(frameActual,x,y);
    }
}