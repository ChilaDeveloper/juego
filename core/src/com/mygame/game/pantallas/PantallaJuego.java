package com.mygame.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygame.game.elementos.Explosion;
import com.mygame.game.elementos.Marciano1;
import com.mygame.game.elementos.Nave;
import com.mygame.game.elementos.Vida;
import com.mygame.game.elementos.botones.Bala;
import com.mygame.game.elementos.botones.Disparo;
import com.mygame.game.utiles.Render;


import java.util.ArrayList;



public class PantallaJuego implements Screen {

    ArrayList<Marciano1> listaMarciano1s;
    ArrayList<Float> timepos;

    //button
    Disparo disparoBoton;

    //balas
    ArrayList<Bala> balas;

    //vidas
    ArrayList<Vida> vidas;

    //explosiones
    ArrayList<Explosion> listaExplosiones;

    //nave
    Nave nave;
    float tiempoCambioMovimientoNave;
    boolean disparo;

    int ancho;
    int alto;

    float tiempo;

    float tiempoCambioMovimiento;
    int cantidadMovimientosMarcianos;
    int estadoMovimientoMarciano;

    @Override
    public void show() {
//        System.out.println("show pantalla juego");

        timepos = new ArrayList<>();
        timepos.add((float) 0.5);
        timepos.add((float) 0.2);
        timepos.add((float) 0.4);
        timepos.add((float) 0.7);
        timepos.add((float) 0.1);

        ancho = Gdx.app.getGraphics().getWidth();
        alto = Gdx.app.getGraphics().getHeight();

        //Creacion de Marcianos
        crearMarcianos(64, ancho, alto);

        //Creacion de Nave
        nave = new Nave(ancho / 2, 256);
        tiempoCambioMovimientoNave = (float) 0.1;
        disparo = false;

        //Creacion de balas
        balas = new ArrayList<>();

        //Creacion de vidsas
        vidas = new ArrayList<>();
        vidas.add(new Vida(32,32));
        vidas.add(new Vida(110,32));
        vidas.add(new Vida(190,32));

        //Explosiones
        listaExplosiones = new ArrayList<>();

        //Creacion de Button
        disparoBoton = new Disparo(ancho / 3, 16);

        //Tiempo el cual se ejecuta movimientos
        tiempo = 0f;
        tiempoCambioMovimiento = (float) 2.2;
        //El movimiento predeterminado
        cantidadMovimientosMarcianos = 6;
        estadoMovimientoMarciano = 1;


    }

    private void crearMarcianos(int tamanoInagen, int ancho, int alto) {

        int cantidadMarcianos = ancho / tamanoInagen;
        int espacioOcupado = ancho / cantidadMarcianos;
        int puntoInicioY = alto - espacioOcupado * 2;

        int puntoInicioX = 0;

        listaMarciano1s = new ArrayList<>();

        for (int j = 1; j <= 5; j++) {

            for (int i = 1; i <= cantidadMarcianos - 2; i++) {
                puntoInicioX += espacioOcupado;

                listaMarciano1s.add(new Marciano1(puntoInicioX, puntoInicioY, timepos.get(MathUtils.random(0, 4))));
            }

            puntoInicioX = 0;
            puntoInicioY -= espacioOcupado;

        }


    }

    @Override
    public void render(float delta) {

        Render.limpiarPantalla();

        Render.batch.begin();

        tiempo += Gdx.graphics.getDeltaTime();

        if(listaMarciano1s.size()==0){
            System.out.println("Nueva Pantalla");
            Render.app.setScreen(new PantallaJuego2());
        }


        for (int v=0;v < vidas.size();v++){
            vidas.get(v).render(Render.batch);

        }


        disparoBoton.render(Render.batch);

        nave.render(Render.batch);

        for (int i = 0; i < listaMarciano1s.size(); i++) {
            listaMarciano1s.get(i).render(Render.batch);
//            System.out.println("y: "+listaMarciano1s.get(i).getY());
//            System.out.println("nave: "+(nave.getY()));
            if(listaMarciano1s.get(i).getY() <= nave.getY()){
//                this.show();
                listaMarciano1s = null;
                crearMarcianos(64,ancho,alto);

                vidas.remove(vidas.size()-1);

                if(vidas.size()==0) {
                    //game over aqui poner codigo de pantalla game over

                    Render.app.setScreen(new GameOver());
                    vidas.add(new Vida(32, 32));
                    vidas.add(new Vida(110, 32));
                    vidas.add(new Vida(190, 32));
                }



            }
        }

        moverMarcianos(tiempo);


        if (disparo) {
            for (int j = 0; j < balas.size(); j++) {
                balas.get(j).render(Render.batch);
                balas.get(j).setY(balas.get(j).getY() + 10);
            }

            if (balas.size() == 0) {
                disparo = false;
            }

        }


//        System.out.println(disparo);


        varificarColision(tiempo);

        for (int k = 0; k < listaExplosiones.size(); k++) {
            listaExplosiones.get(k).render(Render.batch);

            if(tiempo >= listaExplosiones.get(k).getTiempoExplosion()){
                listaExplosiones.remove(k);
            }

        }





        touch(tiempo);


        Render.batch.end();


    }

    private void varificarColision(float tiempo) {
        for (int i = 0; i < listaMarciano1s.size(); i++) {
            for (int j = 0; j < balas.size(); j++) {

                Rectangle marciano = new Rectangle(listaMarciano1s.get(i).getX(), listaMarciano1s.get(i).getY(), 64, 64);
                Rectangle bala = new Rectangle(balas.get(j).getX(), balas.get(j).getY(), 32, 64);

//                System.out.println("bala"+balas.get(j).getY());
//                System.out.println("alto"+alto);
                if (marciano.overlaps(bala)) {

                    //add explosion
                    listaExplosiones.add(new Explosion(listaMarciano1s.get(i).getX(),listaMarciano1s.get(i).getY(),tiempo+(float)3.5));


                    listaMarciano1s.remove(i);
                    balas.remove(j);

                    i--;
                    j--;
                    break;
                }

            }
        }

        for (int j = 0; j < balas.size(); j++) {
            if (balas.get(j).getY() >= alto) {
                balas.remove(j);
                j--;
            }
        }


    }

    private void touch(float tiempo) {

        if (tiempo >= tiempoCambioMovimientoNave) {
            if (Gdx.input.isTouched()) {
                float xTouchPixels = Gdx.input.getX();
                float yTouchPixels = Gdx.input.getY();

                if (xTouchPixels >= nave.getX()) {
//                    System.out.println("pantalla"+yTouchPixels);
//                    System.out.println("limiteabajo"+(alto-(nave.getY() + 128)));
//                    System.out.println("limitearriba"+(alto-nave.getY()));
                    if (yTouchPixels >= alto - (nave.getY() + 128) && yTouchPixels <= alto - nave.getY()) {
                        nave.setX(nave.getX() + 10);
                    }

                } else {
                    if (yTouchPixels >= alto - (nave.getY() + 128) && yTouchPixels <= alto - nave.getY()) {
                        nave.setX(nave.getX() - 10);
                    }
                }

                //colision para las balas

                Rectangle pantalla = new Rectangle(xTouchPixels, yTouchPixels, 256, 256);
                Rectangle buton = new Rectangle(ancho - disparoBoton.getX(), alto - disparoBoton.getY(), 256, 256);


                if (pantalla.overlaps(buton)) {
//                    System.out.println("colision colision");
                    balas.add(new Bala(nave.getX() + 10, nave.getY() + 128));
                    disparo = true;
                }


            }
            tiempoCambioMovimientoNave = tiempo + (float) 0.1;

        }
    }

    private void moverMarcianos(float tiempo) {

        if (tiempo >= tiempoCambioMovimiento) {


            int cantidadMarcianos = ancho / 64;
            int espacioOcupado = ancho / cantidadMarcianos;

            for (int i = 0; i < listaMarciano1s.size(); i++) {
                switch (estadoMovimientoMarciano) {
                    case 1:
                        listaMarciano1s.get(i).setX(listaMarciano1s.get(i).getX() + espacioOcupado);
                        break;
                    case 2:
                        listaMarciano1s.get(i).setY(listaMarciano1s.get(i).getY() - espacioOcupado);
                        break;
                    case 3:
                        listaMarciano1s.get(i).setX(listaMarciano1s.get(i).getX() - espacioOcupado);
                        break;
                    case 4:
                        listaMarciano1s.get(i).setX(listaMarciano1s.get(i).getX() - espacioOcupado);
                        break;
                    case 5:
                        listaMarciano1s.get(i).setY(listaMarciano1s.get(i).getY() - espacioOcupado);
                        break;
                    case 6:
                        listaMarciano1s.get(i).setX(listaMarciano1s.get(i).getX() + espacioOcupado);
                        break;

                }
            }

            if (estadoMovimientoMarciano < cantidadMovimientosMarcianos) {
                estadoMovimientoMarciano++;
            } else {
                estadoMovimientoMarciano = 1;
            }
            tiempoCambioMovimiento = tiempo + (float) 2.2;


        }


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
