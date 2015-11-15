package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class DestructableWall extends Wall {

    int life;

    public DestructableWall( int n ){

        life = n;
    }

    public int updateDisplay( long paramid  ){

        myView.setImageResource(R.drawable.destructablewall);
        return R.drawable.destructablewall;
    }



}
