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

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;


    public Bullet( int x ){

        id = (( x - (x % 1000)) / 1000) - 2000;
        damage = x % 1000;

    }

    public ImageView display( Context c, GridLayout g, int size, long id ){


        myView = new ImageView( c );
        myView.setImageResource(R.drawable.grass);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        myView.setLayoutParams(layoutParams);
        g.addView(myView);
        return myView;
    }


    public int updateDisplay( long paramid  ){


        myView.setImageResource( R.drawable.grass);
       return R.drawable.grass;

    }

}
