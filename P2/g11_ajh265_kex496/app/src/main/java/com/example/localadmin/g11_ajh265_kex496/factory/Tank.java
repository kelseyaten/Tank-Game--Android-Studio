package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Tank extends CellObject {

    long id;
    int life;
    int direction;

    public Tank(int x){

        direction = x % 10;
        life = ( (x % 10000) - direction ) / 10;
        id = ((x % 10000000) - ( x % 10000 ) ) / 10000;

    }


    public void display( Context c, GridLayout g, int size, long paramid ){


        ImageView m = new ImageView( c );


        if( paramid == id ) {
            m.setImageResource(R.drawable.mytank);
        }else{
            m.setImageResource(R.drawable.tank);

        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        m.setLayoutParams(layoutParams);
        g.addView(m);


    }
}
