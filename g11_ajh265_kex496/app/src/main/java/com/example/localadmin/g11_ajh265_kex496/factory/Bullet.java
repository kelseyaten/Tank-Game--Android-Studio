package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Bullet extends CellObject {

    int id;
    int damage;

    public Bullet( int x ){

        id = (( x - (x % 1000)) / 1000) - 2000;
        damage = x % 1000;

    }

    public void display( Context c, GridLayout g, int size, long id ){


        ImageView m = new ImageView( c );
        m.setImageResource(R.drawable.grass);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        m.setLayoutParams(layoutParams);
        g.addView(m);


    }

}
