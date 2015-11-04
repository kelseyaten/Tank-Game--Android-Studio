package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Bullet extends CellObject {

    long id;
    long damage;

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;


    public Bullet( long x ){

        id = (( x - (x % 1000)) / 1000) - 2000;
        damage = x % 1000;

    }

    public int display(long id ){

        return R.drawable.bullet;
    }


    public int updateDisplay( long paramid  ){

        myView.setImageResource( R.drawable.bullet);
       return R.drawable.bullet;

    }

}
