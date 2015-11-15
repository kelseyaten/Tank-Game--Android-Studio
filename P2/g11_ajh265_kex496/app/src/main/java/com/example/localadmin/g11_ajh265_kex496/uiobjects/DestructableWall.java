package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * destructable wall cell objct
 * Created by localadmin on 10/31/15.
 */
public class DestructableWall extends Wall {

    int life;

    public DestructableWall( int n ){

        life = n;
    }

    /**
     * returns id of image to display the object
     * @return the display id
     */

    public int updateDisplay( long paramid  ){

        return R.drawable.destructablewall;
    }



}
