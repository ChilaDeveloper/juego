package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class objeto {

    SpriteBatch batch;
    Texture img;
    Animation animation;

    int with;
    int heigth;

    int dy;

    public objeto(int with, int heigth,int velocidad) {
        batch = new SpriteBatch();
        img = new Texture("avion.jpg");
        animation = new Animation(1,img);

        this.with = with;
        this.heigth = heigth;
        this.dy = velocidad;
    }

    public void render(){
        batch.begin();

        batch.draw(img, with,  heigth);

        heigth = heigth + dy;

        if(heigth >= Gdx.app.getGraphics().getHeight()-img.getHeight() || heigth<=0){
            dy = dy * -1;
        }

        batch.end();
    }

}
