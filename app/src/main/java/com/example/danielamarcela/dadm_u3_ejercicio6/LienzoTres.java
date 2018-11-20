package com.example.danielamarcela.dadm_u3_ejercicio6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class LienzoTres extends View {
    ImagenesN icono,productoUno,productoDos,productoTres,puntero;
    ImagenesN pUnoDes, pDosDes,pTresDes;
    public LienzoTres(Context context) {
        super(context);

        icono=new ImagenesN(R.drawable.navidad,32   ,40,this);
        productoUno=new ImagenesN(R.drawable.navidaduno,32,440,this);
        productoDos=new ImagenesN(R.drawable.navidaddos,32,940,this);
        productoTres=new ImagenesN(R.drawable.navidadtres,32,1440,this);

        pDosDes=new ImagenesN(R.drawable.nproductodos,430,340,this);
        pUnoDes=new ImagenesN(R.drawable.nproductouno,430,340,this);
        pTresDes=new ImagenesN(R.drawable.nproductotres,430,340,this);
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
        p.setColor(Color.parseColor("#0B3B0B"));
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
