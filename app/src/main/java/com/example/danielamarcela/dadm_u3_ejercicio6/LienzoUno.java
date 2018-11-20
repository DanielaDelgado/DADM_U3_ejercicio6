package com.example.danielamarcela.dadm_u3_ejercicio6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;

public class LienzoUno extends View {
    Imagenes logo,halloween,navidad,aNuevo,halloDes,navidadDes,aNuevoDes;
    Imagenes flechaH,flechaN,flechaA;
    MainActivity obj;
    Imagenes puntero;
    public LienzoUno(Context context) {
        super(context);
        logo=new Imagenes(R.drawable.logo,9,45,this);

        halloween=new Imagenes(R.drawable.halloween,80,490,this);
        halloDes=new Imagenes(R.drawable.hallodes,7,1050,this);
        navidad=new Imagenes(R.drawable.navidad,480,490,this);
        navidadDes=new Imagenes(R.drawable.navidaddes,7,1050,this);
        aNuevo=new Imagenes(R.drawable.anuevo,880,490,this);
        aNuevoDes=new Imagenes(R.drawable.anuevodes,7,1050,this);

        flechaH=new Imagenes(R.drawable.flecha,350,1420,this);
        flechaN=new Imagenes(R.drawable.flecha,350,1420,this);
        flechaA=new Imagenes(R.drawable.flecha,350,1420,this);

        puntero=null;
        halloDes.hacerVisible(false);
        navidadDes.hacerVisible(false);
        aNuevoDes.hacerVisible(false);
        flechaH.hacerVisible(false);
        flechaN.hacerVisible(false);
        flechaA.hacerVisible(false);

        obj = (MainActivity) context;


    }

    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        Paint p=new Paint();

        c.drawColor(Color.BLACK);
        p.setColor(Color.parseColor("#a8a797"));
        c.drawRect(1,460,1075,880,p);


        logo.pintar(c,p);
        halloween.pintar(c,p);
        navidad.pintar(c,p);
        aNuevo.pintar(c,p);
        halloDes.pintar(c,p);
        navidadDes.pintar(c,p);
        aNuevoDes.pintar(c,p);
        flechaH.pintar(c,p);
        flechaN.pintar(c,p);
        flechaA.pintar(c,p);


    }
    public boolean onTouchEvent(MotionEvent e)
    {
        float xp= e.getX();
        float yp= e.getY();

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(halloween.estaEnArea(xp,yp))
                {
                    //putero apunta a icono 1
                    puntero=halloween;
                    halloDes.hacerVisible(true);
                    navidadDes.hacerVisible(false);
                    aNuevoDes.hacerVisible(false);

                    flechaH.hacerVisible(true);
                    flechaN.hacerVisible(false);
                    flechaA.hacerVisible(false);

                }
                if(navidad.estaEnArea(xp,yp))
                {
                    puntero=navidad;
                    navidadDes.hacerVisible(true);
                    halloDes.hacerVisible(false);
                    aNuevoDes.hacerVisible(false);

                    flechaN.hacerVisible(true);
                    flechaH.hacerVisible(false);
                    flechaA.hacerVisible(false);
                }
                if(aNuevo.estaEnArea(xp,yp))
                {
                    puntero=aNuevo;
                    aNuevoDes.hacerVisible(true);
                    halloDes.hacerVisible(false);
                    navidadDes.hacerVisible(false);

                    flechaA.hacerVisible(true);
                    flechaH.hacerVisible(false);
                    flechaN.hacerVisible(false);
                }

                if(flechaH.estaEnArea(xp,yp))
                {
                    Intent otraventana=new Intent(obj, Main2Activity.class);
                    obj.startActivity(otraventana);
                }

                if(flechaN.estaEnArea(xp,yp))
                {
                    Intent otraventana=new Intent(obj, Main3Activity.class);
                    obj.startActivity(otraventana);
                }

                if(flechaA.estaEnArea(xp,yp))
                {
                    Intent otraventana=new Intent(obj, Main4Activity.class);
                    obj.startActivity(otraventana);
                }




                break;
            case MotionEvent.ACTION_MOVE:
                if(puntero!=null)
                {
                    if(puntero==halloween)
                    {
                      halloween.mover(xp);
                      navidad.mover(xp+400);
                      aNuevo.mover(xp+800);
                    }

                    if(puntero==navidad)
                    {

                        navidad.mover(xp);
                        halloween.mover(xp-400);
                        aNuevo.mover(xp+400);
                    }

                    if(puntero==aNuevo)
                    {

                        aNuevo.mover(xp);
                        navidad.mover(xp-400);
                        halloween.mover(xp-800);

                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                puntero=null;
                break;
        }

        invalidate();
        return true;
    }
}
