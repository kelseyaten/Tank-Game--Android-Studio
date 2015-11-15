package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.constraint.BulletObserver;

/**
 *
 * the bullet class. has info  pertaining to bullet id and damage
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


    /**
     *
     * constructor
     * @param x the id taken from the gridwrapper
     */

    public Bullet( long x ){

        id = (( x - (x % 1000)) / 1000) - 2000;
        damage = x % 1000;

    }


    /**
     * returns id of image to display the bullet
     * @param id the tanks id
     * @return the display id
     */
    public int display(long id ){


        return R.drawable.bullet;
    }

    /**
     * returns id of image to display the bullet
     * @return the display id
     */

    public int updateDisplay( long paramid  ){

        myView.setImageResource( R.drawable.bullet);
       return R.drawable.bullet;

    }

}
