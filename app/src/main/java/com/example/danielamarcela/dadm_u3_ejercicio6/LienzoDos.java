package com.example.danielamarcela.dadm_u3_ejercicio6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class LienzoDos extends View {
    ImagenesH logo,productoUno,productoDos,productoTres,puntero;
    ImagenesH pUnoDes, pDosDes,pTresDes,des;
    public LienzoDos(Context context) {
        super(context);
       logo=new ImagenesH(R.drawable.halloween,32   ,40,this);
       productoUno=new ImagenesH(R.drawable.hallouno,32,440,this);
       productoDos=new ImagenesH(R.drawable.hallodos,32,940,this);
       productoTres=new ImagenesH(R.drawable.hallotres,32,1440,this);

       pDosDes=new ImagenesH(R.drawable.hproductodos,430,340,this);
       pUnoDes=new ImagenesH(R.drawable.hproductouno,430,340,this);
       pTresDes=new ImagenesH(R.drawable.hproductotres,430,340,this);
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
        p.setColor(Color.parseColor("#FE9A2E"));
        c.drawRect(1,1,405,1580,p);
        //top 400

        logo.pintar(c,p);
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
                    if(productoUno.colision(logo))
                    {
                        logo.hacerVisible(false);
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
