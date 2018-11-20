package com.example.danielamarcela.dadm_u3_ejercicio6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Imagenes {
    private Bitmap icono;
    private   float x, y;
    private    Boolean visible;

    public Imagenes(int resourse, float _x, float _y, LienzoUno l) {
        icono = BitmapFactory.decodeResource(l.getResources(), resourse);
        x = _x;
        y = _y;
        visible=true;
    }


    public void pintar(Canvas c, Paint p) {
        if (visible)  c.drawBitmap(icono, x, y, p);
    }


    public boolean estaEnArea(float xp, float yp)
    {
        if(!visible) return false;

        float x2,y2;
        x2=x+icono.getWidth();
        y2=y+icono.getHeight();

        if(xp>=x && xp<=x2)
        {
            if(yp>=y && yp<=y2)
            {
                return true;
            }
        }


        return false;
    }

    public void hacerVisible(boolean v)
    {
        visible=v;
    }

    public void mover(float xp)
    {
        x=xp-(icono.getWidth()/2);

    }


}
