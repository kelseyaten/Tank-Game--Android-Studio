package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class IndestructableWall extends Wall {


    public IndestructableWall(){

    }

    public int updateDisplay( long paramid  ){

        myView.setImageResource(R.drawable.wall);
        return R.drawable.wall;
    }


}
