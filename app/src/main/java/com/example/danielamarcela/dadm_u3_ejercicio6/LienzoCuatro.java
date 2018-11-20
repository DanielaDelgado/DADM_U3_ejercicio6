package com.example.danielamarcela.dadm_u3_ejercicio6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class LienzoCuatro extends View {
    ImagenesA icono,productoUno,productoDos,productoTres,puntero;
    ImagenesA pUnoDes, pDosDes,pTresDes;
    public LienzoCuatro(Context context) {
        super(context);

        icono=new ImagenesA(R.drawable.anuevo,32   ,40,this);
        productoUno=new ImagenesA(R.drawable.anuevouno,32,440,this);
        productoDos=new ImagenesA(R.drawable.anuevodos,32,940,this);
        productoTres=new ImagenesA(R.drawable.anuevotres,32,1440,this);

        pDosDes=new ImagenesA(R.drawable.aproductodos,430,340,this);
        pUnoDes=new ImagenesA(R.drawable.aproductouno,430,340,this);
        pTresDes=new ImagenesA(R.drawable.aproductotres,430,340,this);
        puntero=null;

        pDosDes.hacerVisible(false);
        pUnoDes.hacerVisible(false);
        pTresDes.hacerVisible(false);
    }

    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        Paint p=new Paint();

        c.drawColor(Color.parseColor("#a8a797"));
        p.setColor(Color.parseColor("#DBA901"));
        c.drawRect(1,1,405,1580,p);
        //top 400

        icono.pintar(c,p);
        productoUno.pintar(c,p);
        productoDos.pintar(c,p);
        productoTres.pintar(c,p);


        pDosDes.pintar(c,p);
        pUnoDes.pintar(c,p);
        pTresDes.pintar(c,p);
    }
    public boolean onTouchEvent(MotionEvent e)
    {
        float xp= e.getX();
        float yp= e.getY();

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(productoUno.estaEnArea(xp,yp))
                {
                    puntero=productoUno;
                    pUnoDes.hacerVisible(true);
                    pDosDes.hacerVisible(false);
                    pTresDes.hacerVisible(false);

                }
                if(productoDos.estaEnArea(xp,yp))
                {
                    puntero=productoDos;
                    pDosDes.hacerVisible(true);
                    pUnoDes.hacerVisible(false);
                    pTresDes.hacerVisible(false);
                }
                if(productoTres.estaEnArea(xp,yp))
                {
                    puntero=productoTres;
                    pTresDes.hacerVisible(true);
                    pUnoDes.hacerVisible(false);
                    pDosDes.hacerVisible(false);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if(puntero!=null)
                {
                    if(productoUno.colision(icono))
                    {
                        icono.hacerVisible(false);
                    }
                    if(puntero==productoUno)
                    {
                        productoUno.mover(yp);
                        productoDos.mover(yp+500);
                        productoTres.mover(yp+1000);
                    }

                    if(puntero==productoDos)
                    {

                        productoDos.mover(yp);
                        productoUno.mover(yp-500);
                        productoTres.mover(yp+500);
                    }

                    if (puntero==productoTres)
                    {
                        productoTres.mover(yp);
                        productoUno.mover(yp-1000);
                        productoDos.mover(yp-500);
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
