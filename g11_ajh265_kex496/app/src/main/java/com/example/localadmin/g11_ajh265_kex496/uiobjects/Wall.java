package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.CellObject;

/**
 * the wall class.
 * Created by localadmin on 10/31/15.
 */
public class Wall extends CellObject {



    public Wall(){


    }
    /**
     * returns id of image to display the object
     * @return the display id
     */


    public int display( long id ){

        return R.drawable.wall;

    }

    public int updateDisplay( long paramid  ){

        return R.drawable.wall;
    }



}
